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
     * Updates the position of the iceball in every game tick by calling move().
     * Applies freezing effects to the target upon collision.
     */
    @Override
    public void tick() {
        move();
        if(this.x > (targetX-10) && this.x < (targetX-10) + 20){
	        if(this.y > (targetY-10) && this.y < (targetY-10) + 20){
                target.freeze();
                target.takeDamage(damage);
                isCollided = true;
	        }
        }
    }
}
