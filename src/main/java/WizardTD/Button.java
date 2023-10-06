package WizardTD;

public class Button {
    protected float x;
    protected float y;
    protected boolean isClicked;
    protected String shortHand;
    protected String label;

    public Button(float x, float y, String shortHand, String label, boolean isClicked) {
        this.x = x;
        this.y = y;
        this.isClicked = isClicked;
        this.shortHand = shortHand;
        this.label = label;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getShortHand() {
        return shortHand;
    }

    public String getLabel() {
        return label;
    }

    public boolean getIsClicked() {
        return isClicked;
    }

    public void isClicked() {
        if (!isClicked) {
            isClicked = true;
        } else {
            isClicked = false;
        }
    }
}
