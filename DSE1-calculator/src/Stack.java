/**
 * Created by Tremble on 2016/9/8.
 */
public interface Stack<T> {
    boolean isEmpty();

    void clear();

    int length();

    boolean push(T data);

    T pop();

    T top();
}
