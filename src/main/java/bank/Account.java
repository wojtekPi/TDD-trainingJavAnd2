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

    public void setBalance(int balance) {
        this.balance.setAmount(balance);
    }
    public void setCurrency(Currency currency) {
        this.balance.setCurrency(currency);
    }
    public Currency getCurrency(){
        return this.balance.getCurrency();
    }
    public int getBalance(){
        return this.balance.getAmount();
    }
}
