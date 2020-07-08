package company.IModels;

import company.Models.Cashier;
import company.Models.Goods;

public interface IShop  {

    void addCashier(Cashier cashier);

    void firedCashier(Cashier cashier);

    void addGoods(Goods goods);

    void removeGoods(Goods goods);

    void increaseTurnover(double money);

    double getCurrTurnover();
}
