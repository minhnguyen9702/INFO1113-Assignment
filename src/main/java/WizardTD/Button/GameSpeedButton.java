package WizardTD.Button;

import WizardTD.App;

/**
 * The GameSpeedButton class represents a button that allows the player to change the game's speed.
 * It is a specialized button that extends the Button class.
 */
public class GameSpeedButton extends Button {
    public GameSpeedButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
    }

    /**
     * Changes the game speed based on the current game state (paused, fast forward, or normal speed).
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
