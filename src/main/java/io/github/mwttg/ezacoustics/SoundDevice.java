package io.github.mwttg.ezacoustics;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.system.MemoryUtil.NULL;

public final class SoundDevice {

    private SoundDevice() {
    }

    public static long create() {
        final var deviceId = ALC10.alcOpenDevice((ByteBuffer) null);
        if (deviceId == NULL) {
            throw new IllegalStateException("Failed to open the default OpenAL device.");
        }

        final var capabilities = ALC.createCapabilities(deviceId);
        final var context = ALC10.alcCreateContext(deviceId, (IntBuffer) null);
        if (context == NULL) {
            throw new IllegalStateException("Failed to create OpenAL context.");
        }

        ALC10.alcMakeContextCurrent(context);
        AL.createCapabilities(capabilities);

        return deviceId;
    }
}
