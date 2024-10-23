import java.util.Random;
import java.util.Vector;

/**
 * Main Class
 */
public class Main {

    private static void createIntegerData () {
        // Initializing generic auxiliary variables
        Random rand = new Random();

        // Initializing the data initializer
        ArrayInitializer<Integer> integerArrayInitializer = (Integer[] array) -> {
            // Max random value
            int max_random_value = 5000;

            // Filling the array with random variables
            for (int i = 0; i < array.length; i++ )
                array[i] = rand.nextInt(max_random_value);
        };

        // Initializing the vector initializer
        VectorInitializer<Integer []> integersVectorInitializer = () -> {
            // Auxiliary variables
            final int arraysAmount = 4;

            // Initializing the array
            Vector<Integer []> vector = new Vector<>(arraysAmount);

            // Adding j array of type Integer, starting with size 100 and increasing by power of 10 each iteration
            for(int i = 100, j = 0; j < arraysAmount; i *= 10, j++)
                vector.add(new Integer[i]);

            return vector;
        };

        // Creating the Data Generator of type Integer
        DataGenerator<Integer> integerGenerator = new DataGenerator<>(integersVectorInitializer, integerArrayInitializer);

        // Initializing the sorting classes
        BubbleSortUntilNoChange<Integer> integerBubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();

        // Sorting
        for(Integer[] array : integerGenerator.getArrays()){
            final long pre_exec_time = System.nanoTime();
            integerBubbleSortUntilNoChange.sort(array);
            final long difference = System.nanoTime() - pre_exec_time;
            System.out.println("Bubble sort needed " + difference + " nanoseconds with size " + array.length);
        }
    };

    private void createLongData () {

    };

    private void createCharacterData (){

    };

    /**
     * Main function
     * @param args Arguments provided by command lines
     */
    public static void main(String[] args){
        createIntegerData();
    }
}