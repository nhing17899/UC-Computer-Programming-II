import java.util.Scanner;

public class Product
{
    private Product product;
    private String name;
    private double unitPrice;

    Scanner in;

    public Product()
    {
        in = new Scanner(System.in);
    }

    public Product(String name, double unitPrice)
    {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public void getProductData()
    {
        setName(SafeInput.getNonZeroLenString(in, "Product name: "));
        setUnitPrice(SafeInput.getDouble(in, " The unit price for the Product: "));
    }
}