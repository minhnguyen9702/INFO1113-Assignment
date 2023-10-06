package WizardTD.Tile;

import processing.core.PImage;

public class SpawnTile extends PathTile {
    public SpawnTile(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = true;
    }
}
