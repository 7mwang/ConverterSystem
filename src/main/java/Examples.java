import java.util.Scanner;

public class Examples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = sc.next();
        String output = "";
        System.out.printf("Hello, %s", name);
        System.out.println();
        for (int i = 0; i < name.length(); i++) {
            int decNum = (int) name.charAt(i);
            output += decNum;
            if (i != name.length()-1) output += ", ";
        }
        System.out.println(output);
        sc.close();
    }
    // [77, 97, 116, 116, 104, 101, 119]
}
