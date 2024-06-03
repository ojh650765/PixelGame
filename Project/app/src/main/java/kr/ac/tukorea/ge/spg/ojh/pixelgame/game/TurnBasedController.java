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
    private float fClearScenedelayTime;
    private float fAccumulateResetTime;
    private float fAccumulateClearSceneTime;
    private boolean resetTurn = false;
    private float fSlimeDelayTime;
    private float fAccumulateSlimeTime;

    public TurnBasedController(MainScene scene, TileGenerator tileGenerator, WarriorHead warriorHead, Warrior warrior) {
        this.scene = scene;
        this.tileGenerator = tileGenerator;
        this.warriorHead =warriorHead;
        this.warrior = warrior;

        this.fResetdelayTime= 1.3f;
        this.fClearScenedelayTime= 1.f;
        this.fAccumulateResetTime= 0;
        this.fAccumulateClearSceneTime= 0;
        this.fSlimeDelayTime= 0.8f;
        this.fAccumulateSlimeTime= 0.f;
        this.resetTurn =false;
    }
    @Override
    public void update(float elapsedSeconds) {
        if(scene ==null) return;

        if(resetTurn){
            fAccumulateResetTime+=elapsedSeconds;
            if(fAccumulateResetTime >= fResetdelayTime){
                fAccumulateResetTime = 0;
                resetTurn = false;
                warriorHead.ResetDef();
                scene.SetDefScore(0);
                ResetWarriorAndTiles();
                this.tileGenerator.ResetGenerateObjects(warriorHead);
                GameStateManager.getInstance().setTurnActive(false);
            }
        }
        else if(GameStateManager.getInstance().isTurnActive()) {
            warrior.ChangeState(Warrior.State.attack);
            fAccumulateSlimeTime+=elapsedSeconds;
            if(fAccumulateSlimeTime >= fSlimeDelayTime) {
                fAccumulateSlimeTime = 0;
                ArrayList<IGameObject> Slimes = scene.objectsAt(MainScene.Layer.enemy);
                for (int s = Slimes.size() - 1; s >= 0; s--) {
                    Slime slime = (Slime) Slimes.get(s);
                    slime.startLeftMove(slime.getMoveDistance());

                    if (slime.GetAttackStats()) {
                        warriorHead.GetDamage(slime.GetPower());
                        warrior.ChangeHittedState();
                        warriorHead.ResetDef();

                    }
                }
                resetTurn = true;
            }
        }
       if (CheckMonsterState(elapsedSeconds) == false){
            if(warriorHead.GetHP()<= 0){
                new GameOverScene().push();
            }
       }
    }
    private  boolean CheckMonsterState(float elapsedSeconds) {
        int count = 0;
        ArrayList<IGameObject> Slimes = scene.objectsAt(MainScene.Layer.enemy);
        for (int s = Slimes.size() - 1; s >= 0; s--) {
            Slime slime = (Slime) Slimes.get(s);
            if (slime.dead) {
                count += 1;
            } else {
                count = 0;
                break;
            }
        }
        if (count >= Slimes.size()) {
            fAccumulateClearSceneTime+=elapsedSeconds;
            if (fAccumulateClearSceneTime >= fClearScenedelayTime) {
                fAccumulateClearSceneTime = 0;
                GameStateManager gameStateManager = GameStateManager.getInstance();
                float pw = gameStateManager.GetPowerInfo();
                float def = gameStateManager.GetDefInfo();

                gameStateManager.UpdatePowerInfo(pw);
                gameStateManager.UpdateDefInfo(def);

                float totalAtk = pw +10;
                float totalDef = def +10;
                if(totalAtk>=100) totalAtk =99;
                if(totalDef>=100) totalDef =99;

                gameStateManager.UpdatePowerInfo(totalAtk);
                gameStateManager.UpdateDefInfo(totalDef);

                new GameClearScene().push();
                return true;
            }
        }
        return false;
    }

    public void ResetWarriorAndTiles() {
        RemoveAllObjects();
        warriorHead.ResetMove();
        warrior.SetAvailableChangeState();
        if(!GameStateManager.getInstance().isTurnActive())
            GameStateManager.getInstance().setTurnActive(true);

        this.resetTurn = false;

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