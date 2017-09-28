package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * @author boonkui
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

        public ProgramSlot createValueObject() {
                return new ProgramSlot();
        }
        
        public ProgramSlot getObject(int id) throws NotFoundException, SQLException {
                    ProgramSlot valueObject = createValueObject();
                    valueObject.setId(id);
                    load(valueObject);
                    return valueObject;
         }

            @Override
            public void load(ProgramSlot valueObject) throws NotFoundException, SQLException {
           
                if (valueObject.getId()== 0) {
                // System.out.println("Can not select without Primary-Key!");
                throw new NotFoundException("Can not select without Primary-Key!");
               }

                String sql = "SELECT * FROM `program-slot` WHERE (`id` = ? ); ";
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

            @Override
            public List<ProgramSlot> loadAll() throws SQLException {
                openConnection();
		String sql = "SELECT * FROM `program-slot` ; ";
		List<ProgramSlot> searchResults = listQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
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
                //ResultSet rs = stmt.getGeneratedKeys();
              //  if (rs.next()) {
                 //   result = rs.getInt(1);
              //  }
		return result;
	}

            @Override
            public void create(ProgramSlot valueObject) throws SQLException {              
                
		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
                try {
			sql = "INSERT INTO `program-slot` "
                                + "(`duration`, `startTime`,`weekId`, `presenterId`, "
                                + "`producerId`,`dateOfProgram`, `programId`,) "
                                + "VALUES (?,?,?); ";
			stmt = connection.prepareStatement(sql);
			stmt.setTime(1, valueObject.getDuration());
			stmt.setDate(2, (Date) valueObject.getStartTime());
			stmt.setInt(3, valueObject.getWeekId());
                        stmt.setInt(4,valueObject.getPresenter().getUserId());
                        stmt.setInt(5,valueObject.getProducer().getUserId());
                        stmt.setDate(6, (Date) valueObject.getEndTime());
                        stmt.setInt(7,valueObject.getRadioProgram().getRadioId());
                        
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
            }

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
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
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
public boolean isProgramSlotAssigned() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void isScheduleOverlaps() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

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
                        // System.out.println("RadioProgram Object Not Found!");
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
protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        ResultSet result = null;
        openConnection();
        try {
                result = stmt.executeQuery();

                while (result.next()) {
                        ProgramSlot temp = createValueObject();
                        temp.setId(result.getInt("id"));
                        temp.setDuration(result.getTime("duration"));
                        temp.setStartTime(result.getDate("startTime"));
                        temp.setWeekId(result.getInt("weekId"));                        
                        temp.setStartTime(result.getDate("endTime"));
                        if(result.getInt("presenterId")!=0)
                        {
                            User presenter = new User();
                            temp.setPresenter(presenter);
                        }
                        if(result.getInt("producerId")!=0)
                        {
                            User producer = new User();
                            temp.setPresenter(producer);
                        }
                        if(result.getInt("programId")!=0)
                        {
                            RadioProgram radioProgram = new RadioProgram();
                            temp.setRadioProgram(radioProgram);
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
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        try {
                this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                                DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

}

private void closeConnection() {
        try {
                this.connection.close();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
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