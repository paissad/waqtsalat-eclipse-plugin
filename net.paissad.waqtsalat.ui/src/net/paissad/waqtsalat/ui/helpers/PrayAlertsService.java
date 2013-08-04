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

    private static final ILogger                           logger = WaqtSalatUIPlugin.getPlugin().getLogger();

    private final Collection<ScheduledFutureWrapper<Void>> tasks  = new LinkedList<ScheduledFutureWrapper<Void>>();

    private final ReentrantLock                            lock   = new ReentrantLock(true);

    private ScheduledExecutorService                       scheduler;

    private static PrayAlertsService                       instance;

    private PrayAlertsService() {
        initScheduler();
    }

    private void initScheduler() {
        scheduler = Executors.newScheduledThreadPool(5);
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
                        if (scheduler.isShutdown()) {
                            initScheduler();
                        }
                        ScheduledFuture<Void> scheduledFuture = scheduler.schedule(new PrayAdhanCallable(pray), delay,
                                TimeUnit.MILLISECONDS);
                        this.tasks.add(new ScheduledFutureWrapper<Void>(
                                "Adhan Player - " + prayName.getLiteral(), scheduledFuture)); //$NON-NLS-1$
                    }
                }
            }

        } finally {
            lock.unlock();
        }
    }

    private void cancelAlreadyScheduledTasks() {
        for (ScheduledFutureWrapper<Void> task : tasks) {
            if (!task.getScheduledFuture().cancel(true)) {
                logger.warn("Unable to cancel task : " + task.getName());
            }
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
                    pray.setPlayingAdhan(true);
                    pray.setAdhanPlayer(player);
                    player.play();
                    pray.setPlayingAdhan(false);

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
            cancelAlreadyScheduledTasks();
            if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }

    private static class ScheduledFutureWrapper<T> {

        private final ScheduledFuture<T> scheduledFuture;

        private final String             name;

        public ScheduledFutureWrapper(String taskName, ScheduledFuture<T> scheduledFuture) {
            this.name = taskName;
            this.scheduledFuture = scheduledFuture;
        }

        public String getName() {
            return this.name;
        }

        public ScheduledFuture<T> getScheduledFuture() {
            return this.scheduledFuture;
        }
    }

}
