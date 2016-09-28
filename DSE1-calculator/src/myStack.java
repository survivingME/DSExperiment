/**
 * Created by Tremble on 2016/9/8.
 */
public class myStack<T> implements Stack<T> {
    private Object[] stackArray = new Object[100];
    private int length = 0;

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            stackArray[i] = null;
        }
        length = 0;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public boolean push(T data) {
        stackArray[length++] = data;
        return true;
    }

    @Override
    public T pop() {
        if (length == 0) {
            return null;
        }
        T temp = (T) stackArray[--length];
        stackArray[length] = null;
        return temp;
    }

    public T top() {
        return (T) stackArray[length-1];
    }
}
