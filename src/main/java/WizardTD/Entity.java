package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * The abstract base class for all entities that have a sprite in the WizardTD.
 */
public abstract class Entity {
    protected float x;
    protected float y;
    protected PImage sprite;


    /**
     * Changes the entity's sprite (image).
     *
     * @param sprite The PImage object that visually represents the entity.
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Draws the entity onscreen using its sprite, x position and y position.
     *
     * @param app The PApplet object responsible for rendering the game.
     */
    public void draw(PApplet app) {
        app.image(sprite, x, y);
    }

    /**
     * Returns the entity's X-coordinate in the game.
     *
     * @return The entity's X-coordinate.
     */
    public float getX() {
        return x;
    }

        /**
     * Returns the entity's Y-coordinate in the game.
     *
     * @return The entity's Y-coordinate.
     */
    public float getY() {
        return y;
    }
}
