import java.io.File;
import it.sauronsoftware.jave.*;
/**
 * Created with IntelliJ IDEA.
 * User: Dima
 * Date: 09.12.13
 * Time: 17:39
 * To change this template use File | Settings | File Templates.
 */
public class Converter {

    static File Convert(File inputFile, String way) throws EncoderException {

        String fileName = inputFile.getName();
        int index = fileName.lastIndexOf('.');
        fileName = new StringBuffer(fileName).replace(index + 1, fileName.length(), "mp3").toString();

        File outputFile = new File(way + "\\" + fileName);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));

        EncodingAttributes attributes = new EncodingAttributes();
        attributes.setFormat("mp3");
        attributes.setAudioAttributes(audio);

        Encoder encoder = new Encoder();
        encoder.encode(inputFile, outputFile, attributes);

        return  outputFile;
    }
}
