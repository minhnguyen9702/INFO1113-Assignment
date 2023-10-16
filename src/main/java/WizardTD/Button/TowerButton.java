package WizardTD.Button;

import WizardTD.App;

/**
 * The TowerButton class extends the Button class and is toggle the ability
 * to purchase a tower in the game. it also allows specifying the tower's price.
 */
public class TowerButton extends Button {
    private float towerPrice;

    /**
     * Creates a new TowerButton instance with the specified coordinates, shortHand, label, and initial click state.
     * The tower's price is set to the default tower cost defined in the App class.
     *
     * @param x         The x-coordinate of the button.
     * @param y         The y-coordinate of the button.
     * @param shortHand The short-hand identifier for the button.
     * @param label     The label or text associated with the button.
     * @param isClicked The initial click state of the button.
     */
    public TowerButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
        this.towerPrice = App.towerCost;
    }

    /**
     * Creates a new TowerButton instance with the specified coordinates, shortHand, label, initial click state, and
     * the tower's price.
     *
     * @param x         The x-coordinate of the button.
     * @param y         The y-coordinate of the button.
     * @param shortHand The short-hand identifier for the button.
     * @param label     The label or text associated with the button.
     * @param isClicked The initial click state of the button.
     * @param towerPrice The price of the tower associated with the button.
     */
    public TowerButton(float x, float y, String shortHand, String label, boolean isClicked, float towerPrice) {
        super(x, y, shortHand, label, isClicked);
        this.towerPrice = towerPrice;
    }


    /**
     * Returns the price of the tower associated with the button.
     *
     * @return The price of the tower.
     */
    public float getTowerPrice() {
        return towerPrice;
    }
}
