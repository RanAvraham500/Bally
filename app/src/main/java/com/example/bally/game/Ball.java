package com.example.bally.game;

import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    private ImageView imageView;
    private int frameWidth;
    private int frameHeight;
    private int radius;
    private float x, y;
    private float vx = 0, vy = 0;
    private float ax = 0, ay = 0;
    private boolean gameOn;

    private Timer fuseTimer = new Timer();
    //delay between each calculation
    public static final int TIME_CONSTANT = 20;

    public Ball(ImageView imageView, int frameWidth, int frameHeight, int radius) {
        this.imageView = imageView;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.radius = radius;
        x = imageView.getX(); y = imageView.getY();
        gameOn = true;


        fuseTimer.scheduleAtFixedRate(new CalculatePosition(),
                1000, TIME_CONSTANT);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setA(float ax, float ay) {
        this.ax = ax;
        this.ay = ay;
    }

    public void startBall() {
        gameOn = true;
    }
    public void stopBall() {
        gameOn = false;
    }

    // What proportion of the velocity is retained on a bounce?  if 1.0, no energy
    // is lost, and the ball will bounce infinitely.
    private final float COEFFICIENT_OF_RESTITUTION = 0.8f;
    private class CalculatePosition extends TimerTask {
        @Override
        public void run() {
            //להתחשב ביחידות מידה
            //אם השינוי בתאוצה קטן מדי התאוצה לא משתנה
            if (gameOn) {
                float dt = TIME_CONSTANT/5f;

                x += vx*dt + 0.5*ax*Math.pow(dt, 2); //אורך
                y += vy*dt + 0.5*ay*Math.pow(dt, 2); //רוחב

                if (y > frameHeight-280) {
                    y = frameHeight - 320;
                    vy = -COEFFICIENT_OF_RESTITUTION * vy;
                } else if (y < 0) {
                    y = 0;
                    vy = -COEFFICIENT_OF_RESTITUTION * vy;
                }


                // Ball is out of bounds in x dimension
                if (x > frameWidth-55) {
                    x = frameWidth - 60;
                    vx = -COEFFICIENT_OF_RESTITUTION * vx;
                } else if (x < 0) {
                    x = 0;
                    vx = -COEFFICIENT_OF_RESTITUTION * vx;
                }

                // ball is rolling along the bottom
                /*if (ball.getY() == maxY) {
                    ball.setxVelocity(COEFFICIENT_OF_FRICTION * ball.getxVelocity());
                }*/
                imageView.setX(x);
                imageView.setY(y);
            }
        }
    }

}
