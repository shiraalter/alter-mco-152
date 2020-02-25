package alter.dictionary;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class ScrabbleDictionaryTest {
    ScrabbleDictionary dictionary;

    @Test
    public void wordExists() throws IOException {
         dictionary = new ScrabbleDictionary();
         assertTrue(dictionary.wordExists("happy"));
    }

    @Test
    public void wordDoesNotExist() throws IOException {
        dictionary = new ScrabbleDictionary();
        assertFalse(dictionary.wordExists("yo"));

    }

    @Test
    public void caseInsensitivity() throws IOException {
        dictionary = new ScrabbleDictionary();
        assertTrue(dictionary.wordExists("HAPPY"));
    }
}