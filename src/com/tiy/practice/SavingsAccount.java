package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class SavingsAccount extends BankAccount implements Runnable {
    private double interestRate;
    private int sleepTime;
    private boolean threadsKeepRunning = true;

    public SavingsAccount() {
        super();
        setName("Savings");
        interestRate = 1.05;
        sleepTime = 10000;
//        setThreadsKeepRunning(true);
    }

    public SavingsAccount(boolean threadsKeepRunning) {
        super();
        setThreadsKeepRunning(threadsKeepRunning);
        setName("Savings");
        interestRate = 1.05;
        sleepTime = 10000;
    }

//    public void getInterest() {
//        double newBalWithInterest = getBalance() * interestRate;
//        setBalance(newBalWithInterest);
//    }

    public void run() {
        try {
            while (threadsKeepRunning) {
//            while (true) {
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

