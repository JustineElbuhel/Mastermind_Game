package mastermindGame;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

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
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(new URI("https://www.random.org/integers/?num=1&min=0&max=9999&col=1&base=10&format=plain&rnd=new"))
                .header("Authorization","Bearer 07abd06f-08da-49a0-9def-533cb631e501")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request2, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200) {
            secretCode = response.body();
        } else if(response.statusCode() == 503){
            while(secretCode.length() < 4){
                int randomN = (int)(Math.random() * 7) +1;
                secretCode += randomN;
            }
        }
        this.secretCode = secretCode;
    }

    public void setSecretCode(String[] args){
        GameValidations gameValidations = new GameValidations();
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-c") && (i+1) < args.length){
                if(gameValidations.validateSecretCodeArg(args[i+1])) {
                    this.secretCode = args[i + 1];
                    break;
                } else {
                    System.out.println("123Incorrect format for setting secret code. Random code will be generated");
                    this.secretCode = getSecretCode();
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
