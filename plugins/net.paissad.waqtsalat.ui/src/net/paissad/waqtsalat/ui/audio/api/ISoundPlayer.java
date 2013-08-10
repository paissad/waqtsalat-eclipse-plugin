package net.paissad.waqtsalat.ui.audio.api;

import java.io.InputStream;

public interface ISoundPlayer {

    /**
     * @param soundStream - The audio stream to play or stop.
     */
    void setStream(InputStream soundStream) throws Exception;

    /**
     * Plays the audio stream which is set by {@link #setStream(InputStream)}
     * 
     * @throws Exception
     */
    void play() throws Exception;

    /**
     * Stops playing the audio stream.
     * 
     * @throws Exception
     */
    void stop() throws Exception;

}
