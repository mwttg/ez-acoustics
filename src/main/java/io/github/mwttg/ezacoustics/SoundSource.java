package io.github.mwttg.ezacoustics;

import org.joml.Vector3f;
import org.lwjgl.openal.AL10;

public final class SoundSource {

    private final int id;

    private SoundSource(final int id) {
        this.id = id;

    }

    public static int create(final int bufferId, final boolean loop, final Vector3f position) {
        // todo 1. read wav file 2. create buffer 3 create source ??
        final var id = AL10.alGenSources();
        EzCleanUp.addSoundSourceId(id);
        if (loop) {
            AL10.alSourcei(id, AL10.AL_LOOPING, AL10.AL_TRUE);
        }
        AL10.alSourcei(id, AL10.AL_BUFFER, bufferId);
        AL10.alSource3f(id, AL10.AL_POSITION, position.x(), position.y(), position.z());

        return id;
    }

    public static void play(final int id) {
        AL10.alSourcePlay(id);
    }
}
