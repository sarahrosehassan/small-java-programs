public class GameMath{
    public static boolean[] sumOfTwoDistinctSquares(int n) {
        boolean isSumOfSquares[] = new boolean[n];
        int i = 1;
        int j = i+1;
        
        while((i*i + j*j) < n) {
            isSumOfSquares[(i*i + j*j)] = true;
            j++;
            if((i*i + j*j) >= n) {
                i++;
                j = i+1;
            }
        }
        return isSumOfSquares;
}

public static boolean[] subtractSquare(int n) {
    boolean truthValues[] = new boolean[n];
    for(int i = 1; i < n; i++) {
        int move = 1;
        while((i - move*move) >= 0) {            
            if(!truthValues[(i - move*move)]) {
                truthValues[i] = true;
                break;
            }
            move++;
        }
    }
    return truthValues;
}

}
