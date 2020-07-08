package company;

import java.io.*;
import company.Models.*;
import java.awt.dnd.InvalidDnDOperationException;
import java.util.Scanner;

public class Main extends Thread {
    public static void main(String[] args)throws IOException, NullPointerException{

        Shop shop = new Shop();
        Cashier cashier1 = new Cashier("120013", "Patric");
        Cashier cashier2 = new Cashier("120014", "Christ");
        shop.addCashier(cashier1);
        shop.addCashier(cashier2);
        Goods goods1 = new Goods(12345, "Banana",
                2.00, 2);
        Goods goods2 = new Goods(12346, "Avocado",
                8.30, 20);
        Goods goods3 = new Goods(12347, "Protein_Bar",
                5.20, 4);
        Goods goods4 = new Goods(12348, "Protein_Shake",
                7.00, 2);
        Goods goods5 = new Goods(123489, "Milk",
                2.33, 5);

        shop.addGoods(goods1);
        shop.addGoods(goods2);
        shop.addGoods(goods1);
        shop.addGoods(goods1);
        shop.addGoods(goods3);
        shop.addGoods(goods4);
        shop.addGoods(goods5);
        shop.addGoods(goods5);
        shop.addGoods(goods5);
        shop.removeGoods(goods5);
        try {
            //should throw exception when add the same cashier.
            shop.addCashier(cashier2);
        }
        catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        try {
            //should throw exception when remove non-existen cashier.
            Cashier cashierNonExist = new Cashier("1212", "Elena");
            shop.firedCashier(cashierNonExist);
        }
        catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        //current goods in shop
        System.out.println(shop);
        //current turnover
        System.out.println("Current turnover: " + shop.getCurrTurnover());


        CaseRegister caseRegister1 = new CaseRegister(cashier1);
        CaseRegister caseRegister2 = new CaseRegister(cashier2);

        //Create clients
        Client clientMaria = new Client("Maria", 3.77);
        System.out.println("Maria came with " + clientMaria.currBudget());

        Client clientDona = new Client("Dona", 9.00);
        //ClientMaria take goods in her basket.
        clientMaria.addGoodsInBasket(goods5);
        try {
            clientMaria.addGoodsInBasket(goods2);
        }
        catch (InvalidDnDOperationException ioe){
            System.out.println(ioe);
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
        //ClientMaria buy the products in her basket.
        caseRegister2.currClientOnCase(shop, clientMaria);
        System.out.println("Maria's budget after shopping: " + clientMaria.currBudget());
        System.out.println("Current turnover: " + shop.getCurrTurnover());

        goods1.nextDayForGoods();
        goods2.nextDayForGoods();
        goods3.nextDayForGoods();
        goods4.nextDayForGoods();
        goods5.nextDayForGoods();

        //Write on console
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of the client:");
        //Bob
        String nameOfClient = input.nextLine();
        System.out.println("Client budget:");
        //34.00
        double clientBudget = input.nextDouble();
        Client newClient = new Client(nameOfClient, clientBudget);

        try {
            newClient.addGoodsInBasket(goods1);
            newClient.addGoodsInBasket(goods3);
        }
        catch (InvalidDnDOperationException ioe){
            System.out.println(ioe.getMessage());
        }

        System.out.println(newClient);
        System.out.println("Start Process...");
        caseRegister1.currClientOnCase(shop, newClient);

        //Create thread1
        Thread t1 = new Thread("First thread.");
        System.out.println("Creating " +  t1.getName());
        t1.start();

        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println(caseRegister1.getReceipt());
        System.out.println("\n");



        //Creating a file with the current receipt
        File myFile = new File("MyFile.txt");
        try {
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("----------------------------------");

        FileWriter fw = new FileWriter(myFile);
        fw.append(caseRegister1.getReceipt().toString());
        fw.close();

        System.out.println("Turnover after: " + shop.getCurrTurnover());
        t1.interrupt();
    }

    @Override
    public void run() {
        var t1 = new Thread("First thread.");
        System.out.println("Running ");
        try {
                System.out.println("Thread: " + t1 + ", ");
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        catch (Exception e) {
            System.out.println("Thread " +  t1 + " interrupted.");
        }
        System.out.println("Thread " +  t1 + " exiting.");
    }

    public void start() {
        var t2 = new Thread("First thread.");
        System.out.println("Starting " + t2);
        if (t2 == null) {
            t2 = new Thread(this, String.valueOf(t2));
            t2.start();
        }
    }
}
