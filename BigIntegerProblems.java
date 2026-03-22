import java.math.BigInteger;
import java.math.*;
import java.util.*;

public class BigIntegerProblems{
   private static List<BigInteger> fibs = new ArrayList<BigInteger>();
   static {
       fibs.add(BigInteger.ONE);
       fibs.add(BigInteger.ONE);
   }
   
   public static List<BigInteger> fibonacciSum(BigInteger n) {       
       
       BigInteger fibNum = fibs.get(fibs.size() - 2).add(fibs.get(fibs.size() - 1));
       while(n.compareTo(fibNum) >= 0) {
           fibs.add(fibNum);          ;
           fibNum = fibs.get(fibs.size() - 2).add(fibs.get(fibs.size() - 1));
       }
       
       List<BigInteger> fibNumsDesc = new ArrayList<BigInteger>();
       BigInteger decrN = n;
       for(int i = fibs.size()-1; i >= 0; i--) {
           
           if(decrN == BigInteger.ZERO){
               return fibNumsDesc;
            }             
            
           if(fibs.get(i).compareTo(decrN) <= 0) {
               fibNumsDesc.add(fibs.get(i));
               decrN = decrN.subtract(fibs.get(i));
           }
       }      
       return fibNumsDesc;
   }
    
    public static BigInteger sevenZero(int n){
        String seven = "7";
        BigInteger objN = new BigInteger(n+"");
        
        for(int i = 0; i < 1000; i++){ 
            String zero = "";
            for(int j = 0; j < 100 ;j++){
                BigInteger sevZer = new BigInteger(seven + zero);                
                if((sevZer.mod(objN)).compareTo(new BigInteger("0")) == 0){
                return sevZer;
            }
             zero +="0";
            }
             seven += "7";
        }
        return new BigInteger("0");
    }
}

