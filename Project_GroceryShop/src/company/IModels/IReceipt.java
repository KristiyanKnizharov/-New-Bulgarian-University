package company.IModels;

import company.Models.Client;
import company.Models.Goods;
import company.Models.Shop;

public interface IReceipt {

    void addGoodsToReceiptList(Goods goods);

    void removeGoodsFromReceiptList(Shop shop, Goods goods, Client client);
}
