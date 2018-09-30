/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.dao.impl.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;

/**
 *
 * @author Ragu
 */
public class UserDaoImplTest {
    
    Connection connection;
    PreparedStatement stmt;
    
    public UserDaoImplTest() {
    }
    
    @Before
    public void setUp() throws SQLException {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
            try {
                 this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                        DBConstants.dbUserName, DBConstants.dbPassword);
                 assertNotNull(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
		
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        this.connection = null;
        assertNull(connection);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
   
    @Test
    public void testLoadAllUsersByRole() {
        try {
            String role = "presenter";
            //assertFalse(year.length()<4);
            if(connection==null){
                fail();
            }
            String sql = "SELECT * FROM phoenix.user WHERE role LIKE '%"+role+"%'";
            stmt = connection.prepareStatement(sql);
            if(stmt==null){
                fail();
            }
            ResultSet result = stmt.executeQuery();
            User user = new User();
            List<User> users = new ArrayList<User>();
            assertNotNull(result);
            try {
                while (result.next()){
                    user.setId(result.getString("id"));
                    user.setName(result.getString("name"));
                    //user.setPassword(sql);
                    users.add(user);
                }
                assertFalse(users.isEmpty());
                
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException sqe) {
            Logger.getLogger(UserDaoImplTest.class.getName()).log(Level.SEVERE, null, sqe);
        }
    }
}
