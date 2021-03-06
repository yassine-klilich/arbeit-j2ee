
package DAO;

import MODEL.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoUser extends DbConnector{
    
    public static User create(User user)
    {
        String sql = "INSERT INTO users (full_name, user_name, email, password, is_admin ) VALUES (?, ?, ?, ?, ?)";
        Connection connection = DbConnector.getDbConnection();
         
        try 
        {   PreparedStatement Pst= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Pst.setString(1, user.getFull_name());
            Pst.setString(2, user.getUser_name());
            Pst.setString(3, user.getEmail());
            Pst.setString(4, user.getPassword());
            Pst.setBoolean(5, user.getIs_admin());
            Pst.executeUpdate();
            
            ResultSet rs = Pst.getGeneratedKeys();
            if (rs.next()) {
               int id = rs.getInt(1);
               user.setId(id);
               return user;
            }
            System.out.println("Added sucess");
        } 
        catch (SQLException e) 
        {
            System.err.println("Erreur dans Create"+user.getUser_name());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }   
    
    
    public static List<User> getAll()
    {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users ;";
        Connection connection = DbConnector.getDbConnection();
        Statement statement;
        ResultSet resultSet;
        User user;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) 
            {
                user = new User();
                user.setId(resultSet.getInt("id"));
                 user.setFull_name(resultSet.getString("full_name"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIs_admin(resultSet.getBoolean("is_admin"));
                
//user.setCreated_at(resultSet.getString("created_at"));
               
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    
    }
 
    public static User getuser(int iduser) {
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection connection = DbConnector.getDbConnection();
        ResultSet resultSet;
        User user = null;
        try {
             PreparedStatement Pst= connection.prepareStatement(sql);
            Pst.setInt(1, iduser);
            resultSet = Pst.executeQuery();
            if (resultSet.first()==true)
            {
                System.out.println(" user found ");
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFull_name(resultSet.getString("full_name"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIs_admin(resultSet.getBoolean("is_admin"));
                System.out.println(resultSet.getString("created_at"));
                //user.setCreated_at(resultSet.getString("created_at"));

                return user;
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public static User updateUser(User user) {
        //String sql = " UPDATE users set  full_name=?, user_name=?, email=?, password=?, is_admin=? WHERE id =?";
        Connection connection = DbConnector.getDbConnection();
        PreparedStatement Pstatement;
        try {
            String sql = "call sp_updateUser(?, ?, ?, ?, ?, ?)";
            Pstatement = connection.prepareStatement(sql);
            Pstatement.setInt(1, user.getId());
            Pstatement.setString(2, user.getFull_name());
            Pstatement.setString(3, user.getUser_name());
            Pstatement.setString(4, user.getEmail());
            Pstatement.setString(5, user.getPassword());
            Pstatement.setBoolean(6, user.getIs_admin());
            Pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getuser(user.getId());
    }

    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?;";
        Connection connection = DbConnector.getDbConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static User checkAuth(String username, String passowrd) {
        String sql = "SELECT * FROM users WHERE user_name = ? AND password=?";
        Connection connection = DbConnector.getDbConnection();
        ResultSet resultSet;
        User user = new User();
        try {
            PreparedStatement Pst = connection.prepareStatement(sql);
            Pst.setString(1, username);
            Pst.setString(2, passowrd);
            resultSet = Pst.executeQuery();
            if (resultSet.first()==true)
            {
                user.setId(resultSet.getInt("id"));
                user.setFull_name(resultSet.getString("full_name"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setIs_admin(resultSet.getBoolean("is_admin"));
                return user;
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int nomberOfUsers(){
        String sql = "select count(*) as total from users";
        Statement statement;
        Connection connection = DbConnector.getDbConnection();
        ResultSet resultSet;
        int nbr;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
           if (resultSet.first()==true)
            {
                nbr=resultSet.getInt("total");
                return nbr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
}
