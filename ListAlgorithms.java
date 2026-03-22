import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;

public class ListAlgorithms{
    
    public static List<Integer> runningMedianOfThree(List<Integer> items) {   
        List<Integer> medianList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (i < 2) {
                medianList.add(items.get(i));
            } 
            else {
                int n1 = items.get(i-2);
                int n2 = items.get(i-1);
                int n3 = items.get(i);
                
                int min = n1;
                if (n2 < min) {
                    min = n2;
                }
                
                if (n3 < min){ 
                    min = n3;
                }
                
                int max = n1;
                if (n2 > max){ 
                    max = n2;
                }
                
                if (n3 > max){
                    max = n3;
                }                
                medianList.add((n1 + n2 + n3) - (min + max));
            }
        }
        return medianList;
    }
    
    public static int firstMissingPositive(List<Integer> items){
       int missingInt = 1;        
       for(int n: items){
           
           if(items.indexOf(missingInt) == -1)
               return missingInt;
           missingInt += 1;
        }
       return missingInt;
   }
   
   public static void sortByElementFrequency(List items) {
       final Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
       for (Object ob : items) {
           if (counts.containsKey((Integer) ob)) { 
               counts.put((Integer) ob, counts.get((Integer) ob) + 1);
            } 
            else {
                counts.put((Integer) ob, 1);
            }
        }
        
        Collections.sort(items, new Comparator<Integer>() {
            @Override
            public int compare(Integer ob1, Integer ob2) {
                if (counts.get(ob2) - counts.get(ob1) != 0) {
                    return counts.get(ob2) - counts.get(ob1);
                }                       
                return ob1.compareTo(ob2);
            }
        
        });
   }
   
   public static void addFactors(int n, List<Integer> list){
       int x = n;
       while(x % 2 == 0){
           list.add(2);
           x /= 2;
        }

        for(int i = 3;i <= x;i += 2){
            while(x % i == 0){
                list.add(i);
                x /= i;
            }
        }
        
        if(x > 1){
        list.add(x);
       }
    }
    
   public static List<Integer> factorFactorial(int n){           
        List<Integer> primeFactors = new ArrayList<Integer>();
        
        for(int i = 2;i <= n; i++){            
            addFactors(i, primeFactors);
        }        
        
        Collections.sort(primeFactors);
        return primeFactors;
   }
}

