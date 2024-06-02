package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class Bomb  extends AnimSprite implements IBoxCollidable {
    private static final float BOMB_WIDTH = 0.5f;
    private static final float BOMB_HEIGHT = BOMB_WIDTH;
    private boolean isExplosion;

    private static final int[] resIds = {
            R.mipmap.bomb,
            R.mipmap.explosion_sprite
    };
    private static float ANIM_FPS = 5.f;
    private static float ANIM_END_FPS = 4.f;

    public Bomb(float x, float y) {
        super(resIds[0], ANIM_FPS);
        isExplosion = false;
        setPosition(x, y, BOMB_WIDTH, BOMB_HEIGHT);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    @Override
    public void draw(Canvas canvas) {
        if (isExplosion && frameIndex == ANIM_END_FPS) return;
        super.draw(canvas);
    }

    public void ExplosionEffect() {
        isExplosion = true;
        ChangeAnimSprite(resIds[1], ANIM_FPS);
    }
}