package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class SavingsAccount extends BankAccount implements Runnable {
    private double interestRate;
    private int sleepTime;

    public SavingsAccount() {
        super();
        setName("Savings");
        interestRate = 1.05;
        sleepTime = 10000;
    }

    public void run() {
        try {
            while (Assignment9Runner.runThreads) {
//            while (true) {
                System.out.println("Savings thread running");
                Thread.sleep(sleepTime);
                double newBalWithInterest = getBalance() * interestRate;
                setBalance(newBalWithInterest);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

