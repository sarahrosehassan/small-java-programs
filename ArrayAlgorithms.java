import java.util.Arrays;
import java.util.ArrayList;
import java.io.*; 
import java.lang.*; 
import java.util.*;

public class ArrayAlgorithms{
    public static long fallingPower(int n, int k) {
        long product = 1;
        for (; k > 0; k--, n --){
            product *= n;
        }
        return product;
    }
    
    public static int[] everyOther(int[] arr) {
        int everyOtherArr[] = new int[0];
        
        if (arr.length <= 0) {
            return everyOtherArr;
        }
        
        for (int i = 0; i < arr.length; i+= 2) {
            everyOtherArr = Arrays.copyOf(everyOtherArr, everyOtherArr.length + 1);
            everyOtherArr [everyOtherArr.length - 1] = arr[i];
        }
        return everyOtherArr; 
    }
    
    
    public static int[][] createZigZag(int rows, int cols, int start){
        int[][] zigArr = new int[rows][cols];
        int i,j;
        
        for(i = 0;i < rows;i++){
            if(i % 2 == 0){             
                for(j = 0;j < cols;j++){ 
                   zigArr[i][j] = start;
                   start++;
               }
           }
 
           if(i % 2 != 0){            
               for(j = cols - 1 ; j >= 0;j--){ 
                   zigArr[i][j] = start;
                   start++;
               }
           }
        }
       return zigArr;
    }
    
    public static int countInversions(int[] arr) {
        int countInv = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    countInv++;
                }
            }
        }        
        return countInv;
    }
}
   