package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;


import android.graphics.Canvas;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Score;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class DefCount extends Score {
    public DefCount() {
        super(R.mipmap.number_24x32);
        setPosition(Metrics.width/6.f, Metrics.height/1.88f);
    }

    @Override
    public void update(float elapsedSeconds) {
      
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

}
