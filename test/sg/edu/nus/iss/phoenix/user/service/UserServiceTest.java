/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author shashwatjain
 */
public class UserServiceTest {
    private User user;
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        user = new User();
        user.setAll("testUser", "PAssWord", "Test User", "Test");
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of getRoles method, of class UserService.
     */
//    @Test
//    public void testGetRoles() {
//        System.out.println("getRoles");
//        UserService instance = new UserService();
//        List<Role> expResult = null;
//        List<Role> result = instance.getRoles();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of findAllUsers method, of class UserService.
     */
//    @Test
//    public void testFindAllUsers() {
//        System.out.println("findAllUsers");
//        UserService instance = new UserService();
//        List<User> expResult = null;
//        List<User> result = instance.findAllUsers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of findAllUsersByRole method, of class UserService.
     */
//    @Test
//    public void testFindAllUsersByRole() {
//        System.out.println("findAllUsersByRole");
//        String role = "";
//        UserService instance = new UserService();
//        List<User> expResult = null;
//        List<User> result = instance.findAllUsersByRole(role);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of processDelete method, of class UserService.
     */
    @Test
    public void testProcessDelete() {
        System.out.println("processDelete");
        String name = user.getId();
        UserService instance = new UserService();
        instance.processCreate(user);
        instance.processDelete(name);
        List<User> results;
        results = instance.findAllUsers();
        Iterator itr = results.iterator();
        int c = 0;
        while(itr.hasNext()){
            User next  = (User) itr.next();
            if (next.getId() == null ? user.getId() == null : next.getId().equals(user.getId()))
                c++;
        }
        if (c != 0)
            fail();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of processCreate method, of class UserService.
     */
    @Test
    public void testProcessCreate() {
        System.out.println("processCreate");
        UserService instance = new UserService();
        instance.processCreate(user);
        List<User> results;
        results = instance.findAllUsers();
        Iterator itr = results.iterator();
        int c = 0;
        while(itr.hasNext()){
            User next  = (User) itr.next();
            if (next.getId() == null ? user.getId() == null : next.getId().equals(user.getId()))
                c++;
        }
        instance.processDelete(user.getId());
        if (c == 0)
            fail();
        
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of processUpdate method, of class UserService.
     */
    @Test
    public void testProcessUpdate() {
        System.out.println("processUpdate");
//        User user = null;
        user.setName("Modified");
        UserService instance = new UserService();
        instance.processCreate(user);
        instance.processUpdate(user);
        List<User> results;
        results = instance.findAllUsers();
        Iterator itr = results.iterator();
        int c = 0;
        while(itr.hasNext()){
            User next  = (User) itr.next();
            if (next.getName()== null ? user.getName()== null : next.getName().equals(user.getName()))
                c++;
        }
        instance.processDelete(user.getId());
        if (c == 0)
            fail();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
