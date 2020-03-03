package alter.cashier;

import org.junit.Test;

import static org.junit.Assert.*;

public class CashierTest {
    Cashier register;
    Cash cashInRegister;
    Cash customer;

    @Test
    public void pay() throws NotEnoughChangeException, InsufficientPaymentException, BrokeCashierException {
        //given
        cashInRegister = new Cash();
        cashInRegister.setPennies(100);
        cashInRegister.setQuarters(100);
        register = new Cashier(cashInRegister);

        Cash customer = new Cash();
        customer.setOneDollars(3);
        double price = 2.49;        //register owes .51

        //when
        Cash changeGiven = register.pay(price, customer);

        //then
        assertEquals(2, changeGiven.getQuarters());
        assertEquals(1, changeGiven.getPennies());
        assertEquals(99, cashInRegister.getPennies());
        assertEquals(98, cashInRegister.getQuarters());
        assertEquals(3, cashInRegister.getOneDollars());
        //make sure customer amount updates:
        assertEquals(0, customer.getOneDollars());
        assertEquals(2, customer.getQuarters());
        assertEquals(1, customer.getPennies());
    }

        @Test(expected = InsufficientPaymentException.class)
        public void insufficientPayment() throws InsufficientPaymentException, NotEnoughChangeException, BrokeCashierException {

            //given
            cashInRegister = new Cash();
            cashInRegister.setOneDollars(10);
            register = new Cashier(cashInRegister); //set the register with cash object
            double price = 5.00;
            customer = new Cash();
            customer.setOneDollars(3);

            //when
            register.pay(price, customer);

        }

    @Test(expected = BrokeCashierException.class)
    public void brokeCashier() throws NotEnoughChangeException, InsufficientPaymentException, BrokeCashierException {
        //given
        cashInRegister = new Cash();
        register = new Cashier(cashInRegister);  //empty cash object added
        double price = 5.00;
        customer = new Cash();
        customer.setOneDollars(10);

        //when
        register.pay(price, customer);
    }

    @Test(expected = NotEnoughChangeException.class)
    public void notExactChange() throws BrokeCashierException,NotEnoughChangeException,InsufficientPaymentException {
        //given
        cashInRegister = new Cash();
        cashInRegister.setTwentyDollars(1);
        register = new Cashier(cashInRegister);
        double price = 3.50;
        customer = new Cash();
        customer.setFiveDollars(1);     //need 1.50 change

        //when
       register.pay(price, customer);
    }

    /*
Write a test to make sure that if your Cashier.pay()
method throws an Exception, that the money in the Cashier doesn't change.
To do this you need to try-catch the Exception and then in the catch, make sure
the Cashier's money is unchanged from what it was at the beginning.
     */
    @Test
    public void exactPaymentEmptyRegister(){
        //test that cashier updates/takes money when it starts off EMPTY and there is exact payment given
            //even though there should be an exception thrown if the register is empty, it should update if no change is needed

        //given
        cashInRegister = new Cash();
        register = new Cashier(cashInRegister); //set register with 0 dollar
        double price = 3.00;
        customer = new Cash();
        customer.setOneDollars(3);

        //when
        try {
            register.pay(price,customer);
        } catch (NotEnoughChangeException e) {
            e.printStackTrace();
        } catch (InsufficientPaymentException e) {
            e.printStackTrace();
        } catch (BrokeCashierException e) {
            e.printStackTrace();
        }

        //then
        assertEquals(3, cashInRegister.getOneDollars());
    }

    @Test   //test if pay() throws exception, cashier $ will be unchanged with starting off EMPTY
    public void emptyRegisterNotEnoughChange()  {

        //given
            //price is 3, register is empty, customer pays 4
        cashInRegister = new Cash();
        register = new Cashier(cashInRegister); //set register with 0 dollar
        double price = 3.00;
        customer = new Cash();
        customer.setOneDollars(4);

        //when
        try {
            register.pay(price, customer);
        }

        //then
        catch (NotEnoughChangeException | InsufficientPaymentException | BrokeCashierException e) {
            assertEquals(0, cashInRegister.getOneDollars());
        }

    }
    @Test   //test if pay() throws exception, cashier $ will be unchanged
    public void notEnoughChange() {

        //given
        //price is 5, register has 2, payment is 10, chang = 5
        cashInRegister = new Cash();
        cashInRegister.setOneDollars(2);
        register = new Cashier(cashInRegister); //set register with 0 dollar
        double price = 5.00;
        customer = new Cash();
        customer.setOneDollars(10);

        //when
        try {
            register.pay(price, customer);
        }

        //then
        catch (NotEnoughChangeException | InsufficientPaymentException | BrokeCashierException e) {
            assertEquals(2, cashInRegister.getOneDollars());
        }

    }


}
