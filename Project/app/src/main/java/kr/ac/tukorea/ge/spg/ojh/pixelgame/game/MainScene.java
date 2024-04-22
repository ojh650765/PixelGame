package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.HorizonBackground;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final WarriorHead warriorHead;
    //Score score; // package private

    public enum Layer {
        bg, enemy, bullet, player, controller, COUNT
    }
    public MainScene() {
        initLayers(Layer.COUNT);

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker(this));

        add(Layer.bg, new HorizonBackground(R.mipmap.bg_forest));
        //add(Layer.bg, new VertScrollBackground(R.mipmap.clouds, 0.4f));

        this.warriorHead = new WarriorHead();
        add(Layer.player, warriorHead);

        //this.score = new Score(R.mipmap.number_24x32, 8.5f, 0.5f, 0.6f);
        //score.setScore(0);
        //add(Layer.ui, score);
    }

    public void addScore(int amount) {
        //score.add(amount);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return warriorHead.onTouch(event);
    }
}
