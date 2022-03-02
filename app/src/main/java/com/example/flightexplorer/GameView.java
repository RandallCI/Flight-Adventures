package com.example.flightexplorer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private Boolean isPlaying;
    private Paint paint;
    private Fly fly;
    public static float screenRatioX, screeRatioY;
    private int screenX, screenY;
    private GameBackground gameBackgroundOne, gameBackgroundTwo;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 800f / screenX;
        screeRatioY = 1050 / screenY;

        //Initialise flight
        fly = new Fly(screenY - 100, getResources());

        gameBackgroundOne = new GameBackground(screenX, screenY, getResources());
        gameBackgroundTwo = new GameBackground(screenX, screenY, getResources());
        gameBackgroundOne.x = screenX;

        paint = new Paint();
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

    }

    private void update(){
        gameBackgroundOne.x -= 10 * screenRatioX;
        gameBackgroundTwo.x -= 10 * screenRatioX;

        if (gameBackgroundOne.x + gameBackgroundOne.background.getWidth() < 0) {
            gameBackgroundOne.x = screenX;
        }

        if (gameBackgroundTwo.x + gameBackgroundTwo.background.getWidth() < 0) {
            gameBackgroundTwo.x = screenX;
        }

            if (fly.movingUp) {
                fly.y -= 10 * screeRatioY;
            } else {
                fly.y += 10 * screeRatioY;
            }

         if (fly.y < 1) {
             fly.y = 1;
         }
        if (fly.y > screenY - fly.height - 70) {
            fly.y = screenY - fly.height - 70;
        }

    }
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(gameBackgroundOne.background, gameBackgroundOne.x, gameBackgroundOne.y, paint);
            canvas.drawBitmap(gameBackgroundTwo.background, gameBackgroundTwo.x, gameBackgroundTwo.y, paint);

            canvas.drawBitmap(fly.getFlight(), fly.x, fly.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }

    }
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < (float) screenX / 2) {
                    fly.movingUp = true;
                }
                  break;
            case MotionEvent.ACTION_UP:
                fly.movingUp = false;
                break;
        }

        return true;
    }
}
