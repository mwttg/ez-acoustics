package io.github.mwttg.ezacoustics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.openal.AL11;

public class SoundListener {

    private SoundListener() {
    }

    public static void create(final Vector3f position) {
        AL11.alListener3f(AL11.AL_POSITION, position.x(), position.y(), position.z());
        AL11.alListener3f(AL11.AL_VELOCITY, 0, 0, 0);
    }

    public static void create(final Matrix4f viewMatrix) {
        AL11.alListener3f(AL11.AL_VELOCITY, 0, 0, 0);
        updatePosition(viewMatrix);
    }

    public static void updatePosition(final Vector3f position) {
        AL11.alListener3f(AL11.AL_POSITION, position.x(), position.y(), position.z());
    }

    public static void updatePosition(final Matrix4f viewMatrix) {
        // http://forum.lwjgl.org/index.php?topic=6080.0
        final var at = new Vector3f();
        viewMatrix.positiveZ(at).negate();
        final var up = new Vector3f();
        viewMatrix.positiveY(up);
        final var data = new float[]{at.x(), at.y(), at.z(), up.x(), up.y(), up.z()};
        AL11.alListenerfv(AL11.AL_ORIENTATION, data);
    }

    public static void updateVelocity(final Vector3f velocity) {
        AL11.alListener3f(AL11.AL_VELOCITY, velocity.x(), velocity.y(), velocity.z());
    }
}
