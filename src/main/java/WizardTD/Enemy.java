package WizardTD;

import java.util.ArrayList;
import java.util.List;

import WizardTD.Tile.Tile;
import processing.core.PImage;

public class Enemy extends Entity {

    private int yOffset = 0;
    private int xOffset = 0;
    private int pathIndex = 1;
    private int spriteIndex = 0;
    private List<Tile> path;
    private ArrayList<PImage> spriteSheet;
    private float currentHitPoints;
    private float maxHitPoints;
    private float initialMovementSpeed;
    private float movementSpeed;
    private float armour;
    private float manaGainedOnKill;
    private boolean isPlayingDeathAnimation;
    private boolean isAlive;
    private boolean isFrozen;
    private float animationTimer;
    private float freezeTimer;

    public Enemy(MonsterData monsterData, List<Tile> path) {
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
        this.path = path;

        this.spriteSheet = App.enemySpriteMap.get(monsterData.getType());
        this.sprite = spriteSheet.get(spriteIndex);
        this.currentHitPoints = monsterData.getHP();
        this.maxHitPoints = monsterData.getHP();
        this.initialMovementSpeed = monsterData.getSpeed();
        this.movementSpeed = monsterData.getSpeed();
        this.armour = monsterData.getArmour();
        this.manaGainedOnKill = monsterData.getManaGainedOnKill();
        this.isPlayingDeathAnimation = false;
        this.isAlive = true;
        this.isFrozen = false;
        this.animationTimer = 0;
        monsterData.quantityDecrement();
    }

    public void takeDamage(float damage) {
        currentHitPoints -= damage * armour;
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

    public void banish() {
        this.x = path.get(0).getX() + 6 + xOffset;
        this.y = path.get(0).getY() + 6 + yOffset;
        pathIndex = 1;
        App.currentMana -= currentHitPoints;
    }

    public void move() {
        if (pathIndex == path.size() - 1) {
            if (Math.abs(x - (path.get(pathIndex).getX() + 14)) <= movementSpeed * App.gameSpeed &&
                Math.abs(y - (path.get(pathIndex).getY() + 14)) <= movementSpeed * App.gameSpeed) {
                x = path.get(pathIndex).getX() + 14;
                y = path.get(pathIndex).getY() + 14;
                banish();
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
            if (Math.abs(x - (path.get(pathIndex).getX() + 6)) <= movementSpeed * App.gameSpeed &&
                Math.abs(y - (path.get(pathIndex).getY() + 6)) <= movementSpeed * App.gameSpeed) {
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

    public void freeze() {
        isFrozen = true;
    }

    public boolean isFreeze() {
        return isFrozen;
    }

    public void tick() {
        this.move();
        if (isFrozen) {
            movementSpeed = (initialMovementSpeed / 3);
            freezeTimer += 1 * App.gameSpeed;
            if (freezeTimer >= 60) {
                isFrozen = false;
                movementSpeed = initialMovementSpeed;
                freezeTimer = 0;
            }
        }
        if (currentHitPoints <= 0) {
            isPlayingDeathAnimation = true;
            movementSpeed = 0;
            animationTimer += 1 * App.gameSpeed;
            if (animationTimer >= 4 && spriteIndex < spriteSheet.size()) {
                sprite = spriteSheet.get(spriteIndex);
                animationTimer = 0;
                spriteIndex++;
                if (spriteIndex >= spriteSheet.size()) {
                    if (App.currentMana + manaGainedOnKill < App.currentManaCap) {
                        App.currentMana += manaGainedOnKill;
                    } else if (App.currentMana + manaGainedOnKill > App.currentManaCap) {
                        App.currentMana = App.currentManaCap;
                    }
                    isAlive = false;
                }
            }
        }
    }
}
