/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 * A class representing a collection of users
 * @author shashwatjain
 */
public class Users {
    
    private List<User> users;

    /**
     * Gets a list of user objects
     * @return list of user objects
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets a list of user objects
     * @param users a list of user objects
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
   
}
