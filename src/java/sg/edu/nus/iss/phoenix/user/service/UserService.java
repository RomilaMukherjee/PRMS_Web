/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 * Class that provides user related services 
 * @author shashwatjain
 */
public class UserService {
    private static final Logger logger = 
			Logger.getLogger(UserService.class.getName());

	DAOFactoryImpl factory;
	UserDao udao;

    /**
     * Constructor
     */    
    public UserService() {
        this.factory = new DAOFactoryImpl();
        this.udao = factory.getUserDAO();
    }
    
    /**
     * Gets all roles as a list 
     * @return list of roles
     */
    public List<Role> getRoles(){
        try{
            return udao.getRoles();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Returns a list of users in ascending order
     * @return List of user objects
     */
    public List<User> findAllUsers(){
        try {
            return udao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
    
    /**
     * Finds all users by a role and returns a list of such users
     * @param role user's role that you want to search by
     * @return List of user objects
     */
    public List<User> findAllUsersByRole(String role){
        try {
            return udao.loadAllUsersByRole(role);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 

    /**
     * Delete user by name
     * @param name name of user that you want to delete
     */
    public void processDelete(String name) {
       
        try {
            User user = new User(name);
            udao.delete(user);
        } catch (NotFoundException | SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Creates a user
     * @param user user object containing all user details
     */
    public void processCreate(User user){
        try {
            udao.create(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Updates a user's details
     * @param user user object containing updated details
     */
    public void processUpdate(User user) {
        try {
            udao.save(user);
        } catch (NotFoundException | SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
