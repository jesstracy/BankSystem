package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class RetirementAccount extends BankAccount implements Runnable {
    private double interestRate;
    private int sleepTime;
    private boolean threadsKeepRunning;

    public RetirementAccount() {
        super();
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
    }

    public RetirementAccount(boolean threadsKeepRunning) {
        super();
        this.threadsKeepRunning = threadsKeepRunning;
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
    }

    public void run() {
        try {
//            while (threadsKeepRunning) {
            while (true) {
                Thread.sleep(sleepTime);
                double newBalWithInterest = getBalance() * interestRate;
                setBalance(newBalWithInterest);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean isThreadsKeepRunning() {
        return threadsKeepRunning;
    }

    public void setThreadsKeepRunning(boolean threadsKeepRunning) {
        this.threadsKeepRunning = threadsKeepRunning;
    }
}
