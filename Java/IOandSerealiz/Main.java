package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        var glassBottle = new GlassBottle(0.99);
        var plasticBottle = new PlasticBottle(0.29);
        var bottles = new ArrayList<Bottle>();
        bottles.add(glassBottle);
        bottles.add(plasticBottle);

        var client = new Client("Peshoo");
        var workshop = new Workshop("HomeworkAdventures", bottles);

        printMsg(">   Now, we have a client and brand new company. When our client tries to buy glass bottle of water, we should get exception since we haven't bottled anything yet: ");
        try{
            workshop.sellBottledWaterToClient(client, 1, BottleType.GLASS);
        }
        catch(InsufficientNumberOfBottlesException ex){
            printMsg("[ERROR]  " + ex.getMessage());
        }

        printMsg(">   Fine, now let's try to bottle water in 3 glass bottles, when there is currently only 1 in the commpany. Error should occur: ");
        try{
            workshop.bottleWater(BottleType.GLASS, 3);
        }
        catch(InsufficientNumberOfBottlesException ex){
            printMsg("[ERROR]  " + ex.getMessage());
        }

        printMsg(">   Now, let's try to buy 11 glass bottles. The company currently has only 10BGN, therefore, we should receive an error: ");
        try{
            workshop.buyBottles(11, glassBottle);
        }
        catch(InsufficientCompanyBalanceException ex){
            printMsg("[ERROR]  " + ex.getMessage());
        }

        printMsg(">   Since all exceptions have been shown, let's continue.");
        printMsg(">   Now, we will buy 2 more bottles of type (currently have 1 only). Then we will bottle them. Then we will sell 3 glass bottles to our client. No error expected.");
        try{
            workshop.buyBottles(2, glassBottle);
            workshop.buyBottles(2, plasticBottle);
            workshop.bottleWater(BottleType.GLASS, 3);
            workshop.bottleWater(BottleType.PLASTIC, 3);
            workshop.sellBottledWaterToClient(client, 3, BottleType.GLASS);
        }
        catch(InsufficientNumberOfBottlesException ex){
            printMsg("[ERROR]  " + ex.getMessage());
        }
        catch(InsufficientCompanyBalanceException ex){
            printMsg("[ERROR]  " + ex.getMessage());
        }
    }

    private static void printMsg(String msg){
        System.out.println(msg);
    }
}
