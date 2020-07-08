package company.Models;

import company.IModels.IClient;
import company.IModels.IGoods;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;

public class Client implements IClient {
    final double mathRound = 100.0;

    private String name;
    private double budget;
    private ArrayList<Goods> goodsClientList;
    private double needMoney;

    public Client(String name, double budget){
        this.name = name;
        this.budget = budget;
        this.goodsClientList = new ArrayList<Goods>();
    }

    public void addGoodsInBasket(Goods goods){
        if (this.needMoney + goods.currPrice() <= this.budget){
                this.goodsClientList.add(goods);
                this.needMoney += goods.currPrice();
        }
        else throw new InvalidDnDOperationException
                ("Not enough money for this product: " + goods.getName());

    }
    public void removeGoodsFromBasket(Goods goods){
        this.goodsClientList.remove(goods);
    }
    public double currBudget(){
        return (Math.round(this.budget * mathRound) / mathRound);
    }

    public ArrayList<Goods> getGoodsClientList() {
        return this.goodsClientList;
    }

    public void giveMoneyToCashier(double money){
        this.budget -= money;
    }

    public void takeMoneyForReturnTheGoods(double money){
        this.budget += money;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client: ");
        sb.append("name='" + this.name);
        sb.append(", budget=" + this.budget + ", Basket: ");
        for(IGoods goods : this.goodsClientList) {
            sb.append(goods + ", ");
        }
        return sb.toString().trim();
    }
}
