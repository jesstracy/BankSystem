package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class SavingsAccount extends BankAccount {
    private double interestRate = 1.05;
    private int sleepTime = 10000;

    public SavingsAccount() {
        super();
        setName("Savings");
        interestRate = 1.05;
        sleepTime = 10000;
    }

    public void getInterest() throws Exception {
        Thread.sleep(sleepTime);
        double newBalWithInterest = getBalance() * interestRate;
        setBalance(newBalWithInterest);
    }

}

