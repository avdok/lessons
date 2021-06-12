import java.util.Arrays;
import java.util.Random;

public class ArrayDemo {

    public static void main(String[] args) {
        int size = 50;


        int[] myArray = genRandomArray(size);
        System.out.println("Создан массив псевдослучайных чисел от 10 до 25000:");
        System.out.println(Arrays.toString(myArray));

        int[] myNewArray = {-10, -3, 0, 2, 5, 15, 100};
//        int[] myNewArray = {8953, 2430, 6638, 4783, 16192, 3648, 630, 21936, 11397, 20761, 10695, 3358};
        System.out.println("Сортировка массива: ");
        //System.out.println(Arrays.toString(myArray));
        System.out.print("Отсортированный массив   (методом пузырька): ");
        System.out.println(Arrays.toString(sortBubble(myArray)));

        System.out.print("Отсортированный массив    (методом вставки): ");
        System.out.println(Arrays.toString(sortInsertion(myArray)));

        System.out.print("Отсортированный массив    (методом слияния): ");
        System.out.println(Arrays.toString(sortMerge(Arrays.copyOf(myArray, myArray.length))));
//        System.out.println(Arrays.toString(sortMerge(myArray)));

        System.out.print("Отсортированный массив (быстрая сортировка): ");
        int[] arrayForQSort = Arrays.copyOf(myArray, myArray.length);
//        int[] arrayForQSort = Arrays.copyOf(myNewArray, myNewArray.length);

        sortQuick(arrayForQSort, 0, arrayForQSort.length - 1);
        System.out.println(Arrays.toString(arrayForQSort));

        System.out.print("Бинарный поиск в массив элемента " + arrayForQSort[13] + ": ");
        int indexFound = binaryFind(arrayForQSort, arrayForQSort[13], 0, arrayForQSort.length-1);
        if (indexFound >= 0) {
            System.out.println(" индекс найденного элемента " + indexFound);
        } else {
            System.out.println("Элемент не найден");
        }


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
        if (min >= max) {
            return;
        }
        int i = min;
        int j = max;
        int m = ( (max - min) / 2 ) + min;
        int p = array[m];
        int temp;

        while (i <= j) {

            while (array[i] < p) {
                i++;
            }
            while (array[j] > p) {
                j--;
            }

            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }

        }
        if ( (max - min) <= 1) {
            return;
        }
        if (j > 0) {
            sortQuick(array, min, j);
        }
        if (i < array.length) {
            sortQuick(array, i, max);
        }
    }

    /**
     * Бинарный поиск
     */
    static int binaryFind(int[] array, int findObj, int min, int max) {
        int result;
        int indexp = ((max - min) / 2 ) + min;

        if ( (min == max) & (findObj != array[min])) {
            return -1;
        }

        if (findObj == array[indexp]) {
            return indexp;
        } else if (findObj > array[indexp]) {
            return binaryFind(array, findObj, indexp + 1, max);
        } else if (findObj < array[indexp]) {
            return binaryFind(array, findObj, min, indexp -1);
        } else {
            return -1;
        }

    }
}
