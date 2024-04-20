package menuFunctions;

import hangmanGame.GameScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    private final int width;
    private final int height;
    static Leaderboard leaderboard = new Leaderboard();

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
        menuPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25)); // used for spacing around panel on frame

        JPanel titlePanel = new JPanel(); // panel to hold title GUI
        JLabel titleLabel = new JLabel("HANGMAN");
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 72)); // changes font and font size for title

        titlePanel.add(titleLabel); // adding components to the frame
        menuPanel.add(titlePanel);

        menuPanel.add(getMenuButtons());
        add(menuPanel);


        setResizable(false); // adjusting frames
        setSize(width, height); // sets size of frame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel getMenuButtons() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 50, 25));
        buttonPanel.setLayout(new GridBagLayout());
    
        Font buttonFont = new Font("SANS_SERIF", Font.BOLD, 48);
    
        JButton playButton = new JButton("PLAY");
        playButton.setFont(buttonFont);
        playButton.setBackground(new Color(139, 247, 123));
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent info) {
                new GameScreen(width, height, leaderboard);
                dispose();
            }
        });

        JButton leaderboardButton = new JButton("RANKING");
        leaderboardButton.setFont(buttonFont);
        leaderboardButton.setBackground(new Color(255, 254, 89));
        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent info) {
                new LeaderboardMenu(width, height, leaderboard);
                dispose();
            }
        });
    
        JButton quitButton = new JButton("QUIT");
        quitButton.setFont(buttonFont);
        quitButton.setBackground(new Color(255, 108, 108));
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent quit) {
                if (confirmQuit()) {
                    System.exit(0);
                }
            }
        });
    
        JButton infoButton = new JButton("RULES");
        infoButton.setFont(buttonFont);
        infoButton.setBackground(new Color(155, 222, 255));
        infoButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent info) {
               new InstructionsMenu(width, height);
               dispose();
           }
        });
    
        buttonPanel.add(playButton, createGBC(0, 0, 2));
        buttonPanel.add(infoButton, createGBC(0, 1, 1));
        buttonPanel.add(leaderboardButton, createGBC(1, 1, 1));
        buttonPanel.add(quitButton, createGBC(0, 2, 2));

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
