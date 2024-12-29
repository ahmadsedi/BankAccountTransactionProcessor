package com.ahmadsedi.bank;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 19/12/2024
 * Time: 10:15
 */

public class Transaction {
    int source;
    int destination;
    int amount;

    public Transaction(int source, int destination, int amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getAmount() {
        return amount;
    }
}
