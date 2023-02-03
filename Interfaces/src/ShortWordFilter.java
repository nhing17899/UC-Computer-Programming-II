import java.util.ArrayList;

public class ShortWordFilter implements Filter {
    @Override
    public boolean accept(Object x) {
        String word = (String) x;
        return word.length() < 5;
    }
}
