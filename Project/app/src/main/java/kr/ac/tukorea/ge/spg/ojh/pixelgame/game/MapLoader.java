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
    private static final int TOTAL_COUNT = 60;
    private static final  int PLAYER_COUNT = 1;
    private static final  int OBSTACLE_COUNT = 5;
    private static final  int BOMB_COUNT = 5;
    private float randomX = 0;
    private float randomY = 0;

    private Set<String> usedPositions = new HashSet<>();
    public MapLoader(MainScene scene) {
        this.scene = scene;
    }
    public void ResetGenerateObjects(WarriorHead warriorHead){

        usedPositions.clear();
        int col = random.nextInt(9);
        randomX = leftBound + (col * 0.64f);
        int row = random.nextInt(9);
        randomY = upperBound + (row * 0.66f);
        String posKey = generatePositionKey(randomX, randomY);
        warriorHead.setPosition(randomX,randomY);
        usedPositions.add(posKey);
        generateObjects(OBSTACLE_COUNT, MainScene.Layer.obstacle);
        generateObjects(BOMB_COUNT, MainScene.Layer.bomb);
        generateObjects(TOTAL_COUNT -PLAYER_COUNT - OBSTACLE_COUNT - BOMB_COUNT + 1, MainScene.Layer.item);


    }
    @Override
    public void update(float elapsedSeconds) {

    }
    private void generateObjects(int count, MainScene.Layer layerType) {

        int attempts = 0; // 현재 시도 횟수
        int attemptLimit = 20;
        int objectsPlaced = 0;
        if (scene == null) return;
        while (objectsPlaced < count && attempts < attemptLimit) {
            for (int i = 0; i < count; i++) {
                calculatePositionX();
                calculatePositionY();
                String posKey = generatePositionKey(randomX, randomY);
                if (!usedPositions.contains(posKey)) {
                   scene.add(layerType, createObject(layerType, randomX, randomY));
                    ++objectsPlaced;
                }
            }
            ++attempts;
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
        return String.format("%.2f,%.2f", x, y);
    }

    private void calculatePositionX() {
        int col = random.nextInt(9);
        randomX = leftBound + (col * 0.64f);
    }

    private void calculatePositionY() {
        int row = random.nextInt(9);
        randomY = upperBound + (row * 0.66f);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
