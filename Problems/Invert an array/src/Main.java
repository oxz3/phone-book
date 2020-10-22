// do not remove imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class ArrayUtils {
    // define invert method here
    static <T> T[] invert(T[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            T t = array[i];
            int j = array.length - 1 - i;
            array[i] = array[j];
            array[j] = t;
        }
        return array;
    }
}