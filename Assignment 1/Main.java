import java.util.Random;

/**
 * Main Class
 */
public class Main {

    /**
     * Main function
     * @param args Arguments provided by command lines
     */
    public static void main(String[] args){

        Integer[] array = new Integer[10];
        Random rand = new Random();

        for (int i = 0; i < array.length; i++ ){
            array[i] = rand.nextInt(1000);
            System.out.println(array[i].toString());
        }
    }
}
