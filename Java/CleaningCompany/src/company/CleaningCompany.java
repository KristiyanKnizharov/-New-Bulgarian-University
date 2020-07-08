package com.company;

import java.util.ArrayList;

public class CleaningCompany {
    private String  name;
    private ArrayList<Cleaning> services;

    public CleaningCompany(String name) {
        this.name = name;
        this.services = new ArrayList<>();
    }

    public void addService(Cleaning service){
        this.services.add(service);
    }

    /*
        Calculate income without the Tax that would be taken by the country
     */
    public double calculateIncome(){
        return services.stream().mapToDouble(s -> s.servicePriceWithoutTax()).sum();
    }

    /*
        Calculate income based on particular priority. The Tax that would be taken by the country is excluded
     */
    public double calculateIncomeByPriority(ServicePriority priority){
        return services.stream().filter(s -> s.getPriority() == priority).mapToDouble(s -> s.servicePriceWithoutTax()).sum();
    }

    public String getName() {
        return name;
    }


}
