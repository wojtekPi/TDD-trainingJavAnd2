package bank;

public interface ExchangeService {
    public int calculate(Instrument howMany, Currency targetCurrency);
}
