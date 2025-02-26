package Service;

import Model.Account;
import Model.Message;
import java.util.ArrayList;
import java.util.List;

public class AccountService
{
    public Account registerNewAccount(Account account)
    {
        return account;
    }
    public Account tryLogin(Account account)
    {
        return account;
    }
    public Message postMessage(Message message)
    {
        return message;
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



