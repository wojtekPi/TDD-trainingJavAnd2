import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTest {

//    @Test
//    public void shouldCreateObject() throws Exception {
//        StringCalculator testedObject = new StringCalculator();
//        assertThat(testedObject).isNotNull();
//        assertThat(testedObject.Add("")).isEqualTo(0);
//    }
//
//    @Test
//    public void shouldRturnOneWhenOnePassed() {
//        StringCalculator testedObject = new StringCalculator();
//        int result = testedObject.Add("1");
//        assertThat(result).isEqualTo(1);
//    }
//
//    @Test
//    public void shouldRturnTwoWhenOneAndOnePassed() {
//        StringCalculator testedObject = new StringCalculator();
//        int result = testedObject.Add("1,1");
//        assertThat(result).isEqualTo(2);
//    }
//
//    @Test
//    public void shouldRturnTwentyOneWhenOneAndTwentyPassed() {
//        StringCalculator testedObject = new StringCalculator();
//        int result = testedObject.Add("1,20");
//        assertThat(result).isEqualTo(21);
//    }
//
//    @Test
//    public void shouldRturnTwoThousandWhenThousandAndThousandPassed() {
//        StringCalculator testedObject = new StringCalculator();
//        int result = testedObject.Add("1000,1000");
//        assertThat(result).isEqualTo(2000);
//    }
//
//    @Test
//    public void shouldRturnThreeWhenOneAndOneAndOnePassed() {
//        StringCalculator testedObject = new StringCalculator();
//        int result = testedObject.Add("1,1,1");
//        assertThat(result).isEqualTo(3);
//    }

// to zastepuje powyzsze testy
    private Object[][] paramsForTests(){
        return new Object[][] {
                {"", 0},
                {"1", 1},
                {"1,1", 2},
                {"1,20", 21},
                {"1000,1000", 2000},
                {"1,1,1", 3},
        };
    }

    @Test
    @Parameters(method = "paramsForTests")
    public void shouldWorksWithParametrizedTest (String input, int expectedResult) {
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedResult);
    }

}



