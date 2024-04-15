package hangmanGame;
import menuFunctions.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class GameScreen extends JFrame {
    private final int width;
    private final int height;
    private HangmanGame currentGame;
    private final HangmanCharacter hangmanChar = new HangmanCharacter();
    JLabel hiddenWord;
    JLabel guessedLetters = new JLabel("");

    public GameScreen() {
        width = 720;
        height = 640;
        this.currentGame = new HangmanGame();
        createFrame();
    }

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.currentGame = new HangmanGame();
        createFrame();
    }

    private void createFrame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(75, 75, 75, 75));

        Box mainVertical = Box.createVerticalBox();
        mainPanel.add(mainVertical, BorderLayout.PAGE_START);

        JPanel upperPanel = new JPanel(); // holds hangman image and guessed letters
        upperPanel.setLayout(new GridLayout(1, 2));

        upperPanel.add(createHangmanImagePanel());
        upperPanel.add(createGuessedLettersPanel());

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        Box lowerVertical = Box.createVerticalBox();
        lowerPanel.add(lowerVertical, BorderLayout.PAGE_START);

        lowerVertical.add(createHiddenWordPanel());
        lowerVertical.add(createInputPanel());

        mainVertical.add(upperPanel);
        mainVertical.add(lowerPanel);

        add(mainPanel);

        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createHangmanImagePanel() {
        JPanel hangmanImagePanel = new JPanel();
        hangmanImagePanel.add(hangmanChar.getHangmanImage());

        return hangmanImagePanel;
    }

    private JPanel createGuessedLettersPanel() {

        JPanel guessedLettersPanel = new JPanel();
        guessedLetters.setFont(new Font("SANS_SERIF", Font.BOLD, 48));

        guessedLettersPanel.add(guessedLetters);

        return guessedLettersPanel;
    };

    private JPanel createHiddenWordPanel() { // creates the panel where the
        JPanel hiddenWordPanel = new JPanel();

        hiddenWord = new JLabel(currentGame.spaceWord(currentGame.showHiddenWord()));
        hiddenWord.setFont(new Font("SANS_SERIF", Font.BOLD, 48));

        hiddenWordPanel.add(hiddenWord);

        return hiddenWordPanel;
    }

    private JPanel createInputPanel() {
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

    JTextField userInput = new JTextField(1);
    userInput.setDocument(new JTextFieldLimit(1));
    
    // Add key listener to the text field
    userInput.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = userInput.getText();
            if (!input.isEmpty()) { // Check if input is not empty
                currentGame.guessLetter(input.charAt(0));
                hiddenWord.setText(currentGame.spaceWord(currentGame.showHiddenWord()));

                String incorrectLetters = currentGame.getIncorrectLetters();
                String incorrectString = "";
                for (int i = 0; i < incorrectLetters.length(); i++) {
                    incorrectString += incorrectLetters.charAt(i) + " ";
                }

                hangmanChar.updateImage(currentGame.getIncorrectGuesses());

                guessedLetters.setText(incorrectString);

                if (currentGame.wonGame()) { // checks if the word is complete or user is out of guesses
                    wordComplete();
                } else if (!currentGame.isAlive())
                    GameOver();
            }
            userInput.setText("");
        }
    });
    
    inputPanel.add(userInput);

    // Set the default button to null to prevent clicking the button on pressing Enter
    this.getRootPane().setDefaultButton(null);

    return inputPanel;
}


    class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            // Only allow letters
            if (str.chars().allMatch(Character::isLetter)) {
                if ((getLength() + str.length()) <= limit) {
                    super.insertString(offset, str, attr);
                }
            }
        }
    }

    public void wordComplete() {
        JOptionPane.showMessageDialog(this, "You guessed the word. The word was " + currentGame.getWord() + ".", "YOU WIN", JOptionPane.INFORMATION_MESSAGE);

        new MainMenu();
        dispose();
    }
    
    public void GameOver() {
        JOptionPane.showMessageDialog(this, "Out of guesses. The word was " + currentGame.getWord() + ".", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);

        new MainMenu();
        dispose();
    }
}
