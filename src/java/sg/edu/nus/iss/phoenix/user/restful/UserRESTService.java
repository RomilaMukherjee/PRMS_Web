/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
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
        return service.findAllUsers();
    }
    @GET
    @Path("/allroles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> getAllRoles() {
        return service.getRoles();
    }
    
    
    @GET
    @Path("/allUserByRole/{user_role}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsersByRole(@PathParam("user_role") String role){
        List<User> userList = service.findAllUsersByRole(role);
        return userList;
    }
    @DELETE
    @Path("/delete/{uname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteRadioProgram(@PathParam("uname") String name) {
        String name2;
        try { 
            name2 = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDelete(name2);
    }
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRadioProgram(User user) {
        service.processCreate(user);
    }
    /**
     * PUT method for updating or creating an instance of UserRESTService
     * @param user
     *            content representation for the resource
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifyUser(User user) {
        service.processUpdate(user);
    }
}
