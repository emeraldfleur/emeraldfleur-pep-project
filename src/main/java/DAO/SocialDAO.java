package DAO;
import Model.Account;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*; 
import java.util.List;
import java.util.ArrayList;

public class SocialDAO { //test
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

    public static boolean accountExists(int account_id) throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT 1 FROM Account WHERE account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch(SQLException e)
        {
            return false;
        }
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
            
            preparedStatement.executeUpdate();

            String sql2 = "SELECT * FROM Account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setString(1, account.username);
            preparedStatement2.setString(2, account.password);
            
            ResultSet rs2 = preparedStatement2.executeQuery();
            
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
        int message_id_indicator = message.getPosted_by();

        if(accountExists(message_id_indicator))
        {
            Connection connection = ConnectionUtil.getConnection();
            try 
            {
                String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, message.posted_by);
                preparedStatement.setString(2,message.message_text);
                preparedStatement.setLong(3,message.time_posted_epoch);
                preparedStatement.executeUpdate(); //Don't need to store results for first execution.

                String sql2 = "SELECT * FROM Message WHERE message_text = ?;";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setString(1, message.message_text);
                ResultSet rs2 = preparedStatement2.executeQuery();

                if(rs2.next())
                {
                    int message_id = rs2.getInt(1);
                    int posted_by = rs2.getInt(2);
                    String message_text = rs2.getString(3);
                    Long time_posted_epoch = rs2.getLong(4);
                    Message returnMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);
                    return returnMessage;
                }
                else
                {
                    throw new Exception();
                }
                
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
                ResultSet rs = preparedStatement.executeQuery();
                List<Message> messageList = new ArrayList<>();
                while(rs.next())
                {
                    int message_id = rs.getInt(1);
                    int posted_by = rs.getInt(2);
                    String message_text = rs.getString(3);
                    Long time_posted_epoch = rs.getLong(4);
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

    public static List<Message> pullMessages(int userID) throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
            try 
            {
                String sql2 = "SELECT * FROM Message WHERE posted_by = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1, userID);
                ResultSet rs = preparedStatement.executeQuery();
                List<Message> messageList = new ArrayList<>();
                while(rs.next())
                {
                    int message_id = rs.getInt(1);
                    int posted_by = rs.getInt(2);
                    String message_text = rs.getString(3);
                    Long time_posted_epoch = rs.getLong(4);
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

    public static Message retrieveMessagebyMessageID(int messageID) throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
            try 
            {
                String sql2 = "SELECT * FROM Message WHERE message_id = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1, messageID);
                ResultSet rs = preparedStatement.executeQuery();

                //Build return message

                Message returnMessage = new Message();

                while(rs.next())
                {
                    int message_id = rs.getInt(1);
                    int posted_by = rs.getInt(2);
                    String message_text = rs.getString(3);
                    Long time_posted_epoch = rs.getLong(4);
                    returnMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);
                    
                }
                return returnMessage;
            }
             catch(SQLException e)
            {
                throw new Exception();
            }
    }

    public static Message deleteMessagebyID(int messageID) throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
            try 
            {
                String sql2 = "SELECT * FROM Message WHERE message_id = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1, messageID);
                ResultSet rs = preparedStatement.executeQuery();

                Message returnMessage = new Message();

                //Build return message
                if (rs.next())
                {
                    int message_id = rs.getInt(1);
                    int posted_by = rs.getInt(2);
                    String message_text = rs.getString(3);
                    Long time_posted_epoch = rs.getLong(4);
                    returnMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);
                }
                
                
                //Delete the message.

                sql2 = "DELETE FROM Message WHERE message_id = ?;";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1, messageID);
                preparedStatement.executeUpdate();

                return returnMessage;
             }
             catch(SQLException e)
            {
                throw new Exception();
            }
    }
    
    public static Message patchMessage(int messageID, Message message) throws Exception
    {
        Connection connection = ConnectionUtil.getConnection();
            try 
            {
                String sql2 = "SELECT * FROM Message WHERE message_id = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1, messageID);
                ResultSet rs = preparedStatement.executeQuery();

                //Build message to give to message poster.
                int message_id = rs.getInt(1);
                int posted_by = rs.getInt(2);
                String message_text = message.getMessage_text();
                Long time_posted_epoch = rs.getLong(4);
                Message postMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);

                
                String sql = "UPDATE  Message (posted_by, message_text, time_posted_epoch, message_id) VALUES (?, ?, ?, ?);";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
                preparedStatement2.setInt(1, posted_by);
                preparedStatement2.setString(2,message_text);
                preparedStatement2.setLong(3,time_posted_epoch);
                preparedStatement2.setInt(4, message_id);
                preparedStatement2.executeUpdate(); //Don't need to store results for first execution.
                
                return postMessage;
             }
             catch(SQLException e)
            {
                throw new Exception();
            }
    }
}
