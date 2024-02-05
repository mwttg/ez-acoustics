package io.github.mwttg.ezacoustics;

import org.joml.Vector3f;
import org.lwjgl.openal.AL10;

public class Sound {

    private final int soundSourceId;

    private Sound(final int soundSourceId) {
        this.soundSourceId = soundSourceId;
    }

    public static Sound create(final String filename) {
        return create(filename, false);
    }

    public static Sound create(final String filename, final boolean loop) {
        final var soundFileData = WavFile.readFrom(filename);
        final var soundBufferId = SoundBuffer.create(soundFileData);
        final var soundSourceId = SoundSource.create(soundBufferId, loop, new Vector3f());

        return new Sound(soundSourceId);
    }

    public void play() {
        AL10.alSourcePlay(soundSourceId);
    }
}
