package net.paissad.waqtsalat.ui.helpers;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.audio.api.ISoundPlayer;
import net.paissad.waqtsalat.ui.beans.SoundPlayerExtension;

public class SoundPlayer {

    private SoundPlayer() {
    }

    /**
     * @param audioFile
     * @return The correct sound player to use for this specified file, or <code>null</code> if no sound player is
     *         available for that file.
     */
    public static ISoundPlayer getPlayer(final File audioFile) {
        if (audioFile != null && audioFile.isFile()) {
            String audioFileExtension = getExtension(audioFile).toLowerCase(Locale.ENGLISH);
            Map<String, SoundPlayerExtension> manager = WaqtSalatUIPlugin.getSoundPlayersManager();
            for (String id : manager.keySet()) {
                SoundPlayerExtension soundPlayerExt = manager.get(id);
                if (soundPlayerExt.getSupportedTypes().contains(audioFileExtension)) {
                    return soundPlayerExt.getSoundPlayer();
                }
            }
        }
        return null;
    }

    private static String getExtension(final File file) {
        String filename = file.getName();
        if (filename.contains(".")) { //$NON-NLS-1$
            return filename.replaceAll(".*\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return "";
    }

}
