package com.company;

public class Main {
    public static void main(String[] args) {
        CleaningCompany cleaningCompany = new CleaningCompany("Pronto");
        Cleaning cleaning = new Cleaning(ServicePriority.LOW, 10.5);
        var a = cleaning.servicePriceWithoutTax();
        var b = cleaning.servicePriceWithTax();
        boolean c = cleaning.isWithHighPriority();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
