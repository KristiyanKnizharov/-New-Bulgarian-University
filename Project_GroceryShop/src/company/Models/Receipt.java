package company.Models;

import company.IModels.IGoods;
import company.IModels.IReceipt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt implements IReceipt {
    final double mathRound = 100.0;

    private int countOfReceipt;
    private double cost;
    private String nameOfCashier;
    private String date_time;
    private List<Goods> goodsListReceipt;

    public Receipt( String nameCashier){
        this.countOfReceipt += 1;
        this.nameOfCashier = nameCashier;
        this.date_time = LocalDateTime.now().toString();
        goodsListReceipt = new ArrayList<>();
    }

    public void addGoodsToReceiptList(Goods goods){
        goodsListReceipt.add(goods);
    }

    public void removeGoodsFromReceiptList(Shop shop, Goods goods, Client client){
        goodsListReceipt.remove(goods);
        client.takeMoneyForReturnTheGoods(goods.currPrice());
        shop.addGoods(goods);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append('\n' + "__________________________________________" + "\n")
                .append("|.........................................|" + "\n")
                .append("|____________KNIZHAROV SHOP_______________|" + "\n")
                .append("|Receipt number:                         " + this.countOfReceipt + "|\n")
                .append("|Cashier:                           " + this.nameOfCashier + "|\n")
                .append("|Date and time:       " + this.date_time.substring(0,10) + " " + this.date_time.substring(11,20) + "|\n");
        //TODO every goods to be printed;
        int prodCount = 1;

        for(Goods hm : this.goodsListReceipt) {
            sb.append("| " + prodCount + ". " + hm.getName() + ": " + Math.round(hm.currPrice()* mathRound) / mathRound + "\n");
            prodCount++;
            cost+= hm.currPrice();
        }
        sb
                .append("|Cost:                          " + Math.round(this.cost* mathRound) / mathRound + " leva.| \n")
                .append("|............Have a nice day!.............|\n")
                .append("|_________________________________________|\n");
        return sb.toString();
    }

}
