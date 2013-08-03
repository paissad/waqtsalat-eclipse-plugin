package net.paissad.waqtsalat.ui.audio.impl;

import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.audio.api.ISoundPlayer;

public class WaveSoundPlayer implements ISoundPlayer {

    private static final ILogger logger = WaqtSalatUIPlugin.getPlugin().getLogger();

    Clip                         clip;

    public WaveSoundPlayer() {
    }

    @Override
    public void setStream(InputStream soundStream) throws Exception {

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundStream);
        float sampleRate = 44100.0f;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;

        AudioFormat audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        int bufferSize = 4096;
        DataLine.Info info = new DataLine.Info(Clip.class, audioFormat, bufferSize);
        this.clip = (Clip) AudioSystem.getLine(info);
        if (!AudioSystem.isLineSupported(info)) {
            logger.warn("The audio line is not supported : " + info); //$NON-NLS-1$
        }
        clip.open(audioStream);
    }

    @Override
    public void play() throws Exception {
        try {
            clip.start();
        } finally {
            clip.drain();
            clip.close();
        }
    }

    @Override
    public void stop() throws Exception {
        clip.drain();
        clip.stop();
        clip.close();
    }

}
