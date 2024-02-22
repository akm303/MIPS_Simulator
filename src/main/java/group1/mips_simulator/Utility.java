package group1.mips_simulator;


public class Utility {
    public static final int WORD_SIZE = 16;

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String octalStringToBinaryString(String octal) {
        StringBuilder result = new StringBuilder();
        for (char c : octal.toCharArray()) {
            switch (c) {
                case '0' -> result.append("000");
                case '1' -> result.append("001");
                case '2' -> result.append("010");
                case '3' -> result.append("011");
                case '4' -> result.append("100");
                case '5' -> result.append("101");
                case '6' -> result.append("110");
                case '7' -> result.append("111");
                default -> throw new IllegalArgumentException("Octal number contains invalid character: " + octal);
            }
        }
        return result.toString();
    }

    /**
     * Convert the octal string number into a binary number representation of the
     * target size. 0s will be padded to hit the target, or only the 'targetSize'
     * least significant bits will be returned.
     * IE: The most significant bits will cut in that case.
     */
    public static String octalStringToBinaryString(String octal, int targetSize) {
        StringBuilder binary = new StringBuilder(octalStringToBinaryString(octal));

        if (binary.length() == targetSize) {
            // Just right
            return binary.toString();
        } else if (binary.length() < targetSize) {
            // Too small
            while (binary.length() < targetSize) {
                binary.insert(0, "0");
            }
        }
        // else too large
        // Truncate the leading bits
        int startIndex = binary.length() - targetSize;
        return binary.substring(startIndex);
    }

    public static int binaryToInt(String binary) {
        // Strip all un-expected characters
        binary = binary.replaceAll("[^10]", "");

        int result = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            char currentBit = binary.charAt(i);
            if (currentBit == '1') {
                // If current bit == 1
                int powerOfTwo = binary.length() - i - 1;
                result += (1 << powerOfTwo);
            }
            // Else current bit == 0
        }
        return result;
    }

    public static short binaryToShort(String binary) {
        return (short) binaryToInt(binary);
    }
}