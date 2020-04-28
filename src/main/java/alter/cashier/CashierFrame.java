package alter.cashier;

import javax.swing.*;
import java.awt.*;

public class CashierFrame extends JFrame {
    Cash customerCash = new Cash();
    Cash cashInRegister = new Cash();
    private JTextArea originalAmountText;
    private JTextArea changeText;
    private JPanel cashierPanel;
    private JPanel payPanel;
    private JPanel leftPanel;
    private JPanel middlePanel;
    private JTextArea runningTotal;
    private JButton penny;
    private JButton nickel;
    private JButton dime;
    private JButton quarter;
    private JButton oneDollar;
    private JButton fiveDollar;
    private JButton tenDollar;
    private JButton twentyDollar;
    private JButton clearPayment;
    private JButton clearRegister;
    private JButton calculateChange;
    private JTextField price;


    public CashierFrame() {

        setLayout(new BorderLayout());  //border layout

        setSize(900, 800);
        setTitle("Cash Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create register
        CashRegister();

        //create customer payment
        customerPayment();

        //create results area
        middlePanel = new JPanel();
        middlePanel.setBorder(BorderFactory.createTitledBorder("Results"));
        originalAmountText = new JTextArea();
        changeText = new JTextArea();
        middlePanel.add(originalAmountText);
        middlePanel.add(changeText);
        add(middlePanel, BorderLayout.CENTER);


        //create price input
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        price = new JTextField();
        price.setBorder(BorderFactory.createTitledBorder("Price Of Item"));
        price.setPreferredSize(new Dimension(160, 20));
        leftPanel.add(price);
        //add running total to WEST
        runningTotal = new JTextArea();
        leftPanel.add(runningTotal);
        add(leftPanel, BorderLayout.WEST);


        //create change button
        calculateChange = new JButton("Calculate Change");
        add(calculateChange, BorderLayout.EAST);
        calculateChange.addActionListener(actionEvent -> {
            try {
                change();
            } catch (BrokeCashierException e) {
                changeText.setText("Cash Register is Broke");
            } catch (NotEnoughChangeException e) {
                changeText.setText("Not Enough Change");
            } catch (InsufficientPaymentException e) {
                changeText.setText("Insufficient Payment!");
            }catch(NumberFormatException e){
                changeText.setText("Please Enter a Price");
            }
        });





    }

    private void change() throws BrokeCashierException, NotEnoughChangeException, InsufficientPaymentException {
        Cashier register = new Cashier(cashInRegister);

        originalAmountText.setText("Customer Paid: $" + customerCash.totalCash() + "\n" + customerCash.getPennies() + " Pennies, " + customerCash.getNickels() + " Nickels, "
                + customerCash.getDimes() + " Dimes, " + customerCash.getQuarters() + " Quarters, \n" + customerCash.getOneDollars() + " One Dollar Bills, "
                + customerCash.getFiveDollars() + " Five Dollar Bills, " + customerCash.getTenDollars() + " Ten Dollar Bills, " + customerCash.getTwentyDollars()
                + " Twenty Dollar Bills.\n" +
                "\nRegister Amount: $" + cashInRegister.totalCash() + "\n" + cashInRegister.getPennies() + " Pennies, " + cashInRegister.getNickels() + " Nickels, "
                + cashInRegister.getDimes() + " Dimes, " + cashInRegister.getQuarters() + " Quarters, \n" + cashInRegister.getOneDollars() + " One Dollar Bills, "
                + cashInRegister.getFiveDollars() + " Five Dollar Bills, " + cashInRegister.getTenDollars() + " Ten Dollar Bills, " + cashInRegister.getTwentyDollars()
                + " Twenty Dollar Bills.\n");

        Cash changeReturned = register.pay(Double.parseDouble(price.getText()), customerCash);    //call pay method from Cashier class

        changeText.setText("\nAmount of Change Given: $" + changeReturned.totalCash() + "\n" + changeReturned.getPennies() + " Pennies, " + changeReturned.getNickels() + " Nickels, "
                + changeReturned.getDimes() + " Dimes, " + changeReturned.getQuarters() + " Quarters, \n" + changeReturned.getOneDollars() + " One Dollar Bills, "
                + changeReturned.getFiveDollars() + " Five Dollar Bills, " + changeReturned.getTenDollars() + " Ten Dollar Bills, " + changeReturned.getTwentyDollars()
                + " Twenty Dollar Bills.\n");


        //Feedback to user how much updated cash customer & register have
       /* //(values are correct after first round, but it doesn't "lock in" the amount for the second around so I commented it out)
        runningTotal.setText("The Customer Now Has: \n" + customerCash.getPennies() + " Pennies, " + customerCash.getNickels() + " Nickels, \n"
                + customerCash.getDimes() + " Dimes, " + customerCash.getQuarters() + " Quarters, \n" + customerCash.getOneDollars() + " One Dollar Bills, "
                + customerCash.getFiveDollars() + " Five Dollar Bills, \n" + customerCash.getTenDollars() + " Ten Dollar Bills, " + customerCash.getTwentyDollars()
                + " Twenty Dollar Bills.\n" +
                "\nThe Register NOW has: \n" + cashInRegister.getPennies() + " Pennies, " + cashInRegister.getNickels() + " Nickels, \n"
                + cashInRegister.getDimes() + " Dimes, " + cashInRegister.getQuarters() + " Quarters, \n" + cashInRegister.getOneDollars() + " One Dollar Bills, "
                + cashInRegister.getFiveDollars() + " Five Dollar Bills, \n" + cashInRegister.getTenDollars() + " Ten Dollar Bills, " + cashInRegister.getTwentyDollars()
                + " Twenty Dollar Bills.\n " + "-------------------------------------------------------------------"
                + "\n\n Amount of Change Given: $" + changeReturned.totalCash());*/


    }

    private void customerPayment() {
        payPanel = new JPanel();
        payPanel.setBorder(BorderFactory.createTitledBorder("Customer Payment"));
        payPanel.setLayout((new GridLayout(2, 3)));
        payPanel.add(penny = new JButton("Penny"));
        payPanel.add(nickel = new JButton("Nickel"));
        payPanel.add(dime = new JButton("Dime"));
        payPanel.add(quarter = new JButton("Quarter"));
        payPanel.add(oneDollar = new JButton("One Dollar"));
        payPanel.add(fiveDollar = new JButton("Five Dollars"));
        payPanel.add(tenDollar = new JButton("Ten Dollars"));
        payPanel.add(twentyDollar = new JButton("Twenty Dollars"));
        payPanel.add(clearPayment = new JButton("CLEAR PAYMENT"));
        // payPanel.add(payButton = new JButton("PAY"));
        add(payPanel, BorderLayout.NORTH);

        //add money to customer cash object
        //action listeners to incorporate running total as user clicks each button
        penny.addActionListener(actionEvent -> customerCash.addPennies());
        penny.addActionListener(actionEvent -> runningTotal());

        nickel.addActionListener(actionEvent -> customerCash.addNickels());
        nickel.addActionListener(actionEvent -> runningTotal());

        dime.addActionListener(actionEvent -> customerCash.addDimes());
        dime.addActionListener(actionEvent -> runningTotal());

        quarter.addActionListener(actionEvent -> customerCash.addQuarters());
        quarter.addActionListener(actionEvent -> runningTotal());

        oneDollar.addActionListener(actionEvent -> customerCash.addOneDollars());
        oneDollar.addActionListener(actionEvent -> runningTotal());

        fiveDollar.addActionListener(actionEvent -> customerCash.addFiveDollars());
        fiveDollar.addActionListener(actionEvent -> runningTotal());

        tenDollar.addActionListener(actionEvent -> customerCash.addTenDollars());
        tenDollar.addActionListener(actionEvent -> runningTotal());

        twentyDollar.addActionListener(actionEvent -> customerCash.addTwentyDollars());
        twentyDollar.addActionListener(actionEvent -> runningTotal());

        clearPayment.addActionListener(actionEvent -> customerCash.clearCash());
        clearPayment.addActionListener(actionEvent -> runningTotal());
    }

    private void CashRegister() {
        cashierPanel = new JPanel();
        cashierPanel.setBorder(BorderFactory.createTitledBorder("Cash Register"));
        cashierPanel.setLayout((new GridLayout(2, 3)));

        //add buttons to register
        cashierPanel.add(penny = new JButton("Penny"));
        cashierPanel.add(nickel = new JButton("Nickel"));
        cashierPanel.add(dime = new JButton("Dime"));
        cashierPanel.add(quarter = new JButton("Quarter"));
        cashierPanel.add(oneDollar = new JButton("One Dollar"));
        cashierPanel.add(fiveDollar = new JButton("Five Dollars"));
        cashierPanel.add(tenDollar = new JButton("Ten Dollars"));
        cashierPanel.add(twentyDollar = new JButton("Twenty Dollars"));
        cashierPanel.add(clearRegister = new JButton("CLEAR REGISTER"));
        add(cashierPanel, BorderLayout.SOUTH);

        //add money to register cash object on click
        penny.addActionListener(actionEvent -> cashInRegister.addPennies());
        penny.addActionListener(actionEvent -> runningTotal());

        nickel.addActionListener(actionEvent -> cashInRegister.addNickels());
        nickel.addActionListener(actionEvent -> runningTotal());

        dime.addActionListener(actionEvent -> cashInRegister.addDimes());
        dime.addActionListener(actionEvent -> runningTotal());

        quarter.addActionListener(actionEvent -> cashInRegister.addQuarters());
        quarter.addActionListener(actionEvent -> runningTotal());

        oneDollar.addActionListener(actionEvent -> cashInRegister.addOneDollars());
        oneDollar.addActionListener(actionEvent -> runningTotal());

        fiveDollar.addActionListener(actionEvent -> cashInRegister.addFiveDollars());
        fiveDollar.addActionListener(actionEvent -> runningTotal());

        tenDollar.addActionListener(actionEvent -> cashInRegister.addTenDollars());
        tenDollar.addActionListener(actionEvent -> runningTotal());

        twentyDollar.addActionListener(actionEvent -> cashInRegister.addTwentyDollars());
        twentyDollar.addActionListener(actionEvent -> runningTotal());

        clearRegister.addActionListener(actionEvent -> cashInRegister.clearCash());
        clearRegister.addActionListener(actionEvent -> runningTotal());




    }

    //display running total
    private void runningTotal() {
        runningTotal.setText("The Customer Now Has: \n" + customerCash.getPennies() + " Pennies, " + customerCash.getNickels() + " Nickels, \n"
                + customerCash.getDimes() + " Dimes, " + customerCash.getQuarters() + " Quarters, \n" + customerCash.getOneDollars() + " One Dollar Bills, "
                + customerCash.getFiveDollars() + " Five Dollar Bills, \n" + customerCash.getTenDollars() + " Ten Dollar Bills, " + customerCash.getTwentyDollars()
                + " Twenty Dollar Bills.\n" +
                "\nThe Register NOW has: \n" + cashInRegister.getPennies() + " Pennies, " + cashInRegister.getNickels() + " Nickels, \n"
                + cashInRegister.getDimes() + " Dimes, " + cashInRegister.getQuarters() + " Quarters, \n" + cashInRegister.getOneDollars() + " One Dollar Bills, "
                + cashInRegister.getFiveDollars() + " Five Dollar Bills, \n" + cashInRegister.getTenDollars() + " Ten Dollar Bills, " + cashInRegister.getTwentyDollars()
                + " Twenty Dollar Bills.\n " + "-------------------------------------------------------------------");
               // + "\n\n Amount of Change Given: $" + changeReturned.totalCash());
    }


    public static void main(String[] args) {
        CashierFrame cashFrame = new CashierFrame();
        cashFrame.setVisible(true);
    }

}
