/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.user.entity.Role;

/**
 *
 * @author User
 */
public class Roles {
    
    private List <Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }
 
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    
}