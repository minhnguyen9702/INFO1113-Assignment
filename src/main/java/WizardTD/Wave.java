package WizardTD;

import java.util.ArrayList;

public class Wave {
    private float duration;
    private float originalDuration;
    private float preWavePause;
    private int totalNumberOfMonsters;
    private ArrayList<MonsterData> monsterDataList = new ArrayList<MonsterData>();

    public Wave(float duration, float preWavePause) {
        this.duration = duration;
        this.originalDuration = duration;
        this.preWavePause = preWavePause;
        totalNumberOfMonsters = 0;
    }

    public void addMonster(MonsterData monsterData) {
        monsterDataList.add(monsterData);
    }

    public float getDuration(){
        return duration;
    }

    public float getOriginalDuration(){
        return originalDuration;
    }

    public float getPreWavePause(){
        return preWavePause;
    }

    public void setDuration(float num){
        duration = num;
    }

    public void setPreWavePause(float num){
        preWavePause = num;
    }

    public int getTotalNumberOfMonsters(){
        return totalNumberOfMonsters;
    }

    public void addTotalNumberOfMonsters(int quantity){
        totalNumberOfMonsters += quantity;
    }
}
