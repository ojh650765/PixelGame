package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Button;
import kr.ac.tukorea.ge.spg.ojh.framework.res.Sound;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.HorizonBackground;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final WarriorHead warriorHead;
    private final Warrior warrior;
    public static String KEY_STAGE = "stage";
    private static final Random random = new Random();
    private AtkCount atkScore;
    private DefCount defScore;
    private TurnBasedController turnBasedController;
    private int stage = 2;

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
        int eventNum = random.nextInt(3);
        GameStateManager.getInstance().InitState(eventNum);

        float pw = GameStateManager.getInstance().GetPowerInfo();
        float def = GameStateManager.getInstance().GetDefInfo();
        float hp = GameStateManager.getInstance().GetPlayerHP();
        this.warriorHead = new WarriorHead(pw, def, hp);

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
        turnBasedController = new TurnBasedController(this,tileGenerator,warriorHead,this.warrior);
        add(Layer.controller, turnBasedController);
        add(Layer.bg, new HorizonBackground(stage));


        add(Layer.under_player, warriorHead);
        add(Layer.up_player, warrior);

        this.atkScore = new AtkCount();
        this.defScore = new DefCount();
        SetAtkScore((int)this.warriorHead.GetEarnPower());
        SetDefScore((int)this.warriorHead.GetEarnDef());
        add(Layer.ui, atkScore);
        add(Layer.ui, defScore);
        add(Layer.ui, new AtkUI());
        add(Layer.ui, new DefUI());
        add(Layer.touch, new Button(R.mipmap.btn_reset_n, 14.f, 8.0f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                turnBasedController.ResetWarriorAndTiles();
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_pause_n, 15.6f, .4f,.6f, .6f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
              new GamePauseScene().push();
                return false;
            }
        }));
    }

    public void SetAtkScore(int amount) {
        int t = amount/5;
        atkScore.setScore(t);
    }
    public void SetDefScore(int amount) {
        int t = amount/5;
        defScore.setScore(t);
    }
    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }
    @Override
    protected int getTouchLayerIndex() {
        return MainScene.Layer.touch.ordinal();
    }
    @Override
    public boolean onTouch(MotionEvent event) {
        super.onTouch(event);
        return warriorHead.onTouch(event);
    }
    private int  resid[] = {R.raw.explosion,R.raw.stage_1_voldown,R.raw.stage_2,R.raw.stage_3,
            R.raw.slash,R.raw.puch,
            R.raw.hit_damage2,R.raw.stage_over,R.raw.game_clear,
            R.raw.item, R.raw.explosion_bomb};
    @Override
    protected void onStart() {
        Sound.initializeSoundPool( resid );
        if(stage == 1)
            Sound.playMusic(R.raw.stage_1_voldown);
        else if (stage == 2)
            Sound.playMusic(R.raw.stage_2);
        else if(stage == 3)
            Sound.playMusic(R.raw.stage_3);
    }
    @Override
    protected void onPause() {
        GameStateManager.getInstance().SetPauseState(true);
        Sound.pauseMusic();
        pauseAnimations();
    }
    @Override
    protected void onResume() {
        GameStateManager.getInstance().SetPauseState(false);
        resumeAnimations();
        Sound.resumeMusic();
    }
    private void pauseAnimations() {

    }
    private void resumeAnimations() {
        warriorHead.onResume();
    }

    @Override
    protected void onEnd() {
        Sound.stopMusic();
    }
}
