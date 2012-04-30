/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Books;

/**
 *
 * @author gautam
 */
public class BooksDAO {

    private static Context ctx;
    private static DataSource dataSource;
    private static Connection connection;

    static {
        try {
            ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("obs");
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Books getBook(int ID) {
        String Query = "SELECT * FROM OBS.BOOKS WHERE ID=?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setMaxRows(1);
            preparedStatement.setInt(1, ID);
            results = preparedStatement.executeQuery();
            results.next();
            Books book = new Books();
            book.setId(results.getInt("ID"));
            book.setName(results.getString("NAME"));
            book.setDescription(results.getString("DESCRIPTION"));
            book.setPrice(results.getInt("PRICE"));
            return book;

        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                results.close();
            } catch (SQLException ex) {
                Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;

    }

    public static List<Books> getRandomBooks(int limit) {
        String Query = "SELECT * FROM OBS.BOOKS ORDER BY RANDOM()";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        ArrayList<Books> books = new ArrayList<Books>();
        try {
            preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setMaxRows(limit);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                Books book = new Books();
                book.setId(results.getInt("ID"));
                book.setName(results.getString("NAME"));
                book.setDescription(results.getString("DESCRIPTION"));
                book.setPrice(results.getInt("PRICE"));
                books.add(book);
            }
            return books;

        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                results.close();
            } catch (SQLException ex) {
                Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } return null;
    }
}
