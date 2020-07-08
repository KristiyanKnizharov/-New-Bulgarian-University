package com.company;

import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Serializable {
    private static int invoiceId = 12345;

    private int currentId;
    private String distributorName;
    private String clientName;
    private LocalDate date;
    private int itemsCount;
    private double priceForItem;
    private double priceForAll;

    public Invoice(String distributorName, String clientName, int itemsCount, double priceForItem, double priceForAll) {
        this.distributorName = distributorName;
        this.clientName = clientName;
        this.itemsCount = itemsCount;
        this.priceForItem = priceForItem;
        this.priceForAll = priceForAll;

        this.currentId = invoiceId++;
        this.date = LocalDate.now();
    }
}
