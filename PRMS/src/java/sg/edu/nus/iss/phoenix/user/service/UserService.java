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

    public ArrayList<User> findAllPresenters() throws SQLException {
        ArrayList<User> currentList = new ArrayList<User>();
        System.out.println("In findAllPresenters");
        currentList = (ArrayList<User>) userdao.loadAllPresenters(); // TODO Auto-generated catch block
	return currentList;   
    }
        
    public User findUser(int userId) throws SQLException, NotFoundException {
        User user = new User(userId);
        System.out.println("In findUser");
        userdao.loadUser(user); // TODO Auto-generated catch block
	return user;
    }
    public int findUserName(String name) throws SQLException, NotFoundException {
     
        System.out.println("In findUser");
        int userId = userdao.getUserId(name);
        return userId;
    }
    public ArrayList<User> findAllProducers() throws SQLException {
        ArrayList<User> currentList = new ArrayList<User>();
        System.out.println("In findAllProducers");
        currentList = (ArrayList<User>) userdao.loadAllProducers(); // TODO Auto-generated catch block
	return currentList;
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

    public int processCreate(User usr) {
        int newID=0;
        try {
                newID = userdao.create(usr);
                System.out.println("Inserted record ID is:"+ newID);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            return newID;
    }

    
    public void assignUserRole(User usr) {
        try {
            System.out.println("in UserService");
            userdao.assignrole(usr);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    
    public void processDelete(int userid){
        try {
            System.out.println("In process delete of UserService:"+userid);
            int a = userid;
            User user = new User(a);
            System.out.println("In process delete of UserService after:"+user.getUserId());
            userdao.delete(user);
        } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } 

        
    }

    public void processModify(User user) {
        try {
            userdao.save(user);
	} catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
    }

    public void updateUserRole(User usr) {
        try {
            System.out.println("in UserService in updateUserRole");
            userdao.updaterole(usr);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }

}//end UserService