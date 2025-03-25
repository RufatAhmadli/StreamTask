import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SecondPart {
    public static void main(String[] args) {
        //finding frequency of word
        List<String> fruits = List.of("apple", "banana", "apple", "orange", "banana", "banana");
        int k = 2;
        var selectedOnes = fruits.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() >= k)
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(selectedOnes);

        //compute average score
        Map<String, List<Integer>> scores = new LinkedHashMap<>();
        scores.putIfAbsent("Alice", new ArrayList<>());
        scores.putIfAbsent("Bob", new ArrayList<>());
        scores.get("Alice").add(90);
        scores.get("Bob").add(80);
        scores.get("Alice").add(100);
        scores.get("Bob").add(70);

        Map<String, Double> res = scores.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .mapToInt(Integer::intValue)
                                .average()
                                .orElse(0.0)
                ));
        System.out.println(res);

        //first non-repeating character
        String s = "swiss";
        s.chars().mapToObj(c -> (char) c)
                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .ifPresentOrElse(e -> System.out.println(e.getKey()), () -> System.out.println("No unique element"));
    }
}