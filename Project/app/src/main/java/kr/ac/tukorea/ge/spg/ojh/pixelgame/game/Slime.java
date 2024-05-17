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
    private float fPower;

    public float getMoveDistance() {
        return fDistance;
    }
    public boolean GetAttackStats() {
        return battack;
    }

    public float GetPower() {
        return fPower;
    }

    public enum State {
        idle, hitted
    }
    private float SPEED = 1.f;
    private float fDistance = 1.f;
    private boolean battack = false;
    private static final float RADIUS = 0.6f;
    private static final int[] resIds = {
            R.mipmap.blue_slime_sheet,
            R.mipmap.blue_slime_hitted_sheet,
            R.mipmap.red_slime_sheet,
            R.mipmap.red_slime_hitted_sheet,
    };
    public static final int MAX_LEVEL = resIds.length - 1;
    public static final float ANIM_FPS = 5.0f;
    protected RectF collisionRect = new RectF();
    private float targetX;
    public boolean isMoving;
    private  float HP;
    private int level;
    public boolean dead = false;
    private  State state = State.idle;
    private Slime(int stage, int index) {
        super(resIds[(stage -1 ) * 2], ANIM_FPS);
        this.level = stage;
        setPosition(Metrics.width + index, Metrics.height/4.5f, RADIUS);
        if(stage == 1 ){
            HP = 80.f;
            fDistance = 4.f;
            SPEED = 4.f;
            fPower = 10.f;
        }
        else if(stage == 2){
            HP = 60.f;
            fDistance = 5.f;
            SPEED = 5.f;
            fPower = 20.f;
        }
        else {
            HP = 100.f;
            SPEED = 1.f;
            fDistance = 1.f;
            fPower = 30.f;
        }
        battack = false;
        dx = 0;
    }
    public static Slime get(int stage, int index) {
        Slime enemy = (Slime) RecycleBin.get(Slime.class);
        if (enemy != null) {
            enemy.setAnimationResource(resIds[stage], ANIM_FPS);
            enemy.setPosition(Metrics.width / 10 * (2 * index + 1), -RADIUS, RADIUS);
            if(stage == 1 ){
                enemy.HP = 40.f;
                enemy.fDistance = 6.f;
                enemy.SPEED = 6.f;
                enemy.fPower = 10.f;
            }
            else if(stage == 2){
                enemy.HP = 60.f;
                enemy.fDistance = 5.f;
                enemy.SPEED = 5.f;
                enemy.fPower = 20.f;
            }
            else {
                enemy.HP = 100.f;
                enemy.fDistance = 3.f;
                enemy.SPEED = 3.f;
                enemy.fPower = 30.f;

            }
            enemy.battack = false;
            enemy.level = stage;
            return enemy;
        }
        return new Slime(stage, index);
    }
    @Override
    public void update(float elapsedSeconds) {
        switch (state){
            case idle:
                ChangeAnimSprite(resIds[(level -1 ) * 2],ANIM_FPS);
                break;
            case hitted:
                ChangeAnimSprite(resIds[(level -1 ) * 2 + 1],ANIM_FPS);
                if(frameIndex == SLIME_HITTED_MOTION_END_FRAME){
                    state= State.idle;
                }
                break;
        }
        if(x<= 2.5f){
            battack = true;
        }
        else if (isMoving && x > 2.5f) {
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
    public boolean ApplyDamage(float damage){
        HP -= damage;
        if(HP<=0) {
            dead = true;
            return true;
        }
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
