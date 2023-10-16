package WizardTD.Tile;

import WizardTD.Entity;
import processing.core.PImage;

/**
 * The Tile class represents a tile in the GameMap, which can be used for
 * towers to be placed on or enemies to walk on.
 */
public abstract class Tile extends Entity {
    protected boolean isTowerPlaceable;
    protected boolean canEnemyWalk;

    /**
     * Creates a new Tile instance with the specified coordinates and sprite.
     *
     * @param x      The x-coordinate of the tile.
     * @param y      The y-coordinate of the tile.
     * @param sprite The PImage object acting as the tile's sprite.
     */
    public Tile(float x, float y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

     /**
     * Creates a new Tile instance with the specified coordinates.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     */
    public Tile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if a tower can be placed on this tile.
     *
     * @return true if a tower can be placed on this tile; otherwise, false.
     */
    public boolean isTowerPlaceable(){
        return isTowerPlaceable;
    }

    /**
     * Checks if an enemy can walk on this tile.
     *
     * @return true if an enemy can walk on this tile; otherwise, false.
     */
    public boolean canEnemyWalk(){
        return canEnemyWalk;
    }

    /**
     * Returns the row index of the tile within the gameMap array.
     *
     * @return The row index of the tile.
     */
    public int getRow() {
        return (int)(y-40)/32;
    }


    /**
     * Returns the column index of the tile within the gameMap array.
     *
     * @return The column index of the tile.
     */
    public int getCol() {
        return (int)x/32;
    }
}

