import java.awt.*;

public class BigRectangleFilter implements Filter{
    @Override
    public boolean accept(Object x) {
        Rectangle rectangle = (Rectangle) x;
        return 2*(rectangle.height + rectangle.width) > 10;
    }
}
