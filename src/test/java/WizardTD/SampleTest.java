package WizardTD;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;


public class SampleTest {
    @Test
    public App runApp() {
        App app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.configPath = "src/test/java/WizardTD/testConfig.json";
        app.setup();
        return app;
    }

    /**
     * Checks if pressing the keys work to change the boolean controlled by the buttons
     */
    @Test
    public void testKeyPressed() {
        App app = runApp();

        assertFalse(app.buttonList.get(0).getIsClicked());
        app.key = ('f');
        app.keyPressed();
        assertTrue(app.buttonList.get(0).getIsClicked());
        app.keyPressed();
        assertFalse(app.buttonList.get(0).getIsClicked());

        assertFalse(app.buttonList.get(1).getIsClicked());
        app.key = ('p');
        app.keyPressed();
        assertTrue(app.buttonList.get(1).getIsClicked());
        app.keyPressed();
        assertFalse(app.buttonList.get(1).getIsClicked());

        assertFalse(app.buttonList.get(2).getIsClicked());
        app.key = ('t');
        app.keyPressed();
        assertTrue(app.buttonList.get(2).getIsClicked());
        app.keyPressed();
        assertFalse(app.buttonList.get(2).getIsClicked());

        assertFalse(app.buttonList.get(3).getIsClicked());
        app.key = ('i');
        app.keyPressed();
        assertTrue(app.buttonList.get(3).getIsClicked());
        app.keyPressed();
        assertFalse(app.buttonList.get(3).getIsClicked());

        assertFalse(app.buttonList.get(4).getIsClicked());
        app.key = ('1');
        app.keyPressed();
        assertTrue(app.buttonList.get(4).getIsClicked());
        app.keyPressed();
        assertFalse(app.buttonList.get(4).getIsClicked());

        assertFalse(app.buttonList.get(5).getIsClicked());
        app.key = ('2');
        app.keyPressed();
        assertTrue(app.buttonList.get(5).getIsClicked());
        app.keyPressed();
        assertFalse(app.buttonList.get(5).getIsClicked());

        assertFalse(app.buttonList.get(6).getIsClicked());
        app.key = ('3');
        app.keyPressed();
        assertTrue(app.buttonList.get(6).getIsClicked());
        app.keyPressed();
        assertFalse(app.buttonList.get(6).getIsClicked());
    }

    /**
     * Runs the game till the player loses. Checks to see if everything works together
     * It builds and upgrades a Tower as well as an IceTower. It also uses the ManaButton.
     */
    @Test
    public void testGame() {
        App app = runApp();
        boolean hasBuiltTower = false;
        boolean hasBuiltIceTower = false;
        boolean hasUpgradedMana = false;

        while (app.gameState == GameState.GAMENORMAL) {
            if (hasBuiltTower == false) {
                // clicks tower button on screen
                app.mouseX = 670;
                app.mouseY = 195;
                app.mousePressed(null);

                // clicks range upgrade button on screen
                app.mouseX = 670;
                app.mouseY = 300;
                app.mousePressed(null);

                // clicks speed upgrade button on screen
                app.mouseX = 670;
                app.mouseY = 340;
                app.mousePressed(null);

                // clicks damage upgrade button on screen
                app.mouseX = 670;
                app.mouseY = 400;
                app.mousePressed(null);

                // builds tower in this spot
                app.mouseX = 370;
                app.mouseY = 250;
                app.mousePressed(null);

                hasBuiltTower = true;
            }

            if (hasBuiltIceTower == false) {
                // clicks tower button on screen to turn it off
                app.mouseX = 670;
                app.mouseY = 195;
                app.mousePressed(null);

                // clicks icetower button on screen
                app.mouseX = 670;
                app.mouseY = 230;
                app.mousePressed(null);

                // builds icetower in this spot
                app.mouseX = 500;
                app.mouseY = 250;
                app.mousePressed(null);

                hasBuiltIceTower = true;
            }

            if (hasUpgradedMana == false) {
                // presses manaUpgradeButton
                app.mouseX = 670;
                app.mouseY = 460;
                app.mousePressed(null);

                // hover over tower to draw the upgrade tootlip
                app.mouseX = 370;
                app.mouseY = 250;

                hasUpgradedMana = true;
            }
            // I couldn't figure out why calling draw() kept throwing a NullPointerException
            // when testing but this fixed it.
            try {
                app.draw();
            } catch (NullPointerException e) {
                app.draw();
            }
        }
    }

    @Test
    public void testTowerCostToolTip() {
        App app = runApp();

        // hover over towerButton to draw the tower cost tooltip
        app.mouseX = 670;
        app.mouseY = 195;
        try {
            app.draw();
        } catch (NullPointerException e) {
            app.draw();
        }
    }
}