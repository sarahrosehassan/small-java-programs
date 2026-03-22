import java.util.*;
public class Polynomial implements Comparable <Polynomial> {
   private int[] coefficients;
   private int degree ;
   public Polynomial(int[] coefficients){
       for(degree = coefficients.length-1; degree > 0 && coefficients[degree] == 0 ; degree--);
       
       this.coefficients = new int[degree+1];
       for(int i = 0 ; i <= degree ; i++){
           this.coefficients[i] = coefficients[i];
        }
    }
    
   public int getDegree(){
       return this.coefficients.length-1;
    }
    
    public int getCoefficient(int k){
        if(k >= 0 && k < this.coefficients.length){
            return this.coefficients[k];
        }
        return 0;
   }
   
   public long evaluate(int x){
       long sum = 0;
       for (int i = 0; i < this.coefficients.length; i++) {
           sum += this.coefficients[i] * Math.pow(x,i);
       }
       return sum;
   }
   /*
   @Override public String toString(){
       int[] p = {42,-7,0,5};
       Polynomial polynomial = new Polynomial(p);
       System.out.println("Degree: " + polynomial.getDegree()); 
       System.out.println("Coefficient at order 0 : " + polynomial.getCoefficient(0));
       System.out.println("Sum when x = 2: " + polynomial.evaluate(2));      
       return "";
    }
    */   
    static int max(int m, int n) { 
        return (m > n) ? m : n;
    } 
    
    public Polynomial add(Polynomial other){
        int size = max(this.coefficients.length, other.coefficients.length); 
        int sum[] = new int[size]; 
        
        for (int i = 0; i < this.coefficients.length; i++) { 
            sum[i] = this.coefficients[i]; 
        } 
        
        for (int i = 0; i < other.coefficients.length; i++) { 
            sum[i] += other.coefficients[i]; 
        } 
  
        return new Polynomial(sum);
    } 
    
    public Polynomial multiply(Polynomial other){ 
        int[] prod = new int[this.coefficients.length + other.coefficients.length - 1]; 
        
        for (int i = 0; i < this.coefficients.length + other.coefficients.length - 1; i++){ 
            prod[i] = 0; 
        } 
        
        for (int i = 0; i < this.coefficients.length; i++)  { 
            for (int j = 0; j < other.coefficients.length; j++)  { 
                prod[i + j] += this.coefficients[i] * other.coefficients[j]; 
            } 
        } 
  
        return new Polynomial(prod);
    } 
    
    @Override public boolean equals(Object other){
        if (!(other instanceof Polynomial)) {
            return false;
        }
        
        if (this.compareTo((Polynomial) other) == 0 ){
            return true;
        }
        return false;
    }
    
    @Override public int hashCode(){
        return Arrays.hashCode(this.coefficients);
    }

    public int compareTo(Polynomial other){
        if (this.getDegree() > other.getDegree()){
            return 1;
        }
        else if (this.getDegree() < other.getDegree()){
            return -1;
        }
        else if (this.getDegree() == other.getDegree()){
            for (int i = this.getDegree(); i >= 0; i--){
                if (coefficients[i] > other.getCoefficient(i)){
                    return 1;
                }
                if (coefficients[i] < other.getCoefficient(i)){
                    return -1;
                }
            }
        }
        return 0;
    }
}
