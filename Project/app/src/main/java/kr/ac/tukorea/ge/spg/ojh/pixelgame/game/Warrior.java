package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Warrior extends AnimSprite {
    private static final float WARRIOR_WIDTH = 3.f;
    private static final float WARRIOR_HEIGHT = WARRIOR_WIDTH;
    private static final float WARRIOR_ATTACK_MOTION_FRAME = 2;

    private static final float FIRE_INTERVAL = 0.93f;

    public static final float ANIM_FPS = 8.f;
    private boolean attaked = false;



    private float fireCoolTime = FIRE_INTERVAL;

    public Warrior() {
        super(R.mipmap.warrior_animsheet,ANIM_FPS);
        setPosition(0,WARRIOR_HEIGHT/2 , WARRIOR_WIDTH, WARRIOR_HEIGHT);
    }

    @Override
    public void update(float elapsedSeconds) {
        if (frameIndex==WARRIOR_ATTACK_MOTION_FRAME && !attaked) {
            Slash();
            attaked = true;
        }
        if(frameIndex!=WARRIOR_ATTACK_MOTION_FRAME) attaked = false;
    }

    private void Slash() {
        Scene.top().add(MainScene.Layer.slash, SwordStrike.get(x, y ));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    private void setTargetX(float x) {

    }

    public boolean onTouch(MotionEvent event) {
        return false;
    }
}
