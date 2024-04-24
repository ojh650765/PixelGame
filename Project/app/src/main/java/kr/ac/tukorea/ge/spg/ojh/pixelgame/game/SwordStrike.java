package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class SwordStrike extends Sprite implements IBoxCollidable, IRecyclable {
    private static final float SWORDSTRIKE_WIDTH = 2.f;
    private static final float SWORDSTRIKE_HEIGHT = SWORDSTRIKE_WIDTH * 16 / 15;
    private static final float SPEED = 20.0f;

    private SwordStrike(float x, float y) {
        super(R.mipmap.swordstrike);
        setPosition(x, y, SWORDSTRIKE_WIDTH, SWORDSTRIKE_HEIGHT);
        dx = SPEED;
    }
    public static SwordStrike get(float x, float y) {
        SwordStrike slash = (SwordStrike) RecycleBin.get(SwordStrike.class);
        if (slash != null) {
            slash.setPosition(x, y, SWORDSTRIKE_WIDTH, SWORDSTRIKE_HEIGHT);
            return slash;
        }
        return new SwordStrike(x, y);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if (dstRect.bottom < 0) {
            Scene.top().remove(MainScene.Layer.slash, this);
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
