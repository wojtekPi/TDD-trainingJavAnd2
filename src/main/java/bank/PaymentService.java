package bank;

public class PaymentService {

    private static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";

    void transferMoney(Account from, Account to, Instrument howMuch) {
        if (isEnoughMoney(from)) {
            throw new IllegalArgumentException(SORRY_TEXT);
        }


        from.getBalance().setAmmount(from.getBalance().getAmmount() - howMuch.getAmmount());
        to.getBalance().setAmmount(to.getBalance().getAmmount() + howMuch.getAmmount());


    }


    private boolean isEnoughMoney(Account from) {
        return from.getBalance().getAmmount() <= -500;
    }
}
