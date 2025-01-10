package mastermindGame;

public class PiecesAlgorithm {
    private String secretCode;
    private String userGuess;

    public PiecesAlgorithm(String secretCode, String userGuess){
        this.secretCode = secretCode;
        this.userGuess = userGuess;
    }

    public void checkPieces(String secretCode, String userGuess) {
        int correctN = 0;
        int misplacedN = 0;
        for (int i = 0; i < 4; i++) {
            if (userGuess.charAt(i) == secretCode.charAt(i)) {
                correctN++;
            } else {
                for (int j = 0; j < 4; j++) {
                    if (userGuess.charAt(i) == secretCode.charAt(j) && i != j) {
                        misplacedN++;
                        break;
                    }
                }
            }
        }
        if (correctN == 0 && misplacedN == 0) {
            System.out.println("All pieces are incorrect.");
        } else {
            System.out.println("Correctly placed numbers: " + correctN + "\nMisplaced correct Numbers: " + misplacedN);
        }
    }
}
