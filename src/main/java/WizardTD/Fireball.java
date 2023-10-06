package WizardTD;

public class Fireball extends Entity {
    private Enemy target;
    private float targetX;
    private float targetY;
    private boolean isCollided;

    public Fireball (float startX, float startY, Enemy target, float damage) {
        this.sprite = App.fireballSprite;
        this.target = target;
        this.x = startX;
        this.y = startY;
        isCollided = false;
    }

    public boolean getIsCollided() {
        return isCollided;
    }

    public void tick() {
        targetX = target.getX()+10;
        targetY = target.getY()+10;
        float speed = (float)5.0;    

        float directionX = targetX - this.x;
        float directionY = targetY - this.y;
  
        float magnitude = (float)Math.sqrt(directionX * directionX + directionY * directionY);
  
        float vx = (directionX / magnitude) * speed;
        float vy = (directionY / magnitude) * speed;
  
        this.x += vx;
        this.y += vy;

        if(this.x > (targetX-10) && this.x < (targetX-10) + 20){
	        if(this.y > (targetY-10) && this.y < (targetY-10) + 20){
                isCollided = true;
	        }
        }
    }
}