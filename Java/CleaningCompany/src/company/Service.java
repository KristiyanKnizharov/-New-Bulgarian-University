package com.company;

public abstract class Service {
    private ServicePriority priority;
    private double initialPrice;
    private final double DDSPercentage = 0.20;

    //CONSTRUCTOR
    public Service(ServicePriority priority, double price) {
        this.priority = priority;
        this.initialPrice = price;
    }


    public double servicePriceWithoutTax(){
        return adjustPriceForPriority(priority);
    }

    public double servicePriceWithTax(){
        // In Bulgaria Tax(DDS) is 20%
        var result = servicePriceWithoutTax();
        result += DDSPercentage * result;
        return result;
    }

    public boolean isWithHighPriority(){
        return priority == ServicePriority.HIGH;
    }

    public ServicePriority getPriority(){
        return priority;
    }

    protected double adjustPriceForPriority(ServicePriority priorityToConsider){
        var result = initialPrice;
        if (priorityToConsider == ServicePriority.MEDIUM){
            result += 0.10 * result;
        }
        else if (priorityToConsider == ServicePriority.HIGH){
            result += 0.15 * result;
        }
        return result;
    }

}
