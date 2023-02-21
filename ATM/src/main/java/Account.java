import java.util.ArrayList;
public class Account {

    /** The name of the account */
    private String name;

    /** The account the id number  */
    private String uuid;
    /** The User object that owns this account */
    private User holder;
    /** The list of transactions for this account  */
    private ArrayList<Transaction> transactions;

    /** Create a new account
     * @param name
     * @param holder
     * @param theBank*/
    public Account(String name, User holder, Bank theBank){

        //set the account name and holder
        this.name = name;
        this.holder = holder;

        //get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        //init transactions
        this.transactions = new ArrayList<Transaction>();

        // add to holder and bank lists

    }
    /** get the account ID
     * @return the uuid */
    public String  getUUID(){
        return this.uuid;
    }
    /** get summary line for the account
     * @return */
    public String getSummaryLine(){
        //get the account balance
        double balance = this.getBalance();

        //format the summary line depending of the whether the balance is negative
        if (balance>=0){
            return String.format("%s : $%.02f : %s", this.uuid,balance,
                    this.name);
        }else{
            return String.format("%s : $(%.02f) : %s", this.uuid,balance,
                    this.name);        }
    }
    /** get balance of this account by adding the amounts of the transaction
     * @return */
    public double getBalance(){
        double balance = 0;
        for(Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }
    /** print the transaction history of the account */
    public void printTransHistory(){
        System.out.printf("\nTransaction history for account %s\n" , this.uuid);
        for (int t = this.transactions.size()-1; t>=0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }
    /** add a new transaction in this account
     * @param memo
     * @param amount */
    public void addTransaction(double amount, String memo){
        // create new transaction  object and add it to our list
        Transaction newTrans = new Transaction(amount,memo,this);
        this.transactions.add(newTrans);
    }
}
