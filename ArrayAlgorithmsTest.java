 

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import java.io.*;
import java.util.*;
import java.util.zip.CRC32;

public class ArrayAlgorithmsTest {

    private static final int SEED = 12345;
    private static final int TRIALS = 10000;
    
    @Test public void testFallingPower() {
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for(int b = -10; b < 10; b++) {
            for(int e = 0; e < 10; e++) {
                long p = ArrayAlgorithms.fallingPower(b, e);
                check.update((int)(p & 0xFFFF));
                check.update((int)((p >> 31) & 0xFFFF));
            }
        }
        assertEquals(4140005098L, check.getValue());
    }
    
    @Test public void testEveryOther() {
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for(int i = 0; i < TRIALS; i++) {
            int len = rng.nextInt(1000);
            int[] a = new int[len];
            for(int j = 0; j < len; j++) {
                a[j] = rng.nextInt(100000);
            }
            int[] b = ArrayAlgorithms.everyOther(a);
            check.update(b.length);
            for(int e: b) { check.update(e); }            
        }
        assertEquals(3861208241L, check.getValue());
    }
    
    @Test public void testCreateZigZag() {
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for(int i = 0; i < TRIALS; i++) {
            int rows = rng.nextInt(20) + 1;
            int cols = rng.nextInt(20) + 1;
            int start = rng.nextInt(100);
            int[][] zig = ArrayAlgorithms.createZigZag(rows, cols, start);
            assertEquals(rows, zig.length);
            for(int j = 0; j < rows; j++) {
                assertEquals(cols, zig[j].length);
                for(int e: zig[j]) { check.update(e); }
            }
        }
        assertEquals(3465650385L, check.getValue());
    }
    
    @Test public void testCountInversions() {
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for(int i = 0; i < 1000; i++) {
            int[] a = new int[i];
            for(int j = 0; j < i; j++) {
                a[j] = rng.nextInt(100000);
            }
            check.update(ArrayAlgorithms.countInversions(a));
        }
        assertEquals(1579619806L, check.getValue());
    }
}