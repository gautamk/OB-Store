/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Users;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author gautam
 */
public class UserDAOTest {
    
    public UserDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of authenticate method, of class UserDAO.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        Users user = new Users("admin@admin.com","password");
        boolean expResult = true;
        boolean result = UserDAO.authenticate(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
