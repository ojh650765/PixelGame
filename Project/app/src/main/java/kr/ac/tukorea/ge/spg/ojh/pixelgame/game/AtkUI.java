package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class AtkUI extends Sprite {
    private final float UI_ATK_WIDTH = .7f;
    private final float UI_ATK_HEIGHT = UI_ATK_WIDTH;

    public AtkUI() {
        super(R.mipmap.sword);
        setPosition(Metrics.width/8.5f, Metrics.height/2.1f, UI_ATK_WIDTH, UI_ATK_HEIGHT);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

    }
}