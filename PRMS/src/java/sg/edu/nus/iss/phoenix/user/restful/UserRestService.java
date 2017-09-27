/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class UserRestService {

    @Context
    private UriInfo context;
    
    private UserService service;

    /**
     * Creates a new instance of UserRESTService
     */
    public UserRestService() {
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
    public int createUser(User usr) {
        System.out.println("UserRestService print data:"+usr);
        int newID = service.processCreate(usr);
        return newID;

    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @PUT
    @Path("/assignrole")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Consumes("application/json")
    @Produces("application/json")
    public void assignRole(User usr) {
        System.out.println("UserRestService print data:"+usr);
        service.assignUserRole(usr);
    }

    /**
     * POST method for creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRadioProgram(User user) {
        service.processModify(user);
    }
    
    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
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
                        userlist.get(i).getEmailID(),
                        userlist.get(i).getJoiningDate()));
        }

        return usersList;
    }

	public void deleteUsers(){

	}
        
    @GET
    @Path("/allpresenter")
    @Produces(MediaType.APPLICATION_JSON)
	public Users getAllPresenters() throws SQLException
        {
            System.out.println("In UserRestService");
        ArrayList<User> presenterList = service.findAllPresenters();
        Users users = new Users();
        users.setUserList(new ArrayList<User>());
            //System.out.println("in UserRestService");
        for (int i = 0; i < presenterList.size(); i++) {
            users.getUserList().add(
                new User(presenterList.get(i).getUserId(),
                        presenterList.get(i).getName()
                    //presenterList.get(i).getPassword()
                    
                    
                  ));
        }
        return users;
    }
        
    @GET
    @Path("/allproducer")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getAllProducers() throws SQLException {
        ArrayList<User> producerList = service.findAllProducers();
        Users users = new Users();
        users.setUserList(new ArrayList<User>());
        
        for (int i = 0; i < producerList.size(); i++) {
            users.getUserList().add(
                new User(producerList.get(i).getUserId(), 
                    //producerList.get(i).getPassword(), 
                    producerList.get(i).getName()
                    
                    
                        ));
        }

        return users;
    }
    
    
  


	public void modifyUsers(){

	}

	public void on(){

	}
}//end UserRESTService