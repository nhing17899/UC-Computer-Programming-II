import java.util.ArrayList;

public class Invoice {
    private ArrayList<LineItem> lineItems;

    public Invoice() {
        lineItems = new ArrayList<LineItem>();
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public double calculateTotalAmountDue() {
        double totalAmountDue = 0.0;
        for (LineItem lineItem : lineItems) {
            totalAmountDue += lineItem.calculateLineItemTotal();
        }
        return totalAmountDue;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }
}

