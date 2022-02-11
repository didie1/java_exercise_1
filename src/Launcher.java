import java.util.*;

public class Launcher {
    public static void main(String[] args)
    {
        System.out.println("Welcome! Please type the command freq (most occurencies) or fibo (fibonnaci) and quit to stop ");
        Scanner scan = new Scanner(System.in);
        List<Command> list_cmd = new ArrayList<>();
        list_cmd.add(new Quit());
        list_cmd.add(new Fibo());
        list_cmd.add(new Freq());
        Command com_choice = null;
        boolean stop_loop = false, exist = false;
        do {
            com_choice = null;
            String input = scan.nextLine();
            for (Command c : list_cmd) {
                if (c.name().equals(input)) {
                    exist = true;
                    com_choice = c;
                    break;
                }
            }
            if (exist && com_choice != null) {
                        stop_loop = com_choice.run(scan);
            }
            else
                System.out.println("Unknown command");
        } while (!stop_loop || com_choice == null);
        scan.close();
    }
}
