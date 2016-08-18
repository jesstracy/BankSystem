package com.tiy.practice;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Assignment9Runner {
    public static void main(String[] args) {
        System.out.println("Assignment9Runner running....");
        Assignment9Runner myRunner = new Assignment9Runner();
//        myRunner.writeListOfCustomers();
        myRunner.testBank();
    }

    public void testBank() {
        Bank testBank = new Bank();
        testBank.setName("Wells Fargo");

        Customer testCustomer = new Customer();
        testCustomer.setName("Jessica");

        BankAccount testBankAccount = new BankAccount();
        testBankAccount.setName("Checking");
        testBankAccount.setBalance(0.00);

        BankAccount testBankAccount2 = new BankAccount();
        testBankAccount2.setName("Savings");
        testBankAccount2.setBalance(50.00);

        System.out.println("Depositing $20 into checking...Should be 20");
        testBankAccount.deposit(20.00);
        testBankAccount.printInfo();

        System.out.println();

        System.out.println("Withdrawing $10 from savings...Should be 40");
        testBankAccount2.withdraw(10.00);
        testBankAccount2.printInfo();
    }

//    public void writeListOfCustomers() {
//        FileWriter listOfCustomersWriter = null;
//        try {
//            File listOfCustomersFile = new File("listOfCustomers.txt");
//            listOfCustomersWriter = new FileWriter(listOfCustomersFile);
//            listOfCustomersWriter.write("Jessica,James,Emily");
//        } catch (Exception exception) {
//            System.out.println("Exception caught...");
//            exception.printStackTrace();
//        } finally {
//            if (listOfCustomersWriter == null) {
//                try {
//                    listOfCustomersWriter.close();
//                } catch (Exception ex) {
//                    System.out.println("Exception caught...");
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }

}
