import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    /**Create a new bank object with empty lists of users and account */
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /** Generate a new universally unique ID for a user
     * @return the uuid*/
    public String getNewUserUUID(){
        //init
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        //continuq looping until we get a unique ID
        do {
            //Generate the number
            uuid = "";
            for (int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            // check to make sure it unique
            nonUnique = false;
            for(User u : this.users){
                if (uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }
    /** get a new universally unique ID an account
     * @return the uuid */
    public String getNewAccountUUID(){
        //init
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        //continuq looping until we get a unique ID
        do {
            //Generate the number
            uuid = "";
            for (int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();

            }
            // check to make sure it unique
            nonUnique = false;
            for(Account a : this.accounts){
                if (uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        System.out.println(uuid);
        return uuid;
    }

    /** Create a new user of the bank
     * @param firsName
     * @param lastName
     * @param pin */
    public User addAccount(String firsName, String lastName, String pin){
        // create a new user object and add it our list
        User newUser = new User(firsName,lastName,pin,this);
        this.users.add(newUser);

        //create a savings account for the user and add to user and bank
        // account lists
        Account newAccount = new Account("Savings",newUser,this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);
        return newUser;
    }
    /** get the user object associated with a particular userID and pin if they are valid
     * @param pin
     * @param userID
     * @return  */
    public User userLogin(String userID, String pin){
        //search through  list of users
        for (User u : this.users){
            //check user ID is so current
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }

        //if we have fount  the user or have an incorrect pin
        return null;
    }

    /** get the name of the bank
     * @return the name of the bank
     */
    public String getName(){
        return this.name;
    }

    public void addAccount(Account newAccount) {
    }
}
