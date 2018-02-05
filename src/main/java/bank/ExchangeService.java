package bank;

public interface ExchangeService {
    int calculate(Instrument howMany, Currency targetCurrency);
}
