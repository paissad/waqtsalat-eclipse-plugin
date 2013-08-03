package net.paissad.waqtsalat.ui.beans;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import net.paissad.waqtsalat.ui.audio.api.ISoundPlayer;

public class SoundPlayerExtension {

    private final String             id;
    private final String             name;
    private final ISoundPlayer       soundPlayer;
    private final Collection<String> supportedTypes;

    public SoundPlayerExtension(String id, String name, ISoundPlayer soundPlayer, String[] supportedTypes) {
        this.id = id;
        this.name = name;
        this.soundPlayer = soundPlayer;
        this.supportedTypes = Collections.unmodifiableCollection(Arrays.asList(supportedTypes));
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ISoundPlayer getSoundPlayer() {
        return this.soundPlayer;
    }

    public Collection<String> getSupportedTypes() {
        return this.supportedTypes;
    }

}
