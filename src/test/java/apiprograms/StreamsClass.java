package apiprograms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsClass implements Comparable {
    private int age;
    private double basicSalary;
    private double providentFund;
    private int yearsOfExp;

    private int getAge() {
        return age;
    }

    private double getBasicSalary() {
        return basicSalary;
    }

    private double getProvidentFund() {
        return providentFund;
    }

    private int getYearsOfExp() {
        return yearsOfExp;
    }


    public StreamsClass(int age, double basicSalary, double providentFund, int yearsOfExp) {
        this.age = age;
        this.basicSalary = basicSalary;
        this.providentFund = providentFund;
        this.yearsOfExp = yearsOfExp;
    }

    public static void main(String[] args) {
        List<StreamsClass> streamClassList = Arrays.asList(
                new StreamsClass(28, 10000, 1000, 5),
                new StreamsClass(30, 20000, 5000, 15),
                new StreamsClass(28, 20000, 2000, 10),
                new StreamsClass(35, 10000, 1000, 15),
                new StreamsClass(35, 450000, 2000, 10),
                new StreamsClass(30, 450000, 5000, 5),
                new StreamsClass(30, 350000, 10000, 10),
                new StreamsClass(28, 10000, 50000, 10)
        );
        Supplier<Stream<StreamsClass>> stream = () -> streamClassList.stream();
        double max = stream.get()
                .filter(a -> a.getAge() > 28 || a.getYearsOfExp() == 10)
                .sorted()
                .limit(5)
                .map(a -> a.getBasicSalary() * 10)
                .max((a, b) -> a.compareTo(b))
                .orElseThrow(NullPointerException::new);

        Map<Integer, Map<Double, List<Double>>> mapOfMap = stream.get()
                .filter(a -> a.getYearsOfExp() > 5)
                .collect(Collectors.groupingBy(a -> a.getYearsOfExp(), Collectors.groupingBy(a -> a.getBasicSalary(),
                        Collectors.mapping(a -> a.getProvidentFund(), Collectors.toList()))));
        System.out.println("Max basic salary is:" + max);
        System.out.println("Final result of grouping by years of experience is:"+mapOfMap);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
