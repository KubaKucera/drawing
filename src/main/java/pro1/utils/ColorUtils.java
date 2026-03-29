package pro1.utils;

import java.util.Random;

public class ColorUtils {
    private static final Random random = new Random();

    public static String randomColor() {
        return String.format("#%06X", random.nextInt(0xFFFFFF + 1));
    }

    public static String colorToHex(int r, int g, int b) {
        return String.format("#%02X%02X%02X", r, g, b);
    }
}
