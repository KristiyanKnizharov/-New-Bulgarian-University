package company.Models;


import company.IModels.ICaseRegister;

public class CaseRegister implements ICaseRegister {
    private Cashier cashier;
    private Receipt receipt;
    private double turnoverForShop;

    public CaseRegister(Cashier cashier){
        this.cashier = cashier;
    }

    public void currClientOnCase(Shop shop, Client currClient){
        receipt = new Receipt(cashier.getName());

        for (Goods currGoods : currClient.getGoodsClientList()
             ) {
            turnoverForShop += currGoods.currPrice();
            receipt.addGoodsToReceiptList(currGoods);
            shop.removeGoods(currGoods);
        }
        currClient.giveMoneyToCashier(turnoverForShop);
        shop.increaseTurnover(turnoverForShop);
    }

    public Receipt getReceipt() {
        return this.receipt;
    }
}
