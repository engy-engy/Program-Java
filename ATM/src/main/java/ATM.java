import java.util.Scanner;
public class ATM {
    public static void main(String[] args) {
        //init scanner
        Scanner sc = new Scanner(System.in);

        // init bank
        Bank theBank = new Bank("Bank of Drausin");

        //add user, with also creates a saving account
        User aUser = theBank.addAccount("John","Doe","1234");

        //add a checking account for our user
        Account newAccount = new Account("Checking",aUser,theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true){
            //stay in the login promt until successful login
            curUser = ATM.userMainMenuPromt(theBank,sc);

            //stay in main menu until user quits
            ATM.printUserMenu(curUser,sc);
        }
    }
    /**  Print the ATM login menu
     * @param theBank
     * @param sc */
    public static User userMainMenuPromt(Bank theBank, Scanner sc){
        //inits
        String userID;
        String pin;
        User authUser;

        //promt the user for user ID/PIN combo until a correct one reached
        do {
            System.out.printf("\n\nWelcome to %s \n\n",theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            //try to get  the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID,pin);
            if (authUser == null){
                System.out.println("Incorrect user ID/pin combination. " +
                        "Please try again. ");

            }
        }while(authUser == null); // continue looping until successful login
         return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc){
        // print a summary of the user accounts
        theUser.printAccountSummary();

        // init
        int choice;

        //user menu
        do {
            System.out.printf("Welcome %s, what would you like to do? \n",
            theUser.getFirstName());
            System.out.println();
            System.out.println("  1) Show account  transaction history ");
            System.out.println("  2) Withdraw ");
            System.out.println("  3) Deposit ");
            System.out.println("  4) Transfer ");
            System.out.println("  5) Quit ");
            System.out.println();
            System.out.println("Enter choice: ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5){
                System.out.println("Invalid choice. Please choice 1-5");
            }
        }while (choice < 1 || choice > 5 );

        //process the choice
        switch (choice){
            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transferFunds(theUser, sc);
                break;
            case 5:
                //double up rest of previous input
                sc.nextLine();
                break;
        }

        //redispaly this menu unless the user wants to quit
        if (choice !=5){
            ATM.printUserMenu(theUser,sc);
        }
    }
    /** show in transaction history for a account
     * @param theUser
     * @param sc */
    public static void showTransHistory(User theUser, Scanner sc){
        int theAcct;

        //get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account" +
                    "whose transaction you want to see:" ,
                    theUser.numAccounts());
            theAcct = sc.nextInt()-1;
            if (theAcct < 0 || theAcct >=theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        }while (theAcct < 0 || theAcct >= theUser.numAccounts());

        // print the transaction history
        theUser.printAccTransHistory(theAcct);

    }
    /** process transferring funds from one account to another
     * @param sc
     * @param theUser */
    public static void transferFunds(User theUser, Scanner sc){
        //init
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        // get the account transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account \n"+
                    "to deposit in: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        }while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        //get the account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account \n"+
                    "to transfer to: ",theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        }while (toAcct < 0 || toAcct >= theUser.numAccounts());

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to withdraw (max $%.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater that zero.");
            }else if (amount > acctBal){
                System.out.printf("Amount must be greater that \n." +
                        "balance of $%.02f\n" + acctBal) ;
            }
        }while (amount < 0 || amount > acctBal);

        theUser.addAcctTransaction(fromAcct, -1*amount,
                String.format("Transfer to account %s",theUser.getAcctUUID(toAcct)));
        theUser.addAcctTransaction(toAcct, amount,
                String.format("Transfer to account %s",theUser.getAcctUUID(fromAcct)));
    }

    /** process a fund withdraw from an account
     * @param theUser
     * @param sc */
    public static void withdrawFunds(User theUser, Scanner sc){
        //init
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        // get the account transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account \n"+
                    "to withdraw from: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        }while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater that zero.");
            }else if (amount > acctBal){
                System.out.printf("Amount must be greater that \n." +
                        "balance of $%.02f\n" , acctBal) ;
            }
        }while (amount < 0 || amount > acctBal);

        // gobble up rest of previous input
        sc.nextLine();

        // get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        // do the withdraw
        theUser.addAcctTransaction(fromAcct, -1*amount,memo);

    }
    /** process a fund deposit to an account
     * @param sc
     * @param theUser */
    public static void depositFunds(User theUser,Scanner sc){
        //init
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        // get the account transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account \n"+
                    "to deposit in: ", theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        }while (toAcct < 0 || toAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(toAcct);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater that zero.");
            }
        }while (amount < 0 );

        // gobble up rest of previous input
        sc.nextLine();

        // get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        // do the withdraw
        theUser.addAcctTransaction(toAcct, amount,memo);
    }

}