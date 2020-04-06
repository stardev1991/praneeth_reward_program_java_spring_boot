package com.krstardev.reward.model;

public class Reward {

    final double month1;
    final double month2;
    final double month3;

    final double total;

    final long user;

    public Reward(double month1, double month2, double month3, long user) {
        this.month1 = month1;
        this.month2 = month2;
        this.month3 = month3;
        this.user = user;

        this.total = month1 + month2 + month3;
    }

    public double getMonth1() {
        return month1;
    }

    public double getMonth2() {
        return month2;
    }

    public double getMonth3() {
        return month3;
    }

    public double getTotal() {
        return total;
    }

    public long getUser() {
        return user;
    }

}
