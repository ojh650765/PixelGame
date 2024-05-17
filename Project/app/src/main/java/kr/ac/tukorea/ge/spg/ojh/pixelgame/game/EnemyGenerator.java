package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();

    private int wave=0;
    private  Scene scene;
    public EnemyGenerator(Scene scene){
        this.scene =scene;
        generate();
    }
    @Override
    public void update(float elapsedSeconds) {
    }

    private void generate() {
        if (scene == null) return;
        for (int i = 0; i < 3; i++) {
            scene.add(MainScene.Layer.enemy, Slime.get(i));
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
