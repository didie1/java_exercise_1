import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Fibo implements Command{

    @Override
    public String name() {
        return "fibo";
       }

    @Override
    public boolean run(Scanner scan) {
        String input;
        System.out.println("Please type in a figure for the fibonnaci series");
        input = scan.nextLine();
        int input_number = parseInt(input);
        if ( input_number < 0)
        {
            throw new IllegalArgumentException("Can't be negative");
        }
        int n1 = 0, n2 = 1, result = 0;
        for (int i = 0; i < input_number -1; ++i)
        {
            result = n1 + n2;
            n1 = n2;
            n2 = result;
        }
        System.out.println("The result of the fibonnaci series on "+ input_number + " is : " + result);
        return false;
    }
}
