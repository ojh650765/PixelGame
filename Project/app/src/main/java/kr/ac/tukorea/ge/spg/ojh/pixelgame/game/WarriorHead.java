package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class WarriorHead extends Sprite implements IBoxCollidable {
    private static final float HEAD_WIDTH = 0.6f;
    private static final float HEAD_HEIGHT = HEAD_WIDTH;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 5.0f;
    private static final float SideX = 2.56f;
    private static final float SideY = 4.1f;
    private static final String TAG = WarriorHead.class.getSimpleName();

    private boolean Warriormove = false;
    private boolean shouldDrawLine = false;
    private float targetX;
    private float targetY;
    private Paint dashedLinePaint;
    private static final float leftBound = Metrics.width / 2 - SideX;
    private static final float rightBound = Metrics.width / 2 + SideX;
    private static final float upperBound = Metrics.height / 2 - SideY + 2.9f;
    private static final float lowerBound = Metrics.height / 2 + SideY;

    private RectF targetRect = new RectF();
    private float power;
    private float fullHP = 100;
    private float currentHP=fullHP;
    private float fatkRatio;
    private float fdefRatio;
    private float def;
    private int earnPower;
    private int earnDef;
    private float defaultPower;
    private float defaultDef;

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

    public WarriorHead(float pw, float def) {
        super(R.mipmap.rightface);
        fatkRatio = 1;
        fdefRatio = 1;
        earnPower= 0;
        earnDef = 0;
        defaultPower = 0;
        defaultDef = 0;
        setPosition(leftBound, lowerBound, HEAD_WIDTH, HEAD_HEIGHT);
    }
    
    @Override
    public void update(float elapsedSeconds) {
        // x 위치 업데이트
        x += dx * elapsedSeconds;
        y += dy * elapsedSeconds;


        // 경계 충돌 검사 및 반응
        if (x < leftBound) {
            x = leftBound;
            dx = -dx; // 방향 반전
        } else if (x > rightBound) {
            x = rightBound;
            dx = -dx; // 방향 반전
        }

        // 상하 경계 검사 필요 시 추가
        if (y < upperBound) {
            y = upperBound;
            dy = -dy; // y축 방향 반전
        } else if (y > lowerBound) {
            y = lowerBound;
            dy = -dy; // y축 방향 반전
        }

        // 위치 업데이트
        setPosition(x, y, HEAD_WIDTH, HEAD_HEIGHT);
    }

    @Override
    public void draw(Canvas canvas) {
        if (shouldDrawLine && !Warriormove) {
            canvas.drawLine(x, y, targetX, targetY, getDashedLinePaint());
            //canvas.drawBitmap(targetBmp, null, targetRect, null);
        }
        super.draw(canvas);

    }

    public boolean onTouch(MotionEvent event) {
        Log.d(TAG, "onTouch: ");
        if (Warriormove) {
            return false;
        }
        float[] pts = Metrics.fromScreen(event.getX(), event.getY());
        float touchX = pts[0];
        float touchY = pts[1];
        if (isTouchInsideButton(touchX, touchY)) {
            return false;
        }
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (isTouchInsideHead(touchX, touchY)) {
                    targetX = touchX;
                    targetY = touchY;

                    targetRect.set(
                            targetX - TARGET_RADIUS, targetY - TARGET_RADIUS,
                            targetX + TARGET_RADIUS, targetY + TARGET_RADIUS
                    );

                    shouldDrawLine = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (shouldDrawLine) {
                    targetX = touchX;
                    targetY = touchY;

                    targetRect.set(
                            targetX - TARGET_RADIUS, targetY - TARGET_RADIUS,
                            targetX + TARGET_RADIUS, targetY + TARGET_RADIUS
                    );
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                float deltaX = targetX - x;  // X축 차이 계산
                float deltaY = targetY - y;  // Y축 차이 계산
                float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                dx = SPEED * (deltaX / distance);
                dy = SPEED * (deltaY / distance);

                Warriormove = true;
                shouldDrawLine = false;
              break;
        }
        return false;
    }

    public void Stop() {
        dx = 0;
        dy = 0;
        if (!GameStateManager.getInstance().isTurnActive()) {
            GameStateManager.getInstance().setTurnActive(true);
        }

    }
    private boolean isTouchInsideButton(float touchX, float touchY) {
        // 버튼의 위치와 크기에 따라 버튼 영역을 정의
        float buttonLeft = 1.5f;
        float buttonTop = 8.0f;
        float buttonRight = buttonLeft + 2.0f;
        float buttonBottom = buttonTop + 0.75f;

        return touchX >= buttonLeft && touchX <= buttonRight &&
                touchY >= buttonTop && touchY <= buttonBottom;
    }
    private boolean isTouchInsideHead(float touchX, float touchY) {
        return touchX >= x - HEAD_WIDTH / 2 && touchX <= x + HEAD_WIDTH / 2 &&
                touchY >= y - HEAD_HEIGHT / 2 && touchY <= y + HEAD_HEIGHT / 2;
    }
    public RectF getCollisionRect() {
        return dstRect;
    }

    public void UpdateDx() {
        dx *= -1;
    }

    public void UpdateDy() {
        dy *= -1;
    }

    public void UpdateDxValue(float newDx) {
        dx = newDx;
    }

    public void UpdateDyValue(float newDy) {
        dy = newDy;
    }

    public float GetDx() {
        return dx;
    }

    public float GetDy() {
        return dy;
    }

    public void SetX(float newX) {
        x += newX;
    }

    public void SetY(float newY) {
        y += newY;
    }

    public void ResetMove() {
        Warriormove = false;
        targetX = 0;
        targetY = 0;
        dx = 0;
        dy = 0;
    }

    public float GetPower() {
        return power;
    }
    public  int GetEarnPower(){
        if(this.power> 99)
            this.power = 99;
        return (int)this.power;
    }
    public  int GetEarnDef(){
        if(this.def> 99)
            this.def = 99;
        return (int)this.def;
    }
    public float GetHP() {return currentHP/fullHP; }
    public void GetDamage(float damage) {
        float effectiveDamage = damage - this.def;
        if (effectiveDamage > 0) {
            currentHP -= effectiveDamage;
            if (currentHP < 0) {
                currentHP = 0;
            }
        }
    }
    public void UpdatePower(float power) {
        this.power =  defaultPower + power * fatkRatio;
        earnPower = 0;
    }
    public void ResetDef(){
        earnDef = 0;
        defaultDef = 0;
        this.def = defaultDef;
    }
    public void DefUp(int df) {
        earnDef += df;
        this.def = defaultDef+ this.def + (earnDef * fdefRatio);
    }
    public void PowerUp(int power) {
        earnPower += power;
        this.power = defaultPower + this.power +(earnPower * fatkRatio);

    }
}
