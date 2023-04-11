import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Invoice
{
    private InvoiceAddress customerAddress;
    private InvoiceLineItem Invoicelineitem;
    private ArrayList<InvoiceLineItem> lineItemsList;
    private double amountDue = 0;
    Scanner in = new Scanner(System.in);

    public Invoice()
    {
        lineItemsList = new ArrayList<InvoiceLineItem>();
        customerAddress = new InvoiceAddress();
        customerAddress.getAddress();
    }

    public void getInvoiceData()
    {
        boolean done = false;
        do
        {
            InvoiceLineItem item = new InvoiceLineItem();
            item.getLineItemData();
            lineItemsList.add(item);
            done = SafeInput.getYNConfirm(in, "Do you don't want to add another product to your list and want to exit?");
        }
        while(!done);
    }

    public void getAddressData()
    {
        customerAddress.getAddress();
    }

    private double getTotalAmtDue()
    {
        for(InvoiceLineItem items : lineItemsList)
        {
            this.amountDue += items.getLineTotal();
        }
        return amountDue;
    }

    public void displayInvoice()
    {
        System.out.println("Invoice");
        System.out.println("\n");

        System.out.println(customerAddress.getStreet());
        System.out.println(customerAddress.getCity() + ", " + customerAddress.getState() + ", " + customerAddress.getZip());
        System.out.println("=====================================================================================================");
        System.out.printf("%-30s %-30s %-30s %-30s", "Your Item", "Price", "Quantity", "Total");
        for (InvoiceLineItem item : lineItemsList)
        {
            System.out.println("\n");
            System.out.printf("%-30s %-30.2f %-30s %-30.2f", item.getProduct().getName(), item.getProduct().getUnitPrice(), item.getQuantity(), item.getLineTotal());
        }
        System.out.println("\n");
        System.out.println("=====================================================================================================");
        System.out.printf("%-30s %-30.2f", "Amount Due:", getTotalAmtDue());
    }
}