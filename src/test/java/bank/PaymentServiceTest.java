package bank;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceTest {
    @Test
    public void shouldTransferMoneyFromFirstAccountToAnother(){
        PaymentService payment = new PaymentService();
        Account firstAccount = new Account("1234", 0);
        Account secondAccount = new Account("4567", 0);
        int balanceOfFirsAccount = firstAccount.getBalance();
        int balanceOfSecondAccount = secondAccount.getBalance();

        payment.transferMoney(firstAccount, secondAccount, 100);

        assertThat(firstAccount.getBalance()).isEqualTo(balanceOfFirsAccount - 100);
        assertThat(secondAccount.getBalance()).isEqualTo(balanceOfSecondAccount + 100);
    }
}
