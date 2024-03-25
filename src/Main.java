import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        HangmanGame game = new HangmanGame("hello");
        while(!game.wonGame()) {
            System.out.print("Input a letter: ");
            char guess = input.next().charAt(0);
            game.guessLetter(guess);
            System.out.println(game.getIncorrectGuesses());
            System.out.println(game.showHiddenWord());
            System.out.println(" ");
        }
        System.out.println("yay");
    }
}