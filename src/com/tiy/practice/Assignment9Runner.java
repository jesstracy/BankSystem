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

        BankAccount testBankAccount = new CheckingAccount();
        testBankAccount.setName("Checking");
        testBankAccount.setBalance(100.00);
//        testBankAccount.printInfo();

//        testBank.addCustomer(testCustomer);
//        System.out.println(testBank.getCustomerList());
        testCustomer.addBankAccount(testBankAccount);
        testBank.addCustomer(testCustomer);

        BankAccount testBankAccount2 = new SavingsAccount();
        testBankAccount2.setName("Savings");
        testBankAccount2.setBalance(200.00);
////        testBankAccount2.printInfo();
//
        BankAccount testBankAccount3 = new RetirementAccount();
        testBankAccount3.setName("Retirement");
        testBankAccount3.setBalance(1300.00);
////        testBankAccount3.printInfo();

        System.out.println();


        testCustomer.addBankAccount(testBankAccount2);
        testCustomer.addBankAccount(testBankAccount3);

        testBank.printInfo();

        System.out.println();
        System.out.println();
//
        Customer testCustomer2 = new Customer();
        testCustomer2.setName("James");
//
        BankAccount testBankAccount4 = new SavingsAccount();
        testBankAccount4.setName("Savings");
        testBankAccount4.setBalance(400.00);
//
        BankAccount testBankAccount5 = new RetirementAccount();
        testBankAccount5.setName("Retirement");
        testBankAccount5.setBalance(1500.00);

        testCustomer2.addBankAccount(testBankAccount4);
        testCustomer2.addBankAccount(testBankAccount5);
        testBank.addCustomer(testCustomer2);

        testBank.printInfo();

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
//        ArrayList<BankAccount> testCustomerAccountList = new ArrayList<BankAccount>();
//        testCustomerAccountList.(testBankAccount);
//        testCustomerAccountList.add(testBankAccount2);
//        testCustomerAccountList.add(testBankAccount3);
//        testCustomer.setCustomerListOfAccounts(testCustomerAccountList);
//        testCustomer.printInfo();

//        testBank.addCustomer(testCustomer);
//        System.out.println(testBank.getCustomerList());

//        testCustomer.addBankAccount(testBankAccount);
//        testCustomer.addBankAccount(testBankAccount2);
//        testCustomer.addBankAccount(testBankAccount3);
//        testCustomer.printInfo();

        System.out.println();

//        ArrayList<BankAccount> testCustomerAccountList2 = new ArrayList<BankAccount>();
//        testCustomerAccountList2.add(testBankAccount4);
//        testCustomerAccountList2.add(testBankAccount5);
//        testCustomer2.setCustomerListOfAccounts(testCustomerAccountList2);
////        testCustomer2.printInfo();

        System.out.println();

        //TESTING ADDCUSTOMER AND ADDBANKACCOUNT METHODS
//        Customer testCustomer3 = new Customer();
//        testCustomer3.setName("Emily");
//        testCustomer3.setNumOfAccounts(1);
//        BankAccount testBankAccount6 = new BankAccount();
//        testBankAccount6.setName("Checking");
//        testBankAccount6.setBalance(1000.00);
//        testCustomer3.addBankAccount(testBankAccount6);
//        ArrayList<BankAccount> testCustomerAccountList3 = new ArrayList<BankAccount>();
//        testCustomerAccountList3.add(testBankAccount6);
//        testCustomer3.setCustomerListOfAccounts(testCustomerAccountList3);

//        testBank.addCustomer(testCustomer3);
//        testBank.printInfo();
    }

    public void writeListOfCustomers() {
        FileWriter listOfCustomersWriter = null;
        try {
            File listOfCustomersFile = new File("listOfCustomers.txt");
            listOfCustomersWriter = new FileWriter(listOfCustomersFile);
            listOfCustomersWriter.write("Jessica,James,Emily");
        } catch (Exception exception) {
            System.out.println("Exception caught...");
            exception.printStackTrace();
        } finally {
            if (listOfCustomersWriter != null) { // At first I had if it equals null, so it skipped right over this
                                                 // and never closed, which is why it did not print anything bc it
                                                 // thought I was going to write more. But listOfCustomersWriter is not
                                                 // null bc I used it to write something (?)
                try {
                    listOfCustomersWriter.close();
                } catch (Exception ex) {
                    System.out.println("Exception caught...");
                    ex.printStackTrace();
                }
            }
        }
    }
}
