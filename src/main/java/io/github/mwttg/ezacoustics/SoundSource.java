package io.github.mwttg.ezacoustics;

import org.joml.Vector3f;
import org.lwjgl.openal.AL11;

final class SoundSource {

    static int create(final int bufferId, final boolean loop, final Vector3f position) {
        final var id = AL11.alGenSources();
        EzCleanUp.addSoundSourceId(id);
        if (loop) {
            AL11.alSourcei(id, AL11.AL_LOOPING, AL11.AL_TRUE);
        }
        AL11.alSourcei(id, AL11.AL_BUFFER, bufferId);
        AL11.alSource3f(id, AL11.AL_POSITION, position.x(), position.y(), position.z());

        return id;
    }
}
