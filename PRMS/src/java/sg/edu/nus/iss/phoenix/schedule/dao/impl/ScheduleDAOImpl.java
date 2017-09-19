package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 * @author boonkui
 * @version 1.0
 * @created 18-Sep-2017 10:56:45 PM
 */
public class ScheduleDAOImpl implements ScheduleDAO {



	public void finalize() throws Throwable {

	}
	public ScheduleDAOImpl(){

	}

	/**
	 * Creates a program slot in the database.
	 * 
	 * @param programSlot
	 */
	public void createProgramSlot(ProgramSlot programSlot){

	}

	/**
	 * Returns all the attributes of database.
	 */
	public void getAttribute(){

	}

	/**
	 * Checks if the program slot is assigned already in the weekly schedules of the
	 * year.
	 * 
	 * @param progarmSlot
	 */
	public boolean isProgramSlotAssigned(ProgramSlot progarmSlot){
		return false;
	}

	/**
	 * Checks if the duration overlaps in the existing schedules of that particular
	 * week.
	 * 
	 * @param programSlot
	 */
	public void isScheduleOverlaps(ProgramSlot programSlot){

	}

	/**
	 * Returns all the schedules of a week of a particular year.
	 * 
	 * @param year
	 * @param week
	 */
	public ProgramSlot[] loadAll(int year, int week){
		return null;
	}

	/**
	 * Modifies an existing program slot by changing with a new radio program or
	 * presenter or producer.
	 * 
	 * @param programSlot
	 */
	public void modifyProgramSlot(ProgramSlot programSlot){

	}

	public void programSlot(){

	}

	/**
	 * Reads all annual schedules from the database and builds list of Annual Schedule
	 * entities.
	 */
	public AnnualSchedule[] retrieveAllAnnualSchedules(){
		return null;
	}

	/**
	 * Retrieves all weekly schedule of a week.
	 * 
	 * @param programSlot
	 */
	public void retrieveAllWeeklySchedules(ProgramSlot[] programSlot){

	}

	public void retrieveAnnualSchedule(){

	}

    @Override
    public void createProgramSlot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isProgramSlotAssigned() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void isScheduleOverlaps() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyProgramSlot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retrieveAllWeeklySchedules() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end ScheduleDAOImpl