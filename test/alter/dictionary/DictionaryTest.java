package alter.dictionary;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class DictionaryTest {
    Dictionary dictionary;

    @Test
    public void wordExists() throws IOException {
         dictionary = new Dictionary();
         assertTrue(dictionary.wordExists("happy"));
    }

    @Test
    public void wordDoesNotExist() throws IOException {
        dictionary = new Dictionary();
        assertFalse(dictionary.wordExists("yo"));
    }

    @Test
    public void caseInsensitivity() throws IOException {
        dictionary = new Dictionary();
        assertTrue(dictionary.wordExists("HAPPY"));
    }
}