package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This command captures the request to delete an existing radio program.
 * @author boonkui
 * @version 1.0
 * @created 18-Sep-2017 10:56:42 PM
 */
public class DeleteProgramSlotCmd {

	public DeleteProgramSlotCmd(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * Performs the request by passing the name of the radio program to delete to the
	 * Business Layer, invoking the Review/Select Radio Program use case to display
	 * the resultant list of radio programs.
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
}//end DeleteProgramSlotCmd