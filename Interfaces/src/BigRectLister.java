import java.awt.*;
import java.util.ArrayList;
public class BigRectLister {
    public static void collectAll(ArrayList<Rectangle> rectList) {
        for (Rectangle s : rectList) {
            System.out.println(s);
        }
    };
    public static void main(String[] args) {
        ArrayList<Rectangle> rectList = new ArrayList<>();
        rectList.add(new Rectangle(1,2));
        rectList.add(new Rectangle(1,3));
        rectList.add(new Rectangle(1,4));
        rectList.add(new Rectangle(2,3));
        rectList.add(new Rectangle(2,4));
        rectList.add(new Rectangle(1,1));
        rectList.add(new Rectangle(3,9));
        rectList.add(new Rectangle(8,9));
        rectList.add(new Rectangle(4,6));
        rectList.add(new Rectangle(2,5));

        BigRectangleFilter filter = new BigRectangleFilter();

        ArrayList<Rectangle> rectListBigPeri = new ArrayList<>();

        for (Rectangle rect : rectList) {
            if (filter.accept(rect)) rectListBigPeri.add(rect);
        }
        collectAll(rectListBigPeri);
    }
}
