package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This command captures the request to add a new radio program or edit an
 * existing radio program.
 * @author boonkui
 * @version 1.0
 * @created 18-Sep-2017 10:56:42 PM
 */
public class AddEditProgramSlotCmd {

	public AddEditProgramSlotCmd(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * Performs the request forwarding to the setup program page for creation or
	 * editing of radio program.
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
}//end AddEditProgramSlotCmd