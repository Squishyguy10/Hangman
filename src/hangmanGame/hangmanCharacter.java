package hangmanGame;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

public class HangmanCharacter extends JPanel {

    private static final String[] hangmanImageLocations = { // list of image locations
            "src/resources/hangman0.png",
            "src/resources/hangman1.png",
            "src/resources/hangman2.png",
            "src/resources/hangman3.png",
            "src/resources/hangman4.png",
            "src/resources/hangman5.png",
            "src/resources/hangman6.png"};

    private static int penalties = 0;
    private static JLabel hangmanImage; // image container

    public HangmanCharacter() {

        hangmanImage = new JLabel(new ImageIcon(getCurrentImage()));
        hangmanImage.setSize(100, 100);

    }

    private Image getCurrentImage() { // gets the current hangman image from penalty count

        BufferedImage image = null;

        try {

            image = ImageIO.read(new File(hangmanImageLocations[penalties])); // reads file location

        } catch(Exception e) {

            e.printStackTrace();

        }

        return image.getScaledInstance(250, 250, Image.SCALE_SMOOTH); // sets image size
    }
    public int getPenalties() {
        return penalties;
    }

    public void incrementPenalties() { // increments penalty count and updates image
        penalties += 1;

        hangmanImage.setIcon(new ImageIcon(getCurrentImage()));
        hangmanImage.repaint();
    }

    public JPanel getHangmanImage() { // gets the panel with the image added to it
        add(hangmanImage);
        return this;
    }
}
