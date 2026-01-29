import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome! Pick an operation.");
        System.out.println("1) ASCII to Decimal");
        System.out.println("2) Decimal to Binary");
        System.out.println("3) Binary to Decimal");
        System.out.println("4) Decimal to Octal");
        System.out.println("5) Octal to Decimal");
        System.out.println("6) Binary to Octal");
        System.out.println("7) Octal to Binary");
        System.out.println("8) Read and print pixel values for an image");
        System.out.println("9) Create your own image");
        System.out.println("0) Quit");

        switch(sc.nextInt()) {
            case 1:
                System.out.println("Enter your ASCII");
                System.out.print("...to Decimal is: " + Arrays.toString(AsciiToDecimal(sc.next())));
                return;
            case 2:
                System.out.println("Enter your Decimal");
                System.out.print("...to Binary is: " + DecimalToBinary(sc.nextInt()));
                return;
            case 3:
                System.out.println("Enter your Binary");
                System.out.print("...to Decimal is: " + BinaryToDecimal(sc.next()));
                return;
            case 4:
                System.out.println("Enter your Decimal");
                System.out.print("...to Octal is: " + DecimalToOctal(sc.nextInt()));
                return;
            case 5:
                System.out.println("Enter your Octal");
                System.out.print("...to Decimal is: " + OctalToDecimal(sc.nextInt()));
                return;
            case 6:
                System.out.println("Enter your Binary");
                System.out.print("...to Octal is: " + BinaryToOctal(sc.next()));
                return;
            case 7:
                System.out.println("Enter your Octal");
                System.out.print("...to Binary is: " + OctalToBinary(sc.nextInt()));
                return;
            case 8:
                System.out.println("Enter your file path");
                try {
                    File image = new File(sc.next());
                    BufferedImage input = ImageIO.read(image);
                    int[][] result = readImagePixels(input);
                    System.out.printf("Pixel Values by row for %s:%n", image.getName());
                    for(int i = 0; i < result.length; i++) {
                        System.out.println(Arrays.toString(result[i]));
                    }
                    return;
                }
                catch(Exception e) {
                    System.out.println("No file exists with this path.");
                    return;
                }
            case 9:
                System.out.println("Input your desired file name, followed by your pixel width, height, and pixel values");
                System.out.print("File name: ");
                String fileName = sc.next();
                System.out.print("Width: ");
                int width = sc.nextInt();
                System.out.print("Height: ");
                int height = sc.nextInt();
                System.out.println("Pixels (left to right, up to down): ");
                int[][] pixels = new int[height][width];
                for(int y = 0; y < height; y++) {
                    for(int x = 0; x < width; x++) {
                        pixels[y][x] = sc.nextInt();
                    }
                }
                try {
                    File newImage = new File(fileName + ".png");
                    ImageIO.write(createImage(pixels), "png", newImage);
                    return;
                }
                catch(Exception e) {
                    System.out.println("Image write failed.");
                    return;
                }
            case 0:

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

    // Convert binary to decimal, then decimal to octal

    public static int BinaryToOctal(String num) {
        return DecimalToOctal(BinaryToDecimal(num));
    }

    // Convert octal to decimal, then decimal to binary

    public static String OctalToBinary(int num) {
        return DecimalToBinary(OctalToDecimal(num));
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