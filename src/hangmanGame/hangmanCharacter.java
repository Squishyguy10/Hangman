package hangmanGame;
import javax.swing.*;

public class HangmanCharacter extends JPanel {

    private static final String[] hangmanImageLocations = {
            "src/resources/hangman0",
            "src/resources/hangman1",
            "src/resources/hangman2",
            "src/resources/hangman3",
            "src/resources/hangman4",
            "src/resources/hangman5",
            "src/resources/hangman6"};

    private static int penalties = 0;
    private static JLabel hangmanImage = new JLabel(new ImageIcon(hangmanImageLocations[0]));;
    public HangmanCharacter() {

    }
    public int getPenalties() {
        return penalties;
    }

    public void incrementPenalties() {
        penalties += 1;

        hangmanImage.setIcon(new ImageIcon(hangmanImageLocations[penalties]));
    }

    public JPanel getHangmanImage() {
        add(hangmanImage);
        return this;
    }
}
