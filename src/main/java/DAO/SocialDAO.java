package DAO;
import Model.Account;
import Util.ConnectionUtil;
import java.sql.*; 

public class SocialDAO {
    public static boolean accountExists(Account account)
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
    public static Account retrieveAccountbyUserID(String username)
    {
        Account bob = new Account();
        return bob;
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