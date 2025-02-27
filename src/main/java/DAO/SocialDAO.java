package DAO;
import Model.Account;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*; 
import java.util.List;
import java.util.ArrayList;

public class SocialDAO {
    public static boolean accountExists(Account account) //ONLY checks if account that matches username exists
    {
        Connection connection = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT * FROM Account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean accountExists(String username) //ONLY checks if account that matches username exists
    {
        Connection connection = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT * FROM Account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static Account createAccount(Account account) throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
        try 
        {
            String sql = "INSERT INTO Account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2,account.password);
            ResultSet rs = preparedStatement.executeQuery();

            String sql2 = "SELECT * FROM Account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setString(1, account.username);
            preparedStatement2.setString(2, account.password);
            
            ResultSet rs2 = preparedStatement.executeQuery();
            int userID = rs2.getInt(1);
            String newUsername = rs2.getString(2);
            String password = rs2.getString(3);
            Account returnAccount = new Account(userID, newUsername, password);
            return returnAccount;
        }
        catch(SQLException e)
        {
            throw new Exception();
        }
    }
    public static boolean credentialsMatchExisting(Account account) 
    {
        
        Connection connection = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;

    }
    public static Account retrieveAccountbyUserID(String username) throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT * FROM Account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            int userID = rs.getInt(1);
            String newUsername = rs.getString(2);
            String password = rs.getString(3);
            Account returnAccount = new Account(userID, newUsername, password);
            return returnAccount;
        }
        catch(SQLException e)
        {
            throw new Exception();
        }
    }
    public static Message postMessage(Message message) throws Exception
    {
        String username = Integer.toString(message.getPosted_by());
        if(accountExists(username))
        {
            Connection connection = ConnectionUtil.getConnection();
            try 
            {
                String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, message.posted_by);
                preparedStatement.setString(2,message.message_text);
                preparedStatement.setLong(3,message.time_posted_epoch);
                preparedStatement.executeQuery(); //Don't need to store results for first execution.

                String sql2 = "SELECT * FROM Message WHERE message_text = ?;";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setString(1, message.message_text);
                ResultSet rs2 = preparedStatement2.executeQuery();

                int message_id = rs2.getInt(1);
                int posted_by = rs2.getInt(2);
                String message_text = rs2.getString(3);
                Long time_posted_epoch = rs2.getLong(4);
                Message returnMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);
                return returnMessage;
             }
             catch(SQLException e)
            {
                throw new Exception();
            }
        }
        else
        {
            throw new Exception();
        }
    }

    public static List<Message> pullMessages() throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
            try 
            {
                String sql2 = "SELECT * FROM Message;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                ResultSet rs2 = preparedStatement.executeQuery();
                List<Message> messageList = new ArrayList<>();
                while(rs.next())
                {
                    int message_id = rs2.getInt(1);
                    int posted_by = rs2.getInt(2);
                    String message_text = rs2.getString(3);
                    Long time_posted_epoch = rs2.getLong(4);
                    Message returnMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);
                    messageList.add(returnMessage);
                }
                
                return messageList;
             }
             catch(SQLException e)
            {
                throw new Exception();
            }
    }
}



/*
 * 
 * Connection connection = ConnectionUtil.getConnection();
        List<Author> authors = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Author;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Author author = new Author(rs.getInt("id"), rs.getString("name"));
                authors.add(author);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return authors;
    }
 */