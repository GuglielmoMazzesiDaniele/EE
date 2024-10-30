import java.util.Random;
import java.util.Vector;

/**
 * Main Class
 */
public class Main {
    /**
     * Generic function that generate the execution time using the provided algorithms
     * @param generator The class that initialized the data and contains it
     * @param <T> The type of the data, must extend Comparable
     */
    private static <T extends Comparable<T>> void generateExecutionTime(DataGenerator<T> generator, String typeName) {
        // Auxiliary data
        int arraysAmount = generator.getArraysAmount();
        int maxIterations = 2;

        // Printing the current data type
        System.out.println("-------------- " + typeName + " --------------\n");

        // Initializing a vector containing the mean execution time for each sorter for each data size
        Vector<long[]> executionTimes = new Vector<>(arraysAmount);

        // Initializing the vector of sorters
        Vector<Sorter> sorters = new Vector<>(generator.getArraysAmount());

        // Initializing the sorting classes
        BubbleSortUntilNoChange<T> integerBubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
        sorters.add(integerBubbleSortUntilNoChange);
        BubbleSortWhileNeeded<T> integerBubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();
        sorters.add(integerBubbleSortWhileNeeded);
        QuickSortGPT<T> integerQuickSortGPT = new QuickSortGPT<>();
        sorters.add(integerQuickSortGPT);
        SelectionSortGPT<T> integerSelectionSortGPT = new SelectionSortGPT<>();
        sorters.add(integerSelectionSortGPT);

        // Iterating all the sorters algorithms
        for(int i = 0; i < sorters.size(); i++){
            // Getting the current sorter class
            Sorter current_sorter = sorters.get(i);
            // Printing the sorter name
            System.out.println(current_sorter.getName());

            // Initializing the array that will contain current sorter execution times
            long[] meanExecutionTime = new long[arraysAmount];

            // Iterating all the data arrays
            for(int j = 0; j < arraysAmount; j ++){
                // Sorting the array an arbitrary N (maxIterations) amount of times
                for(int iteration = 0; iteration < maxIterations; iteration++) {
                    // Cloning the current array
                    T[] arrayClone = generator.getArrays().get(j).clone();
                    // Collecting time pre execution
                    final long pre_exec_time = System.nanoTime();
                    // Sorting array
                    current_sorter.sort(arrayClone);
                    // Collecting time post execution
                    final long currentExecutionTime = System.nanoTime() - pre_exec_time;
                    // Adding the execution time to the total
                    meanExecutionTime[j] += currentExecutionTime;
                    // Printing current iterations stats
//                    System.out.println("Array size: " + arrayClone.length
//                            + " - Execution time: " + currentExecutionTime + " ns"
//                            + " - Iteration: " + (iteration + 1));
                }
                // Computing the average execution time
                meanExecutionTime[j] = meanExecutionTime[j] / maxIterations;

                // Adding the execution time to the array
                System.out.println("Array size: " + generator.getArrays().get(j).length +
                        " - Average execution time: " + meanExecutionTime[j] + " ns");

                // Saving the execution times
                executionTimes.add(meanExecutionTime);
            }
            System.out.println();
        }
    }

    /**
     * Function that generates the data using Integer type
     */
    private static void createIntegerData() {
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
        VectorInitializer<Integer []> integersVectorInitializer = (int arraysAmount, int arraysStartingSize, int arraysGrow) -> {
            // Initializing the array
            Vector<Integer []> arrays = new Vector<>(arraysAmount);

            // Adding j array of type Integer, starting with size 100 and increasing by power of 10 each iteration
            for(int i = arraysStartingSize, j = 0; j < arraysAmount; i *= arraysGrow, j++)
                arrays.add(new Integer[i]);

            return arrays;
        };

        // Creating the Data Generator of type Integer
        DataGenerator<Integer> integerGenerator =
                new DataGenerator<>(integersVectorInitializer, integerArrayInitializer);

        // Generating the execution time
        generateExecutionTime(integerGenerator, "Integer");
    };

    private static void createCharacterData() {
        // Initializing generic auxiliary variables
        Random rand = new Random();

        // Initializing the data initializer
        ArrayInitializer<Character> charactersArrayInitializer = (Character[] array) -> {
            // Filling the array with random variables
            for (int i = 0; i < array.length; i++ )
                array[i] = new Character((char)(rand.nextInt(26) + 'a'));
        };

        // Initializing the vector initializer
        VectorInitializer<Character []> charactersVectorInitializer = (int arraysAmount, int arraysStartingSize, int arraysGrow) -> {
            // Initializing the array
            Vector<Character []> arrays = new Vector<>(arraysAmount);

            // Adding j array of type Integer, starting with size 100 and increasing by power of 10 each iteration
            for(int i = arraysStartingSize, j = 0; j < arraysAmount; i *= arraysGrow, j++)
                arrays.add(new Character[i]);

            return arrays;
        };

        // Creating the Data Generator of type Integer
        DataGenerator<Character> charactersGenerator =
                new DataGenerator<>(charactersVectorInitializer, charactersArrayInitializer);

        // Generating the execution time
        generateExecutionTime(charactersGenerator, "Character");
    }

    /**
     * Main function
     * @param args Arguments provided by command lines
     */
    public static void main(String[] args){
        createIntegerData();
        createCharacterData();
    }
}