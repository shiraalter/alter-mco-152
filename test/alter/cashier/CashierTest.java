package alter.cashier;

import org.junit.Test;

import static org.junit.Assert.*;

public class CashierTest {
    Cashier register;
    Cash cashInRegister;

    @Test
    public void pay() {
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

        //Test that no change is given if the amount paid < amount due:
        //given
        cashInRegister = new Cash();
        cashInRegister.setPennies(100);
        cashInRegister.setQuarters(100);
        register = new Cashier(cashInRegister);
        customer.setOneDollars(1);
        price = 3.00;

        //when
        Cash change = register.pay(price, customer);

        //then
        assertEquals(0, change.getPennies());
        assertEquals(0, change.getQuarters());
    }
}