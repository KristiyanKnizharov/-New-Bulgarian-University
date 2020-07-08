package com.company;

import java.io.Serializable;

public abstract class Bottle implements Serializable {
    private double volume;
    private BottleType type;
    private double price;

    protected Bottle(double price) {
        this.price = price;
        this.volume = 0.5;
    }

    public double getVolume() {
        return volume;
    }

    public BottleType getType() {
        return type;
    }

    protected void setType(BottleType type){
        this.type = type;
    }

    public double getPrice() {
        return price;
    }
}
