@FunctionalInterface
public interface ArrayInitializer<T extends Comparable<T>> {
    void initialize(T[] data);
}