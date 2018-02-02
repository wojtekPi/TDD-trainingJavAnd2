package bank;

public class Instrument {
    private Currency currency;
    private int ammount;

    public Instrument(Currency currency, int ammount) {
        this.currency = currency;
        this.ammount = ammount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
}
