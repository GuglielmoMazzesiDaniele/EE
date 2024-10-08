public class Main {

    public static void main(String[] args) {
        // Configuration fields
        final int rows = 1000;
        final int columns = 1000;

        // Auxiliary fields
        float average_exec_time = 0;
        final byte data_to_insert = (byte) 1;

        // Initializing the data
        byte[][] matrix = new byte[rows][columns];

        // Filling the matrix
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                final long pre_exec_time = System.nanoTime();
                matrix[i][j] = data_to_insert;
                final long difference = System.nanoTime() - pre_exec_time;
                average_exec_time += difference;
                if(i == 0 && j == 0)
                    System.out.println("Hello\n");
                if (i == 100 && j == 100)
                    System.out.println("Hello\n");
            }
        }

        // Computing the average insertion time
        average_exec_time = average_exec_time / (columns * rows);

        // Printing the average executionion time in nanoseconds
        System.out.println("The average insertion time is " + average_exec_time + " nanoseconds\n");

        System.out.println("Cell value: " + matrix[0][0]);
    }
}