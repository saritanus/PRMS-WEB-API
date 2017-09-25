package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.dao.RoleDAO;
import sg.edu.nus.iss.phoenix.user.entity.Role;

public class RoleService {
    
    DAOFactoryImpl factory;
    RoleDAO roledao;

    public RoleService(){
        super();
        factory = new DAOFactoryImpl();
        roledao = factory.getRoleDAO();
    }
    
    public void finalize() throws Throwable {

    }
    
    public ArrayList<Role> findAllRoles() {
            ArrayList<Role> currentList = new ArrayList<Role>();
            try {
                currentList = (ArrayList<Role>) roledao.loadAll();
        } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
        }
        return currentList;
    }
    
}//end RoleService