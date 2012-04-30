/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import model.Users;

/**
 *
 * @author gautam
 */
public class UserDAO {
    public UserDAO(){
        
    }
    public static boolean authenticate(Users user){
        DataSource dataSource;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String RETRIEVE_QUERY = "SELECT EMAIL FROM OBS.USERS WHERE EMAIL=? AND PASSWORD=? ";
        ResultSet results = null ;
        int num_of_rows=0;
        boolean authentication = true;
        try{
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("obs");
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(RETRIEVE_QUERY);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            results = preparedStatement.executeQuery();
            while (results.next()){
                String email = results.getString("EMAIL");
                num_of_rows++;
            }
            if(num_of_rows>1){
                throw new SQLException("Too many rows returned");
            }
            else if (num_of_rows<1){
                throw new IllegalAccessException("Username or password is wrong");
            }
            
        }
        catch(SQLException e){
            authentication = false;
            e.printStackTrace();
        }
        catch(IllegalAccessException e){
            authentication=false;
        }
        catch(Exception e){
            authentication = false;
            e.printStackTrace();
        }finally{
            try {
                results.close();
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return authentication;
    }
    
}
