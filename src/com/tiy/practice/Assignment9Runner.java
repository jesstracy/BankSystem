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
//        myRunner.testBank();
        myRunner.runProgram(myRunner);
    }

    public void runProgram(Assignment9Runner myRunner) {
        Bank myBank = new Bank("Wells Fargo");

        //Read in customer list from file (if exists) and create customer accounts
        myRunner.storeFileInfoInBank(myBank);

        Scanner myScanner = new Scanner(System.in);
        System.out.print("Welcome to the bank. What is your first name? ");
        String userName = myScanner.nextLine();
        Customer myCustomer = new Customer(userName);

        //Check if the user is already a customer!
        boolean onList = checkIfCustomerIsOnlist(myBank, myCustomer);

        //If the user is a new customer...
        if (!onList) {
            //See how many accounts the user wants to enter
            System.out.print("Welcome new customer! How many accounts do you have? ");
            int userNumAccounts = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("Entering info for " + userNumAccounts + " accounts...");

            //Get info for all customer's accounts, and link these account to customer
            for (int counter = 0; counter < userNumAccounts; counter++) {
                System.out.println("Account " + (counter + 1) + ":");
                makeNewAccountWithUserInput(myCustomer);
            }

            // Add customer to bank
            myBank.addCustomer(myCustomer);

        } else { //If the customer is already on the Bank's customerList...
            System.out.println("Welcome back, " + userName + "!");
        }

        //Get the right customer from Bank's customerList
        for (Customer customer : myBank.getCustomerList()) {
            if (customer.getName().equals(userName)) {
                myCustomer = customer;
            }
        }

        boolean keepGoing = true;
        //************** Display menu *******************************************
        while (keepGoing) {
            int acctChoiceInt = userChooseWhichAccountToUse(myCustomer, myScanner);
            //If they chose to make a new account, make new account and then again let them choose which to use
            while (acctChoiceInt == 0) {
                makeNewAccountWithUserInput(myCustomer);
                acctChoiceInt = userChooseWhichAccountToUse(myCustomer, myScanner);
            }
            BankAccount acctChoice = myCustomer.getCustomerListOfAccounts().get(acctChoiceInt - 1);

            //#################### Action method ###############################################
            //Ask user what they would like to do - display menu, and loop after.
            //Store return value to keepGoing.
            keepGoing = displayAccountActionsMenu(acctChoice, myCustomer, myBank);
            //##################################################################################
            if (!keepGoing) {
                System.exit(0);
            }

           // Try to turn off all savings and retirement threads
//            if (!keepGoing) {
//                for (Customer customer : myBank.getCustomerList()) {
//                    for (BankAccount account : customer.getCustomerListOfAccounts()) {
//                        if (account.getName().equals("Savings")) {
//                            SavingsAccount accountS = (SavingsAccount)account;
//                            accountS.setThreadsKeepRunning(false);
//                        }
//                        if (account.getName().equals("Retirement")) {
//                            RetirementAccount accountR = (RetirementAccount)account;
//                            accountR.setThreadsKeepRunning(false);
//                        }
//                    }
//                }
//            }
        }
        //******************************************************************************************************************
    }

    public boolean checkIfCustomerIsOnlist(Bank myBank, Customer myCustomer) {
        for (Customer people : myBank.getCustomerList()) {
            //If their name is already on the list...
            if (myCustomer.getName().equalsIgnoreCase(people.getName())) {
                return true;
            }
        }
        return false;
    }

    public void storeFileInfoInBank(Bank myBank) {
        ArrayList<String> accountNameList = new ArrayList<String>();
//
        File myFile = new File("ListOfCustomers.txt");
        if (myFile.exists()) {
            try {
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
                myBank.addCustomer(myCustomer);
            }
        }
    }

    public void makeNewAccountWithUserInput(Customer myCustomer) {
        Scanner myScanner = new Scanner(System.in);
        boolean threadsKeepRunning = true;

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
                break;
            } else if (typeAcct == 2) {
                thisAccount = new SavingsAccount();
                // *** new *****
                SavingsAccount myAccountS = new SavingsAccount(threadsKeepRunning);
                myAccountS = (SavingsAccount)thisAccount;
                Thread savingsThread = new Thread(myAccountS);
                thisAccount = myAccountS;
                savingsThread.start();
                break;
            } else if (typeAcct == 3) {
                thisAccount = new RetirementAccount();
                // ** new *****
                RetirementAccount myAccountR = new RetirementAccount(threadsKeepRunning);
                myAccountR = (RetirementAccount)thisAccount;
                Thread retirementThread = new Thread(myAccountR);
                thisAccount = myAccountR;
                retirementThread.start();
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


    public int userChooseWhichAccountToUse(Customer myCustomer, Scanner myScanner) {
        System.out.println("Which account would you like to use?");
        int counter = 1;
        for (BankAccount account : myCustomer.getCustomerListOfAccounts()) {
            System.out.println(" " + counter + ". " + account.getName() + " (Balance: " + account.getBalance() + ")");
            counter++;
        }
        System.out.println(" 0. Add a new account");
        int acctChoiceInt = myScanner.nextInt();
        myScanner.nextLine();

        return acctChoiceInt;
    }

    public int askUserWhatAccountActionToDo(Scanner myScanner) {
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

        return userChoseToDo;
    }

    public boolean displayAccountActionsMenu(BankAccount acctChoice, Customer myCustomer, Bank myBank) {
        Scanner myScanner = new Scanner(System.in);
        while (true) {
            int userChoseToDo = askUserWhatAccountActionToDo(myScanner);

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
                return false;
//                break;
            } else {
                System.out.println("Invalid. Try again.");
            }
        }
        return true;
    }
}
