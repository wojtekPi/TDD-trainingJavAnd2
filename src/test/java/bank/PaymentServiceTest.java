package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;


@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {



    private Object[][] paramsForTestsAccountB(){
        return new Object[][]{
                {100,100},
                {-5,0},
                {0, 0},
                {2000, 2000}

        };
    }

    private Object[][] paramsForTestsAccountA(){
        return new Object[][]{
                {100,-100},
                {5,-5},
                {0, 0},
                {1, -1},
                {-5, 0},
                {2000, -2000}
        };
    }

    private Object[][] paramsForTestsAccountAStartWithAmmount(){
        return new Object[][]{
                {50,70,-20},
                {-100,50,-150},
                {0, 0, 0},
                {2000, 150, 1850}

        };
    }

    private Object[][] paramsForTestsAccountBStartWithAmmount(){
        return new Object[][]{
                {100,100,200},
                {-100,50,-50},
                {0, 0, 0},
                {-20, 100, 80}
        };
    }





    @Test
    public void shouldWorksWithEmptyConstuctor() throws Exception {
        PaymentService testedObject = new PaymentService();
        assertThat(testedObject).isNotNull();
    }



    @Test
    @Parameters(method = "paramsForTestsAccountB")
    public void shouldWorksWithParametrizedTestB(int howMuch, int expectedResults){
        PaymentService testedObject = new PaymentService();

        Account from = new Account("A", 0);
        Account to = new Account("B", 0);
        testedObject.transferMoney(from, to, howMuch);
        assertThat(to.getBalance()).isEqualTo(expectedResults);

    }

    @Test
    @Parameters(method = "paramsForTestsAccountA")
    public void shouldWorksWithParametrizedTestA(int howMuch, int expectedResults){
        PaymentService testedObject = new PaymentService();

        Account from = new Account("A", 0);
        Account to = new Account("B", 0);
        testedObject.transferMoney(from, to, howMuch);
        assertThat(from.getBalance()).isEqualTo(expectedResults);

    }

    @Test
    @Parameters(method = "paramsForTestsAccountBStartWithAmmount")
    public void shouldWorksWithParametrizedTestBStartWithAmmount(int balance, int howMuch, int expectedResults){
        PaymentService testedObject = new PaymentService();

        Account from = new Account("A", 0);
        Account to = new Account("B", balance);
        testedObject.transferMoney(from, to, howMuch);
        assertThat(to.getBalance()).isEqualTo(expectedResults);
    }

    @Test
    @Parameters(method = "paramsForTestsAccountAStartWithAmmount")
    public void shouldWorksWithParametrizedTestAStartWithAmmount(int balance, int howMuch, int expectedResults){
        PaymentService testedObject = new PaymentService();

        Account from = new Account("A", balance);
        Account to = new Account("B", 0);
        testedObject.transferMoney(from, to, howMuch);
        assertThat(from.getBalance()).isEqualTo(expectedResults);
    }

}
