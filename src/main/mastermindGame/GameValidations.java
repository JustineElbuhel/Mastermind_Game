package mastermindGame;

import java.util.Scanner;

/**
 * @class GameValidations - Contains methods that validates inputs entered into the console/terminal.
 */
public class GameValidations {
    /**
     * @method checkUserInput - Checks to see if the player's guess is a 4 digit guess without any letters.
     * @param userGuess - Player's guess that was entered into the console/terminal.
     * @return userGuess - Valid player guess that will be compared to the secret code.
     */
    public String checkUserInput(String userGuess) {
        Scanner scanner = new Scanner(System.in);
        while (userGuess.length() != 4 || userGuess.matches(".*[a-zA-Z].*")) {
            if (userGuess.matches(".*[a-zA-Z].*") && userGuess.length() != 4) {
                System.out.println("*Your guess is too short/long && contains letters.*");
            } else if (userGuess.length() != 4) {
                System.out.println("*Too many/little digits.*");
            } else if (userGuess.matches(".*[a-zA-Z].*")) {
                System.out.println("*Your guess contains letters.*");
            }

            System.out.println("\033[1mPlease enter a 4 digit guess.\033[0m");
            System.out.print("Enter guess: ");
            userGuess = scanner.nextLine();
        }
        return userGuess;
    }

    /**
     * @method validateSecretCodeArg - Checks to see if the user inputs a valid secret code for the game
     * @param providedCode - Potential secret code that was entered into the console/terminal by a user
     * @return true if code is a 4 digit guess without letters, otherwise returns false
     */
    public Boolean validateSecretCodeArg(String providedCode){
        if(providedCode.matches(".*[a-zA-Z].*") || providedCode.length() != 4){
            return false;
        }
        return true;
    }

    /**
     * @method validateMaxRoundsArg - Checks to see if the user inputs a valid number for setting max rounds for the game
     * @param providedNum - Potential secret code that was entered into the console/terminal by a user
     * @return true if providedNum is a number without letters, otherwise returns false
     */
    public Boolean validateMaxRoundsArg(String providedNum){
        return !providedNum.matches(".*[a-zA-Z].*");
    }
}
