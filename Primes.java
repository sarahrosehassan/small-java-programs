import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Primes{
    public static boolean isPrime(int n){
        if (n < 2) {
            return false;
        }
        
        for (int i = 2;i <= Math.sqrt(n) ; i++){
            if (n % i == 0)
            return false;
        }
        return true;
    }
    
    static ArrayList<Integer> primes = new ArrayList<Integer>();
    static int sieveLimit = 1;

    static void SieveOfEratosthenes(int maxSize)  {
        boolean[] isPrime = new boolean[maxSize + 1];
        for (int i = 2; i <= maxSize; i++) {
            isPrime[i] = true;
        }

        for (int p = 2; p * p <= maxSize; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= maxSize; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        primes.clear();
        for (int p = 2; p <= maxSize; p++) {
            if (isPrime[p]) {
                primes.add(p);
            }
        }
        sieveLimit = maxSize;
    }

    private static int upperBoundForNthPrime(int n) {
        if (n < 6) {
            return 15;
        }
        double dn = n;
        return (int)Math.ceil(dn * (Math.log(dn) + Math.log(Math.log(dn)))) + 10;
    }
    
    public static int kthPrime(int k){
        if (k < 0) {
            throw new IllegalArgumentException("k must be non-negative");
        }

        if (primes.size() <= k) {
            int limit = Math.max(sieveLimit, upperBoundForNthPrime(k + 1));
            while (true) {
                SieveOfEratosthenes(limit);
                if (primes.size() > k) {
                    break;
                }
                limit *= 2;
            }
        }
        return primes.get(k);
    }
    
    public static List<Integer> factorize(int n) {
    List<Integer> factorsList = new ArrayList<>();
    
    while (n % 2 == 0){ 
       factorsList.add(2);
        n /= 2; 
    } 
    
    for (int i = 3; i <= Math.sqrt(n); i+= 2) { 
        while (n % i == 0) { 
            factorsList.add(i);
            n /= i; 
        }
    } 
    
    if (n > 2) {
     factorsList.add(n);  
    }
    return factorsList;
} 
}
