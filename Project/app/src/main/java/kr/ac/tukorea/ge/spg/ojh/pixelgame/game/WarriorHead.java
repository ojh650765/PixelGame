package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class WarriorHead extends Sprite implements IBoxCollidable {
    private static final float HEAD_WIDTH = 0.6f;
    private static final float HEAD_HEIGHT = HEAD_WIDTH;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 5.0f;
    private static final float SideX = 2.56f;
    private static final float ONESTEP = 0.7f;
    private static final float SideY = 4.1f;
    private boolean Warriormove = false;
    private boolean shouldDrawLine = false;
    private float targetX;
    private float targetY;
    private Paint dashedLinePaint;
    private static final float leftBound = Metrics.width / 2 - SideX;
    private static final float rightBound = Metrics.width / 2 + SideX;
    private static final float upperBound = Metrics.height / 2 - SideY + 2.9f;
    private static final  float lowerBound = Metrics.height / 2 + SideY;

    private RectF targetRect = new RectF();
    private Bitmap targetBmp;
    private Paint getDashedLinePaint() {
        if (dashedLinePaint == null) {
            dashedLinePaint = new Paint();
            dashedLinePaint.setColor(Color.YELLOW);  // 점선의 색 설정
            dashedLinePaint.setStyle(Paint.Style.STROKE);  // 선으로 그리기
            dashedLinePaint.setStrokeWidth(0.1f);  // 선의 두께 설정
            dashedLinePaint.setPathEffect(new DashPathEffect(new float[]{0.2f, 0.3f}, 0));
            // 점선의 패턴 설정, 10 픽셀 그리고 20 픽셀 건너뛰기
        }
        return dashedLinePaint;
    }

    public WarriorHead() {
        super(R.mipmap.rightface);
        setPosition(Metrics.width/2 - SideX, Metrics.height/2 - SideY+2.9f, HEAD_WIDTH, HEAD_HEIGHT);
        targetBmp = BitmapPool.get(R.mipmap.rightface);
    }

    @Override
    public void update(float elapsedSeconds) {
        // x 위치 업데이트
        x += dx * elapsedSeconds;
        y += dy * elapsedSeconds;

        // 경계 충돌 검사 및 반응
        if (x  < leftBound) {
            x = leftBound;
            dx = -dx; // 방향 반전
        } else if (x  > rightBound) {
            x = rightBound;
            dx = -dx; // 방향 반전
        }

        // 상하 경계 검사 필요 시 추가
        if (y  < upperBound) {
            y = upperBound;
            dy = -dy; // y축 방향 반전
        } else if (y  > lowerBound) {
            y = lowerBound;
            dy = -dy; // y축 방향 반전
        }

        // 위치 업데이트
        setPosition(x, y, HEAD_WIDTH, HEAD_HEIGHT);
    }

    @Override
    public void draw(Canvas canvas) {
        if(shouldDrawLine && !Warriormove) {
            canvas.drawLine(x, y, targetX, targetY, getDashedLinePaint());
            //canvas.drawBitmap(targetBmp, null, targetRect, null);
        }
        super.draw(canvas);

    }

    public boolean onTouch(MotionEvent event) {
        if(Warriormove){
            return false;
        }
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                targetX = pts[0];
                targetY = pts[1];
                targetRect.set(
                        targetX - TARGET_RADIUS, targetY - TARGET_RADIUS,
                        targetX + TARGET_RADIUS, targetY + TARGET_RADIUS
                );
                shouldDrawLine = true;
                return true;
            case MotionEvent.ACTION_UP:
                float deltaX = targetX - x;  // X축 차이 계산
                float deltaY = targetY - y;  // Y축 차이 계산
                float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                dx = SPEED * (deltaX / distance);
                dy = SPEED * (deltaY / distance);

                Warriormove = true;
                shouldDrawLine = false;
                return true;
        }
        return false;
    }
    public RectF getCollisionRect() {
        return dstRect;
    }
}
