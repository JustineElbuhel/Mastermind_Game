public class PiecesAlgorithm {
    private String secretCode;
    private String userGuess;

    public PiecesAlgorithm(String secretCode, String userGuess){
        this.secretCode = secretCode;
        this.userGuess = userGuess;
    }

    public void checkPieces(String secretCode, String userGuess){
        int correctN = 0;
        int misplacedN = 0;
        for(int i = 0; i < 4; i++){
            if(userGuess.charAt(i) == secretCode.charAt(i)){
                correctN++;
            } else {
                for(int j = 0; j < 4; j++){
                    if(userGuess.charAt(i) == secretCode.charAt(j) && i != j){
                        misplacedN++;
                        break;
                    }
                }
            }
        }
        System.out.println("Correct Numbers: " + correctN);
        System.out.println("Misplaced Numbers: " + misplacedN);
    }
}
