package WizardTD;

import java.util.ArrayList;
import java.util.List;

import WizardTD.Tile.Tile;
import processing.core.PImage;
/**
 * The Enemy class extends the entity class. It follows a predefined path
 * and can take damage.
 */
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

    /**
     * Creates a new instance of Enemy using the monsterData provided in the JSON file and a path
     * represented by a series of Tiles to follow.
     *
     * @param monsterData The data for the enemy, including type, HP, speed, etc.
     * @param path        The path of Tiles that the instance of enemy will follow.
     */
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

    /**
     * Enemy takes damage thus reducing its hit points.
     *
     * @param damage The amount of damage dealt to the enemy.
     */
    public void takeDamage(float damage) {
        currentHitPoints -= damage * armour;
    }

    /**
     * Returns the current hit points of the enemy.
     *
     * @return The current hit points of the enemy.
     */
    public float getCurrentHitPoints() {
        return currentHitPoints;
    }

    /**
     * Returns the maximum hit points of the enemy. The amount of hit points it starts with.
     *
     * @return The maximum hit points of the enemy.
     */
    public float getMaxHitPoints() {
        return maxHitPoints;
    }
    
    /**
     * Checks if the enemy is currently playing a death animation.
     *
     * @return True if the enemy is playing a death animation, otherwise false.
     */
    public boolean isPlayingDeathAnimation() {
        return isPlayingDeathAnimation;
    }

    /**
     * Checks if enemy is alive.
     *
     * @return True if enemy is alive, otherwise false.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Resets the enemy's position to its initial point and deducts
     * the game's current Mana by the enemy's current hit points.
     */
    public void banish() {
        this.x = path.get(0).getX() + 6 + xOffset;
        this.y = path.get(0).getY() + 6 + yOffset;
        pathIndex = 1;
        App.currentMana -= currentHitPoints;
    }

    /**
     * Moves the enemy along the predefined path.
     */
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

    /**
     * Sets the boolean isFrozen to true;
     */
    public void freeze() {
        isFrozen = true;
    }

    /**
     * Checks if the enemy is currently frozen.
     *
     * @return True if the enemy is frozen, otherwise false.
     */
    public boolean isFreeze() {
        return isFrozen;
    }

    /**
     * Updates enemy's state every tick, by calling move() in order to move and
     * checking if the enemy is frozen, dead, or playing death animation.
     */
    public void tick() {
        this.move();
        if (isFrozen) {
            movementSpeed = (initialMovementSpeed / 3);
            freezeTimer += 1 * App.gameSpeed;
            if (freezeTimer >= 90) {
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
