import java.util.Vector;

public class DataGenerator<T extends Comparable<T>> {

    private static int arraysAmount = 4;
    private static int startingArraysSize = 10;
    private static int arraysGrow = 10;

    private Vector<T[]> arrays;

    /**
     * Constructor
     * @param arrayInitializer The initializer of this data generator
     */
    DataGenerator(VectorInitializer<T[]> vectorInitializer,ArrayInitializer<T> arrayInitializer) {
        arrays  = vectorInitializer.initialize(arraysAmount, startingArraysSize, arraysGrow);
        for (T [] array : arrays){
            arrayInitializer.initialize(array);
        }
    }

    Vector<T []> getArrays() {
        return arrays;
    }

    int getArraysAmount() {
        return arraysAmount;
    }
}