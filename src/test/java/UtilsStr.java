import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class UtilsStr {


    static String sb(String str, String from, String to) {
        if (str == null || from == null || to == null) {
            return null;
        }

        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] fromBytes = from.getBytes(StandardCharsets.UTF_8);
        byte[] toBytes = to.getBytes(StandardCharsets.UTF_8);

        int startIndex = indexOf(bytes, fromBytes, 0);
        if (startIndex == -1) {
            return null;
        }
        startIndex += fromBytes.length;

        int endIndex = indexOf(bytes, toBytes, startIndex);
        if (endIndex == -1) {
            return null;
        }

        byte[] resultBytes = Arrays.copyOfRange(bytes, startIndex, endIndex);
        return new String(resultBytes, StandardCharsets.UTF_8);
    }

    private static int indexOf(byte[] array, byte[] target, int fromIndex) {
        if (target.length == 0) {
            return fromIndex;
        }
        if (target.length > array.length) {
            return -1;
        }

        int[] a = new int[256];
        for (int i = 0; i < target.length; i++) {
            a[target[i] & 0xFF] = i;
        }

        int m = target.length;
        int n = array.length;

        int s = fromIndex;
        while (s <= n - m) {
            int j = m - 1;
            while (j >= 0 && target[j] == array[s + j]) {
                j--;
            }
            if (j < 0) {
                return s;
            } else {
                s += Math.max(1, j - a[array[s + j] & 0xFF]);
            }
        }
        return -1;
    }
}
