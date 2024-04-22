package kr.ac.tukorea.ge.spg.ojh.framework.res;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.HashMap;

import kr.ac.tukorea.ge.spg.ojh.framework.view.GameView;

public class BitmapPool {
    private static final String TAG = BitmapPool.class.getSimpleName();
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();
    private static BitmapFactory.Options opts;

    public static Bitmap get(int mipmapResId) {
        Bitmap bitmap = bitmaps.get(mipmapResId);
        if (bitmap == null) {
            if (opts == null) {
                opts = new BitmapFactory.Options();
                opts.inScaled = false;
            }
            bitmap = BitmapFactory.decodeResource(GameView.res, mipmapResId, opts);
            Log.d(TAG, "Bitmap ID " + mipmapResId + " : " + bitmap.getWidth() + "x" + bitmap.getHeight());
            bitmaps.put(mipmapResId, bitmap);
            if (bitmap != null) {
                // 로드된 비트맵이 유효한 경우 로그를 찍고 맵에 추가
                Log.d(TAG, "Bitmap ID " + mipmapResId + " : " + bitmap.getWidth() + "x" + bitmap.getHeight());
                bitmaps.put(mipmapResId, bitmap);
            } else {
                // 비트맵 로드 실패 시 로그 기록
                Log.e(TAG, "Failed to load bitmap with ID " + mipmapResId);
            }
        }
        return bitmap;
    }
}
