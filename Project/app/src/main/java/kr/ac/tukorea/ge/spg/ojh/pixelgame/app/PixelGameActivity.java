package kr.ac.tukorea.ge.spg.ojh.pixelgame.app;

import android.os.Bundle;

import kr.ac.tukorea.ge.spg.ojh.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.game.MainScene;

public class PixelGameActivity extends GameActivity {

    protected void onCreate(Bundle savedInstanceState) {
        Metrics.setGameSize(16, 9);
        super.onCreate(savedInstanceState);
        new MainScene().push();
    }
}
