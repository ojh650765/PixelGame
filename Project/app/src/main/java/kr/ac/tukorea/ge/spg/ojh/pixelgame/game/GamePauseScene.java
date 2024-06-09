package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Button;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.Sound;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class GamePauseScene extends Scene {
    public enum Layer {
        bg, title, touch, COUNT
    }
    private final Sprite title;
    private float angle;
    public GamePauseScene() {
        initLayers(Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        float cx = w / 2, cy = h / 2;
        add(Layer.bg, new Sprite(R.mipmap.trans_50b, cx, cy, w, h));
        add(Layer.bg, new Sprite(R.mipmap.bg_pause, cx, cy, 12.00f, 6.75f));
        title = new Sprite(R.mipmap.pixelgamelogo, cx, cy, 3.69f, 1.36f);
        add(Layer.title, title);
        float offsetX =0.25f;
        add(Layer.touch, new Button(R.mipmap.btn_resume_n, cx -0.75f/2 -offsetX, cy + 1.8f, 0.75f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                pop();
                return false;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.btn_pause_exit_n, cx +0.75f/2 + offsetX, cy + 1.8f, 0.75f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                finishActivity();
                return false;
            }
        }));
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    protected void onStart() {

    }
    @Override
    protected void onEnd() {

    }
}
