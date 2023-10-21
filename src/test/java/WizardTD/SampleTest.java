package WizardTD;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import WizardTD.Tile.Tower;
import processing.core.PApplet;


public class SampleTest {
    public static App app;

    @BeforeAll
    public static void testApp() {
        app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        while (app.gameState == GameState.GAMENORMAL) {
            app.draw();
        }
    }
}