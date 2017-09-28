package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;

/**
 * @author The Administrator
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

        
        public ArrayList<ProgramSlot> findAllProgramSlots()throws SQLException
        {
         ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
            System.out.println("In findAllProgramSlots");
         currentList = (ArrayList<ProgramSlot>) scheduleDAO.loadAll(); // TODO Auto-generated catch block
		return currentList;   

	}
/*
	public void processCopy(ProgramSlot programSlot1,ProgramSlot programSlot2){
            try {
			scheduleDAO.copy(programSlot1,programSlot2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void processCreate(ProgramSlot programSlot){
                try {
			scheduleDAO.create(programSlot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        public void processModify(ProgramSlot programSlot)
        {try {
            scheduleDAO.modify(programSlot);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // TODO Auto-generated catch block
	}

	public void processDelete(ProgramSlot programSlot)
        {
            try {
                
                scheduleDAO.delete(programSlot);
            
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}

	*/
}//end ScheduleService