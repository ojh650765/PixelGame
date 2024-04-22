package kr.ac.tukorea.ge.spg.ojh.pixelgame.app;

import android.os.Bundle;

import kr.ac.tukorea.ge.spg.ojh.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.game.MainScene;

public class PixelGameActivity extends GameActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainScene().push();
    }
}
