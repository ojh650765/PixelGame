package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class MapLoader implements IGameObject {
    private final MainScene scene;
    private final Random random = new Random();
    private static final float SideX = 2.56f;
    private static final float SideY = 4.1f;
    private static final float leftBound = Metrics.width / 2 - SideX;
    private static final float rightBound = Metrics.width / 2 + SideX;
    private static final float upperBound = Metrics.height / 2 - SideY + 2.9f;
    private static final  float lowerBound = Metrics.height / 2 + SideY;
    private float randomX;
    private float randomY;
    public MapLoader(MainScene scene) {
        this.scene = scene;
        generate();
    }

    @Override
    public void update(float elapsedSeconds) {

    }
    private void generate() {
;
        if (scene == null) return;
        for (int i = 0; i < 4; i++) {
            calculatePositionX();
            calculatePositionY();
            scene.add(MainScene.Layer.obstacle, new Obstacle(randomX, randomY));
        }
    }
    private void calculatePositionX() {
        int col = random.nextInt(8); // 0에서 7 사이의 칼럼 선택
        randomX = leftBound + (col * 0.64f) +0.32f;
    }

    private void calculatePositionY() {
        int row = random.nextInt(8); // 0에서 7 사이의 로우 선택
        randomY = upperBound + (row * 0.64f) + 0.32f;
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
