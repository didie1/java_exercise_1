import java.util.*;

public class Launcher {
    public static void main(String[] args)
    {
        System.out.println("Welcome! Please type the command freq (most occurencies) or fibo (fibonnaci) and quit to stop ");
        Scanner scan = new Scanner(System.in);
        List<Command> list_cmd = new ArrayList<>();
        List<String> list_name = new ArrayList<>();
        list_cmd.add(new Quit());
        list_cmd.add(new Fibo());
        list_cmd.add(new Freq());
        list_name.add(new Quit().name());
        list_name.add(new Fibo().name());
        list_name.add(new Freq().name());
        boolean stop_loop = false, exist = false;
        do {
            System.out.println();
            String input = scan.nextLine();
            scan.nextLine();
            if (list_name.contains(input))
                exist = true;
            else
                System.out.println("Unknown command");
            if (exist) {
                for (Command c : list_cmd) {
                    if (c.name().equals(input)) {
                        stop_loop = c.run(scan);
                    }
                }
            }
        } while (!stop_loop);
    }
}
