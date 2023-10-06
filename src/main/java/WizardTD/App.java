// 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
// do the mana button properly;
// make it so towers are upgradeable and display the right sprite;
// implement keyboard hotkeys;
// read json file for enemy data.
// do enemy deaths.
// do enemy healthbars and mana bars

package WizardTD;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import WizardTD.Button.Button;
import WizardTD.Button.ManaButton;
import WizardTD.Button.GameSpeedButton;

import WizardTD.Tile.Grass;
import WizardTD.Tile.PathTile;
import WizardTD.Tile.Shrub;
import WizardTD.Tile.SpawnTile;
import WizardTD.Tile.Tile;
import WizardTD.Tile.Tower;
import WizardTD.Tile.WizardHome;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.event.MouseEvent;

public class App extends PApplet {

    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;


    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE+TOPBAR;

    public static final int FPS = 60;

    public String configPath;

    JSONObject json;

    public static float gameSpeed;
    public Tile[][] gameMap = new Tile[20][20];
    public PImage wizardHomeBackground;
    public int homeCol;
    public int homeRow;
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    public ArrayList<Tile> spawnTiles = new ArrayList<Tile>();
    public ArrayList<List<Tile>> pathsList = new ArrayList<List<Tile>>();
    public ArrayList<Tower> towerList = new ArrayList<Tower>();
    public static ArrayList<Fireball> fireballList = new ArrayList<Fireball>();
    public ArrayList<Button> buttonList = new ArrayList<Button>();

    public PImage beetleSprite;
    public PImage beetle1Sprite;
    public PImage beetle2Sprite;
    public PImage beetle3Sprite;
    public PImage beetle4Sprite;
    public PImage beetle5Sprite;
    public static PImage fireballSprite;
    public PImage grassSprite;
    public PImage gremlinSprite;
    public PImage gremlin1Sprite;
    public PImage gremlin2Sprite;
    public PImage gremlin3Sprite;
    public PImage gremlin4Sprite;
    public PImage gremlin5Sprite;
    public static PImage icetower0Sprite;
    public static PImage icetower1Sprite;
    public static PImage icetower2Sprite;
    public PImage path0Sprite;
    public PImage path1Sprite;
    public PImage path2Sprite;
    public PImage path3Sprite;
    public PImage shrubSprite;
    public static PImage tower0Sprite;
    public static PImage tower1Sprite;
    public static PImage tower2Sprite;
    public PImage wizardHomeSprite;
    public PImage wormSprite;
    public PImage worm1Sprite;
    public PImage worm2Sprite;
    public PImage worm3Sprite;
    public PImage worm4Sprite;
    public PImage worm5Sprite;

    public static float initialTowerRange;
    public static float initialTowerSpeed;
    public static float initialTowerDamage;
    public static float initialMana;
    public static float initialManaCap;
    public static float initialManaGainedPerSecond;
    public static float towerCost;
    public static float manaPoolSpellInitialCost;
    public static float manaPoolSpellCostIncreasePerUse;
    public static float manaPoolSpellCapMultiplier;
    public static float manaPoolSpellManaGainedMultiplier;

    public static boolean isFastForward;
    public static boolean isPaused;
    public static boolean isBuildTower;
    public static boolean isBuildIceTower;
    public static boolean isUpgradeRange;
    public static boolean isUpgradeSpeed;
    public static boolean isUpgradeDamage;
    public static boolean isUpgradeMana;

    public Random random = new Random();
	
	// Feel free to add any additional methods or attributes you want. Please put classes in different files.

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
	@Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
     */
	@Override
    public void setup() {
        gameSpeed = 1;
        frameRate(FPS);

        beetleSprite = loadImage("src/main/resources/WizardTD/beetle.png");
        beetle1Sprite = loadImage("src/main/resources/WizardTD/beetle1.png");
        beetle2Sprite = loadImage("src/main/resources/WizardTD/beetle2.png");
        beetle3Sprite = loadImage("src/main/resources/WizardTD/beetle3.png");
        beetle4Sprite = loadImage("src/main/resources/WizardTD/beetle4.png");
        beetle5Sprite = loadImage("src/main/resources/WizardTD/beetle5.png");
        fireballSprite = loadImage("src/main/resources/WizardTD/fireball.png");
        grassSprite = loadImage("src/main/resources/WizardTD/grass.png");
        gremlinSprite = loadImage("src/main/resources/WizardTD/gremlin.png");
        gremlin1Sprite = loadImage("src/main/resources/WizardTD/gremlin1.png");
        gremlin2Sprite = loadImage("src/main/resources/WizardTD/gremlin2.png");
        gremlin3Sprite = loadImage("src/main/resources/WizardTD/gremlin3.png");
        gremlin4Sprite = loadImage("src/main/resources/WizardTD/gremlin4.png");
        gremlin5Sprite = loadImage("src/main/resources/WizardTD/gremlin5.png");
        icetower0Sprite = loadImage("src/main/resources/WizardTD/icetower0.png");
        icetower1Sprite = loadImage("src/main/resources/WizardTD/icetower1.png");
        icetower2Sprite = loadImage("src/main/resources/WizardTD/icetower2.png");
        path0Sprite = loadImage("src/main/resources/WizardTD/path0.png");
        path1Sprite = loadImage("src/main/resources/WizardTD/path1.png");
        path2Sprite = loadImage("src/main/resources/WizardTD/path2.png");
        path3Sprite = loadImage("src/main/resources/WizardTD/path3.png");
        shrubSprite = loadImage("src/main/resources/WizardTD/shrub.png");
        tower0Sprite = loadImage("src/main/resources/WizardTD/tower0.png");
        tower1Sprite = loadImage("src/main/resources/WizardTD/tower1.png");
        tower2Sprite = loadImage("src/main/resources/WizardTD/tower2.png");
        wizardHomeSprite = loadImage("src/main/resources/WizardTD/wizard_house.png");
        wormSprite = loadImage("src/main/resources/WizardTD/worm.png");
        worm1Sprite = loadImage("src/main/resources/WizardTD/worm1.png");
        worm2Sprite = loadImage("src/main/resources/WizardTD/worm2.png");
        worm3Sprite = loadImage("src/main/resources/WizardTD/worm3.png");
        worm4Sprite = loadImage("src/main/resources/WizardTD/worm4.png");
        worm5Sprite = loadImage("src/main/resources/WizardTD/worm5.png");
        
        // in this section of code I am loading all json variables that aren't inside JSONArrays
        json = loadJSONObject(configPath);
        initialTowerRange = json.getFloat("initial_tower_range");
        initialTowerSpeed = json.getFloat("initial_tower_firing_speed");
        initialTowerDamage = json.getFloat("initial_tower_damage");
        initialMana = json.getFloat("initial_mana");
        initialManaCap = json.getFloat("initial_mana_cap");
        initialManaGainedPerSecond = json.getFloat("initial_mana_gained_per_second");
        towerCost = json.getFloat("tower_cost");
        manaPoolSpellInitialCost = json.getFloat("mana_pool_spell_initial_cost");
        manaPoolSpellCostIncreasePerUse = json.getFloat("mana_pool_spell_cost_increase_per_use");
        manaPoolSpellCapMultiplier = json.getFloat("mana_pool_spell_cap_multiplier");
        manaPoolSpellManaGainedMultiplier = json.getFloat("mana_pool_spell_mana_gained_multiplier");

        // intializing the booleans the buttons control;
        isFastForward = false;
        isPaused = false;
        isBuildTower = false;
        isBuildIceTower = false;
        isUpgradeRange = false;
        isUpgradeSpeed = false;
        isUpgradeDamage = false;
        isUpgradeMana = false;

        // The section of code below loads the level layout by reading the JSON file which gives it the path to the level.txt file.
        String levelPath = json.getString("layout");
        File f = new File(levelPath);
        try {
            Scanner scan = new Scanner(f);
            int lineCount = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineSplit = line.split("");
                if (lineSplit.length < 20) {
                    String[] temp = new String[lineSplit.length];
                    System.arraycopy(lineSplit, 0, temp, 0, lineSplit.length);
                    lineSplit = new String[20];
                    System.arraycopy(temp, 0, lineSplit, 0, temp.length);
                    for (int i = temp.length; i < 20; i++) {
                        lineSplit[i] = " ";
                    }
                }
                for (int i = 0; i < lineSplit.length; i++) {
                    if (lineSplit[i].equals(" ")) {
                        gameMap[lineCount][i] = new Grass(i*CELLSIZE, lineCount*CELLSIZE+TOPBAR, grassSprite);
                    } else if (lineSplit[i].equals("S")) {
                        gameMap[lineCount][i] = new Shrub(i*CELLSIZE, lineCount*CELLSIZE+TOPBAR, shrubSprite);
                    } else if (lineSplit[i].equals("X")) {
                        if (i == 0 || i == 19 || lineCount == 0 || lineCount == 19) {
                            gameMap[lineCount][i] = new SpawnTile(i*CELLSIZE, lineCount*CELLSIZE+TOPBAR, path0Sprite);
                            spawnTiles.add(gameMap[lineCount][i]);
                        } else {
                            gameMap[lineCount][i] = new PathTile(i*CELLSIZE, lineCount*CELLSIZE+TOPBAR, path0Sprite);
                        }
                    } else if (lineSplit[i].equals("W")) {
                        gameMap[lineCount][i] = new WizardHome(i*CELLSIZE-8, lineCount*CELLSIZE+TOPBAR-8, wizardHomeSprite);
                        homeRow = lineCount;
                        homeCol = i;
                    }
                }
                lineCount++;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        }

        for (Tile spawnTile : spawnTiles) {
            pathsList.add(findPath(gameMap, spawnTile.getRow(), spawnTile.getCol(), homeRow, homeCol));
        }

        // this section of code rotates the path tiles based upon what paths surrounds them.
        for(int i = 0; i < gameMap.length; i++) {
            for(int j = 0; j <gameMap.length; j++) {
                if (gameMap[i][j] instanceof PathTile) {
                    if (i == 0 || i == 19) {
                        gameMap[i][j].setSprite(rotateImageByDegrees(path0Sprite, 90));
                    }

                    try {
                        if (gameMap[i+1][j] instanceof PathTile || gameMap[i-1][j] instanceof PathTile) {
                            gameMap[i][j].setSprite(rotateImageByDegrees(path0Sprite, 90));
                        }

                        if (gameMap[i][j+1] instanceof PathTile && gameMap[i][j-1] instanceof PathTile) {
                            gameMap[i][j].setSprite(path0Sprite);
                        }

                        if (gameMap[i+1][j] instanceof PathTile && gameMap[i][j-1] instanceof PathTile) {
                            gameMap[i][j].setSprite(path1Sprite);
                        }
                        if (gameMap[i-1][j] instanceof PathTile && gameMap[i][j-1] instanceof PathTile) {
                            gameMap[i][j].setSprite(rotateImageByDegrees(path1Sprite, 90));
                        }
                        if (gameMap[i-1][j] instanceof PathTile && gameMap[i][j+1] instanceof PathTile) {
                            gameMap[i][j].setSprite(rotateImageByDegrees(path1Sprite, 180));
                        }
                        if (gameMap[i+1][j] instanceof PathTile && gameMap[i][j+1] instanceof PathTile) {
                            gameMap[i][j].setSprite(rotateImageByDegrees(path1Sprite, 270));
                        }

                        if (gameMap[i+1][j] instanceof PathTile && gameMap[i][j+1] instanceof PathTile && gameMap[i][j-1] instanceof PathTile) {
                            gameMap[i][j].setSprite(path2Sprite);
                        }
                        if (gameMap[i+1][j] instanceof PathTile && gameMap[i-1][j] instanceof PathTile && gameMap[i][j-1] instanceof PathTile) {
                            gameMap[i][j].setSprite(rotateImageByDegrees(path2Sprite, 90));
                        }
                        if (gameMap[i-1][j] instanceof PathTile && gameMap[i][j+1] instanceof PathTile && gameMap[i][j-1] instanceof PathTile) {
                            gameMap[i][j].setSprite(rotateImageByDegrees(path2Sprite, 180));
                        }
                        if (gameMap[i+1][j] instanceof PathTile && gameMap[i-1][j] instanceof PathTile && gameMap[i][j+1] instanceof PathTile) {
                            gameMap[i][j].setSprite(rotateImageByDegrees(path2Sprite, 270));
                        }

                        if (gameMap[i-1][j] instanceof PathTile && gameMap[i+1][j] instanceof PathTile && gameMap[i][j+1] instanceof PathTile && gameMap[i][j-1] instanceof PathTile) {
                            gameMap[i][j].setSprite(path3Sprite);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }

    wizardHomeBackground = grassSprite;
    for (List<Tile> path : pathsList) {
        enemyList.add(new Enemy(wormSprite, path));
    }

    Button fastForward = new GameSpeedButton(650, 55, "FF", "2x speed", isFastForward);
    Button pause = new GameSpeedButton(650, 110, "P", "PAUSE", isPaused);
    Button buildTower = new Button(650, 165, "T", "Build\nTower", isBuildTower);
    Button buildIceTower = new Button(650, 220, "I", "Build\nIce Tower", isBuildIceTower);
    Button upgradeRange = new Button(650, 275, "U1", "Upgrade\nRange", isUpgradeRange);
    Button upgradeSpeed = new Button(650, 330, "U2", "Upgrade\nSpeed", isUpgradeSpeed);
    Button upgradeDamage = new Button(650, 385, "U3", "Upgrade\nDamage", isUpgradeDamage);
    Button upgradeMana = new ManaButton(650, 440, "M", "Mana Pool", isUpgradeMana);
    buttonList.add(fastForward);
    buttonList.add(pause);
    buttonList.add(buildTower);
    buttonList.add(buildIceTower);
    buttonList.add(upgradeRange);
    buttonList.add(upgradeSpeed);
    buttonList.add(upgradeDamage);
    buttonList.add(upgradeMana);

        // Load images during setup
		// Eg:
        // loadImage("src/main/resources/WizardTD/tower0.png");
        // loadImage("src/main/resources/WizardTD/tower1.png");
        // loadImage("src/main/resources/WizardTD/tower2.png");

    }   

    /**
     * Receive key pressed signal from the keyboard.
     */
	@Override
    public void keyPressed(){
        if (key == 'F' || key == 'f') {
            buttonList.get(0).isClicked();
            isFastForward = buttonList.get(0).getIsClicked();
        }

        if (key == 'P' || key == 'p') {
            buttonList.get(1).isClicked();
            isFastForward = buttonList.get(1).getIsClicked();
        }

        if (key == 'T' || key == 't') {
            buttonList.get(2).isClicked();
            isBuildTower = buttonList.get(2).getIsClicked();
        }

        if (key == 'I' || key == 'i') {
            buttonList.get(3).isClicked();
            isBuildIceTower = buttonList.get(3).getIsClicked();
        }

        if (key == '1') {
            buttonList.get(4).isClicked();
            isUpgradeRange = buttonList.get(4).getIsClicked();
        }

        if (key == '2') {
            buttonList.get(5).isClicked();
            isUpgradeSpeed = buttonList.get(5).getIsClicked();
        }

        if (key == '3') {
            buttonList.get(6).isClicked();
            isUpgradeDamage = buttonList.get(6).getIsClicked();
        }

        if (key == 'M' || key == 'm') {
            buttonList.get(7).isClicked();
            isUpgradeMana = buttonList.get(7).getIsClicked();
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */
	@Override
    public void keyReleased(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // for building towers and icetowers
        for (int i = 0; i < gameMap.length; i++) {
            for(int j = 0; j < gameMap[i].length; j++) {
                if (mouseX > gameMap[i][j].getX() && mouseX < gameMap[i][j].getX() + 32 && mouseY > gameMap[i][j].getY() && mouseY < gameMap[i][j].getY() + 32) {
                    if (gameMap[i][j].isTowerPlaceable() && isBuildTower) {
                        gameMap[i][j] = new Tower(gameMap[i][j].getX(), gameMap[i][j].getY());
                        towerList.add((Tower)gameMap[i][j]);
                    }
                }
            }
        }
        
        //checking if buttons are pressed;
        for (Button b : buttonList) {
            if (mouseX > b.getX() && mouseX < b.getX() + 40 && mouseY > b.getY() && mouseY < b.getY() + 40) {
                b.isClicked();
                isFastForward = buttonList.get(0).getIsClicked();
                isPaused = buttonList.get(1).getIsClicked();
                isBuildTower = buttonList.get(2).getIsClicked();
                isBuildIceTower = buttonList.get(3).getIsClicked();
                isUpgradeRange = buttonList.get(4).getIsClicked();
                isUpgradeSpeed = buttonList.get(5).getIsClicked();
                isUpgradeDamage = buttonList.get(6).getIsClicked();
                isUpgradeMana = buttonList.get(7).getIsClicked();
                if (b instanceof GameSpeedButton) {
                    ((GameSpeedButton)b).changeGameSpeed();
                }
            }
        }

        for (Tower tower : towerList) {
            if (mouseX > tower.getX() && mouseX < tower.getX() + 32 && mouseY > tower.getY() && mouseY < tower.getY() + 32) {
                if (isUpgradeRange) {
                    tower.upgradeRange();
                } 
                if (isUpgradeSpeed) {
                    tower.upgradeSpeed();
                } 
                if (isUpgradeDamage) {
                    tower.upgradeDamage();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /*@Override
    public void mouseDragged(MouseEvent e) {

    }*/

    /**
     * Draw all elements in the game by current frame.
     */
	@Override
    public void draw() {
        // the entire game map is drawn and towers can be placed if isTowerPlaceable() == true
        for (int i = 0; i < gameMap.length; i++) {
            for(int j = 0; j < gameMap[i].length; j++) {
                gameMap[i][j].draw(this);
            }
        }

        // a second for loop is needed in order to draw tower ranges :( I couldn't figure out a more efficent way of doing this.
        for (Tower tower : towerList) {
            // this part is for drawing tower upgrades
            strokeWeight(1);
            stroke(255, 0, 255);
            noFill();

            for (int i = 0; i < tower.getRangeLevel() - tower.getBaseLevel(); i++) {
                ellipse(tower.getX()+4+(i*8), tower.getY()+4, 8, 8);
            }

            for (int i = 0; i < tower.getDamageLevel() - tower.getBaseLevel(); i++) {
                line(tower.getX()+(8*i), tower.getY()+24, tower.getX()+8+(8*i), tower.getY()+32);
                line(tower.getX()+8+(8*i), tower.getY()+24, tower.getX()+(8*i), tower.getY()+32);
            }

            stroke(0, 255, 255);
            strokeWeight((float)(tower.getSpeedLevel()-tower.getBaseLevel()));
            if(tower.getSpeedLevel() > 0) {
                rect(tower.getX()+6, tower.getY()+6, 20, 20);
            }
            strokeWeight(1);
            // this section of code is for draing the tower's ranges
            if (mouseX > tower.getX() && mouseX < tower.getX() + 32 && mouseY > tower.getY() && mouseY < tower.getY() + 32) {
                stroke(0, 0, 0);
                noFill();
                ellipse(tower.getX()+16, tower.getY()+16, tower.getRange()*2, tower.getRange()*2);
            }
            // this section of code checks if enemies are in range of the tower
            for (Enemy enemy : enemyList) {
                if (dist(enemy.getX()+10, enemy.getY()+10, tower.getX()+16, tower.getY()+16) < tower.getRange()) {
                    if (!tower.enemyInRangeContains(enemy)) {
                        tower.enemyInRangeAdd(enemy);
                    }
                } else {
                    tower.enemyInRangeRemove(enemy);
                }
            }
            // towers have to tick in order to fire fireballs/iceballs
            tower.tick();
        }

        image(wizardHomeBackground, homeCol*CELLSIZE, homeRow*CELLSIZE+TOPBAR);

        for (Enemy enemy : enemyList) {
            enemy.tick();
            enemy.draw(this);
        }

        for(Fireball fireball : fireballList) {
            if (fireball.getIsCollided() == false) {
                fireball.tick();
                fireball.draw(this);
            }
        }

        gameMap[homeRow][homeCol].draw(this);

        // draws topbar and sidebar
        noStroke();
        fill(210, 180, 140);
        rect(0, 0, 760, 40);
        rect(640, 40,120, 640);

    // this section of code draws the buttons and their labels.
        for (Button b : buttonList) {
    
            stroke(0, 0, 0);
            if (b.getIsClicked()) {
                fill(255, 255, 0);
            } else if (mouseX > b.getX() && mouseX < b.getX() + 40 && mouseY > b.getY() && mouseY < b.getY() + 40) {
                fill(255, 255, 255);
            } else {
                noFill();
            }
            rect(b.getX(), b.getY(), 40, 40);

            fill(0, 0, 0);
            textSize(24);
            text(b.getShortHand(), b.getX()+5, b.getY()+30);
            textSize(12);
            text(b.getLabel(), b.getX()+45, b.getY()+14);
            if (b instanceof ManaButton) {
                text("cost: " + (int)manaPoolSpellInitialCost, b.getX()+45, b.getY()+34);
            }
        }

    }

    public static void main(String[] args) {
        PApplet.main("WizardTD.App");
    }

    /**
     * Source: https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * @param pimg The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = this.createImage(newWidth, newHeight, RGB);
        //BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }

        return result;
    }

    public List<Tile> findPath(Tile[][] grid, int startRow, int startCol, int homeRow, int homeCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Initialize visited array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        // Initialize the path
        List<Tile> path = new ArrayList<>();

        if (dfs(grid, startRow, startCol, homeRow, homeCol, visited, path)) {
            return path;
        } else {
            return null;
        }
    }

    private boolean dfs(Tile[][] grid, int row, int col, int homeRow, int homeCol, boolean[][] visited, List<Tile> path) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check if the current position is out of bounds, an obstacle, or already visited
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col].canEnemyWalk() == false || visited[row][col]) {
            return false;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Add the current cell to the path
        path.add(grid[row][col]);

        // Check if we have reached the end point
        if (row == homeRow && col == homeCol) {
            return true; // Return true to indicate that the destination is reached
        }

        // Define possible moves (right, down, left, up)
        int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Explore all possible moves
        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];

            // Recursively explore the next cell
            if (dfs(grid, newRow, newCol, homeRow, homeCol, visited, path)) {
                return true; // Return true if a path is found
            }
        }

        // If no path is found, backtrack
        path.remove(path.size() - 1);
        return false;
    }
}
