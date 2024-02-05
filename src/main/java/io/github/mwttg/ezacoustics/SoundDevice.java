package io.github.mwttg.ezacoustics;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.system.MemoryUtil.NULL;

public final class SoundDevice {

    private static final Logger LOG = LoggerFactory.getLogger(SoundDevice.class);

    private SoundDevice() {
    }

    public static void initialize() {
        LOG.info("Initialize OpenAL sound device");
        final var deviceId = ALC11.alcOpenDevice((ByteBuffer) null);
        EzCleanUp.addDeviceId(deviceId);
        if (deviceId == NULL) {
            throw new IllegalStateException("Failed to open the default OpenAL device.");
        }

        final var capabilities = ALC.createCapabilities(deviceId);
        final var context = ALC11.alcCreateContext(deviceId, (IntBuffer) null);
        if (context == NULL) {
            throw new IllegalStateException("Failed to create OpenAL context.");
        }

        ALC11.alcMakeContextCurrent(context);
        AL.createCapabilities(capabilities);
    }
}
