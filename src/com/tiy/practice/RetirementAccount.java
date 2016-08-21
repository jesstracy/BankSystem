package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class RetirementAccount extends BankAccount {
    private double interestRate;
    private int sleepTime;

    public RetirementAccount() {
        super();
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
    }

    public void getInterest() throws Exception {
        Thread.sleep(sleepTime);
        double newBalWithInterest = getBalance() * interestRate;
        setBalance(newBalWithInterest);
    }
}
