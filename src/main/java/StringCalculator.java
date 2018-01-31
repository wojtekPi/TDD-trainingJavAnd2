import java.util.Arrays;

public class StringCalculator {


    public int Add(String numbers) {
        if(numbers.length() > 1) {
            int sum = 0;
            int[] intArray = Arrays.asList(numbers.split(",")).stream().mapToInt(Integer::parseInt).toArray();
            for (int e : intArray) {
                sum = sum + e;
            }
            return sum;
        }
        if(numbers.length() == 1){
            return Integer.parseInt(numbers);
        }
        return 0;
    }
}
