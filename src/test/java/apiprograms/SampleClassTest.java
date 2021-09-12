package apiprograms;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SampleClassTest {

    @Test
    public void streamTest1Method() {
        List<String> stringList = Arrays.asList("Bangkok", "Brasilia", "Newyork", "Seol", "Beijing", "Nairobi", "San-Francisco",
                "Berlin", "stolkholm", "Sandiago", "Newark");
        Stream<String> stream = stringList.stream();
        String max = stream.filter(a -> a.startsWith("B"))
                .map(a -> a.toLowerCase())
                .sorted()
                .max((a, b) -> a.compareTo(b)).get();
        System.out.println("Largest length of string is:"+max);
    }
    @Test
    public void streamTest2Method() {
        List<Integer> integerList = Arrays.asList(10, 20, 15, 90, 75, 42, 66, 73, 62, 50, 45);
        Stream<Integer> integerStream = integerList.stream();
        int sum = integerStream
                .filter(a -> a%2 ==0 && a > 10 )
                .sorted()
                .map(a -> a*10)
                .reduce((a, b) -> a+b).orElseThrow(NullPointerException::new);
        System.out.println("sum of numbers is:"+sum);

    }

}
