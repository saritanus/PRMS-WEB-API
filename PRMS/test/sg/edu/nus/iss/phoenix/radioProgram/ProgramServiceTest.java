/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioProgram;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

import java.sql.SQLException;
import java.sql.Time;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ProgramService;
/**
 * @author Srishti Bhargava
 * @version 1.0
 * @created 30-Sep-2017 
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramServiceTest {
    private static RadioProgram radioProgram=null;
    @Mock
    ProgramService mockedProgramService;
    @BeforeClass
	public static void init()
        {
            
        }
    //Testing  processCreate method(for creating radio program) using doThrow method:stubbing void method with exception 
    @Test(expected=Exception.class)
	public void testProcessCreate() {
		RadioProgram createProgram = radioProgram;
                createProgram.setRadioId(78);
                
		doThrow(new SQLException()).when(mockedProgramService).processCreate(any(RadioProgram.class));
		mockedProgramService.processCreate(createProgram);
	}
    
}
