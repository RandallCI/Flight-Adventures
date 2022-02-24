package com.example.flightexplorer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.flightexplorer.R;

public class GameBackground {
    int x = 0, y = 0;
    Bitmap background;

    GameBackground(int screenX, int screenY, Resources resources) {
        background = BitmapFactory.decodeResource(resources, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }

}
