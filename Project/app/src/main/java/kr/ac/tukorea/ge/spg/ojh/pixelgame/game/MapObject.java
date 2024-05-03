package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Random;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class MapObject extends Sprite implements IBoxCollidable, IRecyclable {
    protected MainScene.Layer layer;
    protected MapObject(MainScene.Layer layer) {
        super(0);
        this.layer = layer;
    }

    private static final String TAG = MapObject.class.getSimpleName();


    @Override
    public void update(float elapsedSeconds) {
    }

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + System.identityHashCode(this) + "(" + width + "x" + height + ")";
    }

    public void removeFromScene() {
        Scene scene = Scene.top();
        if (scene == null) {
            Log.e(TAG, "Scene stack is empty in removeFromScene() " + this.getClass().getSimpleName());
            return;
        }
        scene.remove(layer, this);
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    @Override
    public void onRecycle() {

    }
}
