package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.app.AlertDialog;
import android.content.DialogInterface;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Button;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class GameOverScene extends Scene {
    public enum Layer {
        bg, title, touch, COUNT
    }
    private final Sprite title;
    private float angle;
    public GameOverScene() {
        initLayers(Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        float cx = w / 2, cy = h / 2;
        add(Layer.bg, new Sprite(R.mipmap.trans_50b, cx, cy, w, h));
        title = new Sprite(R.mipmap.game_over, cx, cy, 3.69f, 3.69f);
        add(Layer.title, title);

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
        return true;
    }
}
