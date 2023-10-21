package WizardTD.Tile;

import WizardTD.App;
import WizardTD.Fireball;
import WizardTD.Iceball;
import java.util.Random;;

/**
* The IceTower class represents an ice-themed tower in the game, which can slow and attack enemies within its range.
* It is a specialized tower that extends the Tower class.
*/
public class IceTower extends Tower {
    public Random random = new Random();

    /**
     * Creates a new IceTower instance at the specified coordinates.
     *
     * @param x The x-coordinate of the IceTower.
     * @param y The y-coordinate of the IceTower.
     */
    public IceTower(float x, float y) {
        super(x, y);
        this.sprite = App.icetower0Sprite;
        this.rangeLevel = 0;
        this.speedLevel = 0;
        this.damageLevel = 0;
        this.range = App.initialTowerRange - 32;
        this.speed = App.initialTowerSpeed *3/2;
        this.damage = App.initialTowerDamage *2/3;

        isTowerPlaceable = false;
        canEnemyWalk = false;
    }

    /**
     * Deducts the cost of purchasing an IceTower from the player's mana when 
     * an Icetower is created.
     */
    @Override
    public void purchaseTower() {
        App.currentMana -= App.iceTowerCost;;
    }

    /**
     * Changes the ice tower's sprite based on its base level.
     */
    @Override
    public void changeTowerSprite() {
        if (this.getBaseLevel() == 2) {
            this.sprite = App.icetower2Sprite;
        } else if (this.getBaseLevel() == 1) {
            this.sprite = App.icetower1Sprite;
        } else {
            this.sprite = App.icetower0Sprite;
        }
    }

    /**
     * Shoots a projectile (Iceball) at a random enemy in range.
     * Creates a new Iceball object and adds it to the list of fireballs in the game.
     *
     * @see Iceball
     */
    @Override
    public void shootProjectile() {
        App.fireballList.add(new Iceball(this.x+16, this.y+16, enemyInRange.get(random.nextInt(enemyInRange.size())), damage));
    }
}
