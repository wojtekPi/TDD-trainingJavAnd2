package bank;

public class PaymentService {

    private static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";

    void transferMoney(Account from, Account to, Instrument instrument) {
        if (isEnoughMoney(from)) {
            throw new IllegalArgumentException(SORRY_TEXT);
        }
        if (!isTheSameCurrency(from, to, instrument)) {
            throw new IllegalArgumentException();
        }

        from.getBalance().setAmount(from.getBalance().getAmount() - instrument.getAmount());
        to.getBalance().setAmount(to.getBalance().getAmount() + instrument.getAmount());
    }

    private boolean isEnoughMoney(Account from) {
        return from.getBalance().getAmount() <= -500;
    }

    private boolean isTheSameCurrency(Account from, Account to, Instrument instrument) {
        return instrument.getCurrency().equals(from.getBalance().getCurrency())
                && instrument.getCurrency().equals(to.getBalance().getCurrency());

    }
}
