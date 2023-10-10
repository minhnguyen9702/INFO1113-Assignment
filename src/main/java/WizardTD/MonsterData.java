package WizardTD;

public class MonsterData {
    private String type;
    private float hp;
    private float speed;
    private float armour;
    private float manaGainedOnKill;
    private int quantity;

    public MonsterData (String type, float hp, float speed, float armour, float manaGainedOnKill, int quantity) {
        this.type = type;
        this.hp = hp;
        this.speed = speed;
        this.armour = armour;
        this.manaGainedOnKill = manaGainedOnKill;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public float getHP() {
        return hp;
    }

    public float getSpeed() {
        return speed;
    }

    public float getArmour() {
        return armour;
    }

    public float getManaGainedOnKill() {
        return manaGainedOnKill;
    }

    public float getQuantity() {
        return quantity;
    }

    public void quantityDecrement() {
        quantity--;
    }
}
