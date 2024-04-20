package menuFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionsMenu extends JFrame {

    private final int width;
    private final int height;

    public InstructionsMenu() { // default constructor
        width = 720;
        height = 640;

        createFrame();
    }

    public InstructionsMenu(int width, int height) { // constructor to set sizes
        this.width = width;
        this.height = height;

        createFrame();
    }

    private void createFrame() {
        // creating panel to hold all GUI
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(75, 75, 75, 75));

        Box vertical = Box.createVerticalBox(); // used to create a vertical row layout
        infoPanel.add(vertical, BorderLayout.PAGE_START);

        vertical.add(createUpperPanel());
        vertical.add(createLowerPanel());

        add(infoPanel); // adds info panel to frame

        // adjusting frame
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createUpperPanel() { // creates the upper panel with the title screen
        JPanel upperPanel = new JPanel();

        JLabel titleLabel = new JLabel("INSTRUCTIONS");
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 72));

        upperPanel.add(titleLabel);

        return upperPanel;
    }

    private JPanel createLowerPanel() { // creates the lower panel with instructions and back button
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        String[] ruleList = { // list of rules that will be formatted into labels
                "1. A RANDOM WORD WILL BE CHOSEN",
                "2. CORRECT GUESSES WILL FILL IN LETTERS",
                "3. INCORRECT GUESSES WILL ADD A PENALTY",
                "4. COMPLETE THE WORD TO WIN"
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        JButton backButton = new JButton("BACK");
        backButton.setBackground(new Color(155, 222, 255));
        backButton.setPreferredSize(new Dimension(250, 75));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // creates next screen and disposes of current
                new MainMenu();
                dispose();
            }
        });
        buttonPanel.add(backButton);

        for(String s : ruleList) { // adds a label for each rule in instructions
            JLabel rule = new JLabel(s);
            rule.setFont(new Font("SANS_SERIF", Font.BOLD, 22));

            lowerPanel.add(rule);
        }
        lowerPanel.add(buttonPanel);

        return lowerPanel;
    }
}
