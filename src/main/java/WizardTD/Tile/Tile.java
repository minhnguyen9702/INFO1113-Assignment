package WizardTD.Tile;

import WizardTD.Entity;
import processing.core.PImage;

public abstract class Tile extends Entity {
    protected boolean isTowerPlaceable;
    protected boolean canEnemyWalk;

    public Tile(float x, float y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public Tile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isTowerPlaceable(){
        return isTowerPlaceable;
    }

    public boolean canEnemyWalk(){
        return canEnemyWalk;
    }

    public int getRow() {
        return (int)(y-40)/32;
    }

    public int getCol() {
        return (int)x/32;
    }
}

