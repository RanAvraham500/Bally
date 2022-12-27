package com.example.bally.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bally.R;

public class GameActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {
    final boolean DEBUG = true;
    ConstraintLayout constraint;
    TextView tvTest;
    ImageView ivBall;
    Ball ball;
    DisplayMetrics displayMetrics;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        constraint = findViewById(R.id.constraint);
        constraint.setOnClickListener(this);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ivBall = findViewById(R.id.ivBall);
        ball = new Ball(ivBall, displayMetrics.widthPixels, displayMetrics.heightPixels, 25);

        //Gets the system sensors
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) == null) {
            Toast.makeText(this, "אין את החומרה המתאימה", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (DEBUG) {
            tvTest = findViewById(R.id.tvTest);
            tvTest.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        CustomDialog customDialog = new CustomDialog(this, ball);
        customDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = {
                event.values[0],
                event.values[1],
                event.values[2],
        };
        ball.setA(values[1], values[0]);


        if (DEBUG) {
            tvTest.setText("x: " + values[0] +
                    "\ny: " + values[1] +
                    "\nz: " + values[2] +
                    "\ndt: " + event.timestamp +
                    "\nwidth: " + displayMetrics.widthPixels +
                    "\nheight: " + displayMetrics.heightPixels +
                    "\nBall x: " + ball.getX() +
                    "\nBall y: " + ball.getY());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}