package alter.dictionary;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleDictionary {
    //check if word exists in dictionary
    Set<String> dictionarySet = new HashSet<String>();

    public ScrabbleDictionary() throws IOException {
        File dictionaryFile = new File("C:\\Users\\Shira\\Desktop\\dictionary.txt");
        Scanner scanner = new Scanner(dictionaryFile);
        while(scanner.hasNextLine()) {
            String word = scanner.next();
            dictionarySet.add(word.toLowerCase());
            scanner.nextLine();
        }
    }
    public boolean wordExists(String word)   {
        word = word.toLowerCase();
        return (dictionarySet.contains(word));
        
    }
}

