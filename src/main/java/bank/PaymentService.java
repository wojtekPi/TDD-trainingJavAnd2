package bank;

public class PaymentService {

    private static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";

    void transferMoney(Account from, Account to, Instrument instrument) {
        if (isEnoughMoney(from)) {
            throw new IllegalArgumentException(SORRY_TEXT);
        }

        from.getInstrument().setAmount(from.getInstrument().getAmount() - instrument.getAmount());
        to.getInstrument().setAmount(to.getInstrument().getAmount() + instrument.getAmount());
    }

    private boolean isEnoughMoney(Account from) {
        return from.getInstrument().getAmount() <= -500;
    }
}
