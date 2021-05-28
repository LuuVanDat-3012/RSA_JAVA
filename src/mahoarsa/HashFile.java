/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahoarsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Dat
 */
public class HashFile {

    public static String getMD5(String path) throws FileNotFoundException, IOException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(path);
            byte[] dataBytes = new byte[256];
            int nread = 0;
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] byteData = md.digest();
            fis.close();
            return convertByteToHex1(byteData);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String convertByteToHex1(byte[] data) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(0xff & data[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String readFile(String path) throws FileNotFoundException, IOException {
        FileInputStream fin = null;

        fin = new FileInputStream(path);
        String content = "";
        int i = 0;
        while ((i = fin.read()) != -1) {
            content += (char) i;
        }
        fin.close();
        return content;
    }

    public static String getMD5Text(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex1(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
