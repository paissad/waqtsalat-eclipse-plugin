package net.paissad.waqtsalat.ui.audio.impl;

import java.io.InputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;
import net.paissad.waqtsalat.ui.audio.api.ISoundPlayer;

public class MP3SoundPlayer implements ISoundPlayer {

    private AdvancedPlayer mp3player = null;

    public MP3SoundPlayer() {
    }

    @Override
    public void setStream(InputStream soundStream) throws Exception {
        this.mp3player = new AdvancedPlayer(soundStream);
    }

    @Override
    public void play() throws Exception {
        mp3player.play();
    }

    @Override
    public void stop() throws Exception {
        mp3player.close();
    }

}
