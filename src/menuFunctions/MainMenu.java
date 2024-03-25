package menuFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
public class MainMenu extends JFrame {

    private final int width;
    private final int height;

    public MainMenu() {
        width = 720;
        height = 640;

        createFrame();
    }
    public MainMenu(int width, int height) {

        this.width = width;
        this.height = height;

        createFrame();
    }

    private void createFrame() {

        JPanel menuPanel = new JPanel(); // main panel
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(75, 75, 75, 75)); // used for spacing around panel on frame

        JPanel titlePanel = new JPanel(); // panel to hold title GUI

        JLabel titleLabel = new JLabel("HANGMAN");
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 72)); // changes font and font size for title


        titlePanel.add(titleLabel); // adding components to the frame
        menuPanel.add(titlePanel);
        menuPanel.add(getMenuButtons());
        add(menuPanel);

        setResizable(false); // adjusting frames
        setLocationRelativeTo(null);
        setSize(width, height); // sets size of frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel getMenuButtons() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 50, 50));
        buttonPanel.setLayout(new GridBagLayout());


        Font buttonFont = new Font("SANS_SERIF", Font.BOLD, 48);

        JButton playButton = new JButton("PLAY");
        playButton.setFont(buttonFont);

        JButton quitButton = new JButton("QUIT");
        quitButton.setFont(buttonFont);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent quit) {

                if (confirmQuit()) {
                    System.exit(0);
                }
            }

        });

        JButton infoButton = new JButton("INSTRUCTIONS");
        infoButton.setFont(buttonFont);
        infoButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent info) {

               new InstructionsMenu(width, height);
               dispose();
           }
        });

        buttonPanel.add(playButton, createGBC(0, 0, 1));
        buttonPanel.add(quitButton, createGBC(1, 0, 1));
        buttonPanel.add(infoButton, createGBC(0, 1, 2));

        return buttonPanel;
    }

    private GridBagConstraints createGBC( int x, int y, int gridWidth) { // used with GridBagLayouts to create spacing between components
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x; // location of components on grid
        gbc.gridy = y;

        gbc.insets = new Insets(5, 5, 5, 5); // space around components in a grid
        gbc.gridwidth = gridWidth; // how big each grid is

        gbc.weightx = 0.5; // determines how much of space elements take up compared to others
        gbc.weighty = 0.5;

        gbc.fill = GridBagConstraints.BOTH; // components fill up available space both horizontally and vertically

        return gbc;
    }

    private boolean confirmQuit() {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION); // gets an integer from option chosen on option pane

        return answer == JOptionPane.YES_OPTION;
    }
}