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
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Books;
import model.Shipped;
import model.Users;

/**
 *
 * @author gautam
 */
public class ShippingDAO {

    private static final String DEFAULT_SHIPPING_STATUS = "pending";

    public static Shipped getStatus(int ID) throws SQLException {
        DataSource dataSource;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet result;
        Shipped status;
        final String RETRIEVE_QUERY = "SELECT * FROM SHIPPED WHERE ID=?";
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("obs");
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(RETRIEVE_QUERY);
            preparedStatement.setMaxRows(1);
            preparedStatement.setInt(1, ID);
            result = preparedStatement.executeQuery();
            result.next();
            status = new Shipped(ID);
            status.setEmail(result.getString("EMAIL"));
            status.setAddress(result.getString("ADDRESS"));
            status.setPhone(result.getInt("PHONE"));
            status.setStatus(result.getString("STATUS"));
            status.setTimestamp(result.getTime("TIMESTAMP"));
            status.setBookid(result.getInt("BOOKID"));
            return status;
        } catch (NamingException ex) {
            Logger.getLogger(ShippingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void shipBook(Books book, Users user) {
        DataSource dataSource;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String INSERT_QUERY = "INSERT INTO OBS.SHIPPED "
                + "(EMAIL, ADDRESS, PHONE, STATUS, BOOKID) VALUES "
                + "(?,      ?,      ?,      ?,      ?)";
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("obs");
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setInt(3, user.getPhone());
            preparedStatement.setString(4, DEFAULT_SHIPPING_STATUS);
            preparedStatement.setInt(5, book.getId());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ShippingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ShippingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ShippingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
