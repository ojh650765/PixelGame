package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class Bomb  extends Sprite implements IBoxCollidable {
    private static final float OBSTACLE_WIDTH = 0.6f;
    private static final float OBSTACLE_HEIGHT = OBSTACLE_WIDTH ;


    public Bomb(float x, float y) {
        super(R.mipmap.bomb);
        setPosition(x, y, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}