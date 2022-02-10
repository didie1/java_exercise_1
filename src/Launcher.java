import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Launcher {
    public static void freq(Path filePath)
    {
        try
        {
            String content = Files.readString(filePath);
            content = content.toLowerCase();
            String[] listMessage = content.split(" ");
            Stream<String> streamMessage = Arrays.stream(listMessage);
            Map<String, Long> freqMap = streamMessage
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
            freqMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).forEachOrdered(e -> System.out.println(e.getKey()));
        }
        catch (IOException e)
        {
            System.out.println("Unreadable file: " + e);
        }
    }
    public static int fibo(int input_number)
    {
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
        return result;
    }
    public static void main(String[] args)
    {
        System.out.println("Welcome!");
        Scanner scan = new Scanner(System.in);
        boolean stop_loop = false;
        do {
            System.out.println();
            String cmd = scan.nextLine();
            if("quit".equals(cmd))
            {
                stop_loop = true;
            }
            else if ("fibo".equals(cmd))
            {
                String input_number;
                System.out.println("Please type in a figure for the fibonnaci series");
                input_number = scan.nextLine();
                System.out.println("The result of the fibonnaci series on "+ input_number + " is : " + fibo(parseInt(input_number)));
            }
            else if ("freq".equals(cmd))
            {
                System.out.println("Please type in the explicit path where the text file is stored");
                String path = scan.nextLine();
                Path filePath = Paths.get(path);
                freq(filePath);
            }
            else
                System.out.println("Unknown command");
        } while (!stop_loop);
    }
}
