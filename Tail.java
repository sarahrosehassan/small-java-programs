import java.util.LinkedList;
import java.util.List;

public class Tail extends FileProcessor<List<String>>{
    private int n;
    private LinkedList<String> lastLines;
    public Tail(int n){
        this.n = n;
        lastLines = new LinkedList<String>();
    }
    
    @Override
    protected void startFile() {
    }
    
    @Override
    protected void processLine(String line) {
        if(lastLines.size() == n){
            lastLines.removeFirst();
        }
        lastLines.addLast(line);
    }
    
    @Override
    protected List<String> endFile() {
        return lastLines;
    }
}