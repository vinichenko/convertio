package com.kpi.epam.convertio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * Created with IntelliJ IDEA.
 * User: Dima
 * Date: 09.12.13
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public class FileMessageDigest {
    public static String getDigest(String filePath) throws NoSuchAlgorithmException, IOException {
        InputStream is = new FileInputStream(filePath);

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
