package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    @Test
    public void shouldCreateObject() throws Exception {
        PaymentService testedObject = new PaymentService();
        assertThat(testedObject).isNotNull();
    }

    @Test
    @Parameters(method = "paramsForTest")
    public void shouldTransferMoneyCorrectly(int fromBalanceBefore, int toBalanceBefore, int howMuch,
                                             int fromBalanceAfter, int toBalanceAfter) {
        PaymentService testedObject = new PaymentService();
        Account firstAccount = new Account();
        Account secondAccount = new Account();
        firstAccount.setBalance(fromBalanceBefore);
        secondAccount.setBalance(toBalanceBefore);
        testedObject.transferMoney(firstAccount, secondAccount, howMuch);
        assertThat(firstAccount.getBalance()).isEqualTo(fromBalanceAfter);
        assertThat(secondAccount.getBalance()).isEqualTo(toBalanceAfter);
    }


    private Object[][] paramsForTest() {
        return new Object[][]{
                {200, 200, 100, 100, 300},
                {1000, 0, 200, 800, 200},
                {1, 1, 1, 0, 2},
                {0, 0, 100, -100, 100},
                {1000000, 2000000, 1000000, 0, 3000000},
        };
    }
}
