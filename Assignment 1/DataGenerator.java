import java.util.Vector;

public  class DataGenerator<T extends Comparable<T>> {

    private Vector<T[]> arrays;

    /**
     * Constructor
     * @param arrayInitializer The initializer of this data generator
     */
    DataGenerator(VectorInitializer<T[]> vectorInitializer,ArrayInitializer<T> arrayInitializer) {
        arrays = vectorInitializer.initialize();
        for (T [] array : arrays){
            arrayInitializer.initialize(array);
        }
    }

    Vector<T []> getArrays() {
        return arrays;
    }
}