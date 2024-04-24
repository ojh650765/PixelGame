package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;


import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Slime extends AnimSprite implements IBoxCollidable, IRecyclable {
    private static final float SPEED = 1.f;
    private static final float RADIUS = 0.6f;
    private static final int[] resIds = {
            R.mipmap.blue_slime_sheet,R.mipmap.red_slime_sheet
    };
    public static final int MAX_LEVEL = resIds.length - 1;
    public static final float ANIM_FPS = 5.0f;
    protected RectF collisionRect = new RectF();
    private int level;

    private Slime(int level, int index) {
        super(resIds[level], ANIM_FPS);
        this.level = level;
        setPosition(Metrics.width + index, Metrics.height/4.5f, RADIUS);
        dx = -SPEED;
    }
    public static Slime get(int level, int index) {
        Slime enemy = (Slime) RecycleBin.get(Slime.class);
        if (enemy != null) {
            enemy.setAnimationResource(resIds[level], ANIM_FPS);
            enemy.level = level;
            enemy.setPosition(Metrics.width / 10 * (2 * index + 1), -RADIUS, RADIUS);
            return enemy;
        }
        return new Slime(level, index);
    }
    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if (dstRect.top > Metrics.height) {
            Scene.top().remove(MainScene.Layer.enemy, this);
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

    public int getScore() {
        return (level + 1) * 100;
    }
}
