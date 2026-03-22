import java.util.ArrayList;

public class AccessCountArrayList<E> extends ArrayList<E>
{
    private int count = 0;
    @Override
    public E get(int index){
        count += 1;
        return super.get(index);
    }
    
    @Override
    public E set(int index, E element){
        count += 1;
        return super.set(index,element);       
    }
    
    public int getAccessCount()
    {
        return this.count;
    }

    public void resetCount()
    {
        this.count = 0;
    }
}
