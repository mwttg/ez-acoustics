package io.github.mwttg.ezacoustics;

import org.lwjgl.openal.AL10;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

final class WavFile {

    private static final Logger LOG = LoggerFactory.getLogger(WavFile.class);

    private WavFile() {
    }

    static SoundFileData readFrom(final String filename) {
        LOG.info("Read sound file: '{}'", filename);
        final var file = new File(filename);
        try (var inputStream = AudioSystem.getAudioInputStream(file)) {
            final var data = inputStream.readAllBytes();
            final var format = inputStream.getFormat();
            final var openAlFormat = getOpenAlFormat(format);
            final var sampleRate = (int) format.getSampleRate();
            return new SoundFileData(data, openAlFormat, sampleRate);
        } catch (final UnsupportedAudioFileException e) {
            throw new RuntimeException("The sound file '%s' was not a .wav audio file".formatted(filename));
        } catch (final IOException e) {
            throw new RuntimeException("The file '%s' could not opened".formatted(filename));
        }
    }

    private static int getOpenAlFormat(AudioFormat format) {
        final var channels = format.getChannels();
        final var sampleSize = format.getSampleSizeInBits();
        final var tuple = new Pair(channels, sampleSize);
        return switch (tuple) {
            case Pair(var c, var s) when (c == 1) && (s == 8) -> AL10.AL_FORMAT_MONO8;
            case Pair(var c, var s) when (c == 1) && (s == 16) -> AL10.AL_FORMAT_MONO16;
            case Pair(var c, var s) when (c == 2) && (s == 8) -> AL10.AL_FORMAT_STEREO8;
            case Pair(var c, var s) when (c == 2) && (s == 16) -> AL10.AL_FORMAT_STEREO16;
            default ->
                    throw new IllegalArgumentException("Unsupported format channels = '%s', sampleSize = '%s' bit".formatted(channels, sampleSize));
        };
    }

    record Pair(int channels, int sampleSize) {
    }
}
