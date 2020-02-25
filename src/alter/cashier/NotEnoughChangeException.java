package alter.cashier;

public class NotEnoughChangeException extends Exception {
    public NotEnoughChangeException() {
        super("Error: Not Enough Change");
    }

}

