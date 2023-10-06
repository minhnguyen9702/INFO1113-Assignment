package WizardTD.Tile;

import processing.core.PImage;

public class WizardHome extends Tile {
    public WizardHome(float x, float y, PImage sprite) {
        super(x, y, sprite);

        isTowerPlaceable = false;
        canEnemyWalk = true;
    }
}