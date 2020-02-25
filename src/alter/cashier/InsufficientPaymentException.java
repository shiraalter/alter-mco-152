package alter.cashier;

public class InsufficientPaymentException extends Exception {
    public InsufficientPaymentException(){
        super("Error: Insufficient payment");
    }
}
