package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;


import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.res.Sound;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Slime extends AnimSprite implements IBoxCollidable, IRecyclable {
    private int type;
    private int SLIME_HITTED_MOTION_END_FRAME = 2;
    private int SLIME_DEAD_MOTION_END_FRAME = 3;
    private float fPower;
    private boolean deadAnimPlayDone;

    public float getMoveDistance() {
        return fDistance;
    }
    public boolean GetAttackStats() {
        if(dead || deadAnimPlayDone) return false;
        return battack;
    }

    public float GetPower() {
        return fPower;
    }

    public enum State {
        idle, hitted, dead,none
    }
    private float SPEED = 1.f;
    private float fDistance = 1.f;
    private boolean battack = false;
    private float RADIUS = 0.7f;

    private static final int[][] resIds = {

            {R.mipmap.blue_slime_sheet, R.mipmap.blue_slime_hitted_sheet, R.mipmap.blue_slime_dead_sheet},
            {R.mipmap.red_slime_sheet, R.mipmap.red_slime_hitted_sheet, R.mipmap.red_slime_dead_sheet},
            {R.mipmap.magma_slime_sheet, R.mipmap.magma_slime_hitted_sheet, R.mipmap.magma_slime_dead_sheet},
            {R.mipmap.boss_slime_sheet, R.mipmap.boss_slime_hitted_sheet, R.mipmap.boss_slime_dead_sheet}

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
    private Slime(int stage, int type, int index) {
        super(resIds[type][0], ANIM_FPS);
        this.level = stage;
        this.type = type;
        if(type == 2) RADIUS =0.9f;
        else if(type == 3) RADIUS =1.4f;
        setPosition(Metrics.width -RADIUS* 3.5f + index, Metrics.height/(3 * RADIUS + 2.2f), RADIUS);
        dead =false;
        InitSlime();
    }

    public void InitSlime( ) {
        if(type == 0 ){
            HP = 50.f;
            fDistance = 3.f;
            SPEED = 3.f;
            fPower = 5.f;
        }
        else if(type == 1){
            HP = 70.f;
            fDistance = 2.f;
            SPEED = 2.f;
            fPower = 10.f;
        }
        else if(type == 2){
            HP = 120.f;
            fDistance = 2.2f;
            SPEED = 2.2f;
            fPower = 15.f;
        }
        else if(type == 3){
            HP = 260.f;
            SPEED = 1.f;
            fDistance = 1.f;
            fPower = 40.f;
        }
        battack = false;
        dx = 0;
        targetX = x;
        isMoving = false;
    }

    public static Slime get(int stage, int type, int index) {
        return new Slime(stage, type, index);
    }
    @Override
    public void update(float elapsedSeconds) {
        if(GameStateManager.getInstance().GetisPause())return;
        if(deadAnimPlayDone) return;
        switch (state){
            case idle:
                ChangeAnimSprite(resIds[type][0],ANIM_FPS);
                break;
            case hitted:
                ChangeAnimSprite(resIds[type][1],ANIM_FPS);
                if(frameIndex == SLIME_HITTED_MOTION_END_FRAME){
                    Sound.playEffect(R.raw.hit_damage2);
                    state= State.idle;
                }
                break;
            case dead:
                ChangeAnimSprite(resIds[type][2],ANIM_FPS);
                if(frameIndex == SLIME_DEAD_MOTION_END_FRAME){
                    Sound.playEffect(R.raw.explosion);
                    deadAnimPlayDone = true;
                    state= State.none;
                }
                break;
        }
        if(x<= 2.5f){
            battack = true;
        }
        else if (!dead && isMoving && x > 2.5f) {
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
        if(HP<=0) {
            dead = true;
        }
        collisionRect.set(dstRect);
        collisionRect.inset(0.11f, 0.11f);

    }
    @Override
    public void draw(Canvas canvas){
        if(deadAnimPlayDone) return;
        super.draw(canvas);

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
