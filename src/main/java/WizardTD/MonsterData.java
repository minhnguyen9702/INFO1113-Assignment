package WizardTD;
/**
 * The MonsterData class stores information about enemies in the game,
 * including their attributes and quantity.
 */
public class MonsterData {
    private String type;
    private float hp;
    private float speed;
    private float armour;
    private float manaGainedOnKill;
    private int quantity;

    /**
     * Creates a new MonsterData instance with the specified attributes.
     *
     * @param type              The type or name of the monster.
     * @param hp                The hit points (health) of the monster.
     * @param speed             The movement speed of the monster.
     * @param armour            The armor rating of the monster.
     * @param manaGainedOnKill  The amount of mana gained by the player upon killing this monster.
     * @param quantity          The quantity of this type of monster in the Wave.
     */
    public MonsterData (String type, float hp, float speed, 
        float armour, float manaGainedOnKill, int quantity) {
        this.type = type;
        this.hp = hp;
        this.speed = speed;
        this.armour = armour;
        this.manaGainedOnKill = manaGainedOnKill;
        this.quantity = quantity;
    }

    /**
     * Returns the type or name of the monster.
     *
     * @return The type or name of the monster.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the hit points (health) of the monster.
     *
     * @return The hit points of the monster.
     */
    public float getHP() {
        return hp;
    }

    /**
     * Returns the movement speed of the monster.
     *
     * @return The movement speed of the monster.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Returns the armour of the monster.
     *
     * @return The armour of the monster.
     */
    public float getArmour() {
        return armour;
    }

    /**
     * Returns the amount of mana gained by the player upon killing this monster.
     *
     * @return The amount of mana gained on kill.
     */
    public float getManaGainedOnKill() {
        return manaGainedOnKill;
    }

    /**
     * Returns the quantity of this type of monster in the wave.
     *
     * @return The quantity of this type of monster.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Decrement the quantity of this type of monster by 1.
     */
    public void quantityDecrement() {
        quantity--;
    }
}
