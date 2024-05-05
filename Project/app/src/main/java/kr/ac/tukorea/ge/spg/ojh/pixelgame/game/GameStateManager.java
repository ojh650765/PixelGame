package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.util.CollisionHelper;

public class GameStateManager implements IGameObject {

    private static GameStateManager instance;
    private boolean isTurnActive;
    public GameStateManager () {
        isTurnActive = false;
    }
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }
    public boolean isTurnActive() {
        return isTurnActive;
    }
    public void setTurnActive(boolean turnActive) {
        isTurnActive = turnActive;
    }
    @Override
    public void update(float elapsedSeconds) {

    }
    @Override
    public void draw(Canvas canvas) {
    }

}
