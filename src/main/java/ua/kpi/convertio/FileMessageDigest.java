package ua.kpi.convertio;

import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMessageDigest {

    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // Should not happen
        }
    }

    public static String getDigest(InputStream is) throws IOException {
        md.reset();
        int byteArraySize = 2048;
        byte[] bytes = new byte[byteArraySize];
        int numBytes;

        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }

        byte[] digest = md.digest();

        return new String(Hex.encodeHex(digest));
    }
}
