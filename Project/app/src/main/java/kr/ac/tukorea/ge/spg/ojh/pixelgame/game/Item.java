package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spg.ojh.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;

public class Item  extends MapObject{
    public Item() {
        super(MainScene.Layer.item);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

    }
    public static Item get(int index, float left, float top) {
        Item item = (Item) RecycleBin.get(Item.class);
        if (item == null) {
            item = new Item();
        }
        item.init(index, left, top);
        return item;
    }
    private void init(int index, float left, float top) {
        width = height = 0.6f;
        dstRect.set(left, top, left + width, top + height);
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}