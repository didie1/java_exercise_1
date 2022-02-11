import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command{
    private static class WordOperation {
        public final String word;
        private final Map<String, Integer> frequencies = new HashMap<>();

        public WordOperation(String word) {
            this.word = word;
        }

        public void addNext(String w) {
            this.frequencies.put(w, this.frequencies.getOrDefault(w, 0) + 1);
        }

        public String predictEquals() {
            if (frequencies.isEmpty())
                return null;
            var i = Collections.max(frequencies.values());
            var list = this.frequencies.keySet().stream().filter(k -> frequencies.get(k).equals(i)).toList();
            return list.get(0);
        }
    }
    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scan) {
        String content = "";
        System.out.println("Please type in the explicit path where the text file is stored");
        try {
            String path = scan.nextLine();
            content = Files.readString(Paths.get(path));

        }
        catch (IOException e)
        {
            System.out.println("Unreadable file: " + e.getClass() + e.getMessage());
        }
        Map<String, WordOperation> listWord = new HashMap<>();
        content = content.toLowerCase();
        content = content.replaceAll("[^a-z]"," ");
        String[] listMessage = content.split(" ");
        var lastWord = Arrays.stream(listMessage).filter(s -> !s.isBlank())
                .reduce("", (prev, next) -> {
                    if (!prev.isBlank()) {
                        listWord.putIfAbsent(prev, new WordOperation(prev));
                        listWord.get(prev).addNext(next);
                    }
                    return next;
                });

        listWord.putIfAbsent(lastWord, new WordOperation(lastWord));

        System.out.println("Please type in a word :");
        var input = scan.nextLine().toLowerCase();

        if (!listWord.containsKey(input))
            System.err.println("The word you typed in does not exist in the text!");

        else {
            var sentence = new ArrayList<>(List.of(input));
            while (sentence.size() < 20) {
                var nextWord = listWord.get(sentence.get(sentence.size() - 1)).predictEquals();
                if (nextWord == null)
                    break;
                sentence.add(nextWord);
            }

            System.out.println(String.join(" ", sentence));
        }
        return false;
    }

}
