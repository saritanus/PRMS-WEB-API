/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("user")
@RequestScoped
public class UserRESTService {

    @Context
    private UriInfo context;
    
    private UserService service;

    /**
     * Creates a new instance of UserRESTService
     */
    public UserRESTService() {
        service = new UserService();
    }


	public void finalize() throws Throwable {

	}
        
    /**
     * PUT method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User usr) {
        service.processCreate(usr);

    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getAllUsers() {
        ArrayList<User> userlist = service.findAllUsers();
        System.out.println("In UserRESTService userlist size:"+userlist.size());
        Users usersList = new Users();
        usersList.setUserList(new ArrayList<User>());
        
        for (int i = 0; i < userlist.size(); i++) {
            usersList.getUserList().add(
                new User(userlist.get(i).getUserId(),
                        userlist.get(i).getName(), 
                    userlist.get(i).getPassword()));
        }

        return usersList;
    }

	public void deleteUsers(){

	}

	public void getAllPresenters(){

	}

	public void getAllProducers(){

	}

	public void modifyUsers(){

	}

	public void on(){

	}
}//end UserRESTService