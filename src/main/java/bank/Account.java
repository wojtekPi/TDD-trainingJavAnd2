package bank;

public class Account {
    private String id;
    private Instrument balance;

    public Account(String id, Instrument balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instrument getBalance() {
        return balance;
    }

    public void setBalance(Instrument balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "id='" + id + '\'' + ", balance=" + balance + '}';
    }
}
