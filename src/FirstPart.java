import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstPart {
    public static void main(String[] args) {
        //Find Unique Characters
        List<String> fruits = List.of("apple", "orange", "banana");
        List<Character> res = fruits.stream()
                .flatMap(fruit -> fruit.chars().mapToObj(c -> (char) c))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(res);

        //Filter and transform list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        numbers.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> num * num)
                .sorted((num1, num2) -> num2 - num1)
                .forEach(System.out::println);

        //group words by length
        List<String> animals = List.of("cat", "dog", "bird", "giraffe");
        Map<Integer, List<String>> ans = animals.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(ans);
    }
}