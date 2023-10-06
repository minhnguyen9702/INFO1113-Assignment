package WizardTD;

import java.util.List;

import WizardTD.Tile.Tile;
import processing.core.PImage;

public class Enemy extends Entity {

    private int i = 1;
    private List<Tile> path;

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
    }

    public void tick() {
        if (i == path.size()-1) {
            if (Math.abs(x - (path.get(i).getX() + 14)) <= 1 && Math.abs(y - (path.get(i).getY() + 14)) <= 1) {
                x = path.get(i).getX() + 14;
                y = path.get(i).getY() + 14;
            } else {
                if (x < path.get(i).getX()+14){
                    x+= 1 * App.gameSpeed;
                }
                if (x > path.get(i).getX()+14){
                    x-= 1 * App.gameSpeed;
                }
                if (y < path.get(i).getY()+14){
                    y+= 1 * App.gameSpeed;
                }
                if (y > path.get(i).getY()+14){
                    y-= 1 * App.gameSpeed;
                }
            }
        } else {
            if (Math.abs(x - (path.get(i).getX() + 6)) <= 1 && Math.abs(y - (path.get(i).getY() + 6)) <= 1) {
                x = path.get(i).getX() + 6;
                y = path.get(i).getY() + 6;
                i++;
            } else {
                if (x < path.get(i).getX()+6){
                    x+= 1 * App.gameSpeed;
                }
                if (x > path.get(i).getX()+6){
                    x-= 1 * App.gameSpeed;
                }
                if (y < path.get(i).getY()+6){
                    y+= 1 * App.gameSpeed;
                }
                if (y > path.get(i).getY()+6){
                    y-= 1 * App.gameSpeed;
                }
            }
        }
    }

}
