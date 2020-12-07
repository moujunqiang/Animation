package com.android.animation;


public class Movement {
    public static final int X_DIRECTION_RIGHT = 1;
    public static final int X_DIRECTION_LEFT = 1;
    public static final int Y_DIRECTION_DOWN = 1;
    public static final int Y_DIRECTION_UP = 1;
    private int ySpeed = 2;
    private int xSpeed = 2;
    private int xDirection = X_DIRECTION_LEFT;
    private int yDirection = Y_DIRECTION_DOWN;

    public void setXYSpeed(int x, int y) {
        this.xSpeed = x;
        this.ySpeed = y;
    }

    public void setDirections(int xDirection, int yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }
    public void toggleYDirection(){
        if (yDirection==Y_DIRECTION_UP){
            yDirection=Y_DIRECTION_DOWN;
        }else {
            yDirection=Y_DIRECTION_UP;
        }
    }
    public void toggleXDirection(){
        if (xDirection==X_DIRECTION_LEFT){
            xDirection=X_DIRECTION_RIGHT;
        }else {
            xDirection=X_DIRECTION_LEFT;
        }
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }
}
