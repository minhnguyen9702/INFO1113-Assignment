package WizardTD.Tile;

import processing.core.PImage;

public class Grass extends Tile {
    public Grass(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = true;
        canEnemyWalk = false;
    }
}