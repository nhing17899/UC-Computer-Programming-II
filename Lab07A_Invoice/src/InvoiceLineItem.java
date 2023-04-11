import java.util.Scanner;

public class InvoiceLineItem
{
    private Product product;
    private double lineTotal;
    private int quantity;
    Scanner in;

    public InvoiceLineItem()
    {
        in = new Scanner(System.in);

    }

    public InvoiceLineItem(Product product, double lineTotal, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
        this.lineTotal = lineTotal;
    }

    public void getLineItemData()
    {
        product = new Product();
        product.getProductData();
        getQuantityData();
        setLineTotal(product, this.quantity);
    }

    public double getLineTotal()
    {
        return lineTotal;
    }

    public void setLineTotal(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;

        this.lineTotal = product.getUnitPrice() * quantity;
    };

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }



    public void getQuantityData()
    {
        setQuantity(SafeInput.getInt(in, "Provide the quantity of products:"));
    }
}