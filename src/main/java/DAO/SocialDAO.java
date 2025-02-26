package DAO;
import Model.Account;

public class SocialDAO {
    public static boolean accountExists(Account account)
    {
        return true;
    }
    public static Account createAccount(Account account)
    {
        return account;
    }
    public static boolean credentialsMatchExisting(Account account) 
    {
        //Checks if provided account username and    
        //password match an existing one
        return true;

    }
    public static Account retrieveAccountbyUserID(int userID)
    {
        Account bob = new Account();
        return bob;
    }
}



