package io.github.mwttg.ezacoustics;

import org.joml.Vector3f;
import org.lwjgl.openal.AL10;

final class SoundSource {

    static int create(final int bufferId, final boolean loop, final Vector3f position) {
        final var id = AL10.alGenSources();
        EzCleanUp.addSoundSourceId(id);
        if (loop) {
            AL10.alSourcei(id, AL10.AL_LOOPING, AL10.AL_TRUE);
        }
        AL10.alSourcei(id, AL10.AL_BUFFER, bufferId);
        AL10.alSource3f(id, AL10.AL_POSITION, position.x(), position.y(), position.z());

        return id;
    }
}
