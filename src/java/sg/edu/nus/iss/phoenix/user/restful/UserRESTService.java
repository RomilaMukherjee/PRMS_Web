/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 * REST Web Service
 *
 * @author shashwatjain
 */
@Path("/users")
@RequestScoped
public class UserRESTService {
    
    private static final Logger logger = 
			Logger.getLogger(UserRESTService.class.getName());

    @Context
    private UriInfo context;
    private UserService service;
    /**
     * Creates a new instance of UserRESTService
     */
    public UserRESTService() {
        service = new UserService();
    }

    /**
     * Retrieves representation of an instance of sg.edu.nus.iss.phoenix.user.restful.UserRESTService
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
//        Users users = new Users();
//        users.setUsers(service.findAllUsers());
        logger.info("Inside Call");
        return service.findAllUsers();
//        return users;
        
        //TODO return proper representation object
//        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserRESTService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
