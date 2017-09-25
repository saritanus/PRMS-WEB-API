package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.dao.UserDAO;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class UserService {
        
    	DAOFactoryImpl factory;
	UserDAO userdao;

	public UserService(){
            super();
            // Sorry. This implementation is wrong. To be fixed.
            factory = new DAOFactoryImpl();
            userdao = factory.getUserDAO();
	}

	public void finalize() throws Throwable {

	}
	public void findAllPresenters(){

	}

	public void findAllProducers(){

	}

	public ArrayList<User> findAllUsers() {
            ArrayList<User> currentList = new ArrayList<User>();
            try {
            	currentList = (ArrayList<User>) userdao.loadAll();
                System.out.println("In UserService, currentlist size:"+currentList.size());
                
		} catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
		}
		return currentList;
	}

	public void processCreate(User usr) {
            try {
		userdao.create(usr);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}


	public void processDelete(){

	}

	public void processModify(){

	}
}//end UserService