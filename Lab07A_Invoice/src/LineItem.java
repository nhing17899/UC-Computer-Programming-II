public class LineItem {
    private String productName;
    private double unitPrice;
    private int quantity;

    public LineItem(String productName, double unitPrice, int quantity) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public double calculateLineItemTotal() {
        return quantity * unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String formatLineItem() {
        String formatString = "%-20s %-15.2f %-10d $%-15.2f";
        double lineItemTotal = calculateLineItemTotal();
        return String.format(formatString, productName, unitPrice, quantity, lineItemTotal);
    }
}
