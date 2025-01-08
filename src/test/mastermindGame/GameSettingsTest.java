package mastermindGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSettingsTest {
    private GameSettings gameSettings;

    @BeforeEach
    void setUp() {
        gameSettings = new GameSettings();
    }

    @Test
    void startSettingsWithCodeTest() throws Exception {
        String[] args = new String[]{"-c", "1234"};
        gameSettings.startSettings(args);
        assertEquals("1234", gameSettings.getSecretCode());
    }

    @Test
    void startSettingsWithRoundNTest() throws Exception {
        String[] args = new String[]{"-r", "3"};
        gameSettings.startSettings(args);
        assertEquals(3, gameSettings.getMaxRounds());
    }

    @Test
    void startSettingWithParamsTest() throws Exception {
        String[] args = new String[]{"-c", "1234", "-r", "3"};
        gameSettings.startSettings(args);
        assertEquals("1234", gameSettings.getSecretCode());
        assertEquals(3, gameSettings.getMaxRounds());
    }

    @Test
    void startSettingsWithoutParamsTest() throws Exception {
        String[] args = new String[]{};
        gameSettings.startSettings(args);
        assertNotNull(gameSettings.getSecretCode());
        assertEquals(10, gameSettings.getMaxRounds());
    }
}