package com.example.delightex.entity;


import java.util.Random;

/**
 * Created by mikhail on 05.07.17.
 */
public class RealRectangle {


    private double leftPoint;
    private double rightPoint;
    private double height;

    public RealRectangle(double maxRangeX, double maxRangeY) {
        Random random = new Random();
        this.leftPoint = maxRangeX * random.nextDouble();
        this.rightPoint = this.leftPoint + (maxRangeX - this.leftPoint) * random.nextDouble();
        this.height = random.nextDouble() * maxRangeY;
    }

    public double getRightPoint() {
        return rightPoint;
    }

    public double getHeight() {
        return height;
    }

    public double getLeftPoint() {

        return leftPoint;
    }
}
