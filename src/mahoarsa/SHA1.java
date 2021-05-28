/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahoarsa;

/**
 *
 * @author Dat
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class SHA1 {
  private static int BUFFER_SIZE = 32 * 1024;

  public BigInteger md(String f) throws Exception {
    BufferedInputStream file = new BufferedInputStream(new FileInputStream(f));
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    DigestInputStream in = new DigestInputStream(file, md);
    int i;
    byte[] buffer = new byte[BUFFER_SIZE];
    do {
      i = in.read(buffer, 0, BUFFER_SIZE);
    } while (i == BUFFER_SIZE);
    md = in.getMessageDigest();
    in.close();

    return new BigInteger(md.digest());
  }
}