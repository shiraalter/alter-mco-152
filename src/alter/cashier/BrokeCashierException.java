package alter.cashier;

import java.awt.*;

public class BrokeCashierException extends Exception {
    public BrokeCashierException(){
        super("Error: The Register Is Empty");
    }
}
