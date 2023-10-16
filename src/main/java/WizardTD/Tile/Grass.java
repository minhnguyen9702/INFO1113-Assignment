package WizardTD.Tile;

import processing.core.PImage;

/**
 * The Grass class extends the Tile class and is suitable for tower placement
 * but not for enemy movement.
 */
public class Grass extends Tile {

    /**
     * Creates a new Grass tile instance with the specified coordinates and sprite.
     *
     * @param x      The x-coordinate of the Grass tile.
     * @param y      The y-coordinate of the Grass tile.
     * @param sprite The PImage object acting as the tile's sprite.
     */
    public Grass(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = true;
        canEnemyWalk = false;
    }
}