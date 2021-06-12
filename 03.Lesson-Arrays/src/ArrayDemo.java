import java.util.Arrays;
import java.util.Random;

public class ArrayDemo {

    public static void main(String[] args) {
        int size = 50;


        int[] myArray = genRandomArray(size);
        System.out.println("Создан массив псевдослучайных чисел от 10 до 25000:");
        System.out.println(Arrays.toString(myArray));

//        int[] myNewArray = {5, 3, 2, 0, -10, 100, 1};
        int[] myNewArray = {5, 3, 2, 0, -10};
        System.out.println("Сортировка массива: ");
        //System.out.println(Arrays.toString(myArray));
        System.out.print("Отсортированный массив (методом пузырька): ");
        System.out.println(Arrays.toString(sortBubble(myArray)));

        System.out.print("Отсортированный массив  (методом вставки): ");
        System.out.println(Arrays.toString(sortInsertion(myArray)));

        System.out.print("Отсортированный массив  (методом слияния): ");
        System.out.println(Arrays.toString(sortMerge(Arrays.copyOf(myArray, myArray.length))));
//        System.out.println(Arrays.toString(sortMerge(myArray)));

        System.out.print("Отсортированный массив (быстрая сортировка): ");
//        int[] arrayForQSort = Arrays.copyOf(myArray, myArray.length);
        int[] arrayForQSort = Arrays.copyOf(myNewArray, myNewArray.length);

        sortQuick(arrayForQSort, 0, arrayForQSort.length - 1);
        System.out.println(Arrays.toString(arrayForQSort));


    }



    /**
     * Метод создает массив случайных чисел от 10 до 25000
     * @param arraySize размер массива
     * @return int[]
     */
    static private int[] genRandomArray(int arraySize) {

        Random randomno = new Random();
        int[] result = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            result[i] = 10 + randomno.nextInt(24991);
        }
        return result;
    }

    /**
     * Метод сортирует массив методом пузырька
     */
    static private int[] sortBubble(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        int finI = result.length - 1;
        boolean modified;

        for (int j = 0; j < result.length - 1; j++) {
            modified = false;
            for (int i = 0; i < finI; i++) {
                if (result[i] > result[i+1]) {
                    modified = true;
                    int a = result[i];
                    result[i] = result[i+1];
                    result[i+1] = a;
                }
            }
            finI--;
            if (!modified) {
                break;
            }
        }
        return result;

    }

    /**
     * Метод сортировки вставками
     */
    static int[] sortInsertion(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        int i, key;

        for (int j = 1; j < result.length; j++) {
            key = result[j];
            i = j - 1;
            while (i >= 0 && result[i] > key ) {
                result[i+1] = result[i];
                i = i - 1;
            }
            result[i+1] = key;
        }
        return result;
    }

    /**
     * Метод сортировки слиянием
     */
    static int[] sortMerge(int[] array) {
        int[] A, B;

        if (array.length <= 1) {
            return array;
        }

        A = Arrays.copyOf( array, (array.length/2) );
        B = Arrays.copyOfRange(array, (array.length/2), array.length);

        A = sortMerge(A);
        B = sortMerge(B);

        int maxI = A.length + B.length;
        int[] sortedAB = new int[maxI];
        int indexA = 0, indexB = 0;
        for (int i = 0; i < maxI; i++) {
            if (indexA >= A.length) {
                sortedAB[i] = B[indexB];
                indexB++;
            } else if (indexB >= B.length) {
                sortedAB[i] = A[indexA];
                indexA++;
            } else if (A[indexA] < B[indexB]) {
                sortedAB[i] = A[indexA];
                indexA++;
            } else {
                sortedAB[i] = B[indexB];
                indexB++;
            }
        }

        return sortedAB;
    }

    /**
     * Быстрая сортировка
     */
    static void sortQuick(int[] array, int min, int max) {
        if (min == max) {
            return;
        }
//        int[] result = Arrays.copyOf(array, array.length);
        int i = min;
        int j = max;
        int m = (max - min) / 2;
        int temp;

        while (i < j) {

            if (array[i] < array[m]) {
                i++;
            } else if (array[j] >= array[m]) {
                j--;
            } else {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }
        sortQuick(array, min, m);
        sortQuick(array, m, max);

    }
}
