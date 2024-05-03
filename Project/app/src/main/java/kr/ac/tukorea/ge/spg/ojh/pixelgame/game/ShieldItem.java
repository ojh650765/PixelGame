package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class ShieldItem extends Item {
    private static final float ITEM_WIDTH = 0.6f;
    private static final float ITEM_HEIGHT = ITEM_WIDTH ;
    public ShieldItem(float x, float y) {
        super();
        setPosition(x, y, ITEM_WIDTH, ITEM_HEIGHT);
        bitmap = BitmapPool.get(R.mipmap.shield);
    }

}
