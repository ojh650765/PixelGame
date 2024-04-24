package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;

import androidx.annotation.NonNull;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class Board extends Sprite {

    protected MainScene.Layer layer;
    private static final float BOARD_WIDTH =6.f;
    private static final float BOARD_HEIGHT = BOARD_WIDTH;
    private final Random random = new Random();

    protected Board(MainScene.Layer layer) {
        super(R.mipmap.tile2);
        this.layer = layer;
        setPosition(Metrics.width/2, Metrics.height/2 +1.5f ,BOARD_WIDTH, BOARD_HEIGHT);
    }
    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + System.identityHashCode(this) + "(" + width + "x" + height + ")";
    }


    @Override
    public void update(float elapsedSeconds) {

    }

}
