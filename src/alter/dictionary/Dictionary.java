package alter.dictionary;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
    //check if word exists in dictionary
    Set<String> dictionarySet = new HashSet<String>();

    public Dictionary() throws IOException {
        File dictionaryFile = new File("C:\\Users\\Shira\\Desktop\\dictionary.txt");
        Scanner scanner = new Scanner(dictionaryFile);
        while(scanner.hasNextLine()) {
            String word = scanner.next();
            dictionarySet.add(word.toLowerCase());
            scanner.nextLine();
        }
    }
    public boolean wordExists(String word)   {
        /* there was a bug that if a word was entered without a space at the end, and that word was part
        of word that IS in the dictionary, it came back as true. This adds an automatic space so the user
        can enter a word and the program reads it as its own word (not part of a word)
         */
        word.toLowerCase();
        String newWord = word + " ";
        return (dictionarySet.contains(newWord));
    }
}

