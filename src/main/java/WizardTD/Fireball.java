package WizardTD;

/**
 * The Fireball class is projectile that is fired at a target of the Enemy class.
 * It tracks the target and inflicts damage when it collides with it.
 */
public class Fireball extends Entity {
    protected Enemy target;
    protected float targetX;
    protected float targetY;
    protected float damage;
    protected boolean isCollided;

    /**
     * Creates a new Fireball instance with the specified attributes.
     *
     * @param startX  The starting X-coordinate of the fireball.
     * @param startY  The starting Y-coordinate of the fireball.
     * @param target  The enemy target that the fireball is directed towards.
     * @param damage  The amount of damage the fireball will inflict upon collision.
     */
    public Fireball (float startX, float startY, Enemy target, float damage) {
        this.sprite = App.fireballSprite;
        this.target = target;
        this.x = startX;
        this.y = startY;
        this.damage = damage;
        isCollided = false;
    }

    /**
     * Checks if the fireball has collided with the target.
     *
     * @return True if the fireball has collided with the target, otherwise false.
     */
    public boolean getIsCollided() {
        return isCollided;
    }

    /**
    * Moves the fireball towards its target;
    */
    public void move() {
        targetX = target.getX()+10;
        targetY = target.getY()+10;
        float speed = 5*App.gameSpeed;    

        float directionX = targetX - this.x;
        float directionY = targetY - this.y;
  
        float magnitude = (float)Math.sqrt(directionX * directionX + directionY * directionY);
  
        float vx = (directionX / magnitude) * speed;
        float vy = (directionY / magnitude) * speed;
  
        this.x += vx;
        this.y += vy;
    }

    /**
    * Updates the position using the move() function and
    * checks if the fireball has collided with the target
    * every game tick.
    */
    public void tick() {
        move();
        if(this.x > (targetX-10) && this.x < (targetX-10) + 20){
	        if(this.y > (targetY-10) && this.y < (targetY-10) + 20){
                target.takeDamage(damage);
                isCollided = true;
	        }
        }
    }
}