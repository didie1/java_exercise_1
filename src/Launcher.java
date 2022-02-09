import java.util.Scanner;
public class Launcher {
    public static void main(String[] args)
    {
        System.out.println("Welcome!");
        Scanner scan = new Scanner(System.in);
        boolean stop_loop = false;
        while (!stop_loop) {
            String cmd = scan.nextLine();
            if (!("quit".equals(cmd))) {
                System.out.println("Unknown command");
            } else {
                stop_loop = true;
            }
        }
    }
}
