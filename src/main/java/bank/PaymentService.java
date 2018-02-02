package bank;

public class PaymentService {

    private static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";

    void transferMoney(Account from, Account to, Instrument instrument) {
        if (isEnoughMoney(from)) {
            throw new IllegalArgumentException(SORRY_TEXT);
        }
        if (!from.getCurrency().equals(to.getCurrency())&&!from.getCurrency().equals(instrument.getCurrency())) {
            throw new IllegalArgumentException();
        }
        from.setBalance(from.getBalance() - instrument.getAmount());
        to.setBalance(to.getBalance() + instrument.getAmount());
    }

    private boolean isEnoughMoney(Account from) {
        return from.getBalance() <= -500;
    }
}
