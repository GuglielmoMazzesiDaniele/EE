import java.util.Vector;

@FunctionalInterface
public interface VectorInitializer<T> {
    Vector<T> initialize(int arraysAmount, int arraysStartingSize, int arraysGrow);
}
