/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.dao;


import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.user.dao.RoleDAO;
import sg.edu.nus.iss.phoenix.user.dao.UserDAO;

/**
 *
 * @author projects
 */
public interface DAOFactory {

	ProgramDAO getProgramDAO();

	RoleDAO getRoleDAO();

	UserDAO getUserDAO();
        
        ScheduleDAO getScheduleDAO();
        
	
}
