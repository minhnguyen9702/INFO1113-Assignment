package WizardTD;

import java.util.List;

import WizardTD.Tile.Tile;
import processing.core.PImage;

public class Enemy extends Entity {

    private int i = 1;
    private List<Tile> path;
    private float currentHitPoints;
    private float maxHitPoints;
    private float movementSpeed;
    private float armor;
    private float manaGainedOnKill;
    private boolean isAlive;

    public Enemy(PImage sprite, List<Tile> path) {
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
        this.x = path.get(0).getX()+6+xOffset;
        this.y = path.get(0).getY()+6+yOffset;
        this.sprite = sprite;
        this.path = path;
        this.currentHitPoints = 100;
        this.maxHitPoints = 100;
        this.movementSpeed = 1;
        this.armor = (float)0.5;
        this.manaGainedOnKill = 10;
        this.isAlive = true;
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

    public boolean isAlive() {
        return isAlive;
    }

    public void move() {
        if (i == path.size()-1) {
            if (Math.abs(x - (path.get(i).getX() + 14)) <= movementSpeed && 
                Math.abs(y - (path.get(i).getY() + 14)) <= movementSpeed) {
                x = path.get(i).getX() + 14;
                y = path.get(i).getY() + 14;
            } else {
                if (x < path.get(i).getX()+14){
                    x+= movementSpeed * App.gameSpeed;
                }
                if (x > path.get(i).getX()+14){
                    x-= movementSpeed * App.gameSpeed;
                }
                if (y < path.get(i).getY()+14){
                    y+= movementSpeed * App.gameSpeed;
                }
                if (y > path.get(i).getY()+14){
                    y-= movementSpeed * App.gameSpeed;
                }
            }
        } else {
            if (Math.abs(x - (path.get(i).getX() + 6)) <= movementSpeed &&
                Math.abs(y - (path.get(i).getY() + 6)) <= movementSpeed) {
                x = path.get(i).getX() + 6;
                y = path.get(i).getY() + 6;
                i++;
            } else {
                if (x < path.get(i).getX()+6){
                    x+= movementSpeed * App.gameSpeed;
                }
                if (x > path.get(i).getX()+6){
                    x-= movementSpeed * App.gameSpeed;
                }
                if (y < path.get(i).getY()+6){
                    y+= movementSpeed * App.gameSpeed;
                }
                if (y > path.get(i).getY()+6){
                    y-= movementSpeed * App.gameSpeed;
                }
            }
        }
    }

    public void tick() {
        this.move();
        if (currentHitPoints <= 0) {
            movementSpeed = 0;
            isAlive = false;
        }
    }
}
