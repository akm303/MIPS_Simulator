package group1.mips_simulator;

import java.util.Vector;

public class Utility {


    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidBinary(String str) {
        for (char c : str.toCharArray()) {
            if ((c != ' ') && (c != '1') && (c != '0')) {
                // if it's not a space
                // AND it's not a 1
                // AND it's not a 0
                return false;
            }
            // else continue
        }
        return true; // All characters are 0, 1 or ' ' whitespace
    }


    public static boolean isValidOctal(String str) {
        return str.replace(" ", "").matches("[0-7]*");
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

    public static String binaryStrToOctalStr(String binaryStr) {
        binaryStr = binaryStr.replace(" ", "");
        int binaryInt = binaryToInt(binaryStr);
        return Integer.toOctalString(binaryInt);
    }

    public static String shortToBinaryString(short value, int minSize) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(0xFFFF & value));
        while (sb.length() < minSize) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    public static String shortToOctalString(short value,int size){
        String rstring = binaryStrToOctalStr(shortToBinaryString(value,16));
        while(rstring.length() < size)
            rstring = "0" + rstring;
        return rstring;
    }

    public static String shortToBinaryString_Pretty(short value, int minSize) {
        int bitMask = 0b1111_1111_1111_1111;
        if (minSize == 4) {
            bitMask = 0b1111;
        }
        if (minSize == 12) {
            bitMask = 0b1111_1111_1111;
        }
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(bitMask & value));
        while (sb.length() < minSize) {
            sb.insert(0, '0');
        }
        for (int i = sb.length() - 4; i >= 0; i = i - 4) {
            sb.insert(i, ' ');
        }
        return sb.toString().trim().replace(' ', '_');
    }


    public static String binaryNot(String binaryValue) {
        StringBuilder result = new StringBuilder();
        for(char c : binaryValue.toCharArray()) {
            if(c == '0') {
                result.append('1');
            } else if (c == '1') {
                result.append('0');
            }
        }
        return result.toString();
    }

    public static Vector<short[]> octalStringVector_ToShort(Vector<String[]> stringVector) {
        // convert Vector of two octal strings into a Vector of two shorts
        Vector<short[]> rVector = new Vector<>();

        for (String[] octalString : stringVector) {
            short val1 = Utility.binaryToShort(Utility.octalStringToBinaryString(octalString[0]));
            short val2 = Utility.binaryToShort(Utility.octalStringToBinaryString(octalString[1]));
            short[] line = new short[]{val1, val2};
            rVector.add(line);
        }
        return rVector;
    }

    public static String intTo32BitString(int value) {
        StringBuilder result = new StringBuilder(Integer.toBinaryString(value));
        while (result.length() < 32) {
            result.insert(0, '0');
        }
        return result.toString();
    }

    public static String rotateLeftOne(String bitStr) {
        char leftmostChar = bitStr.toCharArray()[0];
        StringBuilder result = new StringBuilder();
        result.append(bitStr.substring(1));
        result.append(leftmostChar);

        return result.toString();
    }

    public static String rotateRightOne(String bitStr) {
        int lastIndex = bitStr.length() - 1;
        char rightmostChar = bitStr.toCharArray()[lastIndex];
        StringBuilder result = new StringBuilder();
        result.append(rightmostChar);
        result.append(bitStr.substring(0, lastIndex));
        return result.toString();
    }

}