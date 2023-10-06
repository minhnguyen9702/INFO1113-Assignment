package WizardTD.Button;

import WizardTD.App;

public class GameSpeedButton extends Button {
    public GameSpeedButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
    }

    public void changeGameSpeed() {
        if (App.isPaused) {
            App.gameSpeed = 0;
        } else if (App.isFastForward) {
            App.gameSpeed = 2;
        } else {
            App.gameSpeed = 1;
        }
        System.out.println(App.gameSpeed);
    }
}
