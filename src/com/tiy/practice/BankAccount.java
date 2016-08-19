package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class BankAccount {
    private String name;
    private double balance;

    public double deposit(double amountToDeposit) {
        balance += amountToDeposit;
        return balance;
    }

    public double withdraw(double amountToWithdraw) {
        balance -= amountToWithdraw;
        return balance;
    }

    public void printInfo() {
        System.out.println("Printing bank account info...");
        System.out.println("\tName: " + name);
        System.out.println("\tBalance: " + balance);
    }

    //All getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
