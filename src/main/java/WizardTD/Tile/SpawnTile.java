package WizardTD.Tile;

import processing.core.PImage;

/**
 * The SpawnTile class extends the PathTile class and represents a tile used as the starting point
 * for enemy spawns. It allows enemy movement but is not suitable for tower placement.
 */
public class SpawnTile extends PathTile {

    /**
     * Creates a new SpawnTile instance with the specified coordinates and sprite.
     *
     * @param x      The x-coordinate of the SpawnTile.
     * @param y      The y-coordinate of the SpawnTile.
     * @param sprite The PImage object acting as the tile's sprite.
     */
    public SpawnTile(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = true;
    }
}
