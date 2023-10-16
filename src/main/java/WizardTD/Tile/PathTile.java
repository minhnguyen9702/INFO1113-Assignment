package WizardTD.Tile;

import processing.core.PImage;

/**
 * The PathTile class extends the Tile class and is suitable for enemy movement 
 * but not for tower placement.
 */
public class PathTile extends Tile {
    
    /**
     * Creates a new PathTile instance with the specified coordinates and sprite.
     *
     * @param x      The x-coordinate of the PathTile.
     * @param y      The y-coordinate of the PathTile.
     * @param sprite The PImage object acting as the tile's sprite.
     */
    public PathTile(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = true;
    }
}
