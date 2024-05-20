package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.game.MainScene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private  MainScene scene;
    private int stage;
    public EnemyGenerator(MainScene scene){
        this.scene = scene;
        this.stage = scene.getStage();
        generate();
    }
    @Override
    public void update(float elapsedSeconds) {
    }

    private void generate() {
        if (scene == null) return;
        for (int i = 0; i < 3; i++) {
            scene.add(MainScene.Layer.enemy, Slime.get(stage, i));

        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
