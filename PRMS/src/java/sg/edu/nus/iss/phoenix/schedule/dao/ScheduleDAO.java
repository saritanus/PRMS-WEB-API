package sg.edu.nus.iss.phoenix.schedule.dao;


/**
 * This entity object represents the list of annual schedules - passt, present and
 * future - that are maintained by the sysetm.
 * @author Sarita
 * @created 20-Sep-2017 1:07:18 AM
 */
public interface ScheduleDAO {

	public void createProgramSlot();

	public void getAttribute();

	public boolean isProgramSlotAssigned();

	public void isScheduleOverlaps();

	public void loadAll();

	public void modifyProgramSlot();

	public void programSlot();

	//public retrieveAllAnnualSchedules();

	public void retrieveAllWeeklySchedules();

	public void retrieveAnnualSchedule();

}