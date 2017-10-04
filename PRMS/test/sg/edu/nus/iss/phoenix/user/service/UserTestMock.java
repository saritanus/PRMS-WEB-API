/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.user.dao.UserDAO;
import sg.edu.nus.iss.phoenix.user.entity.User;
/**
 * @author Srishti Bhargava
 * @version 1.0
 * @created 30-Sep-2017 
 */


@RunWith(MockitoJUnitRunner.class)
//To test UserService class
public class UserTestMock {
     User userTest=new User(1,"sari","test");
     @Mock
     UserDAO userdao;
     @Mock
     UserService mockedUserService;
    @Before
    //initialize a mock object using Mockito
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
     
               
   /*We are mocking UserDAO and testing that if we implement countAll() ,it should return
     total count of users*/ 
  @Test
   public void testUserCountAll() throws SQLException  {
        //  create mock
        UserDAO test = mock(UserDAO.class);

        // define return value for method 
        when(test.countAll()).thenReturn(8);

        // use mock in test....
        assertEquals(test.countAll(), 8);
}
/*We are mocking UserDAO and testing that if we implement loadAll() ,it should return a list
   of all the users*/
 
@Test
public void testUserLoadAll() throws SQLException  {
        //  create mock
        
        User user1=new User(1,"srishti","test");
        User user2=new User(3,"sujit","abc");
        User user3=new User(4,"gautam","gtest");
        UserDAO test = mock(UserDAO.class);
        ArrayList<User> userList=new ArrayList<User>();
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);

        // define return value for method loadAll()
        when(test.loadAll()).thenReturn(userList);

        // use mock in test....
        assertEquals(test.loadAll(), userList);
}
/*We are mocking UserDAO and testing that if we implement searchMatching() ,it should return
  user whose name match with the name passed in the parameter of this method */
@Test
public void testUserSearchMatching() throws SQLException  {
        //  create mock
        User user1=new User(1,"srishti","test");
        
        UserDAO test = mock(UserDAO.class);
        

        // define return value for method 
        when(test.searchMatching("srishti","test")).thenReturn(user1);

        // use mock in test....
        assertEquals(test.searchMatching("srishti","test"), user1);
}
//Testing  processDelete method using doThrow method:stubbing void method with exception 
@Test(expected=Exception.class)
	public void testProcessDelete() {
		doThrow(new SQLException()).when(mockedUserService).processDelete(any(Integer.class));
		mockedUserService.processDelete(1);
	}
 //Testing  processCreate method using doThrow method:stubbing void method with exception        
@Test(expected=Exception.class)
	public void testProcessCreate() {
		doThrow(new SQLException()).when(mockedUserService).processCreate(any(User.class));
		mockedUserService.processCreate(userTest);
	}
 //Testing  processModify method using doThrow method:stubbing void method with exception  
@Test(expected=Exception.class)
	public void testProcessModify() {
		doThrow(new SQLException()).when(mockedUserService).processModify(any(User.class));
		mockedUserService.processModify(userTest);
	}

}
