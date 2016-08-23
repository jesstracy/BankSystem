package com.tiy.practice;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class RetirementAccount extends BankAccount implements Runnable {
    private double interestRate;
    private int sleepTime;
    private long startRunTime;
    private long finishRunTime;

    public RetirementAccount() {
        super();
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
    }

    public RetirementAccount(double accountBalance) {
        super();
        setName("Retirement");
        interestRate = 1.10;
        sleepTime = 120000;
        this.setBalance(accountBalance);
    }

    public RetirementAccount(long startRunTime, double accountBalance) {
        super();
        setName("Retirement");
        this.setBalance(accountBalance);
        interestRate = 1.10;
        sleepTime = 120000;
        this.startRunTime = startRunTime;
        //read in finish run time from file.
        this.finishRunTime = readFinishTime();
        long timeElapsed = startRunTime - finishRunTime;
        double timeElapsedInMilliseconds = (timeElapsed / 1000000);
        double numIntervals = (timeElapsedInMilliseconds / sleepTime);
        for (int counter = 0; counter < numIntervals; counter++) {
            this.setBalance(this.getBalance() * interestRate);
        }
    }

    public long readFinishTime() {
        try {
            File myFinishTimeFile = new File("finishRunTime.txt");
            Scanner myFileScanner = new Scanner(myFinishTimeFile);
            long finishRunTime = Long.valueOf(myFileScanner.nextLong());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return finishRunTime;
    }

    public void run() {
        try {
            while (Assignment9Runner.runThreads) {
//                System.out.println("Retirement thread running");
                Thread.sleep(sleepTime);
                double newBalWithInterest = getBalance() * interestRate;
                setBalance(newBalWithInterest);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Getters and setters
    public long getStartRunTime() {
        return startRunTime;
    }

    public void setStartRunTime(long startRunTime) {
        this.startRunTime = startRunTime;
    }

    public long getFinishRunTime() {
        return finishRunTime;
    }

    public void setFinishRunTime(long finishRunTime) {
        this.finishRunTime = finishRunTime;
    }
}
