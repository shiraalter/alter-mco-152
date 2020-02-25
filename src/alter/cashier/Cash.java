package alter.cashier;
/*
a. Create a Cash class that can hold:
pennies, nickels, dimes, quarters, oneDollars, fiveDollars, tenDollars, twentyDollars.
(Think of these as the number of these in the register, not a total amount of money in those denominations)
b. Create a Cashier class that has a Cash object
c. Write a method called "pay" which takes a price as a double and a
Cash object (which will be what the consumer pays with) and returns another Cash object (the change).
write unit tests for the "pay" method.
 */


import sun.nio.cs.StreamEncoder;

public class Cash {
    private int pennies, nickels, dimes, quarters, oneDollar, fiveDollars, tenDollars, twentyDollars;
    final double PENNY = 0.01;
    final double NICKEL = 0.05;
    final double DIME = 0.10;
    final double QUARTER = 0.25;
    final double DOLLAR = 1.00;
    final double FIVE = 5.00;
    final double TEN = 10.00;
    final double TWENTY = 20.00;

    public Cash() {
    }
    //method for total cash

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public void setOneDollars(int oneDollar) {
        this.oneDollar = oneDollar;
    }

    public void setFiveDollars(int fiveDollars) {
        this.fiveDollars = fiveDollars;
    }

    public void setTenDollars(int tenDollars) {
        this.tenDollars = tenDollars;
    }

    public void setTwentyDollars(int twentyDollars) {
        this.twentyDollars = twentyDollars;
    }

    public int getPennies() {
        return pennies;
    }

    public int getNickels() {
        return nickels;
    }

    public int getDimes() {
        return dimes;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getOneDollars() {
        return oneDollar;
    }

    public int getFiveDollars() {
        return fiveDollars;
    }

    public int getTenDollars() {
        return tenDollars;
    }

    public int getTwentyDollars() {
        return twentyDollars;
    }

    public void addPennies() {
        pennies++;
    }

    public void decreasePennies() {
        pennies--;
    }

    public void addNickels() {
        nickels++;
    }

    public void decreaseNickels() {
        nickels--;
    }

    public void addDimes() {
        dimes++;
    }

    public void decreaseDimes() {
        dimes--;
    }

    public void addQuarters() {
        quarters++;
    }

    public void decreaseQuarters() {
        quarters--;
    }

    public void addOneDollars() {
        oneDollar++;
    }

    public void decreaseOneDollar() {
        oneDollar--;
    }

    public void addFiveDollars() {
        fiveDollars++;
    }

    public void decreaseFiveDollars() {
        fiveDollars--;
    }

    public void addTenDollars() {
        tenDollars++;
    }

    public void decreaseTenDollars() {
        tenDollars--;
    }

    public void addTwentyDollars() {
        twentyDollars++;
    }

    public void decreaseTwentyDollars() {
        twentyDollars--;
    }

    public double totalCash() {
        return((PENNY * getPennies()) + (NICKEL * getNickels())
                + (DIME * getDimes()) + (DOLLAR * getOneDollars())  + (QUARTER * getQuarters())
                + (FIVE * getFiveDollars()) + (TEN * getTenDollars()) + (TWENTY * getTwentyDollars()));
    }

}


