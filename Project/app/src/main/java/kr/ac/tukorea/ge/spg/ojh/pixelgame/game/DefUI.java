package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class DefUI extends Sprite {
    private final float UI_ATK_WIDTH = .7f;
    private final float UI_ATK_HEIGHT = UI_ATK_WIDTH;

    public DefUI() {
        super(R.mipmap.shield);
        setPosition(Metrics.width/8.5f, Metrics.height/1.76f, UI_ATK_WIDTH, UI_ATK_HEIGHT);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

    }
}