package WizardTD;

public class ManaButton extends Button {
    ManaButton(float x, float y, String shortHand, String label, boolean isClicked) {
        super(x, y, shortHand, label, isClicked);
    }

    public void isClicked() {
        if (App.initialMana > App.manaPoolSpellInitialCost) {
            App.initialMana -= App.manaPoolSpellInitialCost;
            App.manaPoolSpellInitialCost += App.manaPoolSpellCostIncreasePerUse;
            App.initialManaCap *= App.manaPoolSpellCapMultiplier;
            App.initialManaGainedPerSecond *= App.manaPoolSpellManaGainedMultiplier;
        }
    }
}