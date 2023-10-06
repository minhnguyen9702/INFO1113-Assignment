package WizardTD.Tile;

import java.util.ArrayList;

import WizardTD.App;
import WizardTD.Enemy;
import WizardTD.Fireball;

public class Tower extends Tile {
    private ArrayList<Enemy> enemyInRange = new ArrayList<>();
    private float range;
    private float speed;
    private float damage;
    private int rangeLevel;
    private int speedLevel;
    private int damageLevel;
    private float timer;

    public Tower(float x, float y) {
        super(x, y);
        this.sprite = App.tower0Sprite;
        this.rangeLevel = 0;
        this.speedLevel = 0;
        this.damageLevel = 0;
        this.range = App.initialTowerRange;
        this.speed = App.initialTowerSpeed;
        this.damage = App.initialTowerDamage;

        isTowerPlaceable = false;
        canEnemyWalk = false;
    }
    
    public ArrayList<Enemy> getEnemyInRange() {
        return enemyInRange;
    }

    public boolean enemyInRangeContains(Enemy enemy) {
        return enemyInRange.contains(enemy);
    }

    public void enemyInRangeAdd(Enemy enemy) {
        enemyInRange.add(enemy);
    }

    public void enemyInRangeRemove(Enemy enemy) {
        enemyInRange.remove(enemy);
    }

    public float getRange() {
        return range;
    }

    public void upgradeRange() {
        if (rangeLevel < 3 && App.initialMana > 20 + 10*rangeLevel) {
            rangeLevel += 1;
            range += 32;
            changeTowerSprite();
        }
    }

    public void upgradeSpeed() {
        if (speedLevel < 3 && App.initialMana > 20 + 10*speedLevel)  {
            speedLevel += 1;
            speed += 0.5;
            changeTowerSprite();
        }
    }

    public void upgradeDamage() {
        if (damageLevel < 3 && App.initialMana > 20 + 10*damageLevel)  {
            damageLevel += 1;
            damage += (damage/2);
            changeTowerSprite();
        }
    }

    public int getRangeLevel() {
        return rangeLevel;
    }

    public int getSpeedLevel() {
        return speedLevel;
    }

    public int getDamageLevel() {
        return damageLevel;
    }

    public int getBaseLevel() {
        if (rangeLevel >= 2 && speedLevel >= 2 && damageLevel >= 2) {
            return 2;
        } else if (rangeLevel >= 1 && speedLevel >= 1 && damageLevel >= 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void changeTowerSprite() {
        if (this.getBaseLevel() == 2) {
            this.sprite = App.tower2Sprite;
        } else if (this.getBaseLevel() == 1) {
            this.sprite = App.tower1Sprite;
        } else {
            this.sprite = App.tower0Sprite;
        }
    }

    public void tick() {
        if (enemyInRange.size() == 0) {
            timer = 60/speed;
        }
        if (enemyInRange.size() != 0) {
            if (timer >= 60/speed) {
                timer = 0;
                App.fireballList.add(new Fireball(this.x+16, this.y+16, enemyInRange.get(0), damage));
            }
            timer++;
        }
    }
}
