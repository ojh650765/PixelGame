package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.view.MotionEvent;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.HorizonBackground;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Button;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class GameResultScene extends Scene {
    private static final String TAG = GameResultScene.class.getSimpleName();


    //Score score; // package private

    public enum Layer {
        bg,touch,COUNT
    }
    public GameResultScene()  {
        initLayers(Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        add(Layer.bg, new Sprite(R.mipmap.game_result_bg, w/2, h/2, w, h));

        add(Layer.bg, new Sprite(R.mipmap.pixelgamelogo_2, w/2, h/2, 3.69f, 1.36f));
        add(Layer.touch, new Button(R.mipmap.game_restart_bt, 14.5f, 1.0f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                pop();
                return false;
            }
        }));
    }


    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}
