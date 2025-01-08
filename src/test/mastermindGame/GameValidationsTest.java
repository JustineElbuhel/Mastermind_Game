package mastermindGame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameValidationsTest {
    private GameValidations gameValidations;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        gameValidations = new GameValidations();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreSystemIO() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    void checkUserInputValidGuessTest() {
        String userGuessInput = "1234";
        String userGuess = gameValidations.checkUserInput(userGuessInput);
        assertEquals("1234", userGuess);
    }

    @Test
    void checkUserInputInvalidLengthTest(){
        String simulatedInput = "123\n1234";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        gameValidations.checkUserInput("123");
        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("*Too many/little digits.*"));
    }

    @Test
    void checkUserInputInvalidCharTest(){
        String simulatedInput = "123a\n1234";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        gameValidations.checkUserInput("123a");
        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("*Your guess contains letters.*"));
    }

    @Test
    void checkUserInputInvalidLengthCharTest(){
        String simulatedInput = "12s3a\n1234";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        gameValidations.checkUserInput("123a");
        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("*Your guess is too short/long && contains letters.*"));
    }
}
