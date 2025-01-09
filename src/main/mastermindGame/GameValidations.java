package mastermindGame;

import java.util.Scanner;

public class GameValidations {
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

    public Boolean validateSecretCodeArg(String providedCode){
        if(providedCode.matches(".*[a-zA-Z].*") || providedCode.length() != 4){
            return false;
        }
        return true;
    }

    public Boolean validateMaxRoundsArg(String providedNum){
        return !providedNum.matches(".*[a-zA-Z].*");
    }
}
