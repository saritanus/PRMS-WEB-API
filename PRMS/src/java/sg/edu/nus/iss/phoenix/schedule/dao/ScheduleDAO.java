package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;


/**
 * This entity object represents the list of annual schedules - passt, present and
 * future - that are maintained by the sysetm.
 * @author Sarita
 * @created 20-Sep-2017 1:07:18 AM
 */
public interface ScheduleDAO {
    
         /**
	 * createValueObject-method. This method is used when the Dao class needs to
	 * create new value object instance. The reason why this method exists is
	 * that sometimes the programmer may want to extend the valueObject and
	 * then this method can be over-rided to return extended valueObject.
                         * @return 
	 */
	public abstract ProgramSlot createValueObject();
        
        /**
	 * getObject-method. This will create and load valueObject contents from
	 * database using given Primary-Key as identifier. This method is just a
	 * convenience method for the real load-method which accepts the valueObject
	 * as a parameter. Returned valueObject will be created using the
	 * createValueObject() method.
                        * @param name
                        * @return 
                        * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException 
                     b * @throws java.sql.SQLException 
    */
	public abstract ProgramSlot getObject(String name)
			throws NotFoundException, SQLException;
        
        /**
	 * load-method. This will load valueObject contents from database using
	 * Primary-Key as identifier. Upper layer should use this so that
	 * valueObject instance is created and only primary-key should be specified.
	 * Then call this method to complete other persistent information. This
	 * method will overwrite all other fields except primary-key and possible
	 * runtime variables. If load can not find matching row, NotFoundException
	 * will be thrown.
	 * 
	 * @param valueObject
	 * This parameter contains the class instance to be loaded.
	 *  Primary-key field must be set for this to work properly.
                          * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
                         * @throws java.sql.SQLException
	 */
	public abstract void load(ProgramSlot valueObject)
			throws NotFoundException, SQLException;

                        /**
	 * LoadAll-method. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
                         * @return 
                        * @throws java.sql.SQLException
	 */
	public abstract List<ProgramSlot> loadAll(String startTime, String endTime) throws SQLException;
        
                    /**
	 * create-method. This will create new row in database according to supplied
	 * valueObject contents. Make sure that values for all NOT NULL columns are
	 * correctly specified. Also, if this table does not use automatic
	 * surrogate-keys the primary-key must be specified. After INSERT command
	 * this method will read the generated primary-key back to valueObject if
	 * automatic surrogate-keys were used.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be created. If
	 *            automatic surrogate-keys are not used the Primary-key field
	 *            must be set for this to work properly.
                      * @throws java.sql.SQLException
	 */
	public abstract void create(ProgramSlot valueObject) throws SQLException;
        
	/**
	 * save-method. This method will save the current state of valueObject to
	 * database. Save can not be used to create new instances in database, so
	 * upper layer must make sure that the primary-key is correctly specified.
	 * Primary-key will indicate which instance is going to be updated in
	 * database. If save can not find matching row, NotFoundException will be
	 * thrown.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be saved.
	 *            Primary-key field must be set for this to work properly.
                        * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
                         * @throws java.sql.SQLException
	 */
	public abstract void save(ProgramSlot valueObject)
			throws NotFoundException, SQLException;	

	public boolean isProgramSlotAssigned(String startDateTime) throws SQLException;

	public boolean isScheduleOverlaps(String startDate,String duration)  throws SQLException;
/*
	public void modifyProgramSlot() throws SQLException;

	public abstract void retrieveAllWeeklySchedules() throws SQLException;

	public abstract void retrieveAnnualSchedule() throws SQLException;*/
        
                      /**
	 * searchMatching-Method. This method provides searching capability to get
	 * matching valueObjects from database. It works by searching all objects
	 * that match permanent instance variables of given object. Upper layer
	 * should use this by setting some parameters in valueObject and then call
	 * searchMatching. The result will be 0-N objects in a List, all matching
	 * those criteria you specified. Those instance-variables that have NULL
	 * values are excluded in search-criteria.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance where search will
	 *            be based. Primary-key field should not be set.
                        * @return 
                        * @throws java.sql.SQLException 
	 */
	/*public abstract List<ProgramSlot> searchMatching(RadioProgram valueObject)
			throws SQLException;*/

}