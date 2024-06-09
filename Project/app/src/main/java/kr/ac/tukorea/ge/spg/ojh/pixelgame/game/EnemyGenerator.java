package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.util.Log;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.game.MainScene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private  MainScene scene;
    private int stage;
    private static final int[] slimeCountPerStage = {3, 4, 10};
    private static final int[][] slimeTypePerStage = {
            {0,1,0},
            {2,1,1,2,1},
            {3,2,3,1,1,0,2,3,2,3}
    };

    public EnemyGenerator(MainScene scene){
        this.scene = scene;
        this.stage = scene.getStage();

        generate(stage);
    }
    @Override
    public void update(float elapsedSeconds) {
    }

    private void generate(int stage) {
        if (scene == null) return;
        int numberOfSlimes = slimeCountPerStage[stage - 1];
        for (int i = 0; i < numberOfSlimes; i++) {
           scene.add(MainScene.Layer.enemy, Slime.get(stage,slimeTypePerStage[stage-1][i],i));

        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
