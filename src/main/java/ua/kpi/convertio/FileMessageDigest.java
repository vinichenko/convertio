package ua.kpi.convertio;

import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMessageDigest {
    public static String getDigest(InputStream is) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();

        int byteArraySize = 2048;

        byte[] bytes = new byte[byteArraySize];
        int numBytes;

        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }

        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest));

        return result;
    }
}
