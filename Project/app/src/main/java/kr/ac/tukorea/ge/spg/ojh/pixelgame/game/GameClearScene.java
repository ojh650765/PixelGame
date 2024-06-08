package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Button;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.Sound;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class GameClearScene extends Scene {
    public enum Layer {
        bg, title, touch, COUNT
    }

    private float angle;
    public GameClearScene() {
        initLayers(Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        float cx = w / 2, cy = h / 2;
        add(Layer.bg, new Sprite(R.mipmap.bg_clear, cx, cy, w, h));

        add(Layer.touch, new Button(R.mipmap.btn_exit_n, cx, cy + 2.5f, 2.667f, 1f, new Button.Callback() {
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
        return false;
    }

    @Override
    protected void onStart() {
        Sound.stopMusic();
        Sound.playMusic(R.raw.game_clear);
    }
    @Override
    protected void onEnd() {
        Sound.stopMusic();
    }
}
