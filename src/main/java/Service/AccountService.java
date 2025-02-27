package Service;

import Model.Account;
import Model.Message;
import java.util.ArrayList;
import java.util.List;
import DAO.SocialDAO;

public class AccountService
{
    public Account registerNewAccount(Account account) throws Exception
    {
        //Check if account exists first.
        if(SocialDAO.accountExists(account))
        {
            throw new Exception();
        }
        else //if not, create account, return it.
        {
            return SocialDAO.createAccount(account);
        }

    }
    public Account tryLogin(Account account) throws Exception
    {
        if(SocialDAO.accountExists(account)) //Check if account exists. If so, move on, else exception.
        {
            if(SocialDAO.credentialsMatchExisting(account)) //If account matches one in the database, move on. else, exception.
            {
                return SocialDAO.retrieveAccountbyUserID(account.username); //return the account matching userID. 
            }
            else
            {
                throw new Exception();   
            }
        }
        else //if not, throw exception
        {
            throw new Exception();
        }
    }
    public Message postMessage(Message message) throws Exception
    {
        if ((message.toString()).length() > 255)
        {
            throw new Exception();
        }
        else
        {
            return SocialDAO.postMessage(message);
        }
    }
    public List<String> getAllMessages()
    {
        List<String> bob = new ArrayList<String>();
        return bob;
    }
    public Message getIDMessage(int userID)
    {
        Message bob = new Message();
        return bob;
    }
    public List<String> getMessagesOfUser(int userID)
    {
        List<String> bob = new ArrayList<String>();
        return bob;
    }
    public Message deleteIDMessage(int messageID)
    {
        Message bob = new Message();
        return bob;
    }
    public Message updateMessage(int messageID, Message newMessage)
    {
        Message bob = new Message();
        return bob;
    }
}



