package menuFunctions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;


public class GameScreen extends JFrame {
    private final int width;
    private final int height;
    private HangmanGame currentGame;
    JLabel hiddenWord;

    public GameScreen() {
        width = 720;
        height = 640;
        initWord("classroom");
        createFrame();
    }

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        initWord("classroom");
        createFrame();
    }

    private void initWord(String s) {
        this.currentGame = new HangmanGame(s);
    }

    private void createFrame() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBorder(BorderFactory.createEmptyBorder(75, 75, 75, 75));

        Box vertical = Box.createVerticalBox();
        gamePanel.add(vertical, BorderLayout.PAGE_START);

        vertical.add(createUpperPanel());
        vertical.add(createLowerPanel());

        add(gamePanel);

        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createUpperPanel() {
        JPanel upperPanel = new JPanel();

        hiddenWord = new JLabel(currentGame.spaceWord(currentGame.showHiddenWord()));
        hiddenWord.setFont(new Font("SANS_SERIF", Font.BOLD, 72));

        upperPanel.add(hiddenWord);

        return upperPanel;
    }

    private JPanel createLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JTextField userInput = new JTextField(1);
        userInput.setDocument(new JTextFieldLimit(1));
        JButton button = new JButton("check");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = userInput.getText();
                // Word limit
                if (input.length() == 1) {
                    currentGame.guessLetter(input.charAt(0));
                    hiddenWord.setText(currentGame.spaceWord(currentGame.showHiddenWord()));
                }
                userInput.setText("");
            }
        });
        lowerPanel.add(userInput);
        lowerPanel.add(button);

        return lowerPanel;
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
}
