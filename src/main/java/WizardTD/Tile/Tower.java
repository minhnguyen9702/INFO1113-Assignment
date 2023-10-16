package WizardTD.Tile;

import java.util.ArrayList;

import WizardTD.App;
import WizardTD.Enemy;
import WizardTD.Fireball;

/**
 * The Tower class represents a tower in the game, which can attack enemies within its range.
 * It extends the Tile class. Towers cannot be placed on it and enemies cannot walk on it.
 */
public class Tower extends Tile {
    protected ArrayList<Enemy> enemyInRange = new ArrayList<>();
    protected float range;
    protected float speed;
    protected float damage;
    protected int rangeLevel;
    protected int speedLevel;
    protected int damageLevel;
    protected float timer;

    /**
     * Creates a new Tower instance at the specified coordinates.
     *
     * @param x The x-coordinate of the Tower.
     * @param y The y-coordinate of the Tower.
     */
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
    
    /**
     * Returns the list of enemies within the tower's attack range.
     *
     * @return The list of enemies in range of the tower.
     */
    public ArrayList<Enemy> getEnemyInRange() {
        return enemyInRange;
    }

    /**
     * Checks if the given enemy is in enemyInRange list.
     *
     * @param enemy The enemy to check.
     * @return true if the enemy is in enemyInRange list; otherwise, false.
     */
    public boolean enemyInRangeContains(Enemy enemy) {
        return enemyInRange.contains(enemy);
    }

    /**
     * Adds an enemy to enemyInRange list.
     *
     * @param enemy The enemy to add.
     */
    public void enemyInRangeAdd(Enemy enemy) {
        enemyInRange.add(enemy);
    }

    /**
     * Removes an enemy from the enemyInRange list.
     *
     * @param enemy The enemy to remove.
     */
    public void enemyInRangeRemove(Enemy enemy) {
        enemyInRange.remove(enemy);
    }

    /**
     * Removes a list of enemies from the enemyInRange list.
     *
     * @param enemiesToRemove The list of enemies to remove.
     */
    public void enemyInRangeRemoveAll(ArrayList<Enemy> enemiesToRemove) {
        enemyInRange.removeAll(enemiesToRemove);
    }

    /**
     * Returns the attack range of the tower.
     *
     * @return The attack range of the tower.
     */
    public float getRange() {
        return range;
    }

    /**
     * Returns the attack speed of the tower.
     *
     * @return The attack speed of the tower.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Returns the damage dealt by the tower's attacks.
     *
     * @return The damage dealt by the tower's attacks.
     */
    public float getDamage() {
        return damage;
    }

    /**
     * Upgrades the tower's attack range if sufficient mana is available.
     */
    public void upgradeRange() {
        if (App.currentMana > this.getRangeCost()) {
            App.currentMana -= this.getRangeCost();
            rangeLevel += 1;
            range += 32;
            changeTowerSprite();
        }
    }

    /**
     * Upgrades the tower's attack speed if sufficient mana is available.
     */
    public void upgradeSpeed() {
        if (App.currentMana > this.getSpeedCost())  {
            App.currentMana -= this.getSpeedCost();
            speedLevel += 1;
            speed += 0.5;
            changeTowerSprite();
        }
    }

    /**
     * Upgrades the tower's attack damage if sufficient mana is available.
     */
    public void upgradeDamage() {
        if (App.currentMana > this.getDamageCost())  {
            App.currentMana -= this.getDamageCost();
            damageLevel += 1;
            damage += (damage/2);
            changeTowerSprite();
        }
    }

    /**
     * Returns the current level of the tower's attack range.
     *
     * @return The current range level.
     */
    public int getRangeLevel() {
        return rangeLevel;
    }

    /**
     * Returns the current level of the tower's attack speed.
     *
     * @return The current speed level.
     */
    public int getSpeedLevel() {
        return speedLevel;
    }

    /**
     * Returns the current level of the tower's attack damage.
     *
     * @return The current damage level.
     */
    public int getDamageLevel() {
        return damageLevel;
    }

    /**
     * Returns the mana cost of upgrading the tower's attack range.
     *
     * @return The mana cost of upgrading range.
     */
    public float getRangeCost() {
        return 20 + 10*rangeLevel;
    }

    /**
     * Returns the mana cost of upgrading the tower's attack speed.
     *
     * @return The mana cost of upgrading speed.
     */
    public float getSpeedCost() {
        return 20 + 10*speedLevel;
    }

    /**
     * Returns the mana cost of upgrading the tower's attack damage.
     *
     * @return The mana cost of upgrading damage.
     */
    public float getDamageCost() {
        return 20 + 10*damageLevel;
    }

    /**
     * Returns the base level of the tower (the lowest level that all three upgrades are at).
     *
     * @return The base level of the tower.
     */
    public int getBaseLevel() {
        if (rangeLevel >= 2 && speedLevel >= 2 && damageLevel >= 2) {
            return 2;
        } else if (rangeLevel >= 1 && speedLevel >= 1 && damageLevel >= 1) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Deducts the cost of purchasing a tower from the player's mana when a tower is created.
     */
    public void purchaseTower() {
        App.currentMana -= App.towerCost;
    }

    /**
     * Changes the tower's sprite based on its base level.
     */
    public void changeTowerSprite() {
        if (this.getBaseLevel() == 2) {
            this.sprite = App.tower2Sprite;
        } else if (this.getBaseLevel() == 1) {
            this.sprite = App.tower1Sprite;
        } else {
            this.sprite = App.tower0Sprite;
        }
    }

    /**
     * Updates the tower's behavior and attacks enemies within its range with fireballs
     * depending on the tower's attack speed.
     */
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
