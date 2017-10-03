package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.core.util.PhoenixUtil;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;

/**
 * @author Sarita Sethy
 * @version 1.0
 * @created 18-Sep-2017 10:56:45 PM
 */
public class ScheduleService {
    DAOFactoryImpl factory;
    ScheduleDAO scheduleDAO;

	public void finalize() throws Throwable {

	}
	public ScheduleService(){
           super();
           factory = new DAOFactoryImpl();
           scheduleDAO=factory.getScheduleDAO();
	}

        /**
         * findAllProgramSlots-method. This method calls retrieval method of DAO implementation
         * to get all schedule from program slot table.
         * @param startTime
         * @param endTime
         * @return
         * @throws SQLException 
         */
        public ArrayList<ProgramSlot> findAllProgramSlots(String startTime, String endTime)throws SQLException
        {
            ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
            currentList = (ArrayList<ProgramSlot>) scheduleDAO.loadAll(startTime, endTime);
            return currentList;   

	}
        /**
         * processCreate-method. This method calls the create method of DAO implementation
         * @param programSlot 
         */
        public void processCreate(ProgramSlot programSlot){
                try {
			scheduleDAO.create(programSlot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
         public boolean processCheckAssign(String startDateTime) throws ParseException{
             boolean isAssigned = false;
                try {
			isAssigned =scheduleDAO.isProgramSlotAssigned(startDateTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return isAssigned;
	}
         
         public boolean processOverlap(String startDate,String duration) throws ParseException{
             boolean isAssigned = false;
                try {
			isAssigned =scheduleDAO.isScheduleOverlaps(startDate,duration);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return isAssigned;
	}
         
        public void processModify(ProgramSlot programSlot)
        {
           try {
				scheduleDAO.save(programSlot);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
        
        
	
	public void processDelete(ProgramSlot programSlot) throws NotFoundException
        {
            try {
                
                scheduleDAO.delete(programSlot);
            
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}

	

   
}//end ScheduleService