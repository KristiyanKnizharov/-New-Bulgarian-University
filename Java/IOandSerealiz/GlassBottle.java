package com.company;

public class GlassBottle extends Bottle{
    public GlassBottle(double price) {
        super(price);
        setType(BottleType.GLASS);
    }
}
