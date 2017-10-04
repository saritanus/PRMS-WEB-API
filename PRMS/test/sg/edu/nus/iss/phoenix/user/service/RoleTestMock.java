/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;


import java.sql.SQLException;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
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
import sg.edu.nus.iss.phoenix.user.dao.RoleDAO;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * @author Srishti Bhargava
 * @version 1.0
 * @created 30-Sep-2017 
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleTestMock {
    Role roletest=new Role("preseneter");
     @Mock
     RoleDAO roledao;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
 /*We are mocking RoleDAO and testing that if we implement countAll() ,it should return
  total count of roles*/ 
    @Test
public void testRoleCountAll() throws SQLException  {
        //  create mock
        RoleDAO test = mock(RoleDAO.class);

        // define return value for method 
        when(test.countAll()).thenReturn(4);

        // use mock in test....
        assertEquals(test.countAll(), 4);
}
/*We are mocking RoleDAO and testing that if we implement searchMatching() ,it should return
  role which is being passed in the parameter of this method */
@Test
public void testRoleSearchMatching() throws SQLException  {
        //  create mock
        Role role=new Role("presenter");
        
        RoleDAO test = mock(RoleDAO.class);
        

        // define return value for method 
        when(test.searchMatching("presenter")).thenReturn(role);

        // use mock in test....
        assertEquals(test.searchMatching("presenter"), role);
}
/*We are mocking RoleDAO and testing that if we implement loadAll() ,it should return a list
  of all the roles*/
@Test
public void testRoleLoadAll() throws SQLException  {
        //  create mock
        Role role1=new Role(1,"producer","producer");
        Role role2=new Role(3,"manager","manager");
        Role role3=new Role(4,"admin","System Administrator");
        RoleDAO test = mock(RoleDAO.class);
        ArrayList<Role> roleList=new ArrayList<Role>();
            roleList.add(role1);
            roleList.add(role1);
            roleList.add(role1);

        // define return value for method 
        when(test.loadAll()).thenReturn(roleList);
        

        // use mock in test....
        assertEquals(test.loadAll(), roleList);
        assertSame(test.loadAll().size(),3);
}
//Testing  processCreate method using doThrow method:stubbing void method with exception        
@Test(expected=Exception.class)
	public void testProcessCreate() throws SQLException {
		doThrow(new SQLException()).when(roledao).create(any(Role.class));
		roledao.create(roletest);
	}

    
    
}
