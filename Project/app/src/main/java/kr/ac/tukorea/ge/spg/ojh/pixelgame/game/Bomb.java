package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class Bomb  extends Sprite implements IBoxCollidable {
    private static final float BOMB_WIDTH = 0.55f;
    private static final float BOMB_HEIGHT = BOMB_WIDTH ;


    public Bomb(float x, float y) {
        super(R.mipmap.bomb);
        setPosition(x, y, BOMB_WIDTH, BOMB_HEIGHT);
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