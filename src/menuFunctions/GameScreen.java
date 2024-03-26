package menuFunctions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameScreen extends JFrame{
    private final int width;
    private final int height;
    private BufferedImage[] img = new BufferedImage[7];

    public GameScreen() { // default constructor
        width = 720;
        height = 640;
        createFrame();
        initImages();
    }

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        createFrame();
        initImages();
    }

    public void initImages() {
        for(int i = 0; i < 7; i++) {
            try {
                String url = String.format("../resources/hangman%x.png", i);
                img[i] = ImageIO.read(GameScreen.class.getResource(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFrame() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBorder(BorderFactory.createEmptyBorder(75, 75, 75, 75));

        Box vertical = Box.createVerticalBox();
        gamePanel.add(vertical, BorderLayout.PAGE_START);

        vertical.add(createUpperPanel());
        vertical.add(createLowerPanel());

        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createUpperPanel() {
        JPanel upperPanel = new JPanel();

        JLabel displayWord = new JLabel("test");
        upperPanel.setFont(new Font("SANS_SERIF", Font.BOLD, 72));

        upperPanel.add(upperPanel);
        return upperPanel;
    }

    private JPanel createLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JTextField userInput = new JTextField();
        userInput.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        userInput.setFont(new Font("SANS_SERIF", Font.BOLD, 72));

        lowerPanel.add(userInput);

        return lowerPanel;
    }
}
