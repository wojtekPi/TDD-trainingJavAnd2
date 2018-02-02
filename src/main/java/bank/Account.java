package bank;

public class Account {
    private String id;
    private int balance;
    Currency currency;

    public Account(String id, int balance) {
        this.id = id;
        this.balance = balance;
        this.currency = Currency.PLN;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
