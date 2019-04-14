import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.awt.*;

public class HangmanGUI {
    JFrame mainFrame;
    JButton btnTry;
    JLabel labelWord, labelHangmanImg;
    JTextField textFieldGuess;
    JMenuBar menuBar;
    JMenu menuGame;
    JMenuItem itemNewGame, itemExit, itemAddWord;


    public HangmanGUI(){
        mainFrame = new JFrame("Hangman");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        menuBar = new JMenuBar();

        menuGame = new JMenu("Game");

        itemNewGame = new JMenuItem("New Game");
        menuGame.add(itemNewGame);

        menuGame.addSeparator();

        itemAddWord = new JMenuItem("Add a Word");
        menuGame.add(itemAddWord);

        menuGame.addSeparator();

        itemExit = new JMenuItem("Exit");
        menuGame.add(itemExit);

        menuBar.add(menuGame);

        mainFrame.setJMenuBar(menuBar);

        labelHangmanImg = new JLabel(new ImageIcon("resources/hangman/hangman0.png"));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.ipady = 0;
        c.insets = new Insets(0, 0, 10, 0);
        mainFrame.add(labelHangmanImg, c);

        labelWord = new JLabel("", SwingConstants.CENTER);
        labelWord.setOpaque(true);
        labelWord.setBackground(Color.LIGHT_GRAY);
        labelWord.setFont(new Font("Serif", Font.PLAIN, 30));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 10, 0);
        mainFrame.add(labelWord, c);

        textFieldGuess = new JFormattedTextField();
        textFieldGuess.setColumns(10);
        textFieldGuess.setFont(new Font("Serif", Font.PLAIN, 30));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(0, 60, 0, 0);
        mainFrame.add(textFieldGuess, c);

        btnTry = new JButton("Try");
        btnTry.setFont(new Font("Serif", Font.PLAIN, 30));
        c.gridx = 1;
        c.gridy = 2;
        mainFrame.add(btnTry, c);

        mainFrame.getRootPane().setDefaultButton(btnTry);
        mainFrame.setVisible(true);
    }
}
