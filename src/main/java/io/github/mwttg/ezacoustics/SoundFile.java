package io.github.mwttg.ezacoustics;

import javax.sound.sampled.AudioFormat;

record SoundFile(byte[] data, AudioFormat format) {
}
