public class DissimilarityMetrics {
    
    private static int[][] countN(boolean[] v1, boolean[] v2){
        int[][] nCountArr = new int[2][2];
        nCountArr[0][0] = 0;
        nCountArr[0][1] = 0; 
        nCountArr[1][0] = 0;
        nCountArr[1][1] = 0;

        for (int i = 0; i < v1.length; i++){
            if(v1[i] == false && v2[i] == false){
                nCountArr[0][0] += 1;
            }
            else if(v1[i] == false && v2[i] == true){
                nCountArr[0][1] += 1;
            }
            else if(v1[i] == true && v2[i] == false){
                nCountArr[1][0] += 1;
            }
            else{
                nCountArr[1][1] += 1;
            }
        }
        return nCountArr;
    }

    public static Fraction matchingDissimilarity(boolean[] v1, boolean[] v2){
        int[][] n = countN(v1, v2);
        int num = n[1][0] + n[0][1];
        int den = v1.length;
        return new Fraction(num, den);
    }


    public static Fraction jaccardDissimilarity(boolean[] v1, boolean[] v2){
        int[][] n = countN(v1, v2);
        int num = n[1][0] + n[0][1];
        int den = n[1][1] +  n[1][0] + n[0][1];
        return new Fraction(num, den);
    }


    public static Fraction diceDissimilarity(boolean[] v1, boolean[] v2){
        int[][] n = countN(v1, v2);
        int num = n[1][0] + n[0][1];
        int den = 2 * n[1][1] +  n[1][0] + n[0][1];
        return new Fraction(num, den);
    }


    public static Fraction rogersTanimonoDissimilarity(boolean[] v1, boolean[] v2){
        int[][] n = countN(v1, v2);
        int num = 2 * (n[1][0] + n[0][1]);
        int den = n[1][1] +  2 * (n[1][0] + n[0][1]) + n[0][0];
        return new Fraction(num, den);
    }


    public static Fraction russellRaoDissimilarity(boolean[] v1, boolean[] v2){
        int[][] n = countN(v1, v2);
        int num = n[1][0] + n[0][1] + n[0][0];
        int den = v1.length;
        return new Fraction(num, den);
    }


    public static Fraction sokalSneathDissimilarity(boolean[] v1, boolean[] v2){
        int[][] n = countN(v1, v2);
        int num = 2 * (n[1][0] + n[0][1]);
        int den = n[1][1] +  2 * (n[1][0] + n[0][1]);
        return new Fraction(num, den);
    }

}