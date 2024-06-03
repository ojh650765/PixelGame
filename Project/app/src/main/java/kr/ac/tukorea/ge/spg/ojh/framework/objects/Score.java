package kr.ac.tukorea.ge.spg.ojh.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Score implements IGameObject {
    private final Bitmap bitmap;
    private final Rect srcRect = new Rect();
    private final RectF dstRect = new RectF();
    private final int srcCharWidth, srcCharHeight;
    private float right, top, dstCharWidth, dstCharHeight;
    private int score;

    private int displayScore;

    private static final float DEFAULT_SCORE_WIDTH = 0.18f;
    private static final float DEFAULT_SCORE_HEIGHT = DEFAULT_SCORE_WIDTH * 240 / 70;

    public Score(int mipmapId) {
        this.bitmap = BitmapPool.get(mipmapId);
        this.srcCharWidth = bitmap.getWidth() / 10;
        this.srcCharHeight = bitmap.getHeight();
    }

    public void setPosition(float x, float y) {
        setPosition(x, y, DEFAULT_SCORE_WIDTH, DEFAULT_SCORE_HEIGHT);

    }

    public void setPosition(float right, float top, float width, float height) {
        this.right = right;
        this.top = top;
        this.dstCharWidth = width;
        this.dstCharHeight = height;

        dstRect.set(right - width / 2, top - height / 2, right + width / 2, top + height / 2);
    }
    public void setScore(int score) {
        this.score = this.displayScore = score;
    }

    @Override
    public void update(float elapsedSeconds) {
        int diff = score - displayScore;
        if (diff == 0) return;
        if (-10 < diff && diff < 0) {
            displayScore--;
        } else if (0 < diff && diff < 10) {
            displayScore++;
        } else {
            displayScore += diff / 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        float x = right;
        if (value == 0) {
            int digit = 0; // 숫자 0
            srcRect.set(digit * srcCharWidth, 0, (digit + 1) * srcCharWidth, srcCharHeight);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth, top + dstCharHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        }
        while (value > 0) {
            int digit = value % 10;
            srcRect.set(digit * srcCharWidth, 0, (digit + 1) * srcCharWidth, srcCharHeight);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth, top + dstCharHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            value /= 10;
        }
    }

    public void add(int amount) {
        score += amount;
    }
}
