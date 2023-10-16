package WizardTD.Button;

/**
 * The UpgradeButton class extends the Button class and is
 * used to toggle the ability to upgrade towers in the game.
 */
public class UpgradeButton extends Button {

    /**
     * Creates a new UpgradeButton instance with the specified coordinates, shortHand, label, and initial click state.
     *
     * @param x         The x-coordinate of the button.
     * @param y         The y-coordinate of the button.
     * @param shortHand The short-hand identifier for the button.
     * @param label     The label or text associated with the button.
     * @param isClicked The initial click state of the button.
     */
    public UpgradeButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
    }
}