package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class SwordStrike extends Sprite implements IBoxCollidable, IRecyclable {
    private static final float SWORDSTRIKE_WIDTH = 0.68f;
    private static final float SWORDSTRIKE_HEIGHT = SWORDSTRIKE_WIDTH * 40 / 28;
    private static final float SPEED = 20.0f;

    private SwordStrike(float x, float y) {
        super(R.mipmap.swordstrike);
        setPosition(x, y, SWORDSTRIKE_WIDTH, SWORDSTRIKE_HEIGHT);
        dy = -SPEED;
    }
    public static SwordStrike get(float x, float y) {
        SwordStrike bullet = (SwordStrike) RecycleBin.get(SwordStrike.class);
        if (bullet != null) {
            bullet.setPosition(x, y, SWORDSTRIKE_WIDTH, SWORDSTRIKE_HEIGHT);
            return bullet;
        }
        return new SwordStrike(x, y);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if (dstRect.bottom < 0) {
            Scene.top().remove(MainScene.Layer.bullet, this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    @Override
    public void onRecycle() {

    }
}
