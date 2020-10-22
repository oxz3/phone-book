import java.util.*;

public class Main {

    public static void moveThePivot(int[] array, int pivotIndex) {
        // write your code here
        int right = array.length - 1;  // choose the rightmost element as the pivot
        int left = 0;
        int partitionIndex = left; // the first element greater than the pivot
        swap(array, pivotIndex, right);
        /* move large values into the right side of the array */
        for (int i = left; i < right; i++) {
            if (array[i] <= array[right]) { // may be used '<' as well
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right); // put the pivot on a suitable position

    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int pivotIndex = scanner.nextInt();
        moveThePivot(array, pivotIndex);
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }
}