package WizardTD.Tile;

import WizardTD.App;
import WizardTD.Iceball;
import java.util.Random;;

public class IceTower extends Tower {
    public Random random = new Random();

    public IceTower(float x, float y) {
        super(x, y);
        this.sprite = App.icetower0Sprite;
        this.rangeLevel = 0;
        this.speedLevel = 0;
        this.damageLevel = 0;
        this.range = App.initialTowerRange - 32;
        this.speed = App.initialTowerSpeed * 2;
        this.damage = App.initialTowerDamage / 2;

        isTowerPlaceable = false;
        canEnemyWalk = false;
    }

    public void changeTowerSprite() {
        if (this.getBaseLevel() == 2) {
            this.sprite = App.icetower2Sprite;
        } else if (this.getBaseLevel() == 1) {
            this.sprite = App.icetower1Sprite;
        } else {
            this.sprite = App.icetower0Sprite;
        }
    }

    public void tick() {
        if (enemyInRange.size() == 0) {
            timer = 60/speed;
        }
        if (enemyInRange.size() != 0) {
            if (timer >= 60/speed) {
                timer = 0;
                App.fireballList.add(new Iceball(this.x+16, this.y+16, enemyInRange.get(random.nextInt(enemyInRange.size())), damage));
            }
            timer += 1*App.gameSpeed;
        }
    }
}
