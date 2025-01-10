package mastermindGame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
class GamePlayTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreSystemIO() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    void startCorrectGuessTest() {
        String simulatedInput = "1234";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        GamePlay gamePlay = new GamePlay("1234", 10);
        gamePlay.start();

        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("Congrats! You guessed the secret code!"));
    }

    @Test
    void startIncorrectGuessTest(){
        String simulatedInput = "1243";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        PiecesAlgorithm piecesAlgorithm = new PiecesAlgorithm("1234", "1243");
        piecesAlgorithm.checkPieces("1234", "1243");

        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("Correct Numbers: 2\nMisplaced Numbers: 2"));
    }

    @Test
    void startAllIncorrectGuessTest(){
        String simulatedInput = "0000";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        PiecesAlgorithm piecesAlgorithm = new PiecesAlgorithm("1234", "0000");
        piecesAlgorithm.checkPieces("1234", "0000");

        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("All pieces are incorrect."));
    }

    @Test
    void noMoreRoundsTest(){
        String simulatedInput = "1233\n1233\n1233";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));


        GamePlay gamePlay = new GamePlay("1234", 3);
        gamePlay.start();

        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("Sorry, you ran out of guesses. The secret code was 1234"));

    }
}