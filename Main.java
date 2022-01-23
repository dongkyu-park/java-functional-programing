import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set12 = getNumbersFactor.factorSet(10);
        Set<Integer> set6 = getNumbersFactor.factorSet(6);

        Iterator it = set12.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(perfect.is(10));
        System.out.println(perfect.is(6));

        Set<Integer> primeSet10 = getPrimeSet.apply(10);
        Set<Integer> primeSet7 = getPrimeSet.apply(7);

        Iterator it2 = primeSet10.iterator();

        while (it2.hasNext()) {
            System.out.println(it2.next());
        }

        System.out.println(prime.is(10));
        System.out.println(prime.is(7));
    }

    // ClassfierAlpha
    public static Factor factor = (number, potentialFactor) -> number % potentialFactor == 0; // 인수
    public static NumbersFactor getNumbersFactor = (number) -> IntStream.rangeClosed(1, (int) Math.sqrt(number))
            .boxed()
            .filter(potentialNumber -> factor.is(number, potentialNumber))
            .map(pod -> new Integer[] {pod, number / pod})
            .flatMap(Arrays::stream)
            .collect(Collectors.toSet());
    public static SetSum setSum = (set) -> set.stream().mapToInt(i -> (int) i).sum();
    public static Perfect perfect = number -> setSum.sum(getNumbersFactor.factorSet(number)) - number == number; // 완전수
    public static Abundant abundant = number -> setSum.sum(getNumbersFactor.factorSet(number)) - number > number; // 과잉수
    public static Deficient deficient = number -> setSum.sum(getNumbersFactor.factorSet(number)) - number < number; // 부족수

    // PrimeAlpha
    public static Function<Integer, Set> getPrimeSet = number -> Stream.of(1, number).collect(Collectors.toSet());
    public static Prime prime = number -> number > 1 && getNumbersFactor.factorSet(number).equals(getPrimeSet.apply(number)); // 소수
}
