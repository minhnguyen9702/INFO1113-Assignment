package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Entity {
    protected float x;
    protected float y;
    protected PImage sprite;

    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    public void draw(PApplet app) {
        app.image(sprite, x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
