package WizardTD.Tile;

import java.util.ArrayList;

import WizardTD.App;
import WizardTD.Enemy;
import WizardTD.Fireball;

public class Tower extends Tile {
    protected ArrayList<Enemy> enemyInRange = new ArrayList<>();
    protected float range;
    protected float speed;
    protected float damage;
    protected int rangeLevel;
    protected int speedLevel;
    protected int damageLevel;
    protected float timer;

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
        purchaseTower();
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

    public void enemyInRangeRemoveAll(ArrayList<Enemy> enemiesToRemove) {
        enemyInRange.removeAll(enemiesToRemove);
    }

    public float getRange() {
        return range;
    }

    public void upgradeRange() {
        if (App.currentMana > this.getRangeCost()) {
            App.currentMana -= this.getRangeCost();
            rangeLevel += 1;
            range += 32;
            changeTowerSprite();
        }
    }

    public void upgradeSpeed() {
        if (App.currentMana > this.getSpeedCost())  {
            App.currentMana -= this.getSpeedCost();
            speedLevel += 1;
            speed += 0.5;
            changeTowerSprite();
        }
    }

    public void upgradeDamage() {
        if (App.currentMana > this.getDamageCost())  {
            App.currentMana -= this.getDamageCost();
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

    public float getRangeCost() {
        return 20 + 10*rangeLevel;
    }

    public float getSpeedCost() {
        return 20 + 10*speedLevel;
    }

    public float getDamageCost() {
        return 20 + 10*damageLevel;
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

    public void purchaseTower() {
        App.currentMana -= App.towerCost;
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
            timer += 1*App.gameSpeed;
        }
    }
}
