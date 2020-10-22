import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);
        int arrSize = Integer.parseInt(keyboard.nextLine());
        String input = keyboard.nextLine();
        String[] inputArr = input.split(" ");
        int[] intArr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            intArr[i] = Integer.parseInt(inputArr[i]);
        }

        for (int i = 0; i < arrSize; i++) {
            int index = Arrays.binarySearch(intArr, intArr[i]);
            if (index == intArr[i]) {
                System.out.print("true");
                System.exit(0);
            }
        }
        System.out.print("false");
    }
}