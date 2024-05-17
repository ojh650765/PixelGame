package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;


import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Slime extends AnimSprite implements IBoxCollidable, IRecyclable {
    private int SLIME_HITTED_MOTION_END_FRAME = 2;

    public enum State {
        idle, hitted
    }
    private static final float SPEED = 1.f;
    private static final float RADIUS = 0.6f;
    private static final int[] resIds = {
            R.mipmap.blue_slime_sheet,
            R.mipmap.blue_slime_hitted_sheet,
    };
    public static final int MAX_LEVEL = resIds.length - 1;
    public static final float ANIM_FPS = 5.0f;
    protected RectF collisionRect = new RectF();
    private float targetX;
    public boolean isMoving;
    private  float HP;
    private  State state = State.idle;
    private Slime(int index) {
        super(resIds[0], ANIM_FPS);
        setPosition(Metrics.width + index, Metrics.height/4.5f, RADIUS);
        HP = 40.f;
        dx = 0;
    }
    public static Slime get(int index) {
        Slime enemy = (Slime) RecycleBin.get(Slime.class);
        if (enemy != null) {
            enemy.setAnimationResource(resIds[0], ANIM_FPS);
            enemy.setPosition(Metrics.width / 10 * (2 * index + 1), -RADIUS, RADIUS);
            enemy.HP = 100.f;
            return enemy;
        }
        return new Slime(index);
    }
    @Override
    public void update(float elapsedSeconds) {
        switch (state){
            case idle:
                ChangeAnimSprite(resIds[0],ANIM_FPS);
                break;
            case hitted:
                ChangeAnimSprite(resIds[1],ANIM_FPS);
                if(frameIndex == SLIME_HITTED_MOTION_END_FRAME){
                    state= State.idle;
                }
                break;
        }
        if (isMoving) {
            if (Math.abs(x-targetX) > 0.01f)  {
              dx = -SPEED;
            } else {
                dx = 0;
                isMoving = false;
            }
            super.update(elapsedSeconds);
        }else{
            dx = 0;
        }
        collisionRect.set(dstRect);
        collisionRect.inset(0.11f, 0.11f);
    }
    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void onRecycle() {

    }
    public  void ChangeState(State s) {
        anim_PlayTime = System.currentTimeMillis();
        state = s;
    }
    public boolean ApplyDamage(float damaage){
        HP -= damaage;
        if(HP<=0)
            return true;
        return  false;
    }


    public void startLeftMove(float distanceToMove) {
        if (dx== 0) {
            this.targetX = x - distanceToMove;
            this.isMoving = true;
        }else{
            this.targetX = x;
            this.isMoving = false;
        }
    }


}
