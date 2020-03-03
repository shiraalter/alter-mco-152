package alter.dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ScrabbleFrame extends JFrame {
    private final JTextField wordField;
    private final JLabel answerLabel;
    public ScrabbleFrame(){
        setSize(400,300);
        setTitle("Scrabble Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());    //everything is going to be its smallest poss size

        wordField = new JTextField();
        wordField.setPreferredSize(new Dimension(100, 40));
        JButton checkButton = new JButton();
        checkButton.addActionListener(actionEvent -> checkWord());

        answerLabel = new JLabel();
        answerLabel.setPreferredSize(new Dimension(100, 40));
        add(wordField);
        add(checkButton);
        add(answerLabel);

    }

    private void checkWord() {
        ScrabbleDictionary dictionary = null;
        try {
            dictionary = new ScrabbleDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isInDictionary = dictionary.wordExists(wordField.getText());
        answerLabel.setText(String.valueOf(isInDictionary));
    }

    public static void main(String[] args){
        ScrabbleFrame frame = new ScrabbleFrame();
        frame.setVisible(true);
    }
}
