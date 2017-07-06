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

    public RealRectangle(double leftPoint, double rightPoint, double height) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RealRectangle that = (RealRectangle) o;

        if (Double.compare(that.leftPoint, leftPoint) != 0) return false;
        if (Double.compare(that.rightPoint, rightPoint) != 0) return false;
        return Double.compare(that.height, height) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(leftPoint);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rightPoint);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public RealRectangle(RealRectangle realRectangle) {
        this.leftPoint = realRectangle.getLeftPoint();
        this.rightPoint = realRectangle.getRightPoint();
        this.height = realRectangle.getHeight();
    }

    public void setLeftPoint(double leftPoint) {
        this.leftPoint = leftPoint;
    }

    public void setRightPoint(double rightPoint) {
        this.rightPoint = rightPoint;
    }

    public void setHeight(double height) {
        this.height = height;
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
