package menuFunctions;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {
    private static List<PlayerEntry> leaderboard;

    public Leaderboard() {
        leaderboard = new ArrayList<>();
    }

    public void updateScore(String playerName, int score) {
        leaderboard.add(new PlayerEntry(playerName, score));
    }

    public String[] getFormattedLeaderboard() {
        Collections.sort(leaderboard, Comparator.comparingInt(PlayerEntry::getScore));
        List<String> formattedLeaderboard = new ArrayList<>();

        for (PlayerEntry entry : leaderboard) {
            formattedLeaderboard.add(entry.getPlayerName() + "\t\t" + entry.getScore());
        }
        return formattedLeaderboard.toArray(new String[0]);
    }

    private static class PlayerEntry {
        private final String playerName;
        private final int score;

        public PlayerEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }
    }
}
