import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceForm extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JButton addItemButton;
    private JButton addAddressButton;
    private JButton doneButton;
    private JTextArea invoiceTextArea;
    private JScrollPane scrollPane;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField productNameField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private double totalAmountDue;

    public InvoiceForm() {
        super("Invoice Form");

        // Initialize components
        mainPanel = new JPanel(new BorderLayout());
        addItemButton = new JButton("Add Item");
        doneButton = new JButton("Calculate");
        invoiceTextArea = new JTextArea();
        String invoiceTitle = "============================INVOICE============================\n\n";
        invoiceTextArea.append(invoiceTitle);
        scrollPane = new JScrollPane(invoiceTextArea);

        // Set component properties
        invoiceTextArea.setEditable(false);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Add components to main panel
        mainPanel.add(createFormPanel(), BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        // Add main panel to frame
        this.add(mainPanel);

        // Set frame properties
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width * 1 / 2, screen.height * 2/3);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());

        JPanel form1Panel = new JPanel();
        form1Panel.setLayout(new GridLayout(5,1));
        JPanel row1Panel = new JPanel(new GridLayout(1,4));
        JPanel row2Panel = new JPanel(new GridLayout(1,4));
        JPanel row3Panel = new JPanel(new GridLayout(1,4));
        JPanel row4Panel = new JPanel(new GridLayout(1,4));
        streetField = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipField = new JTextField();
        row1Panel.add(new JLabel("Street:"));
        row1Panel.add(streetField);
        row2Panel.add(new JLabel("City:"));
        row2Panel.add(cityField);
        row3Panel.add(new JLabel("State:"));
        row3Panel.add(stateField);
        row4Panel.add(new JLabel("Zip:"));
        row4Panel.add(zipField);

        addAddressButton = new JButton("Add Address");
        addAddressButton.addActionListener(
            (ActionEvent ae) -> {
                String street = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zip = zipField.getText();

                String addressText = street + "\n" + city + ", " + state + " " + zip;
                String separateLine = "\n\n===============================================================\n\n";
                String header = String.format("%s\t%s\t%s\t%s\n\n", "Item", "Price", "Qty", "Total");
                invoiceTextArea.append(addressText);
                invoiceTextArea.append(separateLine);
                invoiceTextArea.append(header);

                addAddressButton.setEnabled(false);
                streetField.setEnabled(false);
                cityField.setEnabled(false);
                stateField.setEnabled(false);
                zipField.setEnabled(false);
            }
        );
        form1Panel.add(row1Panel);
        form1Panel.add(row2Panel);
        form1Panel.add(row3Panel);
        form1Panel.add(row4Panel);
        form1Panel.add(addAddressButton);


        JPanel form2Panel = new JPanel();
        form2Panel.setLayout(new GridLayout(5,1));
        JPanel row5Panel = new JPanel(new GridLayout(1,2));
        JPanel row6Panel = new JPanel(new GridLayout(1,2));
        JPanel row7Panel = new JPanel(new GridLayout(1,2));
        productNameField = new JTextField();
        unitPriceField = new JTextField();
        quantityField = new JTextField();
        row5Panel.add(new JLabel("Product Name:"));
        row5Panel.add(productNameField);
        row6Panel.add(new JLabel("Unit Price:"));
        row6Panel.add(unitPriceField);
        row7Panel.add(new JLabel("Quantity:"));
        row7Panel.add(quantityField);

        form2Panel.add(new Panel());
        form2Panel.add(row5Panel);
        form2Panel.add(row6Panel);
        form2Panel.add(row7Panel);
        form2Panel.add(new Panel());

        formPanel.add(form1Panel, BorderLayout.NORTH);
        formPanel.add(form2Panel, BorderLayout.SOUTH);

        return formPanel;
    }
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addItemButton.addActionListener(this);
        doneButton.addActionListener(this);
        buttonPanel.add(addItemButton);
        buttonPanel.add(doneButton);
        return buttonPanel;
    }

    private void addItem() {
        String productName = productNameField.getText();
        double unitPrice = Double.parseDouble(unitPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        double lineItemTotal = unitPrice * quantity;
        totalAmountDue += lineItemTotal;

        String itemText = String.format("%s\t$%.2f\t%d\t$%.2f\n", productName, unitPrice, quantity, lineItemTotal);
        invoiceTextArea.append(itemText);

        clearFormFields();
    }

    private void clearFormFields() {
        productNameField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
    }

    private void printInvoice() {
        // Add divider
        invoiceTextArea.append("\n===============================================================\n\n");

        String invoiceText = String.format("Total Amount Due: $%.2f", totalAmountDue);
        invoiceTextArea.append(invoiceText);


        // Disable buttons
        addItemButton.setEnabled(false);
        doneButton.setEnabled(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItemButton) {
            addItem();
        } else if (e.getSource() == doneButton) {
            printInvoice();
        }
    }
}
