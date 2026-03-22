import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class Backtracking{
    
   public static List<Integer> sumOfDistinctCubes(int n ){
       List<Integer> cubesList = new ArrayList<Integer>();
       sumOfDistinctCubes(n , (int) Math.cbrt(n), cubesList);
       return cubesList;
   }
   
   private static boolean sumOfDistinctCubes(int n , int c, List<Integer>soFar) {
       if(n == 0){
           return true;
        }
        
       if(c == 0){
           return false;
        }
      
       for(int i = c;i > 0;i--) {
           if(n >= (i*i*i)) {
               soFar.add(i);
               if(sumOfDistinctCubes(n-(i*i*i) , i-1 , soFar)){
                   return true;
                }
               soFar.remove(soFar.size()-1);
           }
       }
       return false;
   }

               
   public static List<String> forbiddenSubstrings(String alphabet, int n, List<String> tabu) {
       ArrayList<String> result = new ArrayList<String>();
       forbiddenSubstrings(alphabet, n, tabu, "", result);
       return result;
    }
    
    private static void forbiddenSubstrings(String alphabet, int n, List<String> tabu, String soFar, List<String> result) {
        for (int i = 0; i < tabu.size(); i++) {
            if (soFar.endsWith(tabu.get(i))) {
                return;
            }            
        }
        if (soFar.length() == n) {
            result.add(soFar);
        } 
        
        else {
            for (int i = 0; i < alphabet.length(); i++) {
                  char ch = alphabet.charAt(i);
                  forbiddenSubstrings(alphabet, n, tabu, soFar + ch, result);
                }
            }
        }
}