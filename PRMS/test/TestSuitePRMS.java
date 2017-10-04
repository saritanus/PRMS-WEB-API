/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sg.edu.nus.iss.phoenix.radioProgram.ProgramServiceTest;
import sg.edu.nus.iss.phoenix.schedule.ScheduleTestMock;
import sg.edu.nus.iss.phoenix.user.service.RoleTestMock;
import sg.edu.nus.iss.phoenix.user.service.UserTestMock;

/**
 *
 * @author Srishti Bhargava
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ProgramServiceTest.class,ScheduleTestMock.class,RoleTestMock.class,UserTestMock.class
})
public class TestSuitePRMS {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
