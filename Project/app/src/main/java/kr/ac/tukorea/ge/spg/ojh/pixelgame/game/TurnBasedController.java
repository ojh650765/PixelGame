package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.game.GameOverScene;
import kr.ac.tukorea.ge.spg.ojh.framework.util.CollisionHelper;

public class TurnBasedController implements IGameObject {
    private static final String TAG = TurnBasedController.class.getSimpleName();
    private final MainScene scene;
    private final WarriorHead warriorHead;
    private final Warrior warrior;
    private TileGenerator tileGenerator;
    private float fResetdelayTime;
    private float fAccumulateResetTime;
    private boolean resetTurn = false;

    public TurnBasedController(MainScene scene, TileGenerator tileGenerator, WarriorHead warriorHead, Warrior warrior) {
        this.scene = scene;
        this.tileGenerator = tileGenerator;
        this.warriorHead =warriorHead;
        this.warrior = warrior;

        this.fResetdelayTime= 1.3f;
        this.fAccumulateResetTime= 0;
        this.resetTurn =false;
    }
    @Override
    public void update(float elapsedSeconds) {
        if(scene ==null) return;

        if(resetTurn){
            fAccumulateResetTime+=elapsedSeconds;
            if( fAccumulateResetTime >= fResetdelayTime){
                fAccumulateResetTime = 0;
                resetTurn = false;
                ResetWarriorAndTiles();
            }
        }
        else if(GameStateManager.getInstance().isTurnActive()) {
            warrior.ChangeState(Warrior.State.attack);
            ArrayList<IGameObject> Slimes = scene.objectsAt(MainScene.Layer.enemy);
            for (int s = Slimes.size() - 1; s >= 0; s--) {
                Slime slime = (Slime) Slimes.get(s);
                slime.startLeftMove(slime.getMoveDistance());
                if(slime.GetAttackStats()){
                    warriorHead.GetDamage(slime.GetPower());
                }
            }
            resetTurn = true;
        }
        if(warriorHead.GetHP()<= 0){
            new GameOverScene().push();
        }
    }

    public void ResetWarriorAndTiles() {
        RemoveAllObjects();
        this.tileGenerator.ResetGenerateObjects(warriorHead);
        warriorHead.ResetMove();
        warrior.SetAvailableChangeState();
        this.resetTurn = false;
        GameStateManager.getInstance().setTurnActive(false);
    }

    private void RemoveAllObjects(){

        ArrayList<IGameObject> bombs = scene.objectsAt(MainScene.Layer.bomb);
        for(int b = bombs.size() - 1; b>=0; b--){
            Bomb bomb = (Bomb) bombs.get(b);
            scene.remove(MainScene.Layer.bomb,bomb);
        }
        ArrayList<IGameObject> items = scene.objectsAt(MainScene.Layer.item);
        for (int i = items.size() - 1; i >= 0; i--) {
            IGameObject gobj = items.get(i);
            scene.remove(MainScene.Layer.item,gobj);
        }
        ArrayList<IGameObject> obstacles = scene.objectsAt(MainScene.Layer.obstacle);
        for(int o = obstacles.size() - 1; o>=0; o--) {
            Obstacle obs = (Obstacle) obstacles.get(o);
            scene.remove(MainScene.Layer.obstacle, obs);
        }

    }
    @Override
    public void draw(Canvas canvas) {

    }
    public void ExplosionEffect() {}

}