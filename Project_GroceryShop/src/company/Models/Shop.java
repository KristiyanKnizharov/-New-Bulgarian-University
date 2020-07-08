package company.Models;

import company.IModels.IShop;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop implements IShop {
    private final int removeOneGoods = 1;
    private HashMap<Goods, Integer> goodsIntegerHashMap;
    private ArrayList<Cashier> cashierArrayList;
    private double currTurnover;


    public Shop(){
        goodsIntegerHashMap = new HashMap<Goods, Integer>();
        cashierArrayList = new ArrayList<Cashier>();
    }

    public void addCashier(Cashier cashier){
        if (!cashierArrayList.contains(cashier)){
            cashierArrayList.add(cashier);
        }
        else {
            throw new IllegalArgumentException("You can't add the same cashier!");
        }
    }
    public void firedCashier(Cashier cashier){
        if (cashierArrayList.contains(cashier)){
            cashierArrayList.remove(cashier);
        }
        else {
            throw new IllegalArgumentException("You can't remove unknown cashier!");
        }
    }

    public void addGoods(Goods goods){
        if (!goodsIntegerHashMap.containsKey(goods))
            goodsIntegerHashMap.put(goods, 1);
        else{
            //update the value(count) of the current goods;
            goodsIntegerHashMap.merge(goods, 1, Integer::sum);}
    }
    public void removeGoods(Goods goods){
        if (!goodsIntegerHashMap.containsKey(goods))
                throw new NullPointerException("Cannot remove unknown goods.");
        else {
            if (goodsIntegerHashMap.containsKey(goods) && goodsIntegerHashMap.get(goods) == 1){
                goodsIntegerHashMap.remove(goods);
            }
            else{
                //update the value(count) of the current goods;
                goodsIntegerHashMap.put(goods, goodsIntegerHashMap.get(goods)-removeOneGoods);
            }
        }
    }
    public void increaseTurnover(double money){
        currTurnover += money;
    }
    public double getCurrTurnover(){
        return (Math.round(this.currTurnover * 100.0) / 100.0);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Goods hm : goodsIntegerHashMap.keySet()) {
            sb.append(hm);
            sb.append("Count: " + goodsIntegerHashMap.size());
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}
