package WizardTD;

import java.util.ArrayList;
import java.util.List;

import WizardTD.Tile.Tile;
import processing.core.PImage;

public class Enemy extends Entity {

    private int pathIndex = 1;
    private int spriteIndex = 0;
    private List<Tile> path;
    private ArrayList<PImage> spriteSheet;
    private float currentHitPoints;
    private float maxHitPoints;
    private float movementSpeed;
    private float armor;
    private float manaGainedOnKill;
    private boolean isPlayingDeathAnimation;
    private boolean isAlive;
    private float timer;

    public Enemy(ArrayList<PImage> spriteSheet, List<Tile> path) {
        int yOffset = 0;
        int xOffset = 0;
        if (path.get(0).getRow() == 0) {
            yOffset = -32;
        }
        if (path.get(0).getRow() == 19) {
            yOffset = 32;
        }
        if (path.get(0).getCol() == 0) {
            xOffset = -32;
        }
        if (path.get(0).getCol() == 19) {
            xOffset = 32;
        }
        this.x = path.get(0).getX() + 6 + xOffset;
        this.y = path.get(0).getY() + 6 + yOffset;
        this.spriteSheet = spriteSheet;
        this.sprite = spriteSheet.get(spriteIndex);
        this.path = path;
        this.currentHitPoints = 100;
        this.maxHitPoints = 100;
        this.movementSpeed = 1;
        this.armor = (float) 0.5;
        this.manaGainedOnKill = 10;
        this.isPlayingDeathAnimation = false;
        this.isAlive = true;
        this.timer = 0;
    }

    public void takeDamage(float damage) {
        currentHitPoints -= damage * armor;
    }

    public float getCurrentHitPoints() {
        return currentHitPoints;
    }

    public float getMaxHitPoints() {
        return maxHitPoints;
    }

    public boolean isPlayingDeathAnimation() {
        return isPlayingDeathAnimation;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void move() {
        if (pathIndex == path.size() - 1) {
            if (Math.abs(x - (path.get(pathIndex).getX() + 14)) <= movementSpeed &&
                Math.abs(y - (path.get(pathIndex).getY() + 14)) <= movementSpeed) {
                x = path.get(pathIndex).getX() + 14;
                y = path.get(pathIndex).getY() + 14;
            } else {
                if (x < path.get(pathIndex).getX() + 14) {
                    x += movementSpeed * App.gameSpeed;
                }
                if (x > path.get(pathIndex).getX() + 14) {
                    x -= movementSpeed * App.gameSpeed;
                }
                if (y < path.get(pathIndex).getY() + 14) {
                    y += movementSpeed * App.gameSpeed;
                }
                if (y > path.get(pathIndex).getY() + 14) {
                    y -= movementSpeed * App.gameSpeed;
                }
            }
        } else {
            if (Math.abs(x - (path.get(pathIndex).getX() + 6)) <= movementSpeed &&
                Math.abs(y - (path.get(pathIndex).getY() + 6)) <= movementSpeed) {
                x = path.get(pathIndex).getX() + 6;
                y = path.get(pathIndex).getY() + 6;
                pathIndex++;
            } else {
                if (x < path.get(pathIndex).getX() + 6) {
                    x += movementSpeed * App.gameSpeed;
                }
                if (x > path.get(pathIndex).getX() + 6) {
                    x -= movementSpeed * App.gameSpeed;
                }
                if (y < path.get(pathIndex).getY() + 6) {
                    y += movementSpeed * App.gameSpeed;
                }
                if (y > path.get(pathIndex).getY() + 6) {
                    y -= movementSpeed * App.gameSpeed;
                }
            }
        }
    }

    public void tick() {
        this.move();
        if (currentHitPoints <= 0) {
            isPlayingDeathAnimation = true;
            movementSpeed = 0;
            timer += 1 * App.gameSpeed;
            if (timer >= 4 && spriteIndex < spriteSheet.size()) {
                sprite = spriteSheet.get(spriteIndex);
                timer = 0;
                spriteIndex++;
                if (spriteIndex >= spriteSheet.size()) {
                    App.currentMana += manaGainedOnKill;
                    isAlive = false;
                }
            }
        }
    }
}
