package WizardTD.Button;

import WizardTD.App;

public class ManaButton extends Button {
    public ManaButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
    }

    public void isClicked() {
        if (App.currentMana > App.manaPoolSpellCost) {
            App.currentMana -= App.manaPoolSpellCost;
            App.manaPoolSpellCost += App.manaPoolSpellCostIncreasePerUse;
            App.currentManaCap *= App.manaPoolSpellCapMultiplier;
            App.currentManaGainedPerSecond *= App.manaPoolSpellManaGainedMultiplier;
        }
    }
}