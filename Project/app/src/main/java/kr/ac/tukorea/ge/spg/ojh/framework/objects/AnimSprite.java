package kr.ac.tukorea.ge.spg.ojh.framework.objects;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;

public class AnimSprite extends Sprite {
    protected Rect srcRect = new Rect();
    private float fps;
    private int frameWidth, frameHeight;
    private int frameCount;
    protected int frameIndex;
    private final long createdOn;
    protected long anim_PlayTime;

    public AnimSprite(int mipmapId, float fps) {
        super(mipmapId);
        setAnimationResource(0, fps, 0);
        createdOn = System.currentTimeMillis();
        anim_PlayTime = System.currentTimeMillis();
    }
    public void setAnimationResource(int mipmapId, float fps) {
        setAnimationResource(mipmapId, fps, 0);
    }
    public void setAnimationResource(int mipmapId, float fps, int frameCount) {
        if (mipmapId != 0) {
            bitmap = BitmapPool.get(mipmapId);
        }
        this.fps = fps;
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        if (frameCount == 0) {
            this.frameWidth = imageHeight;
            this.frameHeight = imageHeight;
            this.frameCount = imageWidth / imageHeight;
        } else {
            this.frameWidth = imageWidth / frameCount;
            this.frameHeight = imageHeight;
            this.frameCount = frameCount;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        // AnimSprite 는 단순반복하는 이미지이므로 time 을 update 에서 꼼꼼히 누적하지 않아도 된다.
        // draw 에서 생성시각과의 차이로 frameIndex 를 계산한다.
        long now = System.currentTimeMillis();
        float time = (now - anim_PlayTime) / 1000.0f;
        frameIndex = Math.round(time * fps) % frameCount;
        srcRect.set(frameIndex * frameWidth, 0, (frameIndex + 1) * frameWidth, frameHeight);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void ChangeAnimSprite(int mipmapId, float fps){
        ChangeSprite(mipmapId);
        setAnimationResource(0, fps, 0);

    }
}
