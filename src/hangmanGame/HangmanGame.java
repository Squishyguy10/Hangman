package hangmanGame;

import javax.swing.*;
import java.util.Random;

public class HangmanGame {
    static final int MAXGUESSES = 6;
    private String word;
    private String guessedLetters;
    private String incorrectLetters = "";
    private int incorrectGuesses;
    private int missingLetters;

    // Constructors
    public HangmanGame() {
        word = wordToGuess();
        guessedLetters = "";
        incorrectGuesses = 0;
        missingLetters = word.length();
    }

    private String wordToGuess() { // randomly chooses the word to guess
        String[] wordArray = {
                "fish",
                "lobster",
                "shrimp",
                "clam",
                "piranha",
                "shark",
                "jellyfish",
                "whale",
                "bass",
                "perch",
                "salmon",
                "snapper",
                "dolphin",
                "orca",
                "crab",
                "stingray",
                "eel",
                "clownfish",
                "turtle",
                "nemo",
                "pufferfish",
                "sponge",
                "seaslug",
                "starfish",
                "lionfish",
                "squid",
                "octopus",
                "parrotfish",
                "sheepfish",
                "coral",
                "seahorse",
                "goldfish",
                "goblinshark",
                "hammerhead",
                "catfish",
                "tigershark",
                "trout",
                "cod"
        };

        Random rand = new Random();

        int randomIndex = rand.nextInt(wordArray.length);
        return wordArray[randomIndex];
    }

    // Getters and setters
    public String getWord() {
        missingLetters = word.length();
        return word.toLowerCase();
    }

    public String getIncorrectLetters() {
        return incorrectLetters;
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
                incorrectLetters += letter;
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
