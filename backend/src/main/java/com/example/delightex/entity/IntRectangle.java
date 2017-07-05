package com.example.delightex.entity;

import java.util.Random;

/**
 * Created by mikhail on 05.07.17.
 */
public class IntRectangle {

    private int leftPoint;
    private int rightPoint;
    private int height;



    public IntRectangle(int maxRangeX, int maxRangeY) {
        Random random = new Random();
        this.leftPoint = random.nextInt(maxRangeX) + 1;
        this.rightPoint = this.leftPoint + random.nextInt(maxRangeX - this.leftPoint);
        this.height = random.nextInt(maxRangeY) + 1;
    }

    public IntRectangle(int leftPoint, int rightPoint, int height) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.height = height;

    }

    public void setLeftPoint(int leftPoint) {
        this.leftPoint = leftPoint;
    }

    public void setRightPoint(int rightPoint) {
        this.rightPoint = rightPoint;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeftPoint() {
        return leftPoint;
    }

    public int getRightPoint() {
        return rightPoint;
    }

    public int getHeight() {
        return height;
    }
}
