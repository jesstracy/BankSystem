package com.tiy.practice;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Assignment9Runner {
    public static void main(String[] args) {
        System.out.println("Assignment9Runner running....");
        Assignment9Runner myRunner = new Assignment9Runner();
//        myRunner.userInputToFileHardCode();
//        myRunner.testBank();
        myRunner.runProgram();
//        myRunner.createBankCustomersFromFile();
    }

//    public void testBank() {
//        //Need to account for if file doesn't exist? Or maybe just call method first time with nothing in it! Use counter in runner to keep track of if it's the first time run?
//        //First, read in what customers we have from customer file.
//        Bank myBank = new Bank("ListOfCustomers.txt", "Wells Fargo");
//        //Make an array of customers
//        String[] customerNameArray = new String[myBank.getCustomerList().size()];
//        int counter = 0;
//        for (Customer customer : myBank.getCustomerList()) {
//
//            //For each customer, we want to access their list of accounts.
//            customerNameArray[counter] = customer.getName();
//            counter++;
//        }
//        for (int index = 0; index < customerNameArray.length; index++) {
//            //Here we are creating a new customer and putting their accounts in an array of accounts called customerListOfAccounts-- Need to add each one of these to the bank.
//            Customer newCustomer = new Customer(customerNameArray[index] + ".txt", customerNameArray[index]);
//            for (BankAccount account : newCustomer.getCustomerListOfAccounts()) {
//                myBank.addBankAccount(newCustomer, account);
//            }
//        }
//        myBank.printInfo();
//    }

    public void runProgram() {
        //Maybe don't add customers in bank constructor. Just put names in array.
        Bank myBank = new Bank("Wells Fargo");
        //When done -- try putting this part in its own method!! *********************
        ArrayList<String> accountNameList = new ArrayList<String>();
        try {
            File myFile = new File("ListOfCustomers.txt");
            Scanner fileScanner = new Scanner(myFile);
            String currentLine = fileScanner.nextLine();
            String[] splitCurrentLine = currentLine.split(",");
            for (String name : splitCurrentLine) {
                accountNameList.add(name);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        for (String name : accountNameList) {
            Customer myCustomer = new Customer(name + ".txt", name);
            //If this gives problems, use below!!
//            myBank.addCustomer(myCustomer);
            myBank.getCustomerList().add(myCustomer);
        }
        //*****************************************************************************
        myBank.printInfo();
        System.out.println();

        Scanner myScanner = new Scanner(System.in);
        System.out.print("Welcome to the bank. What is your first name? ");
        String userName = myScanner.nextLine();
        Customer myCustomer = new Customer(userName);
        //Still have to check if already a customer here!!
        boolean onList = false;
        for (Customer people : myBank.getCustomerList()) {
            //If their name is already on the list...
            if (myCustomer.getName().equalsIgnoreCase(people.getName())) {
                onList = true;
            }
        }
        if (!onList) {
            //Add customer to bank with accounts!
            System.out.print("Welcome new customer! How many accounts do you have? ");
            int userNumAccounts = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("Entering info for " + userNumAccounts + " accounts...");
            //*********** put in method *****************************************
            for (int counter = 0; counter < userNumAccounts; counter++) {
                System.out.println("Account " + (counter + 1) + ":");
                BankAccount thisAccount;
                while (true) {
                    System.out.println("What type of account is this account?");
                    System.out.println(" 1. Checking");
                    System.out.println(" 2. Savings");
                    System.out.println(" 3. Retirement");
                    int typeAcct = myScanner.nextInt();
                    myScanner.nextLine();
                    if (typeAcct == 1) {
                        thisAccount = new CheckingAccount();
                        thisAccount.setName("Checking");
                        break;
                    } else if (typeAcct == 2) {
                        thisAccount = new SavingsAccount();
                        thisAccount.setName("Savings");
                        break;
                    } else if (typeAcct == 3) {
                        thisAccount = new RetirementAccount();
                        thisAccount.setName("Retirement");
                        break;
                    } else {
                        System.out.println("Not valid.");
                    }
                }
                System.out.print("What is the balance of this account? ");
                double thisBalance = myScanner.nextDouble();
                thisAccount.setBalance(thisBalance);
                myScanner.nextLine();
                // Link account to customer
                myCustomer.addBankAccount(thisAccount);
            }
            // Add customer to bank
            myBank.getCustomerList().add(myCustomer);
//            System.out.println("Printing customer info........................");
//            myCustomer.printInfo();
//            myBank.printInfo();
            //*****************************************************************************

        } else {
            System.out.println("Welcome back, " + userName + "!");
        }
        //Need to get the right customer from list
        for (Customer customer : myBank.getCustomerList()) {
            if (customer.getName().equals(userName)) {
                myCustomer = customer;
            }
        }
        boolean keepGoing = true;
        //************** Display menu *******************************************
        while (keepGoing) {
            System.out.println("Which account would you like to use?");
            int counter = 1;
            for (BankAccount account : myCustomer.getCustomerListOfAccounts()) {
                System.out.println(" " + counter + ". " + account.getName() + " (Balance: " + account.getBalance() + ")");
                counter++;
            }
            int acctChoiceInt = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("You chose account " + acctChoiceInt);
            System.out.println();

            //bankaccount they chose: myCustomer.getCustomerListOfAccounts().get(acctChoiceInt)
            //CHECK IF THIS WILL UPDATE USER'S ACCOUNT THO
            BankAccount acctChoice = myCustomer.getCustomerListOfAccounts().get(acctChoiceInt - 1);

            //#################### Action method ###############################################
            while (true) {
                System.out.println("What would you like to do?");
                System.out.println(" 1. Withdraw");
                System.out.println(" 2. Deposit");
                System.out.println(" 3. Transfer");
                System.out.println(" 4. Print account info");
                System.out.println(" 5. Print bank info");
                System.out.println(" 6. Select another account");
                System.out.println(" 7. Exit");

                int userChoseToDo = myScanner.nextInt();
                myScanner.nextLine();

                if (userChoseToDo == 1) {
                    System.out.println("Current balance: " + acctChoice.getBalance());
                    System.out.print("How much would you like to withdraw? ");
                    double withdrawAmount = myScanner.nextDouble();
                    myScanner.nextLine();
                    double newBal = acctChoice.withdraw(withdrawAmount);
                    System.out.println("New balance: " + newBal);
                    System.out.println();
                } else if (userChoseToDo == 2) {
                    System.out.println("Current balance: " + acctChoice.getBalance());
                    System.out.print("How much would you like to deposit? ");
                    double depositAmount = myScanner.nextDouble();
                    myScanner.nextLine();
                    double newBal = acctChoice.deposit(depositAmount);
                    System.out.println("New balance: " + newBal);
                    System.out.println();
                } else if (userChoseToDo == 3) {
                    System.out.println("Current balance: " + acctChoice.getBalance());
                    System.out.print("How much would you like to transfer from this account? ");
                    Double transferAmount = myScanner.nextDouble();
                    myScanner.nextLine();
                    System.out.println("To which account would you like to transfer the money?");
                    int counter2 = 1;
                    for (BankAccount account : myCustomer.getCustomerListOfAccounts()) {
                        System.out.println(" " + counter2 + ". " + account.getName());
                        counter2++;
                    }
                    int transferChoiceInt = myScanner.nextInt();
                    myScanner.nextLine();
                    //(myCustomer.getCustomerListOfAccounts().get(transferChoiceInt - 1) is the account to transfer to
                    System.out.println("You chose to transfer to account " + transferChoiceInt + ", which is a " + (myCustomer.getCustomerListOfAccounts().get(transferChoiceInt - 1).getName() + " account."));
                    System.out.println("Current balance in this account: " + (myCustomer.getCustomerListOfAccounts().get(transferChoiceInt - 1).getBalance()));
                    System.out.println("Transfer successful!\nNew balance in account from which you transferred: " + acctChoice.transfer(transferAmount, myCustomer.getCustomerListOfAccounts().get(transferChoiceInt - 1)));
                    System.out.println("New balance in account to which you transferred: " + (myCustomer.getCustomerListOfAccounts().get(transferChoiceInt - 1).getBalance()));
                    System.out.println();
                } else if (userChoseToDo == 4) {
                    acctChoice.printInfo();
                } else if (userChoseToDo == 5) {
                    myBank.printInfo();
                } else if (userChoseToDo == 6) {
                    //write to file here-- individual customer account info
                    myCustomer.customerListOfAccountsToFile();
                    break;
                } else if (userChoseToDo == 7) {
                    //write to file here-- individual customer account info
                    myCustomer.customerListOfAccountsToFile();
                    //write to file here-- customer list in whole bank
                    myBank.customerListToFile();
                    keepGoing = false;
                    break;
                } else {
                    System.out.println("Invalid. Try again.");
                }
            }
            //##############################################################################################################
        }
        //******************************************************************************************************************
    }

    public void testTransferMethod() {
        Bank myBank = new Bank("Practice Bank");
        Customer testCustomer = new Customer("Bob");
        BankAccount bankAccount1 = new SavingsAccount();
        bankAccount1.setName("Checking");
        bankAccount1.setBalance(400);
        testCustomer.addBankAccount(bankAccount1);
        BankAccount bankAccount2 = new CheckingAccount();
        bankAccount2.setName("Checking");
        bankAccount2.setBalance(100);
        testCustomer.addBankAccount(bankAccount2);
        myBank.getCustomerList().add(testCustomer);

        double newBal = bankAccount2.transfer(100, bankAccount1);
        System.out.println("bankAccount2 should have 0: " + newBal);
        System.out.println("bankAccount2 should have 0: " + testCustomer.getCustomerListOfAccounts().get(1).getBalance());
        System.out.println("bankAccount1 should have 500: " + bankAccount1.getBalance());
        System.out.println("bankAccount1 should have 500: " + testCustomer.getCustomerListOfAccounts().get(0).getBalance());
        }

    public void userInputToFileHardCode() {
        FileWriter listOfCustomersWriter = null;
        try {
            File listOfCustomersFile = new File("listOfCustomers.txt");
            listOfCustomersWriter = new FileWriter(listOfCustomersFile);
            listOfCustomersWriter.write("Jessica,");
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

/*        FileWriter customerAccountListWriter = null;
        try {
            File customerAccountList = new File("jessicaAccounts.txt");
            customerAccountListWriter = new FileWriter(customerAccountList);
            customerAccountListWriter.write("account.name=Checking\naccount.balance=100.00\naccount.name=Savings\naccount.balance=200.00");
        } catch (Exception exception) {
            System.out.println("Exception caught...");
            exception.printStackTrace();
        } finally {
            if (customerAccountListWriter != null) {
                try {
                    customerAccountListWriter.close();
                } catch (Exception ex) {
                    System.out.println("Exception caught...");
                    ex.printStackTrace();
                }
            }
        }
 */
    }

    public void createBankCustomersFromFile() {
//        Bank myBank = new Bank("listOfCustomers.txt", "Wells Fargo");
//        myBank.printInfo();
        Bank myBank = new Bank();
        myBank.setName("Wells Fargo");

        Customer myCustomer = new Customer("jessicaAccounts.txt", "Jessica");
//        myCustomer.printInfo();

        myBank.addCustomer(myCustomer);
        myBank.printInfo();
    }
}
