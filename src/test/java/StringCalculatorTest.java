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

    private Object[][] paramsForTests(){
        return new Object[][]{
                {"1",1},
                {"1,1", 2},
                {"1000,1000", 2000},
                {"1,1,1", 3},
                {"25,25,25", 75},
                {"", 0}
        };
    }

    @Test
    @Parameters(method = "paramsForTests")
    public void shouldWorksWithParametrizedTest(String input, int expectedResults){
        StringCalculator testedObject = new StringCalculator();

        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResults);

    }
}