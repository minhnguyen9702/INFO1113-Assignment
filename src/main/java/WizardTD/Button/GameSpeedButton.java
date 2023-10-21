package WizardTD.Button;

import WizardTD.App;

/**
 * The GameSpeedButton class extends the button class and allows the player to change the game's speed.
 */
public class GameSpeedButton extends Button {
    
    /**
     * Creates a new GameSpeedButton instance with the specified coordinates, shortHand, label, and initial click state.
     *
     * @param x         The x-coordinate of the button.
     * @param y         The y-coordinate of the button.
     * @param shortHand The short-hand identifier for the button.
     * @param label     The label or text associated with the button.
     * @param isClicked The initial click state of the button.
     */
    public GameSpeedButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
    }

    /**
     * Changes the game speed based on the current game speed state (paused, fast forward, or normal speed).
     * It updates the gameSpeed variable in the App class.
     */
    public void changeGameSpeed() {
        if (App.isPaused) {
            App.gameSpeed = 0;
        } else if (App.isFastForward) {
            App.gameSpeed = 2;
        } else {
            App.gameSpeed = 1;
        }
    }
}
