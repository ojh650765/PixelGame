package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.util.Log;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Button;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.Sound;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class EventStageScene extends Scene {
    private static String TAG = EventStageScene.class.getSimpleName();
    public enum Layer {
        bg, touch, COUNT
    }
    private static  int[] resId ={
            R.mipmap.event_background_1,
            R.mipmap.event_background_2,
            R.mipmap.event_background_3,
    };
    private static  int[] btresId ={
            R.mipmap.ev1_1,
            R.mipmap.ev1_2,
            R.mipmap.ev2_1,
            R.mipmap.ev2_2,
            R.mipmap.ev3_1,
            R.mipmap.ev3_2
    };
    static  float SelectBtWidth = 5.f;
    static  float SelectBtHeight = SelectBtWidth * 397/2960;
    public EventStageScene() {
        initLayers(Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        float cx = w / 2, cy = h / 2;


        int index = GameStateManager.getInstance().GetEventStage();

        add(Layer.bg, new Sprite(resId[index], cx, cy, w, h));

        add(Layer.touch, new Button(btresId[2 * index], cx + 2.5f, cy + 2.3f, SelectBtWidth, SelectBtHeight, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (index == 0) {
                    //ev1_1
                    GameStateManager.getInstance().UpdatePlayerHP(GameStateManager.getInstance().GetPlayerHP() - 10);
                    GameStateManager.getInstance().UpdatePowerInfo(GameStateManager.getInstance().GetPowerInfo() + 4);
                }
                else if (index == 1) {
                    //ev2_1
                    GameStateManager.getInstance().UpdatePlayerHP(GameStateManager.getInstance().GetPlayerHP() - 5);
                    GameStateManager.getInstance().UpdateDefInfo(GameStateManager.getInstance().GetDefInfo() + 3);
                } else if (index == 2) {
                    //ev3_1
                    GameStateManager.getInstance().UpdatePlayerHP(GameStateManager.getInstance().GetPlayerHP() - 10);
                    GameStateManager.getInstance().UpdatePowerInfo(GameStateManager.getInstance().GetPowerInfo() + 5);
                    GameStateManager.getInstance().UpdateDefInfo(GameStateManager.getInstance().GetDefInfo() + 2);
                }
                finishActivity();
                return false;
            }
        }));
        add(Layer.touch, new Button(btresId[2* index + 1], cx + 2.5f, cy + 3.5f, SelectBtWidth, SelectBtHeight, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (index == 0) {
                    //ev1_2
                    GameStateManager.getInstance().UpdatePlayerHP(GameStateManager.getInstance().GetPlayerHP() - 20);
                    GameStateManager.getInstance().UpdatePowerInfo(GameStateManager.getInstance().GetPowerInfo() + 6);
                }
                else if (index == 1) {
                    //ev2_2
                    GameStateManager.getInstance().UpdatePlayerHP(GameStateManager.getInstance().GetPlayerHP() - 15);
                    GameStateManager.getInstance().UpdateDefInfo(GameStateManager.getInstance().GetDefInfo() + 5);
                } else if (index == 2) {
                    //ev3_2
                    GameStateManager.getInstance().UpdatePlayerHP(GameStateManager.getInstance().GetPlayerHP() - 10);
                    GameStateManager.getInstance().UpdatePowerInfo(GameStateManager.getInstance().GetPowerInfo() + 3);
                    GameStateManager.getInstance().UpdateDefInfo(GameStateManager.getInstance().GetDefInfo() + 1);
                }
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
        Sound.playMusic(R.raw.event_stage);
    }
    @Override
    protected void onEnd() {
        Sound.stopMusic();
    }
}
