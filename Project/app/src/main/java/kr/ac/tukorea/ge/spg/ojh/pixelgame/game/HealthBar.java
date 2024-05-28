package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import androidx.annotation.NonNull;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class HealthBar extends Sprite {

    protected MainScene.Layer layer;
    private static final float HEALTH_WIDTH = 3.5f;
    private static final float HEALTH_HEIGHT = HEALTH_WIDTH*216/1044;
    protected HealthBar (MainScene.Layer layer) {
        super(R.mipmap.void_bar);
        this.layer = layer;
        setPosition(Metrics.width/6.f, Metrics.height/2.5f ,HEALTH_WIDTH, HEALTH_HEIGHT);
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
