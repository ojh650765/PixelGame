package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.HorizonBackground;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final WarriorHead warriorHead;
    private final Warrior warrior;

    //Score score; // package private

    public enum Layer {
        bg, board,item, up_player,under_player, enemy, slash, obstacle,bomb, controller, COUNT
    }
    public MainScene() {
        initLayers(Layer.COUNT);
        this.warriorHead = new WarriorHead();
        this.warrior = new Warrior();
        MapLoader mapLoader =new MapLoader(this);

        add(Layer.controller, new EnemyGenerator(this));
        add(Layer.controller, new CollisionChecker(this, this.warriorHead));

        add(Layer.controller, mapLoader);
        add(Layer.controller, GameStateManager.getInstance());
        mapLoader.ResetGenerateObjects(this.warriorHead);
        add(Layer.controller,new TurnBasedController(this,mapLoader,warriorHead,this.warrior));
        add(Layer.bg, new HorizonBackground(R.mipmap.bg_forest));
        add(Layer.board,new Board(Layer.board));
        add(Layer.under_player, warriorHead);
        add(Layer.up_player, warrior);
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
