package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.user.dao.RoleDAO;
import sg.edu.nus.iss.phoenix.user.dao.UserDAO;
import sg.edu.nus.iss.phoenix.user.dao.impl.RoleDAOImpl;
import sg.edu.nus.iss.phoenix.user.dao.impl.UserDAOImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;

public class DAOFactoryImpl implements DAOFactory {
	private UserDAO userDAO = new UserDAOImpl();
	private RoleDAO roleDAO = new RoleDAOImpl();
	private ProgramDAO rpDAO = new ProgramDAOImpl();
        private ScheduleDAO psDAO = new ScheduleDAOImpl();

	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return userDAO;
	}

	@Override
	public RoleDAO getRoleDAO() {
		// TODO Auto-generated method stub
		return roleDAO;
	}

	@Override
	public ProgramDAO getProgramDAO() {
		// TODO Auto-generated method stub
		return rpDAO;
	}
        
        @Override
	public ScheduleDAO getScheduleDAO() {
		// TODO Auto-generated method stub
		return psDAO;
	}

}
