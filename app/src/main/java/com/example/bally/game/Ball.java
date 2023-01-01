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


    private class CalculatePosition extends TimerTask {
        final float dt = 1;
        final float COEFFICIENT_OF_REFLECTION = 0.5f;
        final float COEFFICIENT_OF_AIR = 0.1f;
        @Override
        public void run() {
            //להתחשב ביחידות מידה
            //אם השינוי בתאוצה קטן מדי התאוצה לא משתנה

            //לקחת רק את הספרה הראשונה אחרי הנקודה לתאוצה
            //ax = (float)((int)(ax*10))/10;
            //ay = (float)((int)(ay*10))/10;
            if (ax > 0) {
                ax -= COEFFICIENT_OF_AIR;
            } else {
                ax += COEFFICIENT_OF_AIR;
            }
            if (ay > 0) {
                ay -= COEFFICIENT_OF_AIR;
            } else {
                ay += COEFFICIENT_OF_AIR;
            }
            ax /= 10;
            ay /= 10;

            if (gameOn) {
                vx += dt*ax;
                vy += dt*ay;
                x += vx + 0.5*ax*Math.pow(dt, 2); //אורך
                y += vy + 0.5*ay*Math.pow(dt, 2); //רוחב

                if (y > frameHeight - 290) {
                    y = frameHeight - 280;
                    vy = -COEFFICIENT_OF_REFLECTION * vy;
                } else if (y < 0) {
                    y = 0;
                    vy = -COEFFICIENT_OF_REFLECTION * vy;
                }


                // Ball is out of bounds in x dimension
                if (x > frameWidth - 60) {
                    x = frameWidth - 59;
                    vx = -COEFFICIENT_OF_REFLECTION * vx;
                } else if (x < 0) {
                    x = 0;
                    vx = -COEFFICIENT_OF_REFLECTION * vx;
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
