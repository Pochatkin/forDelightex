package com.example.delightex.entity;

import java.util.Random;

/**
 * Created by mikhail on 07.07.17.
 */
public class Rectangle {

    private double leftPoint;

    private double rightPoint;
    private double height;

    public Rectangle(boolean isIntPoints, int maxRangeX, int maxRangeY) {
        Random random = new Random();
        if (!isIntPoints) {
            this.leftPoint = maxRangeX * random.nextDouble();
            this.rightPoint = this.leftPoint + (maxRangeX - this.leftPoint) * random.nextDouble();
            this.height = random.nextDouble() * maxRangeY;
        } else {
            this.leftPoint = random.nextInt(maxRangeX);
            this.rightPoint = this.leftPoint + random.nextInt(maxRangeX - (int)this.leftPoint) + 1;
            this.height = random.nextInt(maxRangeY) + 1;
        }
    }

    public Rectangle(double leftPoint, double rightPoint, double height) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.height = height;
    }

    public Rectangle(Rectangle realRectangle) {
        this.leftPoint = realRectangle.getLeftPoint();
        this.rightPoint = realRectangle.getRightPoint();
        this.height = realRectangle.getHeight();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "leftPoint=" + leftPoint +
                ", rightPoint=" + rightPoint +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle that = (Rectangle) o;

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
