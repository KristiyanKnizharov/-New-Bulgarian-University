package com.company;

public class Cleaning extends Service{
    public Cleaning(ServicePriority priority, double price) {
        super(priority, price);
    }

    @Override
    public double servicePriceWithoutTax() {
        return super.servicePriceWithoutTax();
    }

    public double cleaningPriceWithHighPriority(){
        return adjustPriceForPriority(ServicePriority.HIGH);
    }

    public boolean isWithHigherOrEqualPrice(Cleaning cleaningService){
        return this.servicePriceWithoutTax() >= cleaningService.servicePriceWithoutTax();
    }
}
