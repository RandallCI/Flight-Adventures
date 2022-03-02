package com.example.flightexplorer;

import static com.example.flightexplorer.GameView.screeRatioY;
import static com.example.flightexplorer.GameView.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Fly {

    public boolean movingUp = false;
    int x, y, width, height, flightCount;

    Bitmap flyingBoy;

    Fly(int screenY, Resources resources) {
        flyingBoy = BitmapFactory.decodeResource(resources, R.drawable.flying_boy);

        width = flyingBoy.getWidth();
        height = flyingBoy.getHeight();

        width /= 25;
        height /= 35;

        width *= (int) screenRatioX;
        height *= (int) screeRatioY;

        flyingBoy = Bitmap.createScaledBitmap(flyingBoy, width, height, false);

        y = screenY / 2;

        x = (int) (64 * screenRatioX);
    }

    Bitmap getFlight() {
        if (flightCount == 0) {
            flightCount++;
            return flyingBoy;
        }
        flightCount++;
        return flyingBoy;
    }
}
