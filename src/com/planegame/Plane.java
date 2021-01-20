package com.planegame;

public class Plane {

    // there is no speed information in class because I use fixed speed for planes that is 2

    private int x;   // loaction X
    private int y;   // location Y

    public Plane(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
