package com.genius.ticketing;

public class Theater {

    private long amount;
    private long fee;

    public Theater(long amount, long fee) {
        this.amount = amount;
        this.fee = fee;
    }

    public Theater() {
        this(0, 100);
    }

    public long getAmount() {
        return amount;
    }

    public void enter(Audience audience) {
        audience.minusAmount(fee);
        amount = amount + fee;
    }
}