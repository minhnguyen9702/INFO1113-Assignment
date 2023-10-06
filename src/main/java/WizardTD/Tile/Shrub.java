package WizardTD.Tile;

import processing.core.PImage;

public class Shrub extends Tile {
    public Shrub(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = false;
    }
}