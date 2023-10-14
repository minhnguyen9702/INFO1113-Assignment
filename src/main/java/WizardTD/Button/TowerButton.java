package WizardTD.Button;

import WizardTD.App;

public class TowerButton extends Button {
    private float towerPrice;

    public TowerButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
        this.towerPrice = App.towerCost;
    }

    public TowerButton(float x, float y, String shortHand, String label, boolean isClicked, float towerPrice) {
        super(x, y, shortHand, label, isClicked);
        this.towerPrice = towerPrice;
    }

    public float getTowerPrice() {
        return towerPrice;
    }
}
