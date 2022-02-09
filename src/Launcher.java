import java.util.Scanner;
public class Launcher {
    public static void main(String[] args)
    {
        System.out.println("Welcome!");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();
        if (!("quit".equals(cmd)))
        {
            System.out.println("Unknown command");
        }
    }
}
