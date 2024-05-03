package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private final Random random = new Random();

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

        wave++;
        Log.v(TAG, "Generating: wave " + wave);
        for (int i = 0; i < 5; i++) {
            int level = (wave + 15) / 10 - random.nextInt(3);
            if (level < 0) level = 0;
            if (level > Slime.MAX_LEVEL) level = Slime.MAX_LEVEL;
            scene.add(MainScene.Layer.enemy, Slime.get(level, i));
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
