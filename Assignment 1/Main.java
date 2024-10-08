import java.lang.reflect.Array;
import java.util.Random;

public class Main {


    public static void main(String args[]){

        Integer[] array = new Integer[10];
        Random rand = new Random();


        for (int i = 0; i < array.length; i++ ){
            array[i] = new Integer(rand.nextInt(30));
            System.out.println(array[i].toString());
        }
    }
}
