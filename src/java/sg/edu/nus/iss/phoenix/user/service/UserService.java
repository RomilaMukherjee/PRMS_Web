/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;

/**
 *
 * @author shashwatjain
 */
public class UserService {
    private static final Logger logger = 
			Logger.getLogger(UserService.class.getName());

	DAOFactoryImpl factory;
	UserDao udao;

    public UserService() {
        this.factory = new DAOFactoryImpl();
        this.udao = factory.getUserDAO();
    }
        
    public List<User> findAllUsers(){
        try {
            return udao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }    
}
