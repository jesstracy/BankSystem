package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class RetirementAccount extends BankAccount implements Runnable {
    private double interestRate;
    private int sleepTime;
    private boolean threadsKeepRunning = true;

    public RetirementAccount() {
        super();
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
        setThreadsKeepRunning(true);
    }

    public RetirementAccount(boolean threadsKeepRunning) {
        super();
        setThreadsKeepRunning(threadsKeepRunning);
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
    }

    public void run() {
        try {
            while (threadsKeepRunning) {
//            while (true) {
//                System.out.println("Retirement thread running");
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
