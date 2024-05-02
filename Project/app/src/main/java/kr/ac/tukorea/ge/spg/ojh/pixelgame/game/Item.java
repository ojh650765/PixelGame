package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class Item  extends Sprite implements IBoxCollidable {
    private static final float ITEM_WIDTH = 0.6f;
    private static final float ITEM_HEIGHT = ITEM_WIDTH ;


    public Item(float x, float y) {
        super(R.mipmap.sword);
        setPosition(x, y, ITEM_WIDTH, ITEM_HEIGHT);
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