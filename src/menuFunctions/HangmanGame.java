package menuFunctions;

import javax.swing.*;
package menuFunctions;

import javax.swing.*;

public class HangmanGame {
    static final int MAXGUESSES = 6;
    private String word;
    private String guessedLetters;
    private int incorrectGuesses;
    private int missingLetters;

    // Constructors
    public HangmanGame(String word) {
        this.word = word.toLowerCase();
        guessedLetters = "";
        incorrectGuesses = 0;
        missingLetters = word.length();
    }

    public HangmanGame() {
        word = "";
        guessedLetters = "";
        incorrectGuesses = 0;
    }

    // Getters and setters
    public String getWord() {
        missingLetters = word.length();
        return word.toLowerCase();
    }

    public String getGuessedLetters() {
        return guessedLetters;
    }

    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public void setWord(String word) {
        this.word = word;
    }

    // Class methods
    public boolean isAlive() {
        return MAXGUESSES > incorrectGuesses;
    }

    public boolean wonGame() {
        return missingLetters == 0;
    }

    public void guessLetter(char letter) {
        letter = Character.toLowerCase(letter);

        if(guessedLetters.indexOf(letter) == -1) {
            guessedLetters += letter;
            if(word.indexOf(letter) == -1) {
                incorrectGuesses++;
            }
        }
    }

    public String showHiddenWord() {
        String hidden = "";
        missingLetters = 0;

        for(int i = 0; i < word.length(); i++) {
            if(guessedLetters.indexOf(word.charAt(i)) != -1) {
                hidden += word.charAt(i);
            }
            else {
                missingLetters++;
                hidden += '_';
            }
        }
        return hidden;
    }

    public String spaceWord(String s) {
        String spacedWord = showHiddenWord().replaceAll("", "&nbsp;");
        return "<html><div style='letter-spacing: 10px;'>" + spacedWord + "</div></html>";
    }
}