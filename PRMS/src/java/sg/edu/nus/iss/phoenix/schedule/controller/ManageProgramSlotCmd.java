package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This command captures the request to manage radio programs.
 * @author boonkui
 * @version 1.0
 * @created 18-Sep-2017 10:56:43 PM
 */
public class ManageProgramSlotCmd {

	public ManageProgramSlotCmd(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * Performs the request by invoking the Review/Select Radio Program use case to
	 * display the current list of radio programs.
	 * 
	 * @param path    Refer to Nocturne Application Controller framework.
	 * @param req    Refer to Java API specification.
	 * @param resp    Refer to Java API specification.
	 * @exception IOException,ServletException
	 */
	public String perform(String path, HttpServletRequest req, HttpServletResponse resp)
	  throws IOException,ServletException{
		return "";
	}
}//end ManageProgramSlotCmd