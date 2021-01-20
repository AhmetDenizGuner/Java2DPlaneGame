package com.planegame;

public class Fire {

    private double x;               // location X
    private double y;               // location Y
    private double xIncrease;       // speed vector x axis
    private double yIncrease;       // speed vector y axis

    public  Fire(double x, double y, double xIncrease, double yIncrease){
        this.x = x;
        this.y = y;
        this.xIncrease = xIncrease;
        this.yIncrease = yIncrease;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getxIncrease() {
        return xIncrease;
    }

    public void setxIncrease(int xIncrease) {
        this.xIncrease = xIncrease;
    }

    public double getyIncrease() {
        return yIncrease;
    }

    public void setyIncrease(int yIncrease) {
        this.yIncrease = yIncrease;
    }
}
