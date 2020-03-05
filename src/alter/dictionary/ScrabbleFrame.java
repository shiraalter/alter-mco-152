package alter.dictionary;

//put all member variables in jframe   ??

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScrabbleFrame extends JFrame {
    private final JTextField wordField;
    private final JLabel answerLabel;
    private ScrabbleDictionary dictionary;
    public ScrabbleFrame(){
        setSize(400,300);
        setTitle("Scrabble Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //setLayout(new FlowLayout());    //everything is going to be its smallest poss size  //set horizontally
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));       //set vertically

        wordField = new JTextField();
        wordField.setPreferredSize(new Dimension(160, 40));
        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(actionEvent -> checkWord());  //lambda

        answerLabel = new JLabel();
        answerLabel.setPreferredSize(new Dimension(100, 40));
        answerLabel.setOpaque(true);

        add(wordField);
        add(checkButton);
        add(answerLabel);

        try {
            dictionary = new ScrabbleDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void checkWord() {
        boolean isInDictionary = dictionary.wordExists(wordField.getText());
        answerLabel.setBackground(isInDictionary ? Color.GREEN : Color.RED);
        answerLabel.setText(String.valueOf(isInDictionary));
    }

    public static void main(String[] args){
        ScrabbleFrame frame = new ScrabbleFrame();
        frame.setVisible(true);
    }
}
