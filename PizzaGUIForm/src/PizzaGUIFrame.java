import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class PizzaGUIFrame extends JFrame
{
    JFrame frame = new JFrame();
    JPanel mainPnl;
    JPanel midPnl;
    JPanel topPnl;
    JPanel crustPnl;
    JPanel botPnl;
    JPanel sizePnl;
    JPanel toppingsPnl;
    JPanel formPnl;
    JPanel receiptPnl;
    JLabel titleLbl;
    JButton orderButton;
    JButton clearButton;
    JButton quitButton;
    JRadioButton thinCrustBut;
    JRadioButton regularCrustBut;
    JRadioButton deepDishBut;

    JComboBox<String> sizeBox = new JComboBox<>();

    JTextArea receiptText;
    JScrollPane scroller;

    ButtonGroup crustGroups;
    JCheckBox pepperoniBox;
    JCheckBox pineappleBox;
    JCheckBox cheeseBox;
    JCheckBox cornBox;
    JCheckBox mushroomBox;
    JCheckBox sausageBox;


    public PizzaGUIFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createMiddlePanel();
        mainPnl.add(midPnl, BorderLayout.CENTER);

        createBottomPanel();
        mainPnl.add(botPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setTitle("Pizza Order");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width * 3 / 5, screen.height * 3 / 4);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel()
    {
        topPnl = new JPanel();
        topPnl.setBackground(Color.white);

        titleLbl = new JLabel("PIZZA ORDER FORM",JLabel.CENTER);
        titleLbl.setFont(new Font("SERIF", Font.BOLD, 36));

        topPnl.add(titleLbl);
    }

    private void createMiddlePanel()
    {
        midPnl = new JPanel();
        midPnl.setBackground(Color.white);
        midPnl.setLayout(new BorderLayout());

        formPnl = new JPanel();
        formPnl.setLayout(new GridLayout(3,1));

        // CRUST
        crustPnl = new JPanel();
        crustPnl.setBackground(Color.white);

        thinCrustBut = new JRadioButton("Thin");
        regularCrustBut = new JRadioButton("Regular");
        deepDishBut = new JRadioButton("Deep-dish");

        thinCrustBut.setBackground(Color.white);
        regularCrustBut.setBackground(Color.white);
        deepDishBut.setBackground(Color.white);

        crustGroups = new ButtonGroup();
        crustGroups.add(thinCrustBut);
        crustGroups.add(regularCrustBut);
        crustGroups.add(deepDishBut);

        crustPnl.add(thinCrustBut);
        crustPnl.add(regularCrustBut);
        crustPnl.add(deepDishBut);

        // SIZE
        sizePnl = new JPanel();
        sizePnl.setBackground(Color.white);
        sizePnl.setLayout(new BorderLayout());

        String[] sizes = {"Small", "Medium", "Large", "Super"};
        sizeBox = new JComboBox<String>(sizes);
        sizeBox.setBackground(Color.white);
        sizeBox.setSelectedIndex(0);

        sizePnl.add(sizeBox);

        // TOPPINGS
        toppingsPnl = new JPanel();
        toppingsPnl.setBackground(Color.white);
        toppingsPnl.setLayout(new GridLayout(2,3));

        pepperoniBox = new JCheckBox("Pepperoni");
        cheeseBox = new JCheckBox("Cheese");
        pineappleBox = new JCheckBox("Pineapple");
        cornBox = new JCheckBox("Corn");
        sausageBox = new JCheckBox("Sausage");
        mushroomBox = new JCheckBox("Mushroom");

        pepperoniBox.setBackground(Color.white);
        pepperoniBox.setBackground(Color.white);
        cheeseBox.setBackground(Color.white);
        pineappleBox.setBackground(Color.white);
        cornBox.setBackground(Color.white);
        sausageBox.setBackground(Color.white);
        mushroomBox.setBackground(Color.white);

        toppingsPnl.add(pepperoniBox);
        toppingsPnl.add(pineappleBox);
        toppingsPnl.add(cheeseBox);
        toppingsPnl.add(cornBox);
        toppingsPnl.add(sausageBox);
        toppingsPnl.add(mushroomBox);

        // RECEIPT
        receiptPnl = new JPanel();
        receiptPnl.setBackground(Color.white);

        receiptText = new JTextArea(20,60);
        receiptText.setBackground(new Color(240, 240, 240));

        receiptText.setEditable(false);

        scroller = new JScrollPane(receiptText);

        receiptPnl.add(scroller);

        // FORM
        formPnl.add(crustPnl);
        formPnl.add(sizePnl);
        formPnl.add(toppingsPnl);

        crustPnl.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Crust"));
        sizePnl.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Size"));
        toppingsPnl.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Toppings"));
        receiptPnl.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Receipt"));

        // MAIN PANEL
        midPnl.add(formPnl, BorderLayout.CENTER);
        midPnl.add(receiptPnl, BorderLayout.SOUTH);

    }

    private void createBottomPanel()
    {
        botPnl = new JPanel();
        botPnl.setLayout(new GridLayout(1,3));
        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        orderButton.addActionListener(
                (ActionEvent ae) -> {
                    double total = 0;
                    receiptText.append("\n" + "==============================================Receipt");

                    // CRUST
                    if(thinCrustBut.isSelected())
                    {
                        receiptText.append("\n" + "Thin Crust         $1.00");
                        total += 1;
                    }
                    else if (regularCrustBut.isSelected())
                    {
                        receiptText.append("\n" + "Regular Crust      $2.00");
                        total += 2;
                    }
                    else if (deepDishBut.isSelected())
                    {
                        receiptText.append("\n" + "Deep Dish          $4.00");
                        total += 4;
                    }

                    System.out.println(crustGroups.getSelection());

                    // SIZE
                    switch(sizeBox.getSelectedIndex())
                    {
                        case 0:
                            receiptText.append("\n" + "Small              $8.00");
                            total += 8;
                            break;
                        case 1:
                            receiptText.append("\n" + "Regular            $12.00");
                            total += 12;
                            break;
                        case 2:
                            receiptText.append("\n" + "Large              $16.00");
                            total += 16;
                            break;
                        case 3:
                            receiptText.append("\n" + "Super              $20.00");
                            total += 20;
                            break;
                    }

                    // TOPPINGS
                    if(pepperoniBox.isSelected())
                    {
                        receiptText.append("\n" + "Pepperoni          $1.00");
                        total += 1;
                    }
                    if(cheeseBox.isSelected())
                    {
                        receiptText.append("\n" + "Cheese             $1.00");
                        total += 1;
                    }
                    if( pineappleBox.isSelected())
                    {
                        receiptText.append("\n" + "Pineapple          $1.00");
                        total += 1;
                    }
                    if(cornBox.isSelected())
                    {
                        receiptText.append("\n" + "Corn               $1.00");
                        total += 1;
                    }
                    if(sausageBox.isSelected())
                    {
                        receiptText.append("\n" + "Sausage            $1.00");
                        total += 1;
                    }
                    if(mushroomBox.isSelected())
                    {
                        receiptText.append("\n" + "Mushroom           $1.00");
                        total += 1;
                    }

                    //display Everything
                    String subTotal = new String("");
                    receiptText.append("\n\n" + "Subtotal: $" + total);
                    double tax = total * .07;
                    String taxRound = String.format("%.2f", tax);
                    receiptText.append("\n" + "Tax:      $" + taxRound);
                    total += tax;
                    receiptText.append("\n" + "----------------------------------------------");
                    receiptText.append("\n" + "Total:    $" + total);
                    receiptText.append("\n" + "==============================================End");
                }
        );
        quitButton.addActionListener(
                (ActionEvent ae) -> {
                    int a = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?");

                    if (a == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                }
        );

        clearButton.addActionListener(
                (ActionEvent ae) -> {
                    crustGroups.clearSelection();
                    sizeBox.setSelectedIndex(0);
                    pepperoniBox.setSelected(false);
                    cheeseBox.setSelected(false);
                    cornBox.setSelected(false);
                    pineappleBox.setSelected(false);
                    mushroomBox.setSelected(false);
                    sausageBox.setSelected(false);
                    receiptText.selectAll();
                    receiptText.replaceSelection("");
                }
        );

        botPnl.add(orderButton);
        botPnl.add(clearButton);
        botPnl.add(quitButton);
    }

}