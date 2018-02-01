package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    public static final String SORRY_TEXT = "I'm very sorry, but you don't have enough money...";
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public static final String A = "A";
    public static final String B = "B";

    private PaymentService testedObject;

    private Object[][] paramsForTestingTranseringAmount() {
        return new Object[][]{
                {0, 100, 200, -200, 300},
                {-50, -40, 70, -120, 30},
                {0, 0, 100, -100, 100},
                {-5, 0, -5, 0, -5}
        };
    }

    @Before
    public void setUp(){
        testedObject = new PaymentService();
    }

    @Test
    public void shouldWorksWithEmptyConstuctor() throws Exception {
        assertThat(testedObject).isNotNull();
    }

    @Test
    @Parameters(method = "paramsForTestingTranseringAmount")
    public void shouldHaveCorrectBalanceAfterTransferingMoney(int balanceFromBefore, int balanceToBefore,
                                                              int howMuch, int expectedBalanceFrom, int expectedBalanceTo) {
        Account from = new Account(A, balanceFromBefore);
        Account to = new Account(B, balanceToBefore);

        testedObject.transferMoney(from, to, howMuch);

        assertThat(from.getBalance()).isEqualTo(expectedBalanceFrom);
        assertThat(to.getBalance()).isEqualTo(expectedBalanceTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNotEnoughMoney() {
        Account from = new Account(A, -501);
        Account to = new Account(B, 0);

        testedObject.transferMoney(from, to, 100);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithProperMessageWhenNotEnoughMoney() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(SORRY_TEXT);
        Account from = new Account(A, -501);
        Account to = new Account(B, 0);

        testedObject.transferMoney(from, to, 100);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithProperMessageWhenNotEnoughMoneyUsingAssertJ() {
        Account from = new Account(A, -501);
        Account to = new Account(B, 0);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> testedObject.transferMoney(from, to, 100)
        ).withMessage(SORRY_TEXT);
    }

}
