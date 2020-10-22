import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner keyboard = new Scanner(System.in);
        int arrLength = keyboard.nextInt();
        int[] inputArr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            inputArr[i] = keyboard.nextInt();
        }
        int target = keyboard.nextInt();

        int blockSize = (int) Math.sqrt(arrLength);

        int currentRight = 0;
        int lastRight = 0;
        int count = 1;

        if (target == inputArr[currentRight]) {
            System.out.println(count);
            System.exit(0);
        }
        while (currentRight < arrLength - 1) {
            currentRight = Math.min(arrLength - 1, currentRight + blockSize);
            if (target <= inputArr[currentRight]) {
                for (int i = currentRight; i > lastRight; i--) {
                    count++;
                    if (target == inputArr[i]) {
                        System.out.println(count);
                        System.exit(0);
                    } else if (target > inputArr[i]) {
                        System.out.println(count);
                        System.exit(0);
                    } else if (target < inputArr[lastRight + 1] && i == lastRight + 1) {
                        System.out.println(count);
                        System.exit(0);
                    }
                }
            } else if (target > inputArr[currentRight] && currentRight == arrLength - 1) {
                System.out.println(count + 1);
                System.exit(0);
            } else {
                lastRight = currentRight;
                count++;
            }

        }

    }
}