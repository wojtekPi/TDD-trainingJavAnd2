import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tdd training on 31.01.18.
 */
@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTest {

    @Test
    @Parameters(method = "paramsForTest")
    public void shouldWorksWithParametrizedTest(String input, int expectedResult){
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResult);
    }


    private Object[][] paramsForTest(){
        return new Object[][]{
                {"", 0},
                {"1",1},
                {"1,1",2},
                {"1,20,",21},
                {"1,20,",21},
                {"1000,1000,",2000},
                {"1,1,1",3}
        };
    }
}