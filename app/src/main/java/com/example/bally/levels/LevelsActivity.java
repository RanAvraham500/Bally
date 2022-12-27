package com.example.bally.levels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.bally.R;
import com.example.bally.game.GameActivity;

public class LevelsActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout llInsideHSV;
    Button[] levelsBtn;
    final int LEVEL_COUNT = 14 //the number of levels available

            , CURR_LEVEL = 1; //the number of the user's current level -
                              // later it will be received from firebase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        llInsideHSV = findViewById(R.id.llInsideHSV);
        levelsBtn = new Button[LEVEL_COUNT];
        int j = CURR_LEVEL;
        for (int i = 0; i < LEVEL_COUNT; i++) {
            levelsBtn[i] = new Button(this);
            LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(350, 350);
            if (i != LEVEL_COUNT-1) {
                lp.setMarginEnd(30);
            }
            levelsBtn[i].setLayoutParams(lp);
            levelsBtn[i].setTextSize(20);
            levelsBtn[i].setText("שלב " + (i+1));
            levelsBtn[i].setTextColor(Color.WHITE);
            levelsBtn[i].setBackgroundResource(R.color.btn_home);
            levelsBtn[i].setOnClickListener(this);
            llInsideHSV.addView(levelsBtn[i]);
            if (j <= 0) {
                Resources r = getResources();
                Drawable[] layers = new Drawable[3];
                layers[0] = levelsBtn[i].getBackground();
                layers[1] = r.getDrawable(R.drawable.level_lock);

                LayerDrawable layerDrawable = new LayerDrawable(layers);

                levelsBtn[i].setBackground(layerDrawable);
                levelsBtn[i].setEnabled(false);
            }
            j--;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}