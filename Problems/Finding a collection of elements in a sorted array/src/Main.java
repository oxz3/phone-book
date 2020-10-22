import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);
        int sortedArrSize = keyboard.nextInt();
        int[] sortedArr = new int[sortedArrSize];
        for (int i = 0; i < sortedArrSize; i++) {
            sortedArr[i] = keyboard.nextInt();
        }

        int searchArrSize = keyboard.nextInt();
        int[] searchArr = new int[searchArrSize];
        for (int i = 0; i < searchArrSize; i++) {
            searchArr[i] = keyboard.nextInt();
        }

        for (int i = 0; i < searchArrSize; i++) {
            int index = Arrays.binarySearch(sortedArr, searchArr[i]);
            if (index >= 0) {
                index++;
            } else {
                index = -1;
            }
            String output = String.format("%d ", index);
            System.out.print(output);
        }
    }
}