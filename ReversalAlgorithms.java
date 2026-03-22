
public class ReversalAlgorithms{
    public static void reverseSubarray (int start, int end, int[] items){
        for (; start < end ; start++, end--){
            int temp = items [start];
            items[start] = items [end];
            items[end] = temp;
        } 
    }
    
    public static void reverseAscendingSubarrays (int[] items){
        int start,end = 0;
        for (start = 1; start < items.length; start++){
            if (items[start] > items[start -1] ){
                end++;
            }
            else{
                reverseSubarray(start-end-1, start-1, items);
                end = 0;
            }
        }
        if (end > 0){
            reverseSubarray(start-end-1, start-1, items);
        }
    }
    
    public static String pancakeScramble(String text){
        String str1 = "";
        String str2 = "";
        int charPos = 2;
        for(int i = 0; i < text.length() - 1; i++){
            str1 = new StringBuilder(text.substring(0,charPos)).reverse().toString();
            str2 = text.substring(charPos, text.length());
            text = str1 + str2;
            charPos ++;
        }
        return text; 
    }
    
    static boolean isVowel(char c) {
        return (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' 
        || c == 'I' || c == 'o' || c == 'O' || c == 'u'|| c == 'U');
    }
    
    public static String reverseVowels(String text) {
        int indexVowels = 0;
        char[] textArr = text.toCharArray();
        String vowels = "";
        for (int i = 0; i < textArr.length; i++) {
            if (isVowel(textArr[i])) {
                indexVowels ++;
                vowels += textArr[i];
            }
        }
        
        for (int i = 0; i < textArr.length; i++) {
            if (isVowel(textArr[i])) {
                if(Character.isUpperCase(textArr[i])){ 
                    textArr[i] = Character.toUpperCase(vowels.charAt(--indexVowels));
                }
                if(Character.isLowerCase(textArr[i])){
                    textArr[i] = Character.toLowerCase(vowels.charAt(--indexVowels));
                }
            }
        }
        return new String(textArr);
    }
}
    