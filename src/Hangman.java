import groovy.transform.ToString;

import javax.swing.*;
import java.sql.*;

public class Hangman {
    HangmanGUI gui;
    String wordToGuess;
    String hiddenWord = "";
    int attempts = 0;

    public Hangman(){
        gui = new HangmanGUI();

        newGame();

        gui.itemNewGame.addActionListener(e -> newGame());
        gui.itemExit.addActionListener(e -> System.exit(0));
        gui.itemAddWord.addActionListener(e -> addWord());

        gui.btnTry.addActionListener(e -> {
            if (!gui.textFieldGuess.getText().isEmpty() && gui.textFieldGuess.getText().length() < 2 && wordToGuess.contains(gui.textFieldGuess.getText().toUpperCase())){
                for (int i=0; i<wordToGuess.length(); i++){
                    if (wordToGuess.charAt(i) == gui.textFieldGuess.getText().toUpperCase().charAt(0)){
                        char[] wordToGuessChars = gui.labelWord.getText().toCharArray();
                        wordToGuessChars[i] = gui.textFieldGuess.getText().toUpperCase().charAt(0);
                        String wordToGuess2 = String.valueOf(wordToGuessChars);
                        gui.labelWord.setText(wordToGuess2);
                        if (wordToGuess.equals(wordToGuess2)){
                            JOptionPane.showMessageDialog(null, "CONGRATULATION!", "Victory", JOptionPane.PLAIN_MESSAGE);
                            newGame();
                        }
                    }
                }
                gui.textFieldGuess.setText("");
            }
            else{
                if (wordToGuess.equals(gui.textFieldGuess.getText().toUpperCase())){
                    gui.labelWord.setText(wordToGuess);
                    gui.textFieldGuess.setText("");
                    JOptionPane.showMessageDialog(null, "You Won!", "Victory", JOptionPane.PLAIN_MESSAGE);
                    newGame();
                }
                else {
                    System.out.println("Does not contain.");
                    gui.textFieldGuess.setText("");
                    gui.labelHangmanImg.setIcon(new ImageIcon("resources/hangman/hangman" + (attempts + 1) + ".png"));
                    attempts++;
                    if (attempts == 10){
                        JOptionPane.showMessageDialog(null, "You Lost!", "Defeat", JOptionPane.PLAIN_MESSAGE);
                        newGame();
                    }
                }
            }
        });
    }

    private void newGame(){
        attempts = 0;
        hiddenWord = "";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hangman?useTimezone=true&serverTimezone=UTC", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hangmanwords ORDER BY RAND() LIMIT 1;");
            while (resultSet.next()){
                wordToGuess = resultSet.getString("word");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(wordToGuess);

        for (int i=0; i<wordToGuess.length(); i++){
            hiddenWord = hiddenWord + "-";
        }
        gui.labelWord.setText(hiddenWord);

        gui.labelHangmanImg.setIcon(new ImageIcon("resources/hangman/hangman0.png"));
    }

    private void addWord(){
        String newWord = (String)JOptionPane.showInputDialog(gui.mainFrame, "Add new word:", "Adding", JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (newWord != null && !newWord.equals("")) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hangman?useTimezone=true&serverTimezone=UTC", "root", "");
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO hangmanwords VALUES(null, null, '" + newWord + "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Hangman();
    }
}
