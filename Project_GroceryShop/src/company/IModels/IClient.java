package company.IModels;

import company.Models.Goods;

public interface IClient {

    void addGoodsInBasket(Goods goods);

    void removeGoodsFromBasket(Goods goods);

    double currBudget();

    void giveMoneyToCashier(double money);

    void takeMoneyForReturnTheGoods(double money);
}
