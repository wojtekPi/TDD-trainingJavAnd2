package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;


@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    public static final String A = "A";
    public static final String B = "B";

    private Object[][] paramsForTestingTranseringAmount() {
        return new Object[][]{
                {0, 100, 200, -200, 300},
                {-50, -40, 70, -120, 30},
                {0, 0, 100, -100, 100},
                {-5, 0, -5, 0, -5}
        };
    }

    @Test
    public void shouldWorksWithEmptyConstuctor() throws Exception {
        PaymentService testedObject = new PaymentService();
        assertThat(testedObject).isNotNull();
    }

    @Test
    @Parameters(method = "paramsForTestingTranseringAmount")
    public void shouldHaveCorrectBalanceAfterTransferingMoney(int balanceFromBefore, int balanceToBefore,
                                                              int howMuch, int expectedBalanceFrom, int expectedBalanceTo) {
        PaymentService testedObject = new PaymentService();
        Account from = new Account(A, balanceFromBefore);
        Account to = new Account(B, balanceToBefore);

        testedObject.transferMoney(from, to, howMuch);

        assertThat(from.getBalance()).isEqualTo(expectedBalanceFrom);
        assertThat(to.getBalance()).isEqualTo(expectedBalanceTo);
    }


}
