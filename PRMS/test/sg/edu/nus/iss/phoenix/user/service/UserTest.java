/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Srishti Bhargava
 */
package sg.edu.nus.iss.phoenix.user.service;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.internal.util.collections.Iterables;
import sg.edu.nus.iss.phoenix.user.dao.impl.UserDAOImpl;
import sg.edu.nus.iss.phoenix.user.entity.User;


public class UserTest extends TestCase {
    //Test Fixtures
    String name_expected;
    String name_actual;
    ArrayList<User> userListActual;
	User user1=new User(1,"srishti","test");
        User user2=new User(3,"sujit","abc");
        User user3=new User(4,"gautam","gtest");
        
        ArrayList<User> userListExpected=new ArrayList<User>();
        UserService service=new UserService();
        public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String date="28-09-2017";
        
	
	@Before
	public void setUp() throws Exception {
            
            userListExpected.add(user1);
            userListExpected.add(user2);
            userListExpected.add(user3);
           // u1.setJoiningDate(dateFormatter.parse(date));
            
	}

	@After
	public void tearDown() throws Exception
        {
		userListExpected = null;
		//u2 = null;
	}
        /*This test will test if we implement findAllUsers() and findAllPresenters() then it should return total number of users 
        and presenters resp in database */
        
	@Test
	public void testUserSerive() throws SQLException{
            
        userListActual=service.findAllUsers();
		//assertSame(4,us.processCreate(u1));
                
        assertSame(userListExpected.size(),userListActual.size());
        assertSame(1,service.findAllPresenters().size());
                //assertArrayEquals(ul.toArray(),us.findAllUsers().toArray());
               
        Iterator<User> itrActual=userListActual.iterator();
        Iterator<User> itrExpected=userListExpected.iterator();
                while(itrActual.hasNext())
                {
                     name_actual=itrActual.next().getName();
                    
                     if (itrExpected.hasNext())
                     {
                   name_expected= itrExpected.next().getName();
                   assertEquals(name_expected,name_actual);
                     }
                    
                    
                }
                
                 
                                
        }
}

		


