package com.example.bally.game;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.bally.R;
import com.example.bally.levels.LevelsActivity;

public class CustomDialog extends Dialog implements View.OnClickListener, DialogInterface.OnDismissListener {
    Button btnBack;
    Ball ball;

    public CustomDialog(@NonNull Context context, Ball ball) {
        super(context);
        setCancelable(true);
        setOnDismissListener(this);
        this.ball = ball;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.exit_game_dialog);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        ball.stopBall();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), LevelsActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        ball.startBall();
    }
}
