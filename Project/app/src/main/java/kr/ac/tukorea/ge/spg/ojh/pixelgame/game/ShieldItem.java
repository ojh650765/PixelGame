package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class ShieldItem extends Item {

    public ShieldItem(float x, float y) {
        super(x, y);
        bitmap = BitmapPool.get(R.mipmap.shield);
    }

}
