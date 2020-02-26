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
public class Cashier {

    Cash register = new Cash();

    final double PENNY = 0.01;
    final double NICKEL = 0.05;
    final double DIME = 0.10;
    final double QUARTER = 0.25;
    final double FIVE = 5.00;
    final double TEN = 10.00;
    final double TWENTY = 20.00;


    public Cashier(Cash register) {
        this.register = register;
    }

    public Cash pay(double price, Cash paid) throws NotEnoughChangeException, InsufficientPaymentException, BrokeCashierException {

        Cash changeToReturn = new Cash();       //created change object here instead of in the if statement because of scope issue

        double changeDue = (paid.totalCash() - price);
        changeDue = Math.round(changeDue * 100.0) / 100.0;

        if(register.totalCash() == 0 && paid.totalCash() != price) {
            throw new BrokeCashierException();
        }

        if (paid.totalCash() < price) {
            throw new InsufficientPaymentException();
        }

        if (register.totalCash() < changeDue) {
            throw new NotEnoughChangeException();
        }

        //only add cash to register and calculate change if the conditions are met
        if (paid.totalCash() >= changeDue && register.totalCash() >= changeDue && register.totalCash() != 0) {
            updateRegisterCash(paid);


            while ((changeDue >= 20.00) && (register.getTwentyDollars() >= 1)) {
                changeDue -= 20.00;
                register.decreaseTwentyDollars();
                changeToReturn.addTwentyDollars();
            }

            while ((changeDue >= 10.00) && (register.getTenDollars() >= 1)) {
                changeDue -= 10.00;
                register.decreaseTenDollars();
                changeToReturn.addTenDollars();
            }

            while ((changeDue >= 5.00) && (register.getFiveDollars() >= 1)) {
                changeDue -= 5.00;
                register.decreaseFiveDollars();
                changeToReturn.addFiveDollars();
            }

            while ((changeDue >= 1.00) && (register.getOneDollars() >= 1)) {
                changeDue -= 1.00;
                register.decreaseOneDollar();
                changeToReturn.addOneDollars();
            }

            while ((changeDue >= 0.25) && (register.getQuarters() >= 1)) {
                changeDue -= 0.25;
                register.decreaseQuarters();
                changeToReturn.addQuarters();
            }

            while ((changeDue >= 0.10) && (register.getDimes() >= 1)) {
                changeDue -= 0.10;
                register.decreaseDimes();
                changeToReturn.addDimes();
            }

            while ((changeDue >= 0.05) && (register.getNickels() >= 1)) {
                changeDue -= 0.05;
                register.decreaseNickels();
                changeToReturn.addNickels();
            }

            while ((changeDue >= 0.01) && (register.getPennies() >= 1)) {
                changeDue -= 0.01;
                register.decreasePennies();
                changeToReturn.addPennies();
            }
            if(changeDue != 0) {
                throw new NotEnoughChangeException();
            }
            updateCustomerCash(paid, changeToReturn);       //update customer cash based on change

        }
        return changeToReturn;
    }

    //update the register after customer pays by combining the original amounts with the newly paid amounts
    private void updateRegisterCash(Cash paid) {
        register.setPennies(register.getPennies() + paid.getPennies());
        register.setNickels(register.getNickels() + paid.getNickels());
        register.setDimes(register.getDimes() + paid.getDimes());
        register.setOneDollars(register.getOneDollars() + paid.getOneDollars());
        register.setFiveDollars(register.getFiveDollars() + paid.getFiveDollars());
        register.setTenDollars(register.getTenDollars() + paid.getTenDollars());
        register.setTwentyDollars(register.getTwentyDollars() + paid.getTwentyDollars());
    }

    private void updateCustomerCash(Cash paid, Cash changeToReturn) {
        /*update original customer "paid" object --> (I originally did not have this and
            it was not tracking the changes in the customer cash object when change was given/cash was paid)*/
        paid.setPennies(changeToReturn.getPennies());
        paid.setNickels(changeToReturn.getNickels());
        paid.setDimes(changeToReturn.getDimes());
        paid.setQuarters(changeToReturn.getQuarters());
        paid.setOneDollars(changeToReturn.getOneDollars());
        paid.setFiveDollars(changeToReturn.getFiveDollars());
        paid.setTenDollars(changeToReturn.getTenDollars());
        paid.setTwentyDollars(changeToReturn.getTwentyDollars());
    }
}
