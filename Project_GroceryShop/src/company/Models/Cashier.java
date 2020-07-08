package company.Models;


import company.IModels.ICashier;

public class Cashier implements ICashier {
    private String id;
    private String name;

    public Cashier(String id, String name){
    this.id = id;
    this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
}
