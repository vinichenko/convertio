package ua.kpi.convertio;

import java.io.File;

import it.sauronsoftware.jave.*;

public class Converter {

    private static final String EXTENSION = ".mp3";
    private static final String CODEC = "libmp3lame";
    private static final int BIT_RATE = 128000;
    private static final int CHANNEL_NUMBER = 2;
    private static final int SAMPLING_RATE = 44100;
    private static final String FORMAT = "mp3";

    public static File convert(File inputFile) throws EncoderException {
        File outputFile = new File(inputFile.getName() + EXTENSION);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec(CODEC);
        audio.setBitRate(BIT_RATE);
        audio.setChannels(CHANNEL_NUMBER);
        audio.setSamplingRate(SAMPLING_RATE);

        EncodingAttributes attributes = new EncodingAttributes();
        attributes.setFormat(FORMAT);
        attributes.setAudioAttributes(audio);

        Encoder encoder = new Encoder();
        encoder.encode(inputFile, outputFile, attributes);

        return outputFile;
    }
}
