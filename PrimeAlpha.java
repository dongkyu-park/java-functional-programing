import java.util.HashSet;
import java.util.Set;

public class PrimeAlpha {
    private int number;
    private SetAdder setAdder = (s, n) -> s.add(n);

    public PrimeAlpha(int number) {
        this.number = number;
    }

    public boolean isPrime() {
        Set primeSet = new HashSet();
        setAdder.add(primeSet, 1);
        setAdder.add(primeSet, number);

        return number > 1 && factors().equals(primeSet);
    }

    public boolean isFactor(int potentialFactor) {
        return number % potentialFactor == 0;
    }

    public Set factors() {
        HashSet factors = new HashSet<>();
        for (int pod = 1; pod <= Math.sqrt(number); pod++) {
            if (isFactor(pod)) {
                setAdder.add(factors, pod);
                setAdder.add(factors, number / pod);
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        PrimeAlpha prime1 = new PrimeAlpha(10);
        PrimeAlpha prime2 = new PrimeAlpha(7);

        System.out.println(prime1.isPrime());
        System.out.println(prime2.isPrime());
    }
}