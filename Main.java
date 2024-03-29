
import java.text.ParseException;
import java.util.Scanner;

/**
 * Main class of Library_Managment_2.0
 *
 * @author Hessam Hosseinian
 */

public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);
        String input = new String();
        CommandManeger comandManeger = new CommandManeger();

        while (!input.equals("finish")) {

            input = scanner.nextLine();

            // if (input.equals("res")) { // this is for testing the code by myself

            // comandManeger.res();
            // }

            comandManeger.input(input);

        }

        scanner.close();
    }
}
