import java.util.Arrays;

public class StringCalculator {


    public int Add(String numbers) {
        if(numbers.length() > 0) {
            int sum = 0;
            int[] intArray = Arrays.asList(numbers.split(",")).stream().mapToInt(Integer::parseInt).toArray();
            for (int e : intArray) {
                sum = sum + e;
            }
            return sum;
        }
        return 0;
    }
}
