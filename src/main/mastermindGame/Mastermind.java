package mastermindGame;

public class Mastermind {
    public static void main(String[] args) throws Exception {
        GameSettings gameSettings = new GameSettings();
        gameSettings.startSettings(args);
        gameSettings.generateSecretCode();
        String secretCode = gameSettings.getSecretCode();
        System.out.println(secretCode);
        int maxRounds =  gameSettings.getMaxRounds();

        GamePlay gamePlay = new GamePlay(secretCode, maxRounds);
        gamePlay.start();
    }
}
