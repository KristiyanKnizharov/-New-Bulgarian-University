package com.company;

import java.io.*;
import java.util.ArrayList;

public class Workshop {
    private final String FilePath = "files/Invoice.ser";

    private String name;
    private ArrayList<Bottle> bottles;
    private ArrayList<Bottle> bottlesForSale;
    private double companyBalance;

    public Workshop(String name, ArrayList<Bottle> bottles) {
        this.name = name;
        this.bottles = bottles;
        this.bottlesForSale = new ArrayList<>();
        this.companyBalance = 10;
    }

    public void sellBottledWaterToClient(Client client, int numberOfBottles, BottleType type) throws InsufficientNumberOfBottlesException{
        var readyBottlesCount = bottlesForSale.stream().filter(b -> b.getType() == type).count();
        if(numberOfBottles > readyBottlesCount){
            var errorMsg = client.getName() + " wants to buy " + numberOfBottles + " bottles, however, there currently are " + readyBottlesCount + " left!";
            throw new InsufficientNumberOfBottlesException(errorMsg);
        }

        var bottlePrice = getBottlePrice(type);
        var orderPrice = bottlePrice * numberOfBottles;
        companyBalance += orderPrice;
        var invoice = new Invoice(name, client.getName(), numberOfBottles, bottlePrice, orderPrice);

        try(FileOutputStream fos = new FileOutputStream(FilePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);){
            outputStream.writeObject(invoice);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Invoice readInvoiceFromFile(){
        try(FileInputStream fis = new FileInputStream(FilePath);
            ObjectInputStream outputStream = new ObjectInputStream(fis);){
            return (Invoice)outputStream.readObject();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void bottleWater(BottleType type, int quantity) throws InsufficientNumberOfBottlesException{
        var bottlesOfType = bottles.stream().filter(b -> b.getType() == type);
        var bottlesCounter = bottlesOfType.count();
        if (quantity > bottlesCounter){
            var diff = quantity - bottlesCounter;
            var errorMsg = "Attempted to bottle " + quantity + " " + type + " bottle(s). " + diff + " more should be purchased in order to finish the order.";
            throw new InsufficientNumberOfBottlesException(errorMsg);
        }
        var bottle = bottles.stream().filter(b -> b.getType() == type).findFirst().get();
        for(int i = 0; i < quantity; i++){
            singleBottleOperation(type);
            bottlesForSale.add(copyBottle(bottle));
        }
    }

    public void buyBottles(int quantity, Bottle bottle) throws InsufficientCompanyBalanceException{
        var priceForAll = bottle.getPrice() * quantity;
        if (priceForAll > companyBalance){
            var lackingMoneyAmount = priceForAll - companyBalance;
            var errorMessage = name + " lacks " + lackingMoneyAmount + "BGN in order to buy " + quantity + " " + bottle.getType() + " bottle(s). " +
                    "Price for one ->s " + bottle.getPrice() + "BGN.";
            throw new InsufficientCompanyBalanceException(errorMessage);
        }

        for(int i = 0; i < quantity; i++){
            bottles.add(copyBottle(bottle));
        }
    }

    private static Bottle copyBottle(Bottle bottle){
        if (bottle.getType() == BottleType.GLASS){
            return new GlassBottle(bottle.getPrice());
        }
        return new PlasticBottle(bottle.getPrice());
    }

    private void singleBottleOperation(BottleType type){
        var lastItemIndex = bottles.size() - 1;
        for (int i = lastItemIndex; i >= 0; i--){
            if (bottles.get(i).getType() == type){
                bottles.remove(i);
                break;
            }
        }
    }

    private double getBottlePrice(BottleType bottleType){
        if (bottleType == BottleType.GLASS){
            return 1.99;
        }
        return 0.59;
    }
}
