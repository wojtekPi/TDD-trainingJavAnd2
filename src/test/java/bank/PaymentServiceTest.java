package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static bank.Currency.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    public static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";
    public static final String A = "A";
    public static final String B = "B";
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private PaymentService testedObject;

    private Object[][] paramsForTestingTranseringAmount() {
        return new Object[][]{
                {0, 100, 200, -200, 300, EUR},
                {-50, -40, 70, -120, 30, EUR},
                {0, 0, 100, -100, 100, EUR},
                {-5, 0, -5, 0, -5, EUR}
        };
    }

    private Object[][] paramsForTestingTranseringCurrency() {
        return new Object[][]{
                {PLN, EUR, EUR},
                {USD, EUR, EUR},
                {PLN, PLN, EUR},
                {USD, USD, EUR}
        };
    }


    @Before
    public void setUp() {
        testedObject = new PaymentService();
    }

    @Test
    public void shouldWorksWithEmptyConstuctor() throws Exception {
        assertThat(testedObject).isNotNull();
    }

    @Test
    @Parameters(method = "paramsForTestingTranseringAmount")
    public void shouldHaveCorrectBalanceAfterTransferingMoney(int amountFromBefore, int amountToBefore, int howMuch, int expectedAmountFrom, int expectedAmountTo, Currency currency) {
        Account from = new Account(A, new Instrument(currency, amountFromBefore));
        Account to = new Account(B, new Instrument(currency, amountToBefore));

        TransactionDB transactionDBMock = mock(TransactionDB.class);
        testedObject.setTransactionDB(transactionDBMock);
        testedObject.transferMoney(from, to, new Instrument(currency, howMuch));

        assertThat(from.getBalance().getAmount()).isEqualTo(expectedAmountFrom);
        assertThat(to.getBalance().getAmount()).isEqualTo(expectedAmountTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNotEnoughMoney() {
        Account from = new Account(A, new Instrument(EUR, -501));
        Account to = new Account(B, new Instrument(EUR, 0));

        testedObject.transferMoney(from, to, new Instrument(EUR, 200));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithProperMessageWhenNotEnoughMoney() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(SORRY_TEXT);
        Account from = new Account(A, new Instrument(EUR, -501));
        Account to = new Account(B, new Instrument(EUR, 0));

        testedObject.transferMoney(from, to, new Instrument(EUR, 200));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithProperMessageWhenNotEnoughMoneyUsingAssertJ() {
        Account from = new Account(A, new Instrument(EUR, -501));
        Account to = new Account(B, new Instrument(EUR, 0));


        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> testedObject.transferMoney(from, to, new Instrument(EUR, 200))
        ).withMessage(SORRY_TEXT);
    }

    @Test
    @Parameters(method = "paramsForTestingTranseringCurrency")
    public void shouldThrowIllegalArgumentExceptionWhenNotCompatibleCurrency(Currency currencyAccoutFrom,
                                                                             Currency currecyAccoutTo, Currency currencyInstrument) {
        Account from = new Account(A, new Instrument(currencyAccoutFrom, 0));
        Account to = new Account(B, new Instrument(currecyAccoutTo, 0));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> testedObject.transferMoney(from, to, new Instrument(currencyInstrument, 0)));
    }

    @Test
    public void shouldCallExchangeServiceWhenItIsRequired() {
        Account from = new Account(A, new Instrument(PLN, 100));
        Account to = new Account(B, new Instrument(EUR, 0));
        Instrument howMany = new Instrument(PLN, 80);

        ExchangeService exchangeServiceMock = mock(ExchangeService.class);
        TransactionDB transactionDBMock = mock(TransactionDB.class);
        testedObject.setExchangeService(exchangeServiceMock);
        testedObject.setTransactionDB(transactionDBMock);
        testedObject.transferMoney(from, to, howMany);

        verify(exchangeServiceMock, times(1)).calculate(howMany, EUR);
    }

    @Test
    public void shouldUseCalculatedAmountFromExchangeServiceWhenCurrenciesIs() {
        Account from = new Account(A, new Instrument(PLN, 110));
        Account to = new Account(B, new Instrument(EUR, 0));
        Instrument howMany = new Instrument(PLN, 80);

        ExchangeService exchangeServiceMock = mock(ExchangeService.class);
        TransactionDB transactionDBMock = mock(TransactionDB.class);
        when(exchangeServiceMock.calculate(howMany, EUR)).thenReturn(20);

        testedObject.setExchangeService(exchangeServiceMock);
        testedObject.setTransactionDB(transactionDBMock);

        ExchangeService exchangeService = mock(ExchangeService.class);
        testedObject.setExchangeService(exchangeService);

        testedObject.transferMoney(from, to, howMany);

        verify(exchangeService, times(1)).calculate(howMany, EUR);
    }

    @Test
    public void shouldUseCalculetedAmountFromExchangeServiceWhenCurrenciesDiffers() {
        Account from = new Account(A, new Instrument(PLN, 110));
        Account to = new Account(B, new Instrument(EUR, 0));
        Instrument howMany = new Instrument(PLN, 80);

        ExchangeService exchangeService = mock(ExchangeService.class);
        TransactionDB transactionDBmock = mock(TransactionDB.class);
        Mockito.when(exchangeService.calculate(howMany,EUR)).thenReturn(20);

        testedObject.setExchangeService(exchangeService);
        testedObject.setTransactionDB(transactionDBmock);
        testedObject.transferMoney(from, to, howMany);

        assertThat(to.getBalance().getAmount()).isEqualTo(20);
        assertThat(from.getBalance().getCurrency()).isEqualTo(PLN);
        assertThat(from.getBalance().getAmount()).isEqualTo(30);
        assertThat(to.getBalance().getCurrency()).isEqualTo(EUR);

    }

    @Test
    public void shouldSaveTransactionToDbWhenMoneyWasTransfered(){
        Account from = new Account(A, new Instrument(PLN, 0));
        Account to = new Account(B, new Instrument(PLN, 0));
        Instrument howMany = new Instrument(PLN, 10);

        TransactionDB transactionDBMock = mock(TransactionDB.class);
        testedObject.setTransactionDB(transactionDBMock);

        testedObject.transferMoney(from, to, howMany);

        verify(transactionDBMock).save(from, to, howMany);
    }

}
