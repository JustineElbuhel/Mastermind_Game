package mastermindGame;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Random;

public class GameSettings {
    private String secretCode;
    private int maxRounds ;

    public GameSettings(){
        this.maxRounds = 10;
    }

    public void startSettings(String[] args) throws Exception {
        if(Arrays.asList(args).contains("-c")){
            setSecretCode(args);
        } else {
            generateSecretCode();
        }

        if(Arrays.asList(args).contains("-r")){
           setMaxRounds(args);
        }
    }

    public void generateSecretCode() throws Exception{
        String secretCode = "";
        for (int i = 0; i < 4; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://www.random.org/integers/?num=1&min=0&max=7&col=1&base=10&format=plain&rnd=new"))
                    .header("Authorization", "Bearer 07abd06f-08da-49a0-9def-533cb631e501")
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                secretCode += response.body().trim();
            } else if (response.statusCode() == 503) {
                secretCode = "";
                secretCode = generateSecretCode2(secretCode);
                break;
            }
            Thread.sleep(1000);
        }
        this.secretCode = secretCode;
    }

    public String generateSecretCode2(String secretCode){
        Random random = new Random();
        secretCode = "";

        for(int i = 0; i < 4 ; i++){
            int randomN = random.nextInt(8);
            secretCode += randomN;
        }
        return secretCode;
    }

    public void setSecretCode(String[] args) throws Exception {
        GameValidations gameValidations = new GameValidations();
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-c") && (i+1) < args.length){
                if(gameValidations.validateSecretCodeArg(args[i+1])) {
                    this.secretCode = args[i + 1];
                    break;
                } else {
                    System.out.println("Incorrect format for setting secret code. Random code will be generated");
                    generateSecretCode();
                }
            }
        }
    }

    public String getSecretCode(){
        return this.secretCode;
    }

    public void setMaxRounds(String[] args){
        GameValidations gameValidations = new GameValidations();
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-r") && (i+1) < args.length){
                if(gameValidations.validateMaxRoundsArg(args[i+1])) {
                    this.maxRounds = Integer.parseInt(args[i + 1]);
                    break;
                } else {
                    System.out.println("Incorrect format for setting max rounds. Setting max rounds to 10.");
                    this.maxRounds = 10;
                }
            }
        }
    }

    public int getMaxRounds(){
        return this.maxRounds;
    }
}
