package kr.ac.tukorea.ge.spg.ojh.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class HorizonBackground extends Sprite {
    private final float width;

    public HorizonBackground(int bitmapResId) {
        super(bitmapResId);
        this.width = Metrics.width; // 화면 전체 폭으로 설정
        float startX = 0; // 이미지 시작 위치를 0으로 설정
        setPosition(startX, 0, startX + this.width, Metrics.height);
    }


    @Override
    public void draw(Canvas canvas) {
        // 화면 전체에 이미지를 그리기 위한 설정
        dstRect.set(0, 0,width, Metrics.height);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
