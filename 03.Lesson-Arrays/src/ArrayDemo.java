import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.text.DecimalFormat;

public class ArrayDemo {

    public static void main(String[] args) {
        int size = 50;

        me1();
        return;

//        int[] myArray = genRandomArray(size);
//        System.out.println("Создан массив псевдослучайных чисел от 10 до 25000:");
//        System.out.println(Arrays.toString(myArray));
//
//        int[] myNewArray = {-10, -3, 0, 2, 5, 15, 100};
////        int[] myNewArray = {8953, 2430, 6638, 4783, 16192, 3648, 630, 21936, 11397, 20761, 10695, 3358};
//        System.out.println("Сортировка массива: ");
//        //System.out.println(Arrays.toString(myArray));
//        System.out.print("Отсортированный массив   (методом пузырька): ");
//        System.out.println(Arrays.toString(sortBubble(myArray)));
//
//        System.out.print("Отсортированный массив    (методом вставки): ");
//        System.out.println(Arrays.toString(sortInsertion(myArray)));
//
//        System.out.print("Отсортированный массив    (методом слияния): ");
//        System.out.println(Arrays.toString(sortMerge(Arrays.copyOf(myArray, myArray.length))));
////        System.out.println(Arrays.toString(sortMerge(myArray)));
//
//        System.out.print("Отсортированный массив (быстрая сортировка): ");
//        int[] arrayForQSort = Arrays.copyOf(myArray, myArray.length);
////        int[] arrayForQSort = Arrays.copyOf(myNewArray, myNewArray.length);
//
//        sortQuick(arrayForQSort, 0, arrayForQSort.length - 1);
//        System.out.println(Arrays.toString(arrayForQSort));
//
//        System.out.print("Бинарный поиск в массив элемента " + arrayForQSort[13] + ": ");
//        int indexFound = binaryFind(arrayForQSort, arrayForQSort[13], 0, arrayForQSort.length - 1);
//        if (indexFound >= 0) {
//            System.out.println(" индекс найденного элемента " + indexFound);
//        } else {
//            System.out.println("Элемент не найден");
//        }
//
//        System.out.print("Отсортированный массив от большего к меньшему (быстрая сортировка): ");
//        int[] arrayForQSortReverse = Arrays.copyOf(myArray, myArray.length);
//        sortQuick(arrayForQSortReverse, 0, arrayForQSortReverse.length - 1, false);
//        System.out.println(Arrays.toString(arrayForQSortReverse));
//
//        benchmark(100000);
////        benchmark(10000000);
////        benchmark(100000000);
//

    }

    /**
     * Бенчмарк
     */
    static void benchmark(int size) {
        long startTime, endTime;
        DecimalFormat df = new DecimalFormat("###,###");

        System.out.println("===================================================================");
        System.out.println("Бенчмарк сортировок для массива " + df.format(size) + " элементов:");
        int[] myArray10m = genRandomArray(size);

        System.out.print("Метод пузырька: ");
        startTime = System.currentTimeMillis();
        sortBubble(myArray10m);
        endTime = System.currentTimeMillis();
        System.out.println(df.format(endTime - startTime) + " мс");

        System.out.print("Метод вставки: ");
        startTime = System.currentTimeMillis();
        sortInsertion(myArray10m);
        endTime = System.currentTimeMillis();
        System.out.println(df.format(endTime - startTime) + " мс");

        System.out.print("Метод слияния: ");
        startTime = System.currentTimeMillis();
        sortMerge(myArray10m);
        endTime = System.currentTimeMillis();
        System.out.println(df.format(endTime - startTime) + " мс");

        System.out.print("Быстрая сортировка: ");
        startTime = System.currentTimeMillis();
        sortQuick(myArray10m, 0, myArray10m.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println(df.format(endTime - startTime) + " мс");
    }

    /**
     * Метод создает массив случайных чисел от 10 до 25000
     *
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
                if (result[i] > result[i + 1]) {
                    modified = true;
                    int a = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = a;
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
            while (i >= 0 && result[i] > key) {
                result[i + 1] = result[i];
                i = i - 1;
            }
            result[i + 1] = key;
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

        A = Arrays.copyOf(array, (array.length / 2));
        B = Arrays.copyOfRange(array, (array.length / 2), array.length);

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
        int m = ((max - min) / 2) + min;
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
        if ((max - min) <= 1) {
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
     * Быстрая сортировка c направлением
     */
    static void sortQuick(int[] array, int min, int max, boolean asc) {
        if (min >= max) {
            return;
        }
        int i = min;
        int j = max;
        int m = ((max - min) / 2) + min;
        int p = array[m];
        int temp;

        while (i <= j) {
            //если сортировка от меньшего к большему
            if (asc) {
                while (array[i] < p) {
                    i++;
                }
                while (array[j] > p) {
                    j--;
                }
                //если сортировка от большего к меньшему
            } else {
                while (array[i] > p) {
                    i++;
                }
                while (array[j] < p) {
                    j--;
                }
            }

            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }

        }
        if ((max - min) <= 1) {
            return;
        }
        if (j > 0) {
            sortQuick(array, min, j, asc);
        }
        if (i < array.length) {
            sortQuick(array, i, max, asc);
        }
    }

    /**
     * Бинарный поиск
     */
    static int binaryFind(int[] array, int findObj, int min, int max) {
        int result;
        int indexp = ((max - min) / 2) + min;

        if ((min == max) & (findObj != array[min])) {
            return -1;
        }

        if (findObj == array[indexp]) {
            return indexp;
        } else if (findObj > array[indexp]) {
            return binaryFind(array, findObj, indexp + 1, max);
        } else if (findObj < array[indexp]) {
            return binaryFind(array, findObj, min, indexp - 1);
        } else {
            return -1;
        }

    }

    static void me1() {
        int[] mass = genRandomArray(10);
        int temp;
        System.out.println(Arrays.toString(mass));

        for (int i = mass.length - 1; i >= mass.length / 2; i--) {
            temp = mass[i];
            mass[i] = mass[mass.length - i];
            mass[mass.length - i] = temp;
        }
        System.out.println(Arrays.toString(mass));


    }


}
