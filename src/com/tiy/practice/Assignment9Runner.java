package com.tiy.practice;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

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
        testCustomer.setNumOfAccounts(3);

        BankAccount testBankAccount = new CheckingAccount();
        testBankAccount.setName("Checking");
        testBankAccount.setBalance(100.00);
//        testBankAccount.printInfo();

        BankAccount testBankAccount2 = new SavingsAccount();
        testBankAccount2.setName("Savings");
        testBankAccount2.setBalance(200.00);
//        testBankAccount2.printInfo();

        BankAccount testBankAccount3 = new RetirementAccount();
        testBankAccount3.setName("Retirement");
        testBankAccount3.setBalance(300.00);
//        testBankAccount3.printInfo();

        Customer testCustomer2 = new Customer();
        testCustomer2.setName("James");
        testCustomer2.setNumOfAccounts(2);

        BankAccount testBankAccount4 = new BankAccount();
        testBankAccount4.setName("Savings");
        testBankAccount4.setBalance(400.00);

        BankAccount testBankAccount5 = new BankAccount();
        testBankAccount5.setName("Retirement");
        testBankAccount5.setBalance(500.00);


        // TESTING DEPOSIT AND WITHDRAW METHODS
//        System.out.println("Withdrawing $20 from checking...Should be 10");
//        testBankAccount.withdraw(20.00);
//        testBankAccount.printInfo();
//
//        System.out.println();
//
//        System.out.println("Depositing $10 into savings...Should be 60");
//        testBankAccount2.deposit(10.00);
//        testBankAccount2.printInfo();
//
//        System.out.println();
//
//        System.out.println("Depositing $2000 into retirement...Should be 3000");
//        testBankAccount3.deposit(2000.00);
//        testBankAccount3.printInfo();


        // TESTING CUSTOMER ARRAY LIST OF ACCOUNTS AND CUSTOMER PRINTINFO()
        ArrayList<BankAccount> testCustomerAccountList = new ArrayList<BankAccount>();
        testCustomerAccountList.add(testBankAccount);
        testCustomerAccountList.add(testBankAccount2);
        testCustomerAccountList.add(testBankAccount3);
        testCustomer.setCustomerListOfAccounts(testCustomerAccountList);
//        testCustomer.printInfo();

        System.out.println();

        ArrayList<BankAccount> testCustomerAccountList2 = new ArrayList<BankAccount>();
        testCustomerAccountList2.add(testBankAccount4);
        testCustomerAccountList2.add(testBankAccount5);
        testCustomer2.setCustomerListOfAccounts(testCustomerAccountList2);
//        testCustomer2.printInfo();

        System.out.println();


        // TESTING BANK HASHMAP AND BANK PRINTINFO()
        HashMap<Customer, ArrayList<BankAccount>> testHashMap = new HashMap<Customer, ArrayList<BankAccount>>();
        testHashMap.put(testCustomer, testCustomer.getCustomerListOfAccounts());
        testHashMap.put(testCustomer2, testCustomer2.getCustomerListOfAccounts());
        testBank.setCustomerAccountHashMap(testHashMap);
        testBank.printInfo();

        System.out.println();

        // TESTING GET TOTAL IN DEPOSITS METHOD
        System.out.println(testBank.getTotalInDeposits());

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
