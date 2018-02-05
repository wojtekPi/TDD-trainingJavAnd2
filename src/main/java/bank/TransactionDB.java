package bank;

public interface TransactionDB {
    public void save(Account from, Account to, Instrument howMany);
}
