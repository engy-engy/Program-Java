import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
public class User {
    /** The first name of the user */
    private String firstName;
    /** The last name of the user */
    private String lastName;
    /** The ID number the user */
    private String uuid;
    /** The MD5 hash of the users pin number */
    private byte pinHash[];
    /** The list of accounts for this user */
    private ArrayList<Account> accounts;

    /**
     * Create a new user@param firstName
     * @param lastName
     * @param pin
     * @param theBank */

    public User(String firstName,String lastName,String pin,Bank theBank){
        //Set users name
        this.firstName = firstName;
        this.lastName = lastName;

        //  store the pin MD5 hash rather that the original value for security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgoritmException");
            e.printStackTrace();
            System.exit(1);

        }

        // get a new uuid universal Id for the user
        this.uuid = theBank.getNewUserUUID();

        //create empty of accounts
        this.accounts = new ArrayList<Account>();

        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n " , lastName , firstName , this.uuid);
    }
    /** add an account for the user
     * @param anAcct the account to add */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
    /** return the user UUID
     * @return the uuid */
    public String getUUID(){
        return this.uuid;
    }
    /** Cheack whether a given pin matches the true  user pin
     * @param aPin
     * @return */
    public boolean validatePin(String aPin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return  MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }
    /** retern the user first name
     * @return */
    public String getFirstName(){
        return this.firstName;

    }
    /** print summaries for the accounts of this user*/
    public void printAccountSummary(){
        System.out.printf("\n\n%s's accounts summary \n" , this.firstName);
        for (int a = 0; a < this.accounts.size();a++){
            System.out.printf("  %d) %s\n" , a+1 ,
                    this.accounts.get(a).getSummaryLine());
        }
    }
    /** get the number accounts
     * @return */
    public int numAccounts(){
        return this.accounts.size();
    }

    /** print transaction history
     * @param accIdx */
    public void printAccTransHistory(int accIdx){
        this.accounts.get(accIdx).printTransHistory();
    }

    /** get the balance of a particular account
     * @return */
    public double getAcctBalance(int acctIdx){
        return this.accounts.get(acctIdx).getBalance();
    }
    /** Get the UUID of a particular account
     * @param acctIdx
     * @return */
    public String getAcctUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUUID();
    }
    /** add transaction to a particular account
     * @param acctIdx
     * @param memo
     * @param amount */
    public void addAcctTransaction(int acctIdx, double amount, String memo){
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
