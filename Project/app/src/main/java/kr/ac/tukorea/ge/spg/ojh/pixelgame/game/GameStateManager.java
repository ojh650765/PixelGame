package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.util.CollisionHelper;

public class GameStateManager implements IGameObject {

    private static GameStateManager instance;

    private  static float PlayerPower;
    private  static float PlayerDef;

    private boolean bisTurnActive;
    public GameStateManager () {
        PlayerPower = 1;
        PlayerDef = 0;
        bisTurnActive = false;
    }
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }
    public void UpdatePowerInfo(float p) {
        PlayerPower = p;
    }
    public void UpdateDefInfo(float d) {
        PlayerDef = d;
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
    @Override
    public void update(float elapsedSeconds) {

    }
    @Override
    public void draw(Canvas canvas) {
    }

}
