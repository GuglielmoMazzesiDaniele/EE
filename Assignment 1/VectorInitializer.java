import java.util.Vector;

@FunctionalInterface
public interface VectorInitializer<T> {
    Vector<T> initialize();
}
