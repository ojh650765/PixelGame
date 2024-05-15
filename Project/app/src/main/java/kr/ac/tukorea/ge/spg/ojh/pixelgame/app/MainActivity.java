package kr.ac.tukorea.ge.spg.ojh.pixelgame.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.databinding.ActivityMainBinding;
import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.game.MainScene;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int stage;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setStage(1);
    }
    private void runGameActivity() {
        Intent intent = new Intent(this, PixelGameActivity.class);
        intent.putExtra(MainScene.KEY_STAGE, stage);
        startActivity(intent);
    }

    private void setStage(int stage) {
        this.stage = stage;
        String text = getString(R.string.title_stage_fmt, stage);
        binding.stageTextView.setText(text);
        binding.prevButton.setEnabled(stage > 1);
        binding.nextButton.setEnabled(stage < 3);
    }

    public void onBtnStartGame(View view) {
        runGameActivity();
    }

    public void onBtnPrevious(View view) {
        setStage(stage - 1);
    }
    public void onBtnNext(View view) {
        setStage(stage + 1);
    }
}