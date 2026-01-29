import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
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

    public static int[] AsciiToDecimal(String ascii) {
        char[] asciiArray = ascii.toCharArray();
        int[] decimalArray = new int[asciiArray.length];
        for (int i = 0; i < decimalArray.length; i++) {
            decimalArray[i] = asciiArray[i];
        }

        return decimalArray;
    }

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

    public static int BinaryToDecimal(String num) {
        char[] nums = num.toCharArray();
        int decimal = 0;
        for(int i = nums.length-1; i >= 0; i--) {
            if (nums[i] == '1') decimal += (int) Math.pow(2, ((nums.length-1)-i));
        }

        return decimal;

    }

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

    public static int[][] readImagePixels(BufferedImage path) {
        int width = path.getWidth();
        int height = path.getHeight();
        // int totalpixels = 0;
        int[][] values = new int[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                // totalpixels++;
                values[i][j] = path.getRGB(j,i);
                // System.out.printf("Value: %d x: %d y: %d %n", values[i][j], i, j);
            }
        }
        // System.out.println(totalpixels);
        return values;
    }

    public static BufferedImage createImage(int[][] pixels) {
        BufferedImage image = new BufferedImage(pixels[0].length, pixels.length, TYPE_INT_ARGB);

        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[0].length; j++) {
                image.setRGB(j, i, pixels[i][j]);
            }
        }
        return image;
    }
}