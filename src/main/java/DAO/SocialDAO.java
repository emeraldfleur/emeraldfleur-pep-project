package DAO;
import Model.Account;
import Util.ConnectionUtil;
import java.sql.*; 

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