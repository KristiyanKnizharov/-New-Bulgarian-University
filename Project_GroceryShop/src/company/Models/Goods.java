package company.Models;

import company.IModels.IGoods;

import java.util.ArrayList;

public class Goods implements IGoods {
    private long id;
    private String name;
    private double pricePerGood;
    private int expirationDate;

    public Goods(long id, String name, double pricePerGood, int expirationDate){
        this.id = id;
        this.name = name;
        this.pricePerGood = pricePerGood;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }
    public double currPrice() {
        return this.pricePerGood;
    }
    public void nextDayForGoods(){
        this.expirationDate -= 1;
    }
    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pricePerGood=" + pricePerGood +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
