package com.android.animation;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap bmp;
    private GameThread gameThread;
    private int x = 20;
    private int y = 20;
    private ArrayList<GraphicObject> graphics = new ArrayList<>();

    public Panel(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);

        gameThread = new GameThread(getHolder(), this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        canvas.drawColor(Color.BLACK);
        Coordinates coordinates;
        for (GraphicObject graphic : graphics) {
            coordinates = graphic.getCoordinates();
            x = coordinates.getX();
            y = coordinates.getY();
            canvas.drawBitmap(bmp, x, y, null);

        }
    }

    public void updateMovement() {
        Coordinates coordinates;
        Movement movement;
        for (GraphicObject graphic : graphics) {
            coordinates = graphic.getCoordinates();
            movement = graphic.getMovement();
            x = (movement.getxDirection() == Movement.X_DIRECTION_RIGHT) ? (coordinates.getX() + movement.getxSpeed()) : (coordinates.getX() - movement.getxSpeed());
            if (x < 0) {
                movement.toggleXDirection();
                coordinates.setX(-x);
            } else if (x + graphic.getGraphic().getWidth() > getWidth()) {
                movement.toggleXDirection();
                coordinates.setX(x + getWidth() - (x + graphic.getGraphic().getWidth()));
            } else {
                coordinates.setX(x);
            }
            y = (movement.getyDirection() == Movement.Y_DIRECTION_DOWN) ? (coordinates.getY() + movement.getxSpeed()) : (coordinates.getY() - movement.getySpeed());
            if (y < 0) {
                movement.toggleYDirection();
                coordinates.setY(-y);
            } else if (y + graphic.getGraphic().getHeight() > getHeight()) {
                movement.toggleYDirection();
                coordinates.setY(y + getHeight() - (y + graphic.getGraphic().getHeight()));
            } else {
                coordinates.setY(y);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (gameThread.getSurfaceHolder()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x = (int) event.getX();
                y = (int) event.getY();
                Log.e("xy", x + "," + y);
                GraphicObject graphicObject = new GraphicObject(bmp);

                int bmpW = graphicObject.getGraphic().getWidth();
                int bmpH = graphicObject.getGraphic().getHeight();
                for (int i = 0; i < graphics.size(); i++) {
                    int nowX = graphics.get(i).getCoordinates().getX();
                    int nowY = graphics.get(i).getCoordinates().getY();
                    Log.e("xy1", nowX + "," + nowY);

                    if (Math.abs(x - nowX) <=(x - bmpW / 2) || Math.abs(y - nowY) <= (y - bmpH / 2)) {
                        graphics.remove(i);
                        return true;
                    }
                }

                graphicObject.getCoordinates().setX(x - bmpW / 2);
                graphicObject.getCoordinates().setY(y - bmpH / 2);
                graphics.add(graphicObject);
            }
            return true;
        }


    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (Exception e) {

            }
        }
    }
}
