package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class RetirementAccount extends BankAccount implements Runnable {
    private double interestRate;
    private int sleepTime;

    public RetirementAccount() {
        super();
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
    }

    public void run() {
        try {
            while (Assignment9Runner.runThreads) {
                System.out.println("Retirement thread running");
                Thread.sleep(sleepTime);
                double newBalWithInterest = getBalance() * interestRate;
                setBalance(newBalWithInterest);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
