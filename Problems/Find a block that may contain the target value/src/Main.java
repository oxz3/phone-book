import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
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

        while (currentRight < arrLength - 1) {
            if (currentRight == 0) {
                currentRight = Math.min(arrLength - 1, currentRight - 1 + blockSize);
            } else {
                currentRight = Math.min(arrLength - 1, currentRight + blockSize);
            }
            if (target <= inputArr[currentRight]) {
                if (lastRight == 0) {
                    System.out.println(lastRight + " " + currentRight);
                    System.exit(0);
                } else {
                    System.out.println(lastRight + 1 + " " + currentRight);
                    System.exit(0);
                }

            } else {
                lastRight = currentRight;
            }

        }
        if (target > inputArr[currentRight]) {
            System.out.println("-1");
        }
    }
}