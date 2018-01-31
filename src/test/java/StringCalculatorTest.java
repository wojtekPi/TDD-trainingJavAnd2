import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;


/**
 * Tdd training on 31.01.18.
 */
@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTest {

    @Test
    @Parameters(method = "parametersValues")
    public void shouldReturnExpectedValueWhenParametersPassed(String input, int expectedValue) {
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedValue);
    }

    private Object[] parametersValues() {
        return new Object[]{
                new Object[]{"", 0},
                new Object[]{"0", 0},
                new Object[]{"1,1", 2},
                new Object[]{"1,1,1", 3},
                new Object[]{"100,100", 200}
        };
    }
}