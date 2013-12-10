package ua.kpi.convertio;

import java.io.File;

import it.sauronsoftware.jave.*;

public class Converter {

    static File convert(File inputFile, String way) throws EncoderException {

        String fileName = inputFile.getName();
        int index = fileName.lastIndexOf('.');
        fileName = new StringBuffer(fileName).replace(index + 1, fileName.length(), "mp3").toString();

        File outputFile = new File(way + File.separator + fileName);

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
