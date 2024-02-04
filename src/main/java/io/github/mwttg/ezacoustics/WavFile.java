package io.github.mwttg.ezacoustics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

final class WavFile {

    private static final Logger LOG = LoggerFactory.getLogger(WavFile.class);

    private WavFile() {
    }

    static SoundFile readFrom(final String filename) {
        LOG.info("Read sound file: '{}'", filename);
        final var file = new File(filename);
        try (var inputStream = AudioSystem.getAudioInputStream(file)) {
            final var data = inputStream.readAllBytes();
            final var format = inputStream.getFormat();
            return new SoundFile(data, format);
        } catch (final UnsupportedAudioFileException e) {
            throw new RuntimeException("The sound file '%s' was not a .wav audio file".formatted(filename));
        } catch (final IOException e) {
            throw new RuntimeException("The file '%s' could not opened".formatted(filename));
        }
    }
}
