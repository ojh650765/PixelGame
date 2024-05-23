package kr.ac.tukorea.ge.spg.ojh.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class HorizonBackground extends Sprite {
    private final float width;
    private static int[] baseBitmapResId ={ R.mipmap.bg_forest,R.mipmap.bg_red_forest, R.mipmap.bg_magma};
    public HorizonBackground(int stage) {
        super(baseBitmapResId[stage - 1]);
        this.width = bitmap.getWidth() * Metrics.height / bitmap.getHeight();
        setPosition(Metrics.width / 2, Metrics.height / 2, width, Metrics.height);

    }


    @Override
    public void draw(Canvas canvas) {
        float curr = x % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.width) {
            dstRect.set(curr, 0, curr + width, Metrics.height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }
}
