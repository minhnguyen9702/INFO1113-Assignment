package WizardTD;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.checkerframework.checker.units.qual.A;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.core.PApplet;


public class SampleTest {
    public App runApp() {
        App app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.configPath = "src/test/java/WizardTD/testConfig.json";
        app.setup();
        return app;
    }

    @Test
    public void testGame() {
        App app = runApp();
        while (app.gameState != GameState.GAMEOVER) {
            app.draw();
        }
    }
}