import java.util.ArrayList;
import java.util.List;

public class WordCount extends FileProcessor<List<Integer>> {
    private int charCount, wordCount, lineCount;
    
    protected void startFile() {
        charCount = 0;
        wordCount = 0;
        lineCount = 0;
    }
    
    protected void processLine(String line) {
            charCount += line.length();
            wordCount += wordsInLine(line);
            lineCount++;
    }
    
    private int wordsInLine(String line) {
        String trim = line.trim();
        if (trim.isEmpty()){
            return 0;
        }   
        return trim.split("\\s+").length;
    }
    
    protected List<Integer> endFile() {
        List<Integer> charWordLines = new ArrayList<Integer>();
        charWordLines.add(charCount);
        charWordLines.add(wordCount);
        charWordLines.add(lineCount);
        return charWordLines;
    }
}