package WizardTD.Button;

import WizardTD.App;

/**
 * The ManaButton class extends the button class and allows the player to use a mana pool spell, increasing their
 * mana cap and mana gained per second whilst also deducting the corresponding cost from their current mana.
 */
public class ManaButton extends Button {

    /**
     * Creates a new ManaButton instance with the specified coordinates, shortHand, label, and initial click state.
     *
     * @param x         The x-coordinate of the button.
     * @param y         The y-coordinate of the button.
     * @param shortHand The short-hand identifier for the button.
     * @param label     The label or text associated with the button.
     * @param isClicked The initial click state of the button.
     */
    public ManaButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
    }

    /**
     * Performs the action associated with the ManaButton when clicked. It increases the player's mana cap,
     * and the mana gained per second whislt also deducting the cost from the player's mana pool.
     */
    @Override
    public void isClicked() {
        if (App.currentMana > App.manaPoolSpellCost) {
            App.currentMana -= App.manaPoolSpellCost;
            App.manaPoolSpellCost += App.manaPoolSpellCostIncreasePerUse;
            App.currentManaCap *= App.manaPoolSpellCapMultiplier;
            App.currentManaGainedPerSecond = Math.round(App.currentManaGainedPerSecond*App.manaPoolSpellManaGainedMultiplier*10)/10;
        }
    }
}