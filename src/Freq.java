import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Freq implements Command{

    public static void freq(Path filePath)
    {
        try
        {
            String content = Files.readString(filePath);
            content = content.toLowerCase();
            content = content.replaceAll("^[a-z]"," ");
            String[] listMessage = content.split(" ");
            Stream<String> streamMessage = Arrays.stream(listMessage);
            Map<String, Long> freqMap = streamMessage
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
            freqMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).forEachOrdered(e -> System.out.println(e.getKey()));

        }
        catch (IOException e)
        {
            System.out.println("Unreadable file: " + e.getClass() + e.getMessage());
        }
    }
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scan) {
        System.out.println("Please type in the explicit path where the text file is stored");
        String path = scan.nextLine();
        scan.nextLine();
        Path filePath = Paths.get(path);
        freq(filePath);
        return false;
    }
}
