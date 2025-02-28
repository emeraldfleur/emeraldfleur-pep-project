package Service;

import Model.Account;
import Model.Message;
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
            if(account.username.length() < 1 || account.password.length() < 4)
            {
                throw new Exception();
            }
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
        if ((message.message_text.length() > 255) || (message.message_text.length() < 1)) //Checks not too long
        {
            throw new Exception();
        }
        else
        {
            return SocialDAO.postMessage(message);
        }
    }
    public List<Message> getAllMessages() throws Exception
    {
        List<Message> bob = SocialDAO.pullMessages();
        return bob;
    }
    public Message getIDMessage(int messageID) throws Exception
    {
        Message bob = SocialDAO.retrieveMessagebyMessageID(messageID);
        return bob;
    }
    public List<Message> getMessagesOfUser(int userID) throws Exception
    {
        List<Message> bob = SocialDAO.pullMessages(userID);
        return bob;
    }
    public Message deleteIDMessage(int messageID) throws Exception
    {
        Message bob = SocialDAO.deleteMessagebyID(messageID);
        return bob;
    }
    public Message updateMessage(int messageID, Message newMessage) throws Exception
    {
        if((newMessage.getMessage_text()).length() <= 255 && (newMessage.getMessage_text()).length() > 1) //if not too long
        {
            return SocialDAO.patchMessage(messageID, newMessage);
        }
        else
        {
            throw new Exception();
        }
    }
}



