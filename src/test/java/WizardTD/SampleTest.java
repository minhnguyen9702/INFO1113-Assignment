package WizardTD;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processing.core.PApplet;

public class SampleTest {
    public static App app;

    @Test
    public void simpleTest() {
        
    }

    @BeforeAll
    public static void testApp() {
        app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
    }
}
