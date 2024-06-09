package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;


import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.util.CollisionHelper;

public class GameStateManager implements IGameObject {

    private static GameStateManager instance;
    private static final Random random = new Random();
    private  static float PlayerPower;
    private  static float PlayerDef;

    private boolean bisTurnActive;
    private boolean isPause;
    private int eventStageNum;
    private boolean canIOpenEventStage;
    private float PlayerHP;


    public GameStateManager () {
        PlayerPower = 1;
        PlayerDef = 0;
        PlayerHP = 100;
        InitState(0);
    }
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }

        return instance;
    }
    public  void InitState(int eventStageNum){
        bisTurnActive = false;
        isPause = false;
        this.eventStageNum = eventStageNum;
        this.canIOpenEventStage = random.nextBoolean();
    }
    public void UpdatePowerInfo(float p) {
        PlayerPower = p;
    }
    public void UpdateDefInfo(float d) {
        PlayerDef = d;
    }
    public void UpdatePlayerHP(float hp) {
        PlayerHP = hp;
    }
    public float GetPlayerHP(){
        return PlayerHP;
    }

    public float GetPowerInfo(){
        return  PlayerPower;
    }
    public  float GetDefInfo(){
        return PlayerDef;
    }
    public boolean isTurnActive() {
        return bisTurnActive;
    }
    public void setTurnActive(boolean turnActive) {
        bisTurnActive = turnActive;
    }
    public void SetPauseState(boolean pause) {
        isPause = pause;
    }
    @Override
    public void update(float elapsedSeconds) {

    }
    @Override
    public void draw(Canvas canvas) {
    }

    public boolean GetisPause() {
        return isPause;
    }

    public int GetEventStage() {
        return eventStageNum;
    }
    public boolean GetEventStageCanOpen(){
        return  canIOpenEventStage;
    }
}
