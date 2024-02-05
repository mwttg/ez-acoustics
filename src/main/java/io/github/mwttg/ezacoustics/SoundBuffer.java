package io.github.mwttg.ezacoustics;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class SoundBuffer {

    private static final Logger LOG = LoggerFactory.getLogger(SoundBuffer.class);

    private SoundBuffer() {
    }

    static int create(final SoundFileData soundFileData) {
        final var data = BufferUtils.createByteBuffer(soundFileData.data().length);
        data.put(soundFileData.data());
        data.flip();

        final var id = AL11.alGenBuffers();
        EzCleanUp.addSoundBufferId(id);
        AL11.alBufferData(id, soundFileData.openAlFormat(), data, soundFileData.sampleRate()); // TODO check for mono / stero 16/8 channels
        LOG.debug("... Create Sound Buffer");

        return id;
    }
}
