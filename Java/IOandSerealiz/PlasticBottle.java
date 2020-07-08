package com.company;

public class PlasticBottle extends Bottle{
    public PlasticBottle(double price) {
        super(price);
        setType(BottleType.PLASTIC);
    }
}
