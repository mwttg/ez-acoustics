package io.github.mwttg.ezacoustics;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SoundBuffer {

    private static final Logger LOG = LoggerFactory.getLogger(SoundBuffer.class);

    private SoundBuffer() {
    }

    public static int create(final String filename) {
        final var soundFile = WavFile.readFrom(filename); // TODO Move?
        final var data = BufferUtils.createByteBuffer(soundFile.data().length);
        data.put(soundFile.data());
        data.flip();
        final var sampleRate = (int) soundFile.format().getSampleRate();

        final var id = AL10.alGenBuffers();
        EzCleanUp.addSoundBufferId(id);
        AL10.alBufferData(id, AL10.AL_FORMAT_MONO16, data, sampleRate); // TODO check for mono / stero 16/8 channels
        LOG.debug("... Create Sound Buffer");

        return id;
    }
}
