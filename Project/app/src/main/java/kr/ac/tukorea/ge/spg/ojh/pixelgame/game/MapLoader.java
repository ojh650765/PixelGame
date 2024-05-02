package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

    private static Set<String> usedPositions = new HashSet<>();
    public MapLoader(MainScene scene) {
        this.scene = scene;

        ResetGenerateObjects();
    }
    public void ResetGenerateObjects(){
        usedPositions.add(generatePositionKey(leftBound ,
                lowerBound ));
        generateObjects(4, MainScene.Layer.obstacle);
        generateObjects(3, MainScene.Layer.bomb);
        generateObjects(100, MainScene.Layer.item);
    }
    @Override
    public void update(float elapsedSeconds) {

    }
    private void generateObjects(int count, MainScene.Layer layerType) {
        if (scene == null) return;
        for (int i = 0; i < count; i++) {
            calculatePositionX();
            calculatePositionY();
            String posKey = generatePositionKey(randomX, randomY);
            if (!usedPositions.contains(posKey)) { // 위치가 중복되지 않았는지 확인
                scene.add(layerType, createObject(layerType, randomX, randomY));
            }
        }
    }
    private IGameObject createObject(MainScene.Layer layerType, float x, float y) {
        String posKey = generatePositionKey(x,y);
        if (layerType == MainScene.Layer.obstacle) {
            usedPositions.add(posKey);
            return new Obstacle(x, y);
        } else if (layerType == MainScene.Layer.bomb) {
            usedPositions.add(posKey);
            return new Bomb(x, y);
        }
        else if (layerType == MainScene.Layer.item) {
            if( random.nextInt(2) == 0){
                usedPositions.add(posKey);
                return new SwordItem(x, y);
            }
            else {
                usedPositions.add(posKey);
                return new ShieldItem(x, y);
            }
        }
        return null;
    }

    private String generatePositionKey(float x, float y) {
        return x + "," + y;
    }

    private void calculatePositionX() {
        int col = random.nextInt(9); // 0에서 7 사이의 칼럼 선택
        randomX = leftBound + (col * 0.64f);
    }

    private void calculatePositionY() {
        int row = random.nextInt(9); // 0에서 7 사이의 로우 선택
        randomY = upperBound + (row * 0.64f);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
