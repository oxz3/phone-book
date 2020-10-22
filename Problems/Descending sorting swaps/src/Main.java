import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner keyboard = new Scanner(System.in);
        String[] inputStr = keyboard.nextLine().split(" ");
        int[] inputArr = new int[inputStr.length];
        for (int i = 0; i < inputStr.length; i++) {
            inputArr[i] = Integer.valueOf(inputStr[i]);
        }

        int count = 0;
        for (int i = 0; i < inputArr.length - 1; i++) {
            for (int j = 0; j < inputArr.length - i - 1; j++) {
                if (inputArr[j] < inputArr[j + 1]) {
                    int temp = inputArr[j];
                    inputArr[j] = inputArr[j + 1];
                    inputArr[j + 1] = temp;
                    count++;
                }
            }
        }
        System.out.print(count);
    }
}