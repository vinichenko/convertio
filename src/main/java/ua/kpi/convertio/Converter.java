package ua.kpi.convertio;

import java.io.File;

import it.sauronsoftware.jave.*;

public class Converter {

    static File convert(File inputFile) throws EncoderException {
        File outputFile = new File(inputFile.getName() + ".mp3");

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);

        EncodingAttributes attributes = new EncodingAttributes();
        attributes.setFormat("mp3");
        attributes.setAudioAttributes(audio);

        Encoder encoder = new Encoder();
        encoder.encode(inputFile, outputFile, attributes);

        return outputFile;
    }
}
