import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tdd training on 31.01.18.
 */

public class StringCalculatorTest {

    @Test
    public void shouldCreateObject() throws Exception {
        StringCalculator testedObject = new StringCalculator();
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.Add("")).isEqualTo(0);
    }

}