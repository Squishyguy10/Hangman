package menuFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderboardMenu extends JFrame {
    private final int width;
    private final int height;
    private static Leaderboard leaderboard;

    public LeaderboardMenu() {
        width = 720;
        height = 640;
        leaderboard = new Leaderboard();
        createFrame();
    }

    public LeaderboardMenu(int width, int height, Leaderboard leaderboard) {
        this.width = width;
        this.height = height;
        this.leaderboard = leaderboard;
        createFrame();
    }

    private void createFrame() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(75, 75, 75, 75));

        infoPanel.add(createUpperPanel(), BorderLayout.NORTH);
        infoPanel.add(createLeaderboardPanel(), BorderLayout.CENTER);
        infoPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(infoPanel);

        // adjusting frame
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createUpperPanel() { // creates the upper panel with the title screen
        JPanel upperPanel = new JPanel();

        JLabel titleLabel = new JLabel("LEADERBOARD");
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 72));

        upperPanel.add(titleLabel);

        return upperPanel;
    }

    private JPanel createLeaderboardPanel() {
        JPanel leaderboardPanel = new JPanel(new GridLayout(0, 2, 10, 0));
        leaderboardPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        leaderboardPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
    
        JLabel playerLabel = new JLabel("Player");
        playerLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 24));
        leaderboardPanel.add(playerLabel);
    
        JLabel scoreLabel = new JLabel("Mistakes");
        scoreLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 24));
        leaderboardPanel.add(scoreLabel);
    
        String[] leaderboardList = leaderboard.getFormattedLeaderboard();
    
        for (String entry : leaderboardList) {
            String[] parts = entry.split("\t\t");
            JLabel player = new JLabel(parts[0]);
            JLabel score = new JLabel(parts[1]);
    
            player.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
            score.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
    
            leaderboardPanel.add(player);
            leaderboardPanel.add(score);
        }

        return leaderboardPanel;
    }

    private JPanel createButtonPanel() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        JButton backButton = new JButton("BACK");
        backButton.setBackground(new Color(155, 222, 255));
        backButton.setPreferredSize(new Dimension(250, 75));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                dispose();
            }
        });
        buttonPanel.add(backButton);

        return buttonPanel;
    }
}
