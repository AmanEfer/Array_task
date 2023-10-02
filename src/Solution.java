import java.util.Arrays;

public class Solution {
    public static int[][] array;

    public static void main(String[] args) {
        array = new int[6][4];
        init(array);
        print(array);
        System.out.println("Минимальная сумма элементов строки массива в строке №" + minSumRowNumber(array));
        System.out.println("Максимальная сумма элементов столбца массива в столбце №" + maxSumColumnNumber(array));
    }

    public static void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < 0)
                    System.out.print(array[i][j] + "\t");
                else
                    System.out.print(" " + array[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }

    public static void init(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) (Math.random() * 101) - 50;
            }
        }
    }

    private static int checkIndex(int[] array, int value) {
        int positionNumber = 0;
        for (int i = 0; i < array.length; i++) {
            if (value == array[i])
                positionNumber = i;
        }
        return positionNumber;
    }

    private static int maxSum(int[][] array, int column) {
        int[][] arrayCopy = new int[array.length][];
        for (int i = 0; i < arrayCopy.length; i++) {
            arrayCopy[i] = Arrays.copyOf(array[i], array[i].length);
        }
        boolean isExit = true;
        while (isExit) {
            isExit = false;
            for (int i = 0; i < arrayCopy.length - 1; i++) {
                if (arrayCopy[i][column] > arrayCopy[i + 1][column]) {
                    int temp = arrayCopy[i][column];
                    arrayCopy[i][column] = arrayCopy[i + 1][column];
                    arrayCopy[i + 1][column] = temp;
                    isExit = true;
                }
            }
        }
        int lastElement = arrayCopy.length - 1;
        return arrayCopy[lastElement][column] + arrayCopy[lastElement - 1][column];
    }

    public static int maxSumColumnNumber(int[][] array) {
        int[] result = new int[array[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = maxSum(array, i);
        }
        int max = Integer.MIN_VALUE;
        for (int element : result) {
            max = Math.max(element, max);
        }
        return checkIndex(result, max);
    }

    public static int minSumRowNumber(int[][] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int[] arrayCopy = Arrays.copyOf(array[i], array[i].length);
            Arrays.sort(arrayCopy);
            result[i] = arrayCopy[0] + arrayCopy[1];
        }
        int min = Integer.MAX_VALUE;
        for (int element : result) {
            min = Math.min(element, min);
        }
        return checkIndex(result, min);
    }
}