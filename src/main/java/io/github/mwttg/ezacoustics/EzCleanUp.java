package io.github.mwttg.ezacoustics;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class EzCleanUp {

    private static final Logger LOG = LoggerFactory.getLogger(EzCleanUp.class);

    private static final List<Integer> soundBufferIds = new ArrayList<>();
    private static final List<Integer> soundSourceIds = new ArrayList<>();

    private EzCleanUp() {
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
        soundBufferIds.forEach(AL10::alDeleteBuffers);
    }
}
