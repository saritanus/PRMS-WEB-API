/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.service.RoleService;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("role")
@RequestScoped
public class RoleRESTService {

    @Context
    private UriInfo context;
    
    private RoleService service;

    /**
     * Creates a new instance of RoleRESTService
     */
    public RoleRESTService() {
        service = new RoleService();
    }

	public void finalize() throws Throwable {

	}
        
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Roles getAllRoles() {
        ArrayList<Role> rolelist = service.findAllRoles();
        Roles rolesList = new Roles();
        rolesList.setRoleList(new ArrayList<Role>());
        
        for (int i = 0; i < rolelist.size(); i++) {
            rolesList.getRoleList().add(
                new Role(rolelist.get(i).getRoleId(),
                        rolelist.get(i).getRole(), 
                    rolelist.get(i).getAccessPrivilege()));
        }

        return rolesList;
    }

	public void on(){

	}
}//end RoleRESTService