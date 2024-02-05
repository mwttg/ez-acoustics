package io.github.mwttg.ezacoustics;

import org.joml.Vector3f;
import org.lwjgl.openal.AL10;

public class SoundListener {

    private SoundListener() {
    }

    public static void create(final Vector3f position) {
        AL10.alListener3f(AL10.AL_POSITION, position.x(), position.y(), position.z());
        AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
    }

    public static void updatePosition(final Vector3f position) {

    }

    // TODO more setter functions
}
