package WizardTD;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import WizardTD.Tile.Grass;
import WizardTD.Tile.Tower;
import processing.core.PApplet;
import processing.core.PImage;


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

class GrassTest {

    private Grass grass;
    private PImage sprite;

    public void setUp() {
        float x = 10.0f;
        float y = 20.0f;
        grass = new Grass(x, y, sprite);
    }

    @Test
    public void testIsTowerPlaceable() {
        assertTrue(grass.isTowerPlaceable());
    }

    @Test
    public void testCanEnemyWalk() {
        assertFalse(grass.canEnemyWalk());
    }

    @Test
    public void testGetX() {
        float expectedX = 10.0f;
        float actualX = grass.getX();
        assertEquals(expectedX, actualX, 0.01);
    }

    @Test
    public void testGetY() {
        float expectedY = 20.0f;
        float actualY = grass.getY();
        assertEquals(expectedY, actualY, 0.01);
    }
}

class TowerTest {

    private Tower tower;

    public void setUp() {
        float x = 10.0f;
        float y = 20.0f;

        tower = new Tower(x, y);
    }

    @Test
    public void testUpgradeRange() {
        float initialRange = tower.getRange();
        tower.upgradeRange();
        float newRange = tower.getRange();

        assertEquals(initialRange + 32.0f, newRange, 0.01);
    }

    @Test
    public void testUpgradeSpeed() {
        float initialSpeed = tower.getSpeed();
        tower.upgradeSpeed();
        float newSpeed = tower.getSpeed();

        assertEquals(initialSpeed + 0.5f, newSpeed, 0.01);
    }

    @Test
    public void testUpgradeDamage() {
        float initialDamage = tower.getDamage();
        tower.upgradeDamage();
        float newDamage = tower.getDamage();

        assertEquals(initialDamage * 1.5f, newDamage, 0.01);
    }

    @Test
    public void testGetRangeCost() {
        // Test that the range cost is calculated correctly
        int rangeLevel = tower.getRangeLevel();
        float expectedCost = 20 + 10 * rangeLevel;
        float actualCost = tower.getRangeCost();

        assertEquals(expectedCost, actualCost, 0.01); // Use the appropriate delta value
    }

    // Add more test methods for other functionality as needed

    @Test
    public void testGetBaseLevel() {
        // Test that the base level is determined correctly
        tower.upgradeRange();
        tower.upgradeSpeed();
        tower.upgradeDamage();

        int expectedBaseLevel = 2;
        int actualBaseLevel = tower.getBaseLevel();

        assertEquals(expectedBaseLevel, actualBaseLevel);
    }
}