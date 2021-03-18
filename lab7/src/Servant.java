import java.util.LinkedList;
import java.util.Queue;

public class Servant {
    private int bufSize;
    private Queue<Object> buffer;

    public Servant(int bufSize){
        this.bufSize = bufSize;
        this.buffer = new LinkedList<Object>();
    }
    public void add(Object object) {
        if(!isFull()){
            this.buffer.add(object);
        }
    }

    public Object remove() {
        if(isEmpty()){
            return null;
        }
        else {
            return buffer.remove();
        }
    }
    public boolean isFull() {
        return buffer.size() == bufSize;
    }
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

}
