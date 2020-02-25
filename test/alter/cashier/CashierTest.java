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
        double price = 2.49;

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

    }
