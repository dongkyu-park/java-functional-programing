import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set12 = numbersFactor.factorSet(10);
        Set<Integer> set6 = numbersFactor.factorSet(6);

        Iterator it = set12.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(perfect.is(10));
        System.out.println(perfect.is(6));
    }

    // ClassfierAlpha
    public static Factor isFactor = (number, potentialFactor) -> number % potentialFactor == 0; // 인수
    public static NumbersFactor numbersFactor = (number) -> IntStream.rangeClosed(1, (int) Math.sqrt(number))
            .boxed()
            .filter(potentialNumber -> isFactor.is(number, potentialNumber))
            .map(pod -> new Integer[] {pod, number / pod})
            .flatMap(Arrays::stream)
            .collect(Collectors.toSet());
    public static SetSum setSum = (set) -> set.stream().mapToInt(i -> (int) i).sum();
    public static Perfect perfect = number -> setSum.sum(numbersFactor.factorSet(number)) - number == number; // 완전수
    public static Abundant abundant = number -> setSum.sum(numbersFactor.factorSet(number)) - number > number; // 과잉수
    public static Deficient deficient = number -> setSum.sum(numbersFactor.factorSet(number)) - number < number; // 부족수
}
