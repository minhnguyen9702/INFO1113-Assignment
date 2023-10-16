package WizardTD.Button;

/**
 * The Button class stores data about a clickable button in the game. It provides a graphical user interface element
 * that can be clicked by the player. This class is intended to be extended for specific button types.
 */
public abstract class Button {
    protected float x;
    protected float y;
    protected boolean isClicked;
    protected String shortHand;
    protected String label;

    /**
     * Creates a new Button instance with the specified coordinates, shortHand, label, and initial click state.
     *
     * @param x         The x-coordinate of the button.
     * @param y         The y-coordinate of the button.
     * @param shortHand The short-hand identifier for the button.
     * @param label     The label or text associated with the button.
     * @param isClicked The initial click state of the button.
     */
    public Button(float x, float y, String shortHand, String label, boolean isClicked) {
        this.x = x;
        this.y = y;
        this.isClicked = isClicked;
        this.shortHand = shortHand;
        this.label = label;
    }

    /**
     * Returns the x-coordinate of the button.
     *
     * @return The x-coordinate of the button.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the button.
     *
     * @return The y-coordinate of the button.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the short-hand identifier of the button.
     *
     * @return The short-hand identifier of the button.
     */
    public String getShortHand() {
        return shortHand;
    }

    /**
     * Returns the label or text associated with the button.
     *
     * @return The label or text of the button.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Checks if the button is currently clicked.
     *
     * @return true if the button is clicked; otherwise, false.
     */
    public boolean getIsClicked() {
        return isClicked;
    }

    /**
     * Toggles the click state of the button. If the button is not clicked, it will be set to clicked, and vice versa.
     */
    public void isClicked() {
        if (!isClicked) {
            isClicked = true;
        } else {
            isClicked = false;
        }
    }
}
