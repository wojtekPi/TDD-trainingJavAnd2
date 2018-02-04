package bank;

public class PaymentService {



    private ExchangeService exchangeService;
    private TransactionDB transactionDB;

    private static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";

    void transferMoney(Account from, Account to, Instrument instrument) {
        if (isEnoughMoney(from)) {
            throw new IllegalArgumentException(SORRY_TEXT);
        }
        if (!isTheSameCurrency(from, to, instrument)) {
            throw new IllegalArgumentException();
        }

        int calculatedAmount = instrument.getAmount();
        if(!from.getBalance().getCurrency().equals(to.getBalance().getCurrency())){
            calculatedAmount = exchangeService.calculate(instrument, to.getBalance().getCurrency());
        }

        from.getBalance().setAmount(from.getBalance().getAmount() - instrument.getAmount());
        to.getBalance().setAmount(to.getBalance().getAmount() + calculatedAmount);
        transactionDB.save(from, to, instrument);
    }

    private boolean isEnoughMoney(Account from) {
        return from.getBalance().getAmount() <= -500;
    }

    private boolean isTheSameCurrency(Account from, Account to, Instrument instrument) {
        return instrument.getCurrency().equals(from.getBalance().getCurrency());

    }

    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }
    public void setTransactionDB(TransactionDB transactionDB){this.transactionDB = transactionDB; }
}
