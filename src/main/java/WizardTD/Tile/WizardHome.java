package WizardTD.Tile;

import processing.core.PImage;

/**
 * The WizardHome class extends the Tile class and is the endpoint for all enemy
 * paths in the game. It allows enemy movement but is not suitable for tower placement.
 */
public class WizardHome extends Tile {
    
    /**
     * Creates a new WizardHome instance with the specified coordinates and sprite.
     *
     * @param x      The x-coordinate of the WizardHome.
     * @param y      The y-coordinate of the WizardHome.
     * @param sprite The PImage object acting as the tile's sprite.
     */
    public WizardHome(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = true;
    }
}