package io.github.mwttg.ezacoustics;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class EzCleanUp {

    private static final Logger LOG = LoggerFactory.getLogger(EzCleanUp.class);

    private static long deviceId;
    private static final List<Integer> soundBufferIds = new ArrayList<>();
    private static final List<Integer> soundSourceIds = new ArrayList<>();

    private EzCleanUp() {
    }

    static void addDeviceId(final long id) {
        deviceId = id;
    }

    static void addSoundBufferId(final int id) {
        soundBufferIds.add(id);
    }

    static void addSoundSourceId(final int id) {
        soundSourceIds.add(id);
    }

    public static void purge() {
        LOG.info("clean up OpenAL");
        LOG.debug("... free sound sources");
        soundBufferIds.forEach(AL10::alDeleteSources);
        LOG.debug("... free sound buffers");
        soundSourceIds.forEach(AL10::alDeleteBuffers);
        LOG.debug("... close sound device");
        ALC10.alcCloseDevice(deviceId);
    }
}
