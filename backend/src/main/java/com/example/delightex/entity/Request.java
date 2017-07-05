package com.example.delightex.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mikhail on 05.07.17.
 */
@Entity
@Data
public class Request {
    private @Id
    @GeneratedValue Long id;
    private int number;
    private int maxRangeX;
    private int maxRangeY;

    Request() {}

    public int getNumber() {
        return number;
    }

    public int getMaxRangeX() {
        return maxRangeX;
    }

    public int getMaxRangeY() {
        return maxRangeY;
    }
}
