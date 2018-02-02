package bank;

public class PaymentService {

    private static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";

    void transferMoney(Account from, Account to, int howMuch) {
        if (isEnoughMoney(from)) {
            throw new IllegalArgumentException(SORRY_TEXT);
        }
        from.setBalance(from.getBalance() - howMuch);
        to.setBalance(to.getBalance() + howMuch);
    }

    private boolean isEnoughMoney(Account from) {
        return from.getBalance() <= -500;
    }
}
