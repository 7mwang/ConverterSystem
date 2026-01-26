import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(AsciiToDecimal("Hello")));

        System.out.println(DecimalToBinary(1043)); // 10000010011

    }

    public static int[] AsciiToDecimal(String ascii) {
        char[] asciiArray = ascii.toCharArray();
        int[] decimalArray = new int[asciiArray.length];
        for (int i = 0; i < decimalArray.length; i++) {
            decimalArray[i] = (int) asciiArray[i];
        }

        return decimalArray;
    }

    public static String DecimalToBinary(int num) {
        String result = "";
        int bit;
        while(num != 0) {
            bit = num % 2;
            result = bit + result;
            num /= 2;
        }

        return result;
    }

}
/*
513 / 2 = 256 rem 1
256 / 2 = 128 rem 0
128 / 2 = 64 rem 0
64 / 2 = 32 rem 0
32 / 2 = 16 rem 0
16 / 2 = 8 rem 0
8 / 2 = 4 rem 0
4 / 2 = 2 rem 0
2 / 2 = 1 rem 0
1 / 2 = 0 rem 1
1000000001
 */