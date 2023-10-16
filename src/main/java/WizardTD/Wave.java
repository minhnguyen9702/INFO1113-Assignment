package WizardTD;

import java.util.ArrayList;

/**
 * The Wave class represents a wave of monsters in the WizardTD. It stores information
 * about the monsters in the wave, the wave's pre-wave pause duration and duration.
 */
public class Wave {
    private float duration;
    private float originalDuration;
    private float preWavePause;
    private int totalNumberOfMonsters;
    private ArrayList<MonsterData> monsterDataList = new ArrayList<MonsterData>();
    private ArrayList<MonsterData> monsterDataToRemove = new ArrayList<MonsterData>();

    /**
     * Creates a new instance of Wave with the specified duration and pre-wave pause time.
     *
     * @param duration       The duration of the wave in ticks (1/60th of a second).
     * @param preWavePause   The pause time before the wave starts in ticks (1/60th of a second).
     */
    public Wave(float duration, float preWavePause) {
        this.duration = duration;
        this.originalDuration = duration;
        this.preWavePause = preWavePause;
        totalNumberOfMonsters = 0;
    }

    /**
     * Adds a monster's data to the wave.
     *
     * @param monsterData The MonsterData object representing the monster data to add to the wave.
     */
    public void addMonster(MonsterData monsterData) {
        monsterDataList.add(monsterData);
    }

    /**
     * Returns the current duration of the wave.
     *
     * @return The current duration of the wave in ticks (1/60th of a second).
     */
    public float getDuration(){
        return duration;
    }

       /**
     * Returns the original duration of the wave (before any modifications).
     *
     * @return The original duration of the wave in ticks (1/60th of a second).
     */
    public float getOriginalDuration(){
        return originalDuration;
    }

    /**
     * Returns the time before the wave starts.
     *
     * @return the time before the wave starts in ticks (1/60th of a second).
     */
    public float getPreWavePause(){
        return preWavePause;
    }

    /**
     * Sets the current duration of the wave.
     *
     * @param num The new duration of the wave in ticks (1/60th of a second).
     */
    public void setDuration(float num){
        duration = num;
    }

    /**
     * Sets the pre-wave pause time.
     *
     * @param num The new pre-wave pause time in ticks (1/60th of a second).
     */
    public void setPreWavePause(float num){
        preWavePause = num;
    }

    /**
     * Returns the total quanitity of every type of monster in the wave.
     *
     * @return The total quanitity of every type of monster in the wave.
     */
    public int getTotalNumberOfMonsters(){
        return totalNumberOfMonsters;
    }

    /**
     * Adds the specified quantity to the total number of monsters in the wave.
     *
     * @param quantity The quantity of monsters to add.
     */
    public void addTotalNumberOfMonsters(int quantity){
        totalNumberOfMonsters += quantity;
    }

    /**
     * Returns an Arraylist of the monster data for this wave.
     *
     * @return An ArrayList of MonsterData objects in the wave.
     */
    public ArrayList<MonsterData> getMonsterDataList() {
        return monsterDataList;
    }

     /**
     * Removes monsters with a quantity of 0 from the Wave's monster data list.
     */
    public void updateMonsterData() {
        for (MonsterData monsterData : monsterDataList) {
            if (monsterData.getQuantity() == 0) {
                monsterDataToRemove.add(monsterData);
            }
        }
        monsterDataList.removeAll(monsterDataToRemove);
        monsterDataToRemove.clear();
    }
}
