package com.genius.ticketing;

public class Audience {
    private long amount;

    public Audience(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void minusAmount(long minusAmount) {
        amount = amount - minusAmount;
    }
}
