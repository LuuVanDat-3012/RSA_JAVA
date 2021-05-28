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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AlgorithmRSA {

    private BigInteger n, d, e;

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    /**
     * Create an instance that can encrypt using someone elses public key.
     */
    public AlgorithmRSA(BigInteger newn, BigInteger newe) {
        n = newn;
        e = newe;
    }

    /**
     * Create an instance that can both encrypt and decrypt.
     */
    public AlgorithmRSA() {
        
       
    }
    
    public void KeyRSA(int bits){
         SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bits / 2, 100, r);
        BigInteger q = new BigInteger(bits / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        boolean found = false;
        do {
            e = new BigInteger(bits / 2, 50, r);
            if (m.gcd(e).equals(BigInteger.ONE) && e.compareTo(m) < 0) {
                found = true;
            }
        } while (!found);
        d = e.modInverse(m);
        
    }

    /**
     * Mã hóa với tham số truyền vào là String
     */
    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(d, n).toString();
    }

    /**
     *Mã  hóa RSA chữ kí số với tham số truyền vào là kiểu số nguyên lớn
     */
    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(d, n);
    }

    /**
     * giải mã với tham số truyền vào là text
     */
    public synchronized String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(e, n).toByteArray());
    }

    /**
     * Giải mã với tham số truyền vào là số nguyên lớn
     */
    public synchronized BigInteger decrypt(BigInteger message) {
        return message.modPow(e, n);
    }

   
   

    void setN(int bitleg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
