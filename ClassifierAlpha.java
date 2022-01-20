import java.util.HashSet;
import java.util.Set;

public class ClassifierAlpha {
    private int number;
    private SetAdder setAdder = (s, n) -> s.add(n);

    public ClassifierAlpha(int number) {
        this.number = number;
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

    static public int sum(Set factors) {
        return factors.stream().mapToInt(i -> (int) i).sum();
    }

    public boolean isPerfect() {
        return sum(factors()) - number == number;
    }

    public boolean isAbundant() {
        return sum(factors()) - number > number;
    }

    public boolean isDeficient() {
        return sum(factors()) - number < number;
    }

    public static void main(String[] args) {
        ClassifierAlpha alpha1 = new ClassifierAlpha(10);
        ClassifierAlpha alpha2 = new ClassifierAlpha(6);

        System.out.println(alpha1.isPerfect());
        System.out.println(alpha2.isPerfect());
    }
}