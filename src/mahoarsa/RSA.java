/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahoarsa;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 *
 * @author Dat
 */
public class RSA
    {
        private long p, q;
        public long a, b;
        private long minN = 256L;
        public long n;

        public RSA(long p, long q)
        {
            this.p = p;
            this.q = q;
            this.n = p * q;
            findB();
            findA();
        }

        public RSA()
        {
        }

        public boolean independencePQ()
        {
            return p == q ? false : true;
        }

        public boolean primeNumber(long n)
        {
            if (n < 2) return false;
            long k = (long)Math.sqrt(n);
            for (long l = 2; l <= k; l++)
            {
                if (n % l == 0) return false;
            }
            return true;
        }

        public boolean checkMinN()
        {
            return n > minN ? true : false;
        }

        private long GCD(long a, long b)
        {
            long r;
            while (b != 0)
            {
                r = a % b;
                a = b;
                b = r;
            }
            return a;
        }

        private long totientEuler()
        {
            return (p - 1) * (q - 1);
        }

        private void findB()
        {
            long result = longRandom(2, totientEuler(), new Random());
            while (GCD(result, totientEuler()) != 1)
            {
                result = longRandom(2, totientEuler(), new Random());
            }
            b = result;
        }

        long longRandom(long min, long max, Random rand)
        {
            byte[] buf = new byte[8];
            rand.nextBytes(buf);
            ByteBuffer bb = ByteBuffer.wrap(buf);
            long longRand = bb.getLong();
            return (Math.abs(longRand % (max - min)) + min);
        }

        private long findInverseNumber(long a, long m)
        {
            for (long l = 1; l < m; l++)
            {
                if ((a * l) % m == 1)
                    return l;
            }
            return -1;
        }

        private void findA()
        {
            a = findInverseNumber(b, totientEuler());
        }

        public long calculatePow(long a, long b, long n)
        {
            long result = 1L;
            for (long l = 0; l < b; l++)
            {
                result = (result * a) % n;
            }
            return result;
        }
    }