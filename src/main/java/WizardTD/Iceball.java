package WizardTD;

/**
 * The Iceball class extends Fireball and is projectile that is fired 
 * at a target of the Enemy class. It tracks the target and has freezing properties, 
 * reducing the target's movement speed upon collision.
 */
public class Iceball extends Fireball {
    /**
     * Creates a new Iceball instance with the specified attributes.
     *
     * @param startX  The starting X-coordinate of the iceball.
     * @param startY  The starting Y-coordinate of the iceball.
     * @param target  The enemy target that the iceball is directed towards.
     * @param damage  The amount of damage the iceball will inflict upon collision.
     */
    public Iceball (float startX, float startY, Enemy target, float damage) {
        super(startX, startY, target, damage);
        this.sprite = App.iceballSprite;
        isCollided = false;
    }

    /**
     * Inflicts damage on the target Enemy, marks the Iceball as collided
     * and causes the enemy to freeze. This method is called when the 
     * Iceball hits its intended target.
     */
    @Override
    public void hitEnemy() {
        target.freeze();
        target.takeDamage(damage);
        isCollided = true;
    }
}
