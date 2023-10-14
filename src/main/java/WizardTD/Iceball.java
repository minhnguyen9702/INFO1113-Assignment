package WizardTD;

public class Iceball extends Fireball {
    public Iceball (float startX, float startY, Enemy target, float damage) {
        super(startX, startY, target, damage);
        this.sprite = App.iceballSprite;
        isCollided = false;
    }
}
