package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.core.util.PhoenixUtil;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ProgramService;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 * @author Sarita Sethy
 * @version 1.0
 * @created 18-Sep-2017 10:56:45 PM
 */
public class ScheduleDAOImpl implements ScheduleDAO {    
    
        private static final Logger logger = Logger.getLogger(ScheduleDAOImpl.class.getName());
        Connection connection;
        
        @Override
         public void finalize() throws Throwable {

        }
        public ScheduleDAOImpl(){

        }
        /**
         * createValueObject-method. This method creates and returns a instance of ProgramSlot class. 
         * @return 
         */
        public ProgramSlot createValueObject() {
                return new ProgramSlot();
        }
        /**
         * getObject-method. This method creates an instance of ProgramSlot using the unique id.
         * @param id
         * @return
         * @throws NotFoundException
         * @throws SQLException 
         */
        public ProgramSlot getObject(int id) throws NotFoundException, SQLException {
                    ProgramSlot valueObject = createValueObject();
                    valueObject.setId(id);
                    load(valueObject);
                    return valueObject;
         }
        
        /**
         * load-method. This methods returns the schedule details of a selected schedule.
         * @param valueObject
         * @throws NotFoundException
         * @throws SQLException 
         */
            @Override
            public void load(ProgramSlot valueObject) throws NotFoundException, SQLException {

                if (valueObject.getId()== 0) {
                throw new NotFoundException("Can not select without Primary-Key!");
               }

                String sql = "SELECT * FROM `program-slot` WHERE (`scheduleId` = ? ); ";
                PreparedStatement stmt = null;
                openConnection();
                try {
                        stmt = connection.prepareStatement(sql);
                        stmt.setInt(1, valueObject.getId());
                        singleQuery(stmt, valueObject);

                } finally {
                        if (stmt != null)
                                stmt.close();
                        closeConnection();
                }
        }
        /**
         * loadAll-method. This method returns all the schedules available on selected dates 
         * @return
         * @throws SQLException 
         */
            @Override
            public List<ProgramSlot> loadAll(String startTime,String endTime) throws SQLException{
                openConnection();
                String sql = null;
                if(startTime == null)
                {
                    sql= " SELECT * FROM `program-slot` ; ";
                }
                else
                {
                    sql = "select * from `program-slot` where date(startTime) "
                            + "between date('"+startTime+"') and date('"+endTime+"'); ";
                }
                List<ProgramSlot> searchResults = null;
                try {
                
                searchResults = listQuery(connection.prepareStatement(sql));
                closeConnection();
                System.out.println("record size"+searchResults.size());
              
            } catch (NotFoundException ex) {
                Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
                  return searchResults;
            }
            /**
             * isProgramSlotAssigned-method. This method checks if a program slot is assigned or not on this time.
             * @param startDateTime
             * @return
             * @throws SQLException 
             */
@Override
public boolean isProgramSlotAssigned(String startDateTime) throws SQLException {
    
    String sql = "select count(*) cnt from `program-slot` where date_format('"
                  +startDateTime+"', '%Y-%m-%d %H:%i') between date_format(startTime, '%Y-%m-%d %H:%i') and date_format(endTime, '%Y-%m-%d %H:%i');";
    PreparedStatement stmt = null;
    openConnection();
    ResultSet result = null;
    boolean isAssigned = false;
    try{
        stmt = connection.prepareStatement(sql);
        result = stmt.executeQuery();
        int noSlot = 0;
        if (result.next()) 
        {
            noSlot =  result.getInt("cnt");
        }
        if(noSlot>0)
            isAssigned =true;
    } finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

return isAssigned;
}
/**
 * isScheduleOverlaps-method. This method checks if any program slot is overlapping in any existing program 
 * @param programSlot
 * @return
 * @throws SQLException 
 */
 @Override
public boolean isScheduleOverlaps(String startDate,String duration) throws SQLException {
    
   
    PreparedStatement stmt = null;
    openConnection();
    ResultSet result = null;
    boolean isOverlap = false;
    try{
        String sql = "SELECT count(*) cnt FROM phoenixft04.`program-slot` where "+
                      "addtime(date_format('"+startDate+"', '%Y-%m-%d %H:%i'),time('"+duration+
                      "'))  between "+
                      "date_format(startTime, '%Y-%m-%d %H:%i') and  date_format(endTime, '%Y-%m-%d %H:%i');";
                   
        stmt = connection.prepareStatement(sql);
        result = stmt.executeQuery();
        int noSlot = 0;
        if (result.next()) 
        {
            noSlot =  result.getInt("cnt");
        }
        if(noSlot>0)
            isOverlap =true;
    } finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

return isOverlap;
}

public boolean isYearExist(ProgramSlot programSlot) throws SQLException {    
   
    PreparedStatement stmt = null;
    openConnection();
    ResultSet result = null;
    boolean isYearExist = false;
    try{
        String sql = "SELECT count(*) cnt FROM `annual-schedule` where yearNo=year("
                +PhoenixUtil.dateFormatter.format(programSlot.getStartTime())+");";
                   
        stmt = connection.prepareStatement(sql);
        result = stmt.executeQuery();
        int noSlot = 0;
        if (result.next()) 
        {
            noSlot =  result.getInt("cnt");
        }
        if(noSlot>0)
            isYearExist =true;
        else
        {
            sql = "insert into `annual-schedule` (yearNo) " +"values (year("
                +PhoenixUtil.dateFormatter.format(programSlot.getStartTime())+");";
            stmt = connection.prepareStatement(sql);
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                    throw new SQLException("PrimaryKey Error when updating DB!");
            }
        }
           
    } finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

return isYearExist;
}
public boolean isWeekExist(ProgramSlot programSlot) throws SQLException {
    
   PreparedStatement stmt = null;
    openConnection();
    ResultSet result = null;
    boolean isWeekExist = false;
    try{
        String sql = "SELECT count(ws.weekId) FROM `weekly-schedule` ws , `annual-schedule` ans" +
                    " where ans.yearNo =year("+PhoenixUtil.dateFormatter.format(programSlot.getStartTime())
                    + ")+and ws.annualId = ans.annualId and ws.sequence =week("+PhoenixUtil.dateFormatter.format(programSlot.getStartTime())+");";
                   
        stmt = connection.prepareStatement(sql);
        result = stmt.executeQuery();
        int noWeek = 0;
        if (result.next()) 
        {
            noWeek =  result.getInt("cnt");
        }
        if(noWeek>0)
            isWeekExist =true;
        else
        {
            sql = "SELECT annualId cnt FROM `annual-schedule` where yearNo=year("
                +PhoenixUtil.dateFormatter.format(programSlot.getStartTime())+");";
            int annualId=0;
             if (result.next()) 
            {
                annualId =  result.getInt("annualId");
             }
            sql = "insert into `weekly-schedule` (sequence, annualId) values (week("
                +PhoenixUtil.dateFormatter.format(programSlot.getStartTime())+","+annualId+");";
            stmt = connection.prepareStatement(sql);
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                    throw new SQLException("PrimaryKey Error when updating DB!");
            }
        }
           
    } finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

return isWeekExist;
}
            
         /**
	 * databaseUpdate-method. This method is a helper method for internal use.
	 * It will execute all database handling that will change the information in
	 * tables. SELECT queries will not be executed here however. The return
	 * value indicates how many rows were affected. This method will also make
	 * sure that if cache is used, it will reset when data changes.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
         * @return 
         * @throws java.sql.SQLException
	 */
	protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();
                return result;
	}
        /**
         * create-method. This methods create a new records in the table program-slot.
         * @param valueObject
         * @throws SQLException 
         */
            @Override
            public void create(ProgramSlot valueObject) throws SQLException {              
                
		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
                try {
                        isYearExist(valueObject)  ;  
                        isWeekExist(valueObject);
                        sql = "INSERT INTO `program-slot` "
                                + "(`duration`, `startTime`,`weekId`, `presenterId`, "
                                + "`producerId`,`dateOfProgram`, `programId`,) "
                                + "VALUES (?,?,?); ";
			stmt = connection.prepareStatement(sql);
			stmt.setTime(1, valueObject.getDuration());
			stmt.setDate(2, (java.sql.Date) (Date) valueObject.getStartTime());
			stmt.setInt(3, valueObject.getWeekId());
                        stmt.setInt(4,valueObject.getPresenter().getUserId());
                        stmt.setInt(5,valueObject.getProducer().getUserId());
                        stmt.setDate(6, (java.sql.Date) (Date) valueObject.getEndTime());
                        stmt.setInt(7,valueObject.getRadioProgram().getRadioId());
                        
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				throw new SQLException("PrimaryKey Error when updating DB!");
			}
 
                            }                      
                    
		finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
            }

       /**
        * save-method. This method updates the values of program slot.
        * @param valueObject
        * @throws NotFoundException
        * @throws SQLException 
        */            
         @Override
        public void save(ProgramSlot valueObject) throws NotFoundException, SQLException {
            String sql = "UPDATE `program-slot` "
                    + "SET `duration`=?,`startTime`=?,`weekId`=?,`presenterId`=?,"
                    + "`producerId`=?,`dateOfProgram`=? ,`programId`=? "
                    + " WHERE (`id` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
                        stmt.setTime(1, valueObject.getDuration());
			stmt.setDate(2, (Date) valueObject.getStartTime());
			stmt.setInt(3, valueObject.getWeekId());
                        stmt.setInt(4,valueObject.getPresenter().getUserId());
                        stmt.setInt(5,valueObject.getProducer().getUserId());
                        stmt.setDate(6, (Date) valueObject.getEndTime());
                        stmt.setInt(7,valueObject.getRadioProgram().getRadioId());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				throw new NotFoundException(
						"Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
  }




/*
@Override
public void modifyProgramSlot() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}


@Override
public void retrieveAllWeeklySchedules() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void retrieveAnnualSchedule() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public List<ProgramSlot> searchMatching(RadioProgram valueObject) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}
*/
/**
 * databaseQuery-method. This method is a helper method for internal use. It
 * will execute all database queries that will return only one row. The
 * resultset will be converted to valueObject. If no rows were found,
 * NotFoundException will be thrown.
 * 
 * @param stmt
 *            This parameter contains the SQL statement to be excuted.
 * @param valueObject
 *            Class-instance where resulting data will be stored.
* @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
* @throws java.sql.SQLException
 */
protected void singleQuery(PreparedStatement stmt, ProgramSlot valueObject)
                throws NotFoundException, SQLException {
    
        

        ResultSet result = null;
        openConnection();
        try {
                result = stmt.executeQuery();

                if (result.next()) 
                                                            {						
                        valueObject.setId(result.getInt("id"));
                        valueObject.setDuration(result.getTime("duration"));
                        valueObject.setStartTime(result.getDate("startTime"));
                        valueObject.setWeekId(result.getInt("weekId"));
                       // DAOFactory factory = new DAOFactory();
                        if(result.getInt("presenterId")!=0)
                        {
                            User presenter = new User();
                            valueObject.setPresenter(presenter);
                        }
                        if(result.getInt("producerId")!=0)
                        {
                            User producer = new User();
                            valueObject.setPresenter(producer);
                        }
                        if(result.getInt("programId")!=0)
                        {
                            RadioProgram radioProgram = new RadioProgram();
                            valueObject.setRadioProgram(radioProgram);
                        }
                        valueObject.setStartTime(result.getDate("dateOfProgram"));

                } else {
                        throw new NotFoundException("ProgramSlot Object Not Found!");
                }
        } finally {
                if (result != null)
                        result.close();
                if (stmt != null)
                        stmt.close();
                closeConnection();
        }
}

/**
 * databaseQuery-method. This method is a helper method for internal use. It
 * will execute all database queries that will return multiple rows. The
 * resultset will be converted to the List of valueObjects. If no rows were
 * found, an empty List will be returned.
 * 
 * @param stmt
 *            This parameter contains the SQL statement to be executed.
* @return 
* @throws java.sql.SQLException
 */
protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException, NotFoundException {

        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        ResultSet result = null;
        UserService uService = new UserService();
        ProgramService pService = new ProgramService();
        //openConnection();
        try {
                result = stmt.executeQuery();

                while (result.next()) {
                        ProgramSlot temp = new ProgramSlot();
                        temp.setId(result.getInt(DBConstants.s_scheduleId));
                        temp.setDuration(result.getTime(DBConstants.s_duration));
                        temp.setStartTime(result.getDate(DBConstants.s_startTime));
                        temp.setWeekId(result.getInt(DBConstants.s_weekId));                        
                        temp.setEndTime(result.getDate(DBConstants.s_endTime));
                        int tempId = result.getInt(DBConstants.s_presenterId);
                        if(tempId!=0)
                        {
                           temp.setPresenter(uService.findUser(tempId));
                        }
                        tempId = result.getInt(DBConstants.s_producerId);
                        if(tempId!=0)
                        {
                             temp.setProducer(uService.findUser(tempId));
                        }
                        tempId=result.getInt(DBConstants.s_programId); 
                        if(tempId!=0)
                        {
                           temp.setRadioProgram(pService.findRPID(tempId));
                        }
                        searchResults.add(temp);
                }

        } finally {
                if (result != null)
                        result.close();
                if (stmt != null)
                        stmt.close();
                closeConnection();
        }

        return (List<ProgramSlot>) searchResults;
}

   private void openConnection() {
        try {
                Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }

        try {
                this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                                DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
                 e.printStackTrace();
        }

}

private void closeConnection() {
        try {
                this.connection.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
}

    @Override
    public ProgramSlot getObject(String name) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /* @Override
    public List<ProgramSlot> searchMatching(RadioProgram valueObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

}//end ScheduleDAOImpl