package hangmanGame;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

public class HangmanCharacter extends JLabel {
    private static final String[] hangmanImageLocations = { // list of image locations
        "src/resources/hangman0.png",
        "src/resources/hangman1.png",
        "src/resources/hangman2.png",
        "src/resources/hangman3.png",
        "src/resources/hangman4.png",
        "src/resources/hangman5.png",
        "src/resources/hangman6.png"
    };

    public HangmanCharacter() {
        setSize(100, 100);
        setIcon(new ImageIcon(getCurrentImage(0)));
    }

    private Image getCurrentImage(int i) { // gets the current hangman image from penalty count
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(hangmanImageLocations[i])); // reads file location
        } catch(Exception e) {
            e.printStackTrace();
        }
        return image.getScaledInstance(250, 250, Image.SCALE_SMOOTH); // sets image size
    }
    
    public void updateImage(int i) { // updates image based on number of incorrect guesses
        setIcon(new ImageIcon(getCurrentImage(i)));
    }

    public JLabel getHangmanImage() { // gets the panel with the image added to it
        return this;
    }
}
