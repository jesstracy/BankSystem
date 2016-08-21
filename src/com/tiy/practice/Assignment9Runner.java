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
        //Maybe don't add customers in bank constructor. Just put names in array.
        Bank myBank = new Bank("Wells Fargo");

        //Read in customer list from file (if exists) and create customer accounts
        myRunner.storeFileInfoInBank(myBank);

        //Take out once I know it's working
//        myBank.printInfo();
//        System.out.println();

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

            //**Get info for all customer's accounts, and link these account to customer***
            for (int counter = 0; counter < userNumAccounts; counter++) {
                System.out.println("Account " + (counter + 1) + ":");
                myRunner.takeNewAccountInfo(myCustomer);
            }
            //*****************************************************************************

            // Add customer to bank
            myBank.getCustomerList().add(myCustomer);
//            System.out.println("Printing customer info........................");
//            myCustomer.printInfo();
//            myBank.printInfo();

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
//        myRunner.displayAccountChoiceMenu();
        while (keepGoing) {
            System.out.println("Which account would you like to use?");
            int counter = 1;
            for (BankAccount account : myCustomer.getCustomerListOfAccounts()) {
                System.out.println(" " + counter + ". " + account.getName() + " (Balance: " + account.getBalance() + ")");
                counter++;
            }
            System.out.println(" 0. Add a new account");
            int acctChoiceInt = myScanner.nextInt();
            myScanner.nextLine();

            boolean makeAccountFlag;
            if (acctChoiceInt == 0) {
                makeAccountFlag = true;
            } else {
                makeAccountFlag = false;
            }
            while (makeAccountFlag) {
                myRunner.takeNewAccountInfo(myCustomer);

                //Ask again which account they would like to use. While
                System.out.println("Which account would you like to use?");
                int counterInLoop = 1;
                for (BankAccount account : myCustomer.getCustomerListOfAccounts()) {
                    System.out.println(" " + counterInLoop + ". " + account.getName() + " (Balance: " + account.getBalance() + ")");
                    counterInLoop++;
                }
                System.out.println(" 0. Add a new account");
                acctChoiceInt = myScanner.nextInt();
                myScanner.nextLine();

                if(acctChoiceInt > 0) {
                    makeAccountFlag = false;
                }
            }


            //bankaccount they chose: myCustomer.getCustomerListOfAccounts().get(acctChoiceInt)
            //CHECK IF THIS WILL UPDATE USER'S ACCOUNT THO
            BankAccount acctChoice = myCustomer.getCustomerListOfAccounts().get(acctChoiceInt - 1);


            //#################### Action method ###############################################
            //Ask user what they would like to do - display menu, and loop after.
            //Store return value to keepGoing.
            keepGoing = myRunner.displayAccountActionsMenu(acctChoice, myCustomer, myBank);
            //##################################################################################
        }
        //******************************************************************************************************************
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
                //If this gives problems, use below!!
//            myBank.addCustomer(myCustomer);
                myBank.getCustomerList().add(myCustomer);
            }
        }
    }

    public void takeNewAccountInfo(Customer myCustomer) {
        Scanner myScanner = new Scanner(System.in);
//        for (int counter = 0; counter < userNumAccounts; counter++) {
//            System.out.println("Account " + (counter + 1) + ":");
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
                break;
            } else if (typeAcct == 3) {
                thisAccount = new RetirementAccount();
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

    public boolean displayAccountActionsMenu(BankAccount acctChoice, Customer myCustomer, Bank myBank) {
        Scanner myScanner = new Scanner(System.in);
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
                return false;
//                break;
            } else {
                System.out.println("Invalid. Try again.");
            }
        }
        return true;
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
