import java.util.*;

public class StringAndSequence {
    public static String removeDuplicates(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (i == 0 || text.charAt(i) != result.charAt(result.length() - 1)) {
                result += text.charAt(i);
            }
        }
        return result;
    }
  
    public static String uniqueCharacters (String text) {
        LinkedHashSet<Character> hash = new LinkedHashSet<>(); 
        String uniqueChars = "";        
        for(int i = 0 ; i < text.length() ; i++) 
            hash.add(text.charAt(i)); 
 
        for(Character ch : hash) 
            uniqueChars += ch;
            
        return uniqueChars;
    } 
    
    public static int countSafeSquaresRooks(int n, boolean[][] rooks) {
        int[] rows = new int[n];
        int[] cols = new int[n];
        int safeRows = 0;
        int safeColumns = 0;
        
        for (int i = 0; i < n; i++) {
            rows[i] = 0;
            cols[i] = 0;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rooks[i][j] == true) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (rows[i] == 0) {
                safeRows++;
            }
            if (cols[i] == 0) {
                safeColumns++;
            }
        }
        return safeRows * safeColumns;
    }
    
    public static int recaman(int n){
        int[] arr = new int[n+1];
        boolean[] complete = new boolean[10 * n];
        arr[0] = 0;
        complete[0] = true;
        
        for (int i = 1; i <= n; i++){
            int neg = arr[i - 1] - i;
            int pos = arr[i - 1] + i;
            if ((neg > 0) && (!complete[neg])){
                arr[i] = neg;
                complete[neg] = true;
            }
            else{
                arr[i] = pos;
                complete[pos] = true;
            }
        }
        return arr[n];
    }
}
