package mastermindGame;

import java.util.Scanner;

public class GamePlay {
    private String secretCode;
    private int maxRounds;
    private int roundN;

    public GamePlay(String secretCode, int maxRounds){
        this.secretCode = secretCode;
        this.maxRounds = maxRounds;
        this.roundN = 1;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to My MastermindGame.Mastermind");
        System.out.println("You have " + maxRounds + " chances to guess the secret code.");
        System.out.println("Good luck!");

        while(roundN <= maxRounds){
            int remainingRounds = (maxRounds + 1) - roundN;
            System.out.println("*********************");
            System.out.println("Round " + (roundN) + "   (Remaining rounds: " + remainingRounds + ")");
            System.out.print("Enter guess: ");

            String userGuessInput = scanner.nextLine();
            GameValidations gameValidations = new GameValidations();
            userGuessInput = gameValidations.checkUserInput(userGuessInput);

            if(secretCode.trim().equals(userGuessInput.trim())){
                System.out.println("*****************************************************************");
                System.out.println("Congrats! You guessed the secret code!");
                System.out.println("*****************************************************************");
                break;
            } else {
                PiecesAlgorithm piecesAlgorithm = new PiecesAlgorithm(secretCode, userGuessInput);
                piecesAlgorithm.checkPieces(secretCode, userGuessInput);
            }
            roundN++;
        }
        if(roundN > maxRounds){
            System.out.println("*****************************************************************");
            System.out.println("Sorry, you ran out of guesses. The secret code was " + secretCode);
            System.out.println("*****************************************************************");
        }
    }
}
