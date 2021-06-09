import java.util.Arrays;
import java.util.Random;

public class ArrayDemo {

    public static void main(String[] args) {
        int size = 50;


        int[] myArray = genRandomArray(size);
        System.out.println("Создан массив случайных чисел от 10 до 25000:");
        System.out.println(Arrays.toString(myArray));



    }

    static private int[] genRandomArray(int arraySize) {

        Random randomno = new Random();
        int[] result = new int[arraySize];

        for (int i = 0; i < 50; i++) {
            result[i] = 10 + randomno.nextInt(24991);
        }
        return result;
    }


}
