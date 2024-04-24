package kr.ac.tukorea.ge.spg.ojh.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class HorizonBackground extends Sprite {
    private final float width;

    public HorizonBackground(int bitmapResId) {
        super(bitmapResId);
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
