import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        // write your code with StringBuilder here
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }

        for (int i = 0; i < sb.length(); i++) {
            Character c = sb.charAt(i);
            if (c.compareTo('0') >= 0 & c.compareTo('9') <= 0) {
                sb.deleteCharAt(i);
                i--;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}