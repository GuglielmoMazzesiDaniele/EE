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
            Vector<Integer []> arrays = new Vector<>(arraysAmount);

            // Adding j array of type Integer, starting with size 100 and increasing by power of 10 each iteration
            for(int i = 10, j = 0; j < arraysAmount; i *= 10, j++)
                arrays.add(new Integer[i]);

            return arrays;
        };

        // Creating the Data Generator of type Integer
        DataGenerator<Integer> integerGenerator = new DataGenerator<>(integersVectorInitializer, integerArrayInitializer);

        // Initializing the vector of sorters
        Vector<Sorter> sorters = new Vector<>(4);

        // Initializing the sorting classes
        BubbleSortUntilNoChange<Integer> integerBubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        sorters.add(integerBubbleSortUntilNoChange);

        BubbleSortWhileNeeded<Integer> integerBubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();
        sorters.add(integerBubbleSortWhileNeeded);

        QuickSortGPT<Integer> integerQuickSortGPT = new QuickSortGPT<>();
        sorters.add(integerQuickSortGPT);

        SelectionSortGPT<Integer> integerSelectionSortGPT = new SelectionSortGPT<>();
        sorters.add(integerSelectionSortGPT);

        // Initializing a vector containing the mean execution time for each sorter for each data size
        Vector<int[]> executionTimes = new Vector<>();

        // Sorting
        for(int i = 0; i < sorters.size(); i++){
            // Getting the current sorter class
            Sorter current_sorter = sorters.get(i);
            // Initializing the containers of means
            int maxIterations = 10;
            int[] meanExecutionTime = new int[sorters.size()];
            // Printing the sorter name
            System.out.println(current_sorter.getName());

            // Sorting the arrays
            for(Integer[] array : integerGenerator.getArrays()) {
                // Executing maxIterations amount of iterations
                for(int iteration = 0; iteration < maxIterations; iteration ++) {
                    // Creating a clone of the original  array
                    Integer[] arrayClone = array.clone();
                    // Collecting time pre execution
                    final long pre_exec_time = System.nanoTime();
                    // Sorting array
                    current_sorter.sort(arrayClone);
                    // Collecting time post execution
                    final long difference = System.nanoTime() - pre_exec_time;
                    // Adding the execution time to the total
                    meanExecutionTime[i] += difference;
                    // Printing current iterations stats
                    System.out.println("Array size: " + arrayClone.length
                            + " - Execution time: " + difference + " ns"
                            + " - Iteration: " + iteration);
                }
                // Computing the average execution time
                meanExecutionTime[i] = meanExecutionTime[i] / maxIterations;

                // Adding the execution time to the array
                System.out.println("Array size: " + array.length +
                        " - Average execution time: " + meanExecutionTime[i] + " ns");
                System.out.println();
            }
            // Printing a new line between sorting algorithms
            System.out.println();
            System.out.println();
        }
    };

    /**
     * Main function
     * @param args Arguments provided by command lines
     */
    public static void main(String[] args){
        createIntegerData();
    }
}