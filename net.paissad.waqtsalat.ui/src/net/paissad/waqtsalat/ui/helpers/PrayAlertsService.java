package net.paissad.waqtsalat.ui.helpers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.core.api.Pray;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.audio.api.ISoundPlayer;

public class PrayAlertsService {

    private static final ILogger                    logger    = WaqtSalatUIPlugin.getPlugin().getLogger();

    private final Collection<ScheduledFuture<Void>> tasks     = new LinkedList<ScheduledFuture<Void>>();

    private final ReentrantLock                     lock      = new ReentrantLock(true);

    private final ScheduledExecutorService          scheduler = Executors.newScheduledThreadPool(5);

    private static PrayAlertsService                instance;

    private PrayAlertsService() {
    }

    public static PrayAlertsService getInstance() {
        if (instance == null) {
            instance = new PrayAlertsService();
        }
        return instance;
    }

    public void setInput(final Collection<Pray> input) {
        lock.lock();
        try {
            cancelAlreadyScheduledTasks();
            this.tasks.clear();
            final Calendar currentDate = Calendar.getInstance(PreferenceHelper.getTimezoneFromPreference());
            for (final Pray pray : input) {
                PrayName prayName = pray.getName();
                if (!(PrayName.SUNRISE.equals(prayName) || PrayName.SUNSET.equals(prayName))) {
                    int delay = (int) (pray.getTime().getTimeInMillis() - currentDate.getTimeInMillis());
                    if (delay > 1000) {
                        ScheduledFuture<Void> task = scheduler.schedule(new PrayAdhanCallable(pray), delay,
                                TimeUnit.MILLISECONDS);
                        this.tasks.add(task);
                    }
                }
            }

        } finally {
            lock.unlock();
        }
    }

    private void cancelAlreadyScheduledTasks() {
        for (ScheduledFuture<Void> task : tasks) {
            task.cancel(true);
        }
    }

    private class PrayAdhanCallable implements Callable<Void> {

        private final Pray pray;

        public PrayAdhanCallable(final Pray pray) {
            this.pray = pray;
        }

        @Override
        public Void call() throws Exception {

            File adhanFile = PreferenceHelper.getAdhanFile(this.pray.getName());

            if (adhanFile != null) {
                ISoundPlayer player = SoundPlayer.getPlayer(adhanFile);
                InputStream adhanStream = null;

                try {
                    adhanStream = new BufferedInputStream(new FileInputStream(adhanFile), 8192);
                    player.setStream(adhanStream);
                    player.play();

                } catch (Exception e) {
                    String errMsg = "Error while playing adhan for '" + this.pray.getName() + "' : " + e.getMessage(); //$NON-NLS-1$ //$NON-NLS-2$
                    logger.error(errMsg, e);
                    throw new Exception(errMsg, e);

                } finally {
                    try {
                        adhanStream.close();
                    } catch (Exception e) {
                    }
                }
            }
            return null;
        }
    }

    public void dispose() {
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }

}
