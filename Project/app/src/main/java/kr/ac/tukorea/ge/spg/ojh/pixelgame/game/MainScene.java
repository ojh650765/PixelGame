package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Button;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.HorizonBackground;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final WarriorHead warriorHead;
    private final Warrior warrior;
    public static String KEY_STAGE = "stage";
    private int stage = 2;
    //Score score; // package private
    public int getStage() {
        return stage;
    }
    public enum Layer {
        bg,board,item, up_player,under_player, enemy, slash, obstacle,bomb,touch, controller,ui,COUNT
    }
    public MainScene() {
        Intent intent = GameActivity.activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            stage = extras.getInt(KEY_STAGE);
        }
        initLayers(Layer.COUNT);

        this.warriorHead = new WarriorHead();
        this.warrior = new Warrior();
        add(Layer.ui,new HealthBar(Layer.ui));
        add(Layer.ui,new FillHealth(Layer.ui, warriorHead));
        add(Layer.board,new Board(Layer.board));
        TileGenerator tileGenerator =new TileGenerator(this);

        add(Layer.controller, new EnemyGenerator(this));
        add(Layer.controller, new CollisionChecker(this, this.warriorHead));

        add(Layer.controller, tileGenerator);
        add(Layer.controller, GameStateManager.getInstance());

        tileGenerator.ResetGenerateObjects(this.warriorHead);
        add(Layer.controller,new TurnBasedController(this,tileGenerator,warriorHead,this.warrior));
        add(Layer.bg, new HorizonBackground(stage));


        add(Layer.under_player, warriorHead);
        add(Layer.up_player, warrior);

        add(Layer.touch, new Button(R.mipmap.btn_reset_n, 1.5f, 8.0f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                //Log.d(TAG, "Button: Slide " + action);
                GameStateManager.getInstance().setTurnActive(false);
                return true;
            }
        }));
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
