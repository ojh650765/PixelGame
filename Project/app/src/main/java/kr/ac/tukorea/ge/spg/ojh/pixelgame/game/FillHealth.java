package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import androidx.annotation.NonNull;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class FillHealth extends Sprite {

    protected MainScene.Layer layer;
    private static final float HEALTH_WIDTH = 4.f;
    private static final float HEALTH_HEIGHT = HEALTH_WIDTH*120/853;
    private WarriorHead warriorHead;
    public boolean DecreaseWidth(float hpRatio) {
        float newWidth = HEALTH_WIDTH * hpRatio;
        dstRect.right = dstRect.left + newWidth;
        if(newWidth==0) return false;
        else return true;
    }
    protected FillHealth(MainScene.Layer layer, WarriorHead warriorHead) {
        super(R.mipmap.full_bar);
        this.layer = layer;
        this.warriorHead = warriorHead;
        setPosition(Metrics.width/7.4f, Metrics.height/2.5f ,HEALTH_WIDTH, HEALTH_HEIGHT);
        DecreaseWidth(0.5f);

    }
    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + System.identityHashCode(this) + "(" + width + "x" + height + ")";
    }


    @Override
    public void update(float elapsedSeconds) {
        if(warriorHead==null) return;
        DecreaseWidth( warriorHead.GetHP());
    }

}
