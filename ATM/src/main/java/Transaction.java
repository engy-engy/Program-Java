import java.util.Date;
public class Transaction {
    /** The amount of this transaction */
    private double amount;
    /** The time and date of this transaction  */
    private Date timestamp;
    /** The memo for this transaction  */
    private String memo;
    /** The account in with the transaction was performer   */
    private Account inAccount;
    /** Create a new transaction
     * @param amount the amount transaction
     * @param inAccount the account the transaction belongs to*/
    public Transaction(double amount ,Account inAccount){
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";

    }
    /** Create a new transaction
     * @param amount the amount transaction
     * @param memo the memo for transaction
     * @param inAccount  the account the transaction to*/
    public Transaction(double amount, String memo,  Account inAccount){
        // call the two-arg constructor first
        this(amount,inAccount);
        //set the memo
        this.memo = memo;
    }
    /** get the amount of the transaction
     * @return */
    public double getAmount(){
        return this.amount;
    }
    /** get a string summarizing the transaction
     * @return */
    public String getSummaryLine(){
        if (this.amount >= 0){
            return String.format("%s : $%.02f : %s" ,
                    this.timestamp.toString(),
                    this .amount , this.memo);
        }else{
            return String.format("%s : $(%.02f) : %s" ,
                    this.timestamp.toString(),
                    -this .amount , this.memo);
        }
    }


}
