package WizardTD;

public class Iceball extends Fireball {
    public Iceball (float startX, float startY, Enemy target, float damage) {
        super(startX, startY, target, damage);
        this.sprite = App.iceballSprite;
        isCollided = false;
    }

    @Override
    public void tick() {
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

        if(this.x > (targetX-10) && this.x < (targetX-10) + 20){
	        if(this.y > (targetY-10) && this.y < (targetY-10) + 20){
                target.freeze();
                target.takeDamage(damage);
                isCollided = true;
	        }
        }
    }
}
