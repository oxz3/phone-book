import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner keyboard = new Scanner(System.in);

        int arrLength = keyboard.nextInt();
        int jumpLength = (int) Math.sqrt(arrLength);

        StringBuilder output = new StringBuilder();
        if (arrLength == 0) {
            output.append("0");
        }

        for (int i = 0; i < arrLength; i++) {
            if (i == 0 || i % jumpLength == 0) {
                if (i == 0) {
                    output.append("1 ");
                } else {
                    int multiple = i / jumpLength + 1;
                    output.append(multiple);
                    output.append(" ");
                }
            } else {
                int multiple = i / jumpLength + 1;
                int diff = 0;

                if (multiple * jumpLength >= arrLength) {
                    diff = arrLength - 1 - i;
                } else {
                    diff = multiple * jumpLength - i;
                }
                output.append(multiple + diff + 1);
                output.append(" ");
            }
        }

        System.out.println(output);
    }
}