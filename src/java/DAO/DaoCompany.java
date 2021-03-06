
package DAO;

import MODEL.Company;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoCompany {
    
    public static Company create(Company company)
    {
        String sql = "INSERT INTO companies (company_name, adresse, telephone, email, contact_name, avatar) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnector.getDbConnection();

        try 
        {   PreparedStatement Pst= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Pst.setString(1, company.getCompany_name());
            Pst.setString(2, company.getAdresse());
            Pst.setString(3, company.getTelephone());
            Pst.setString(4, company.getEmail());
            Pst.setString(5, company.getContact_name());
            Pst.setString(6, company.getAvatar());
            Pst.executeUpdate();

            ResultSet rs = Pst.getGeneratedKeys();
            if (rs.next()) {
               int id = rs.getInt(1);
               company.setId(id);
               return company;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }  
    
  public static List<Company> getAll()
{
        List<Company> companies = new ArrayList<Company>();
        String sql = "SELECT * FROM companies ;";
        Connection connection = DbConnector.getDbConnection();
        Statement statement;
        ResultSet resultSet;
        Company company;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) 
            {
                company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setCompany_name(resultSet.getString("company_name"));
                company.setAdresse(resultSet.getString("adresse"));
                company.setTelephone(resultSet.getString("telephone"));
                company.setEmail(resultSet.getString("email"));
                company.setContact_name(resultSet.getString("contact_name"));
               
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    
}
   
public static Company getCompany(int idcompany) {
        String sql = "SELECT * FROM companies WHERE id = ?";
        Connection connection = DbConnector.getDbConnection();
        ResultSet resultSet;
        Company company = new Company();
        try {
             PreparedStatement Pst= connection.prepareStatement(sql);
            Pst.setInt(1, idcompany);
            resultSet = Pst.executeQuery();
            if (resultSet.first()==true)
            {
                System.out.println(" company found ");
                company.setId(resultSet.getInt("id"));
                company.setCompany_name(resultSet.getString("company_name"));
                company.setAdresse(resultSet.getString("adresse"));
                company.setTelephone(resultSet.getString("telephone"));
                company.setEmail(resultSet.getString("email"));
                company.setContact_name(resultSet.getString("contact_name"));
                company.setAvatar(resultSet.getString("avatar"));
                
              
                

                return company;
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

public static void updateCompany(Company company) {
     String sql = " UPDATE companies set  company_name=?, adresse=?, telephone=?, email=?, avatar=?, contact_name=? WHERE id=?";
        Connection connection = DbConnector.getDbConnection();
        PreparedStatement Pst;
        try {
            Pst = connection.prepareStatement(sql);
            Pst.setString(1, company.getCompany_name());
            Pst.setString(2, company.getAdresse());
            Pst.setString(3, company.getTelephone());
            Pst.setString(4, company.getEmail());
            Pst.setString(5, company.getAvatar());
            Pst.setString(6, company.getContact_name());
            Pst.setInt(7, company.getId());
            Pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public static void deleteCompany(Integer id) {
        String sql = "DELETE FROM companies WHERE id = ?;";
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

public int nomberOfCompagnies(){
        String sql = "select count(*) as total from companies";
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
