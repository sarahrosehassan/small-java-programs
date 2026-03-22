import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExercises {
    public int countLines(Path path, int thres) throws IOException{
        Stream<String> lines = Files.lines(path);
        Stream<String> LessThanThres =  lines.filter(line -> line.length() >= thres);
        return (int) LessThanThres.count();
    }

    public List<String> collectWords(Path path) throws IOException{
        Stream<String> lines = Files.lines(path);
        Stream<String> toLowerCase = lines.map(line -> line.toLowerCase());
        Stream<String> indivWords = toLowerCase.map(line-> line.split("[^a-z]+")).flatMap(Arrays::stream);
        Stream<String> discEmptyWords = indivWords.filter(word -> word.length() > 0).sorted();
        List<String> discConsec = discEmptyWords.distinct().collect(Collectors.toList());
        return discConsec; 
    }
}