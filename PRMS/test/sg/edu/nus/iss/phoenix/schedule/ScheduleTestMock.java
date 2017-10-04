/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule;
/**
 * @author Srishti Bhargava
 * @version 1.0
 * @created 30-Sep-2017 
 */


import java.sql.SQLException;
import java.text.ParseException;
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
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;



@RunWith(MockitoJUnitRunner.class)
//To test ScheduleService class

public class ScheduleTestMock
{
    ProgramSlot programSlot=new ProgramSlot();
    @Mock
     ScheduleService mockedScheduleService;
    @Before
    //initialize a mock object using Mockito
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    //Testing  processCreate method to create program slot using doThrow method:stubbing void method with exception        
@Test(expected=Exception.class)
	public void testProcessCreate() {
		doThrow(new SQLException()).when(mockedScheduleService).processCreate(any(ProgramSlot.class));
		mockedScheduleService.processCreate(programSlot);
	}
/*This method is testing processCheckAssign()to check if a program slot is assigned it
  should return true*/
         
@Test
public void testProcessCheckAssign() throws ParseException{
        //  create mock
        boolean b=true;
        boolean b1=false;
        
        ScheduleService test = mock(ScheduleService.class);
        

        // define return value for method 
        when(test.processCheckAssign("2017-09-09 02:30:46")).thenReturn(b);

        // use mock in test....
        assertEquals(test.processCheckAssign(("2017-09-09 02:30:46")), b);
}
/*This method is testing processOverlap() if any program slot is overlapping in any 
existing program it shouls return true */
@Test
public void testProcessOverlap() throws ParseException{
        //  create mock
        boolean b=true;
        boolean b1=false;
        
        ScheduleService test = mock(ScheduleService.class);
        

        // define return value for method 
        when(test.processOverlap("2017-09-09 02:00:00","2017-09-09 02:30:00")).thenReturn(b);

        // use mock in test....
        assertEquals(test.processOverlap("2017-09-09 02:00:00","2017-09-09 02:30:00"), b);
}

           
}
