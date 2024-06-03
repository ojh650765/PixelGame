package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import androidx.annotation.NonNull;

import java.util.Random;


import kr.ac.tukorea.ge.spg.ojh.framework.objects.Score;
import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class AtkCount extends Score {
    public AtkCount() {
        super(R.mipmap.number_24x32);
        setPosition(Metrics.width/6.f, Metrics.height/2.25f);
    }

    @Override
    public void update(float elapsedSeconds) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

}
