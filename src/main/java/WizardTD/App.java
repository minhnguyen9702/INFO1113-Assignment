package WizardTD;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import WizardTD.Button.Button;
import WizardTD.Button.GameSpeedButton;
import WizardTD.Button.ManaButton;
import WizardTD.Button.TowerButton;
import WizardTD.Button.UpgradeButton;
import WizardTD.Tile.Grass;
import WizardTD.Tile.IceTower;
import WizardTD.Tile.PathTile;
import WizardTD.Tile.Shrub;
import WizardTD.Tile.SpawnTile;
import WizardTD.Tile.Tile;
import WizardTD.Tile.Tower;
import WizardTD.Tile.WizardHome;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
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
    JSONArray wavesArray;
    

    public static float gameSpeed;
    public Tile[][] gameMap = new Tile[20][20];
    public PImage wizardHomeBackground;
    public int homeCol;
    public int homeRow;
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    public ArrayList<Enemy> enemiesPlayingDeathAnimation = new ArrayList<Enemy>();
    public ArrayList<Enemy> enemiesDead = new ArrayList<>();
    public ArrayList<Tile> spawnTiles = new ArrayList<Tile>();
    public ArrayList<List<Tile>> pathsList = new ArrayList<List<Tile>>();
    public ArrayList<Tower> towerList = new ArrayList<Tower>();
    public static ArrayList<Fireball> fireballList = new ArrayList<Fireball>();
    public ArrayList<Fireball> fireballsToRemove = new ArrayList<Fireball>();
    public ArrayList<Button> buttonList = new ArrayList<Button>();
    public ArrayList<PImage> beetleSpriteSheet = new ArrayList<PImage>(6);
    public ArrayList<PImage> gremlinSpriteSheet = new ArrayList<PImage>(6);
    public ArrayList<PImage> wormSpriteSheet = new ArrayList<PImage>(6);
    public static HashMap<String, ArrayList<PImage>> enemySpriteMap = new HashMap<String, ArrayList<PImage>>();
    public ArrayList<Wave> waves = new ArrayList<Wave>();
    public Wave currentWave;
    private int enemySpawnTimer;
    private int manaGainedTimer;
    private int waveIndex;
    public GameState gameState;

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
    public static PImage iceballSprite;
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
    public static float currentMana;
    public static float currentManaCap;
    public static float currentManaGainedPerSecond;
    public static float towerCost;
    public static float iceTowerCost;
    public static float manaPoolSpellCost;
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
     * Initialize the game by loading resources such as images and setting up game elements, including the player, enemies, and map elements.
     * This method resets the game state and loads various resources at the beginning of the game.
     */
	@Override
    public void setup() {
        // reset the game by clearing all lists so they can be filled again and restating the gamestate enum;
        manaGainedTimer = 0;
        enemySpawnTimer = 0;
        gameState = GameState.GAMENORMAL;
        gameSpeed = 1;
        enemyList.clear();
        enemiesPlayingDeathAnimation.clear();
        enemiesDead.clear();
        spawnTiles.clear();
        pathsList.clear();
        towerList.clear();
        fireballList.clear();
        buttonList.clear();
        waves.clear();
        frameRate(FPS);

        // this section loads all of the images into the game as sprites
        beetleSprite = loadImage("src/main/resources/WizardTD/beetle.png");
        beetle1Sprite = loadImage("src/main/resources/WizardTD/beetle1.png");
        beetle2Sprite = loadImage("src/main/resources/WizardTD/beetle2.png");
        beetle3Sprite = loadImage("src/main/resources/WizardTD/beetle3.png");
        beetle4Sprite = loadImage("src/main/resources/WizardTD/beetle4.png");
        beetle5Sprite = loadImage("src/main/resources/WizardTD/beetle5.png");
        beetleSpriteSheet.add(beetleSprite);
        beetleSpriteSheet.add(beetle1Sprite);
        beetleSpriteSheet.add(beetle2Sprite);
        beetleSpriteSheet.add(beetle3Sprite);
        beetleSpriteSheet.add(beetle4Sprite);
        beetleSpriteSheet.add(beetle5Sprite);

        fireballSprite = loadImage("src/main/resources/WizardTD/fireball.png");
        grassSprite = loadImage("src/main/resources/WizardTD/grass.png");
        gremlinSprite = loadImage("src/main/resources/WizardTD/gremlin.png");
        gremlin1Sprite = loadImage("src/main/resources/WizardTD/gremlin1.png");
        gremlin2Sprite = loadImage("src/main/resources/WizardTD/gremlin2.png");
        gremlin3Sprite = loadImage("src/main/resources/WizardTD/gremlin3.png");
        gremlin4Sprite = loadImage("src/main/resources/WizardTD/gremlin4.png");
        gremlin5Sprite = loadImage("src/main/resources/WizardTD/gremlin5.png");
        gremlinSpriteSheet.add(gremlinSprite);
        gremlinSpriteSheet.add(gremlin1Sprite);
        gremlinSpriteSheet.add(gremlin2Sprite);
        gremlinSpriteSheet.add(gremlin3Sprite);
        gremlinSpriteSheet.add(gremlin4Sprite);
        gremlinSpriteSheet.add(gremlin5Sprite);

        icetower0Sprite = loadImage("src/main/resources/WizardTD/icetower0.png");
        icetower1Sprite = loadImage("src/main/resources/WizardTD/icetower1.png");
        icetower2Sprite = loadImage("src/main/resources/WizardTD/icetower2.png");
        iceballSprite = loadImage("src/main/resources/WizardTD/iceball.png");
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
        wormSpriteSheet.add(wormSprite);
        wormSpriteSheet.add(worm1Sprite);
        wormSpriteSheet.add(worm2Sprite);
        wormSpriteSheet.add(worm3Sprite);
        wormSpriteSheet.add(worm4Sprite);
        wormSpriteSheet.add(worm5Sprite);

        // adding all of the spritesheets to a HashMap so that Enemy objects can easily access them later
        enemySpriteMap.put("beetle", beetleSpriteSheet);
        enemySpriteMap.put("gremlin", gremlinSpriteSheet);
        enemySpriteMap.put("worm", wormSpriteSheet);

        // in this section of code I am loading all json variables that aren't inside JSONArrays
        json = loadJSONObject(configPath);
        initialTowerRange = json.getFloat("initial_tower_range");
        initialTowerSpeed = json.getFloat("initial_tower_firing_speed");
        initialTowerDamage = json.getFloat("initial_tower_damage");
        currentMana = json.getFloat("initial_mana");
        currentManaCap = json.getFloat("initial_mana_cap");
        currentManaGainedPerSecond = json.getFloat("initial_mana_gained_per_second");
        towerCost = json.getFloat("tower_cost");
        iceTowerCost = towerCost*3/4;
        manaPoolSpellCost = json.getFloat("mana_pool_spell_initial_cost");
        manaPoolSpellCostIncreasePerUse = json.getFloat("mana_pool_spell_cost_increase_per_use");
        manaPoolSpellCapMultiplier = json.getFloat("mana_pool_spell_cap_multiplier");
        manaPoolSpellManaGainedMultiplier = json.getFloat("mana_pool_spell_mana_gained_multiplier");

        // intializing the booleans which the buttons control;
        isFastForward = false;
        isPaused = false;
        isBuildTower = false;
        isBuildIceTower = false;
        isUpgradeRange = false;
        isUpgradeSpeed = false;
        isUpgradeDamage = false;
        isUpgradeMana = false;

        waveIndex = 0;
        wavesArray = json.getJSONArray("waves");
        for (int i = 0; i < wavesArray.size(); i++) {
            JSONObject waveObject = wavesArray.getJSONObject(i);

            float duration = waveObject.getInt("duration") * 60;
            float preWavePause = waveObject.getFloat("pre_wave_pause") * 60;

            Wave wave = new Wave(duration, preWavePause);

            JSONArray monstersArray = waveObject.getJSONArray("monsters");

            for (int j = 0; j < monstersArray.size(); j++) {
                JSONObject monsterObject = monstersArray.getJSONObject(j);

                String type = monsterObject.getString("type");
                float hp = monsterObject.getInt("hp");
                float speed = monsterObject.getFloat("speed");
                float armour = monsterObject.getFloat("armour");
                float manaGainedOnKill = monsterObject.getInt("mana_gained_on_kill");
                int quantity = monsterObject.getInt("quantity");
                wave.addTotalNumberOfMonsters(quantity);

                MonsterData monsterData = new MonsterData(type, hp, speed, armour, manaGainedOnKill, quantity);
                wave.addMonster(monsterData);
            }
            waves.add(wave);
        }
        currentWave = waves.get(waveIndex);

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

    Button fastForward = new GameSpeedButton(650, 55, "FF", "2x speed", isFastForward);
    Button pause = new GameSpeedButton(650, 110, "P", "PAUSE", isPaused);
    Button buildTower = new TowerButton(650, 165, "T", "Build\nTower", isBuildTower);
    Button buildIceTower = new TowerButton(650, 220, "I", "Build\nIce Tower", isBuildIceTower, iceTowerCost);
    Button upgradeRange = new UpgradeButton(650, 275, "U1", "Upgrade\nRange", isUpgradeRange);
    Button upgradeSpeed = new UpgradeButton(650, 330, "U2", "Upgrade\nSpeed", isUpgradeSpeed);
    Button upgradeDamage = new UpgradeButton(650, 385, "U3", "Upgrade\nDamage", isUpgradeDamage);
    Button upgradeMana = new ManaButton(650, 440, "M", "Mana Pool", isUpgradeMana);
    buttonList.add(fastForward);
    buttonList.add(pause);
    buttonList.add(buildTower);
    buttonList.add(buildIceTower);
    buttonList.add(upgradeRange);
    buttonList.add(upgradeSpeed);
    buttonList.add(upgradeDamage);
    buttonList.add(upgradeMana);
    
    // this is needed for the game needs to be restarted when the player loses

        // Load images during setup
		// Eg:
        // loadImage("src/main/resources/WizardTD/tower0.png");
        // loadImage("src/main/resources/WizardTD/tower1.png");
        // loadImage("src/main/resources/WizardTD/tower2.png");

    }   

    /**
     * Handles key press events, triggering specific actions based on the pressed keys and the current game state.
     * This method responds to key presses and performs actions such as changing game speed, pausing the game,
     * building towers, upgrading towers, and restarting the game when appropriate.
     */
	@Override
    public void keyPressed(){
        if (gameState == GameState.GAMENORMAL) {
            if (key == 'F' || key == 'f') {
                buttonList.get(0).isClicked();
                isFastForward = buttonList.get(0).getIsClicked();
                ((GameSpeedButton)buttonList.get(0)).changeGameSpeed();
            }

            if (key == 'P' || key == 'p') {
                buttonList.get(1).isClicked();
                isPaused = buttonList.get(1).getIsClicked();
                ((GameSpeedButton)buttonList.get(1)).changeGameSpeed();
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

        if (key == 'R' || key == 'r' && gameState == GameState.GAMEOVER) {
            setup();
        }
    }

    /**
     * Handles mouse click events, performing various actions based on the game's state and user interactions.
     * This method responds to mouse clicks and triggers actions, including building towers, selecting game buttons,
     * upgrading tower properties, and adjusting the game speed.
     *
     * @param e The MouseEvent object representing the mouse click event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // for building towers and icetowers
        if (gameState == GameState.GAMENORMAL) {
            for (int i = 0; i < gameMap.length; i++) {
                for(int j = 0; j < gameMap[i].length; j++) {
                    if (mouseX > gameMap[i][j].getX() && mouseX < gameMap[i][j].getX() + 32 && mouseY > gameMap[i][j].getY() && mouseY < gameMap[i][j].getY() + 32 
                    && gameMap[i][j].isTowerPlaceable()) {
                        if (isBuildTower && currentMana > towerCost) {
                            gameMap[i][j] = new Tower(gameMap[i][j].getX(), gameMap[i][j].getY());
                            towerList.add((Tower)gameMap[i][j]);
                        } else if (isBuildIceTower && currentMana > iceTowerCost) {
                            gameMap[i][j] = new IceTower(gameMap[i][j].getX(), gameMap[i][j].getY());
                            towerList.add((IceTower)gameMap[i][j]);
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
    }

    /**
     * Checks the current game state, determining whether the player has won or lost the game.
     * If the player's current mana is less than 0 then the GameState is set to Game Over.
     * Else if all waves have been completed and the all enemies killed than the game is won.
     */
    public void checkGameState() {
        if (currentMana < 0) {
            gameState = GameState.GAMEOVER;
            gameSpeed = 0;
        } if (waves.get(2).getDuration() <= 0 && enemyList.isEmpty()) {
            gameState = GameState.GAMEWIN;
            gameSpeed = 0;
        }
    }
    
    /**
    * Increases the player's current mana over time, based on manaGainedPerSecond and game speed.
    * It ensures that mana does not exceed the mana cap when it is gained through manaGainedPerSecond.
    */
    public void gainManaPerSecond() {
        manaGainedTimer += gameSpeed;
        if (manaGainedTimer >= 30) {
            manaGainedTimer = 0;
            if (currentMana + (currentManaGainedPerSecond/2) < currentManaCap) {
                currentMana += currentManaGainedPerSecond/2;
            } else if (currentMana + (currentManaGainedPerSecond/2) > currentManaCap) {
                currentMana = currentManaCap;
            }
        }
    }
    /**
     * Draws a cost indicator next to a game button to display the associated cost.
     * The cost is rendered as a white background with black text, providing a visual representation
     * of the resource cost required for the associated game action.
     *
     * @param buttonX The button's X-coordinate on the screen.
     * @param buttonY The button's Y-coordinate on the screen.
     * @param cost The action's mana cost, displayed next to the button.
     */
    public void drawCostIndicator(float buttonX, float buttonY, float cost) {
        fill(255, 255, 255);
        rect(buttonX-70, buttonY+10, 60, 20);
        fill(0, 0, 0);
        text("cost: " + Math.round(cost), buttonX-65, buttonY+25);
    }

    /**
     * Progresses the current wave forward by reducing the current wave's prewavepause and then duration.
     * Also manages enemy spawning
     */
    public void moveWaveForwards() {
        if (gameSpeed > 0) {
            if (currentWave.getPreWavePause() > 0) {
                currentWave.setPreWavePause(currentWave.getPreWavePause() - gameSpeed);
            } else if (currentWave.getDuration() > 0) {
                currentWave.setDuration(currentWave.getDuration() - gameSpeed);
                enemySpawnTimer += gameSpeed;
                if (enemySpawnTimer >= Math.floor(currentWave.getOriginalDuration() / currentWave.getTotalNumberOfMonsters())) {
                    enemySpawnTimer = 0;
                    currentWave.updateMonsterData();

                    MonsterData randomMonsterData = 
                    currentWave.getMonsterDataList().get(Math.round(
                        random(0, currentWave.getMonsterDataList().size() -1)));

                    List<Tile> randomPath = pathsList.get(Math.round(random(0, pathsList.size()-1)));

                    enemyList.add(new Enemy(randomMonsterData, randomPath));;
                }
            } else {
                if (waveIndex < waves.size() - 1) {
                    enemySpawnTimer = 0;
                    waveIndex++;
                    currentWave = waves.get(waveIndex);
                }
            }
        }
    }
    /**
     * Draw all elements in the game by current frame.
     * updates the game state, draws various game elements, including the Gamemap, towers, enemies,
     * buttons, mana bar, timer, and Gameover/Gamewin text on the screen.
     */
	@Override
    public void draw() {
        gainManaPerSecond();
        moveWaveForwards();
        checkGameState();

        // the entire game map is drawn and towers can be placed if isTowerPlaceable() == true
        for (int i = 0; i < gameMap.length; i++) {
            for(int j = 0; j < gameMap[i].length; j++) {
                gameMap[i][j].draw(this);
            }
        }

        // a second for loop is needed in order to draw tower ranges :( I couldn't figure out a more efficent way of doing this.
        if (towerList != null) {
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

                // this section of code is for draing the tower's ranges
                strokeWeight(1);
                stroke(0, 0, 0);
                noFill();
                if (mouseX > tower.getX() && mouseX < tower.getX() + 32 && mouseY > tower.getY() && mouseY < tower.getY() + 32 &&
                    gameState == GameState.GAMENORMAL) {
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
                tower.enemyInRangeRemoveAll(enemiesPlayingDeathAnimation);
            }
            enemiesPlayingDeathAnimation.clear();
        }

        // this draws the grass behind wizardHome
        image(wizardHomeBackground, homeCol*CELLSIZE, homeRow*CELLSIZE+TOPBAR);

        if (enemyList != null) {
            for (Enemy enemy : enemyList) {
                // this checks if enemies are alive otherwise they are removed from enemyList
                fill(255, 0, 0);
                rect(enemy.getX()-3, enemy.getY()-6, 26, 3);
                if (enemy.getCurrentHitPoints() > 0) {
                    if (enemy.isFreeze()) {
                        fill(0, 255, 255);
                    } else {
                        fill(0, 255, 0);
                    }
                    rect(enemy.getX()-3, enemy.getY()-6, (enemy.getCurrentHitPoints()/enemy.getMaxHitPoints())*26, 3);
                }
                enemy.tick();
                enemy.draw(this);
                if (enemy.isPlayingDeathAnimation())
                    enemiesPlayingDeathAnimation.add(enemy);
                // this checks if enemies are alive otherwise they will be added to enemiesToRemove and thus be removed from enemyList later.
                if (!enemy.isAlive()) {
                    enemiesDead.add(enemy);
                }
            }
            enemyList.removeAll(enemiesDead);
            enemiesDead.clear();
        }

        if (fireballList != null) {
            for(Fireball fireball : fireballList) {
                fireball.tick();
                fireball.draw(this);
                if (fireball.getIsCollided()) {
                    fireballsToRemove.add(fireball);
                }
            }
            fireballList.removeAll(fireballsToRemove);
            fireballsToRemove.clear();
        }

        gameMap[homeRow][homeCol].draw(this);

        // draws topbar and sidebar
        noStroke();
        fill(210, 180, 140);
        rect(0, 0, 760, 40);
        rect(640, 40,120, 640);

        // this section draws the tooltips for the tower upgrade costs
        if (towerList != null) {
            for (Tower tower : towerList) {
                if (mouseX > tower.getX() && mouseX < tower.getX() + 32 && mouseY > tower.getY() && mouseY < tower.getY() + 32) {
                    int rangeCost = 0;
                    int speedCost = 0;
                    int damageCost = 0;
                    if (isUpgradeRange || isUpgradeDamage || isUpgradeSpeed) {
                        fill(255, 255, 255);
                        stroke(0, 0 ,0);
                        rect(650, 500 , 90, 18);
                        rect(650, 518 , 90, 62);
                        rect(650, 580 , 90, 18);
                        fill(0, 0, 0);
                        textSize(12);
                        text("Upgrade Cost:", 652, 514);
                        if (isUpgradeRange) {
                            rangeCost = Math.round(tower.getRangeCost());
                            text("Range: " + rangeCost, 652, 534);
                        }
                        if (isUpgradeSpeed) {
                            speedCost = Math.round(tower.getSpeedCost());
                            text("Speed: " + speedCost, 652, 554);
                        }
                        if (isUpgradeDamage) {
                            damageCost = Math.round(tower.getDamageCost());
                            text("Damage: " + damageCost, 652, 574);
                        }
                        text("Total: " + (rangeCost + speedCost + damageCost), 652, 594);
                    }
                }
            }
        }

    // this section of code draws the buttons and their labels.
        for (Button b : buttonList) {
    
            stroke(0, 0, 0);
            if (mouseX > b.getX() && mouseX < b.getX() + 40 && mouseY > b.getY() && mouseY < b.getY() + 40) {
                // this section draws the cost indicators by calling the drawcost indicator function
                if (b instanceof TowerButton) {
                    TowerButton towerB = (TowerButton)b;
                    drawCostIndicator(b.getX(), b.getY(), towerB.getTowerPrice());
                } if (b instanceof ManaButton) {
                    drawCostIndicator(b.getX(), b.getY(), manaPoolSpellCost);
                }
                // this section of code changes the color of the button based on whether it is being hovered on or not
                if (b.getIsClicked()) {
                    fill(255, 255, 0);
                } else {
                    fill(255, 255, 255);
                }
            } else if (b.getIsClicked()) {
                fill(255, 255, 0);
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
                text("cost: " + Math.round(manaPoolSpellCost), b.getX()+45, b.getY()+34);
            }
        }
    // this section draws the manabar
        stroke(0, 0, 0);
        fill(255, 255, 255);
        rect(400, 4, 340, 28);
        fill(0, 255, 255);
        if (currentMana > 0) {
            rect(400, 4, (currentMana/currentManaCap)*340, 28);
        }
        fill(0, 0, 0);

        textSize(24);
        text("MANA:", 320, 28);
        if (currentMana > 0) {
            text(Math.round(currentMana)+" / "+Math.round(currentManaCap), 560, 28);
        } else {
            text("0 / "+Math.round(currentManaCap), 560, 28);
        }
        

    // this section draws the timer
        int waveDisplay;
        float timeLeftTillWave;
        if (currentWave.getPreWavePause() > 0) {
            waveDisplay = waveIndex + 1;
            timeLeftTillWave = currentWave.getPreWavePause();
            text("Wave " + waveDisplay + " starts: " + Math.round(timeLeftTillWave/60), 5, 28);
        } else if (waveIndex < waves.size() - 1) {
            waveDisplay = waveIndex + 2;
            timeLeftTillWave = currentWave.getDuration() + waves.get(waveIndex + 1).getPreWavePause();
            text("Wave " + waveDisplay + " starts: " + Math.round(timeLeftTillWave/60), 5, 28);
        }
    // this section of draw() draws the gameover / gamewin text
        textSize(72);
        fill(0, 255, 255);
        if (gameState == GameState.GAMEOVER) {
            text("GAME OVER!", 130, 340);
            textSize(36);
            text("Press \"R\" to Restart :(", 130, 392);
        } if (gameState == GameState.GAMEWIN) {
            text("YOU WON !", 170, 320);
            text("BAZINGA !", 170, 392);
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

        /**
     * Finds a path between two points on a grid using Depth-First Search (DFS).
     *
     * @param grid       The 2D grid of tiles representing the environment.
     * @param startRow   The row index of the starting tile.
     * @param startCol   The column index of the starting tile.
     * @param homeRow    The row index of the destination tile.
     * @param homeCol    The column index of the destination tile.
     * @return A list of tiles representing the path from the starting tile to the destination tile if a path exists,
     *         or null if no path is found.
     */

    public List<Tile> findPath(Tile[][] grid, int startRow, int startCol, int homeRow, int homeCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        List<Tile> path = new ArrayList<>();

        if (dfs(grid, startRow, startCol, homeRow, homeCol, visited, path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * A recursive helper function for DFS pathfinding.
     *
     * @param grid       The 2D grid of tiles representing the environment.
     * @param row        The current row index.
     * @param col        The current column index.
     * @param homeRow    The row index of the destination tile.
     * @param homeCol    The column index of the destination tile.
     * @param visited    A boolean array tracking visited cells.
     * @param path       A list of tiles representing the current path being explored.
     * @return true if a path from the current position to the destination is found, false otherwise.
     */

    private boolean dfs(Tile[][] grid, int row, int col, int homeRow, int homeCol, boolean[][] visited, List<Tile> path) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col].canEnemyWalk() == false || visited[row][col]) {
            return false;
        }

        visited[row][col] = true;
        path.add(grid[row][col]);

        if (row == homeRow && col == homeCol) {
            return true; // Return true to indicate that the destination is reached
        }

        int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (dfs(grid, newRow, newCol, homeRow, homeCol, visited, path)) {
                return true; // Return true if a path is found
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
