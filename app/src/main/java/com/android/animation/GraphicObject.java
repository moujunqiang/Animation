package com.android.animation;


import android.graphics.Bitmap;
import android.view.MotionEvent;

public class GraphicObject {
    private Bitmap bitmap;
    private Coordinates coordinates;
    private Movement movement;

    public GraphicObject(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.coordinates = new Coordinates(bitmap);
        this.movement = new Movement();


        movement.setXYSpeed((int) Math.floor((Math.random() * 10) + 1), (int) Math.floor((Math.random() * 10) + 1));

    }

    public Bitmap getGraphic() {
        return bitmap;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Movement getMovement() {
        return movement;
    }
}
