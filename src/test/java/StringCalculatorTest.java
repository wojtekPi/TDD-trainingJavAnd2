import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * Tdd training on 31.01.18.
 */

public class StringCalculatorTest {

    @Test
    public void shouldCreateObject() throws Exception {
        StringCalculator testedObject = new StringCalculator();
        assertThat(testedObject).isNotNull();
    }

    @Test
    public void shouldRturnOneWhenOnePassed() {
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldRturnTwoWhenOneAndOnePassed() {
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add("1,1");
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldRturnTwentyOneWhenOneAndTwentyPassed() {
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add("1,20");
        assertThat(result).isEqualTo(21);
    }

    @Test
    public void shouldRturnTwoThousandWhenThousandAndThousandPassed() {
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add("1000,1000");
        assertThat(result).isEqualTo(2000);
    }

    @Test
    public void shouldRturnThreeWhenOneAndOneAndOnePassed() {
        StringCalculator testedObject = new StringCalculator();
        int result = testedObject.Add("1,1,1");
        assertThat(result).isEqualTo(3);

    }
}