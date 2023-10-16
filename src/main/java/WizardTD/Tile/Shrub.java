package WizardTD.Tile;

import processing.core.PImage;

/**
 * The Shrub class extends the Tile class and represents a decorative tile that is
 * neither suitable for tower placement nor enemy movement.
 */
public class Shrub extends Tile {

    /**
     * Creates a new Shrub tile instance with the specified coordinates and sprite.
     *
     * @param x      The x-coordinate of the Shrub tile.
     * @param y      The y-coordinate of the Shrub tile.
     * @param sprite The PImage object acting as the tile's sprite.
     */
    public Shrub(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = false;
    }
}