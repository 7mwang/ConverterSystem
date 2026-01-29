import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

class Main {
    public static void main(String[] args) {

        // TODO: Build an ASCII-to-Decimal converter.
        System.out.println("Matthew to ASCII is " + Arrays.toString(AsciiToDecimal("Matthew")));

        // TODO: Build a number-base converter that converts between Binary, Decimal, and Octal.
        System.out.println("Decimal 1043 to binary is " + DecimalToBinary(1043));
        System.out.println("Binary 10000010011 to decimal is " + BinaryToDecimal("10000010011"));
        System.out.println("Octal 16 to decimal is " + OctalToDecimal(16));
        System.out.println("Decimal 14 to octal is " + DecimalToOctal(14));

        // TODO: Write a program that reads an image file and prints the pixel values.
        try {
            File image = new File("smile.png");
            BufferedImage input = ImageIO.read(image);
            int[][] result = readImagePixels(input);
            System.out.printf("Pixel Values by row for %s:%n", image.getName());
            for(int i = 0; i < result.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
        }
        catch(Exception e) {
            System.out.println("No file with this path.");
        }

        // TODO: Write a program that reads pixel values and creates an image file.

        try {
            File image = new File("knight.jpg");
            BufferedImage input = ImageIO.read(image);
            File newImage = new File("image.png");
            ImageIO.write(createImage(readImagePixels(input)), "png", newImage);
        }
        catch(Exception e) {
            System.out.println("No file with this path.");
        }
    }
    // Java stores ASCII chars as their decimal values. Casting a char to an int (explicitly or implicitly) will give you its decimal value.
    public static int[] AsciiToDecimal(String ascii) {
        char[] asciiArray = ascii.toCharArray();
        int[] decimalArray = new int[asciiArray.length];
        for (int i = 0; i < decimalArray.length; i++) {
            decimalArray[i] = asciiArray[i];
        }

        return decimalArray;
    }

    /*
    A formula for figuring out whether a bit should be 1 or 0 is used here.
    Starting from the LSB and going to the MSB, if the remainder of the number / 2 is 1, the bit is 1.
    If not, it is 0.
    We take advantage of integer division here by cutting off any decimals.
    After all the division is done, and the bits appended, we have our binary value.
    */

    public static String DecimalToBinary(int num) {
        String result = "";
        int bit;
        if (num == 0) return "0";
        while(num != 0) {
            bit = num % 2;
            result = bit + result;
            num /= 2;
        }

        return result;
    }
    /*
    As an extension of knowing how to count in binary,
    converting binary to decimal is just adding up:
    bit_value * 2 to the power of (bit position) for all bits, where LSB is "bit position 0".
     */
    public static int BinaryToDecimal(String num) {
        char[] nums = num.toCharArray();
        int decimal = 0;
        for(int i = nums.length-1; i >= 0; i--) {
            if (nums[i] == '1') decimal += (int) Math.pow(2, ((nums.length-1)-i));
        }

        return decimal;

    }
    /*
    A similar formula to the binary to decimal formula is used here.
    One difference is that I use an int because unlike binary numbers,
    octal numbers cannot have leading 0's. I wasn't sure how to find a workaround for binary.

    An added bonus is that we can use the modulo trick to separate the number into digits.
    Although the digits are collected from most to least significant, this is actually what we want.
    We then multiply each digit by 8 to the power of its position.
    Ex.
    16 -> [6, 1]
      6*8**0 = 6
    + 1*8**1 = 6+8
             = 14
     */
    public static int OctalToDecimal(int num) {
        int len = String.valueOf(num).length();
        int[] nums = new int[len];
        for(int i = 0; i < len; i++) {
            nums[i] = num % 10;
            num /= 10;
        }
        int decimal = 0;
        for(int i = 0; i < nums.length; i++) {
            decimal += (int) (nums[i]*Math.pow(8, i));
        }

        return decimal;
    }

    /*
    Basically the same formula for decimal to binary is used,
    but instead we are using 8 instead of 2.
    Ex. 14
    14 % 8 = 6
    14 / 8 = 1

    1 % 8 = 1
    1 / 8 = 0
    Digits = 1, 6
    = 16
     */
    public static int DecimalToOctal(int num) {
        String result = "";
        int bit;
        if (num == 0) return 0;
        while(num != 0) {
            bit = num % 8;
            result = bit + result;
            num /= 8;
        }

        return Integer.parseInt(result);
    }

    /*
    First, create a 2d array to store all the pixel values.
    The size is according to the size of the image.
    Then, loop through the entire image/number of pixels and get the rgb (argb) value for each pixel
    Add each pixel value to the array such that by the loop, the (x,y) value of the pixel matches values[y][x]
     */
    public static int[][] readImagePixels(BufferedImage path) {
        int width = path.getWidth();
        int height = path.getHeight();
        int[][] values = new int[height][width];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                values[y][x] = path.getRGB(x,y);
            }
        }
        return values;
    }

    /*
    Traverse the image in the same way as the previous method,
    but this time take an input of pixel values in a 2d array,
    and then set the (a)rgb values for each pixel (x,y) to the corresponding value in pixels[y][x]
     */
    public static BufferedImage createImage(int[][] pixels) {
        int width = pixels[0].length;
        int height = pixels.length;
        BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setRGB(x, y, pixels[y][x]);
            }
        }
        return image;
    }
}