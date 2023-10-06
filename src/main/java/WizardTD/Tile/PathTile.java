package WizardTD.Tile;

import processing.core.PImage;

public class PathTile extends Tile {
    public PathTile(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = true;
    }
}
