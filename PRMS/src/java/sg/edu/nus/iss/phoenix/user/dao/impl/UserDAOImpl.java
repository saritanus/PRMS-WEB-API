package sg.edu.nus.iss.phoenix.user.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.user.dao.RoleDAO;
import sg.edu.nus.iss.phoenix.user.dao.UserDAO;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 * User Data Access Object (DAO). This class contains all database handling that
 * is needed to permanently store and retrieve User object instances.
 */
public class UserDAOImpl implements UserDAO {

    private static final String DELIMITER = ":";
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    Connection connection;

    public UserDAOImpl() {
        super();
        // TODO Auto-generated constructor stub
        connection = openConnection();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#createValueObject()
     */
    @Override
    public User createValueObject() {
        return new User();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#getObject(java.sql
	 * .Connection, int)
     */
    @Override
    public User getObject(String name, String password) throws NotFoundException, SQLException {

        User valueObject = createValueObject();
        valueObject.setName(name);
        valueObject.setPassword(password);
        load(valueObject);
        return valueObject;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#load(java.sql.Connection
	 * , sg.edu.nus.iss.phoenix.authenticate.entity.User)
     */
    @Override
    public void load(User valueObject) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM user WHERE (name = ? and password= ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getName());
            stmt.setString(2, valueObject.getPassword());

            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public void loadUser(User valueObject) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM user WHERE (userid = ?) ";
        PreparedStatement stmt = null;

        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, valueObject.getUserId());
            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#loadAll(java.sql
	 * .Connection)
     */
    @Override
    public List<User> loadAll() throws SQLException {

        String sql = "SELECT * FROM user ORDER BY userid ASC ";
        List<User> searchResults = listQuery(this.connection
                .prepareStatement(sql));
        return searchResults;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#create(java.sql.
	 * Connection, sg.edu.nus.iss.phoenix.authenticate.entity.User)
     */
    
    @Override
	public List<User> loadAllPresenters() throws SQLException {
                System.out.println("In UserDAOImpl");
		openConnection();
		String sql = "SELECT u.userid,name FROM   user AS u JOIN   `user-role` as ur ON u.userid=ur.userId Join   role AS r ON r.roleId=ur.roleId  where r.role='presenter' ORDER BY `name` ASC; ";
		List<User> searchResults = listQueryName(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}
        
    @Override
	public List<User> loadAllProducers() throws SQLException {
		openConnection();
		String sql = "SELECT u.userid,name FROM   user AS u JOIN   `user-role` as ur ON u.userid=ur.userId Join   role AS r ON r.roleId=ur.roleId  where r.role='producer' ORDER BY `name` ASC; ";
		List<User> searchResults = listQueryName(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}
        
    @Override
    public synchronized int create(User valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "INSERT INTO user ( name, password, emailId, joiningDate ) VALUES (?, ?, ?, ?) ";
            stmt = this.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, valueObject.getName());
            stmt.setString(2, valueObject.getPassword());
            stmt.setString(3, valueObject.getEmailID());
            stmt.setDate(4, (Date) valueObject.getJoiningDate());
            int rowcount = databaseUpdate(stmt);
            int last_inserted_id =0;
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next())
                {
                    last_inserted_id = rs.getInt(1);
                }
            System.out.println("created row id is:" + rs); 
            System.out.println("created last id is:"+ last_inserted_id);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
            return last_inserted_id ;

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }
    
    @Override
    public synchronized void assignrole(User valueObject) throws SQLException {
            
        String sql = "";
        PreparedStatement stmt =null;
        System.out.println("in assignrole");
        int i = 0;
        System.out.println("data from front"+valueObject.getUserId());
        int size = valueObject.getRoleId().size();
        System.out.println("got the size"+size);
        while ( i < size){
             try {
                    stmt = null;
                    sql = "INSERT INTO `user-role` VALUES (?, ?) ";
                    stmt = this.connection.prepareStatement(sql);                   
                    stmt.setInt(1, valueObject.getUserId());
                    stmt.setInt(2, valueObject.getRoleId().get(i));
                    i++;
                    int rowcount = databaseUpdate(stmt);
                    if (rowcount != 1) {
                    throw new SQLException("PrimaryKey Error when updating DB!");
            }
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        }    
    }

    /* (non-Javadoc)
         * @see sg.edu.nus.iss.phoenix.user.dao.impl.UserDAO#save(sg.edu.nus.iss.phoenix.user.entity.User)
     */
    @Override
    public void save(User valueObject) throws NotFoundException,
			SQLException {

	String sql = "UPDATE user SET `name`=?, `emailId` = ? WHERE (`userid` = ? ); ";
	PreparedStatement stmt = null;
	openConnection();
	try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getName());
            stmt.setString(2, valueObject.getEmailID());
            stmt.setInt(3, valueObject.getUserId());
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

    
    @Override
    public synchronized void updaterole(User valueObject) throws SQLException {
            
        String sql = "";
        PreparedStatement stmt =null;
        System.out.println("in updaterole");
        System.out.println("Checking if entries wrt to user id exist");
        
        //Deleting the exisitng list for user-role mapping
        String ursql = "DELETE FROM `user-role` WHERE (userid = ? ) ";
        try {
            stmt = this.connection.prepareStatement(ursql);
            stmt.setInt(1, valueObject.getUserId());
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException(
                        "Object could not be deleted! (PrimaryKey not found)");
            }
        } catch (NotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
        //Assinging the new mapping
        
        int i = 0;
        System.out.println("data from front"+valueObject.getUserId());
        int size = valueObject.getRoleId().size();
        System.out.println("got the size"+size);
        while ( i < size){
             try {
                    stmt = null;
                    sql = "INSERT INTO `user-role` VALUES (?, ?) ";
                    stmt = this.connection.prepareStatement(sql);                   
                    stmt.setInt(1, valueObject.getUserId());
                    stmt.setInt(2, valueObject.getRoleId().get(i));
                    i++;
                    int rowcount = databaseUpdate(stmt);
                    if (rowcount != 1) {
                    throw new SQLException("PrimaryKey Error when updating DB!");
            }
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        }    
    }    
    
    
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#delete(java.sql.
	 * Connection, sg.edu.nus.iss.phoenix.authenticate.entity.User)
     */
    @Override
    public void delete(User valueObject) throws NotFoundException, SQLException {
        
        
//        //Deleting User-Role entries
        String ursql = "DELETE FROM `user-role` WHERE (userid = ? ) ";
        PreparedStatement stmt = null;
//        try {
//            stmt = this.connection.prepareStatement(ursql);
//            stmt.setInt(1, valueObject.getUserId());
//
//            int rowcount = databaseUpdate(stmt);
//            if (rowcount == 0) {
//                // System.out.println("Object could not be deleted (PrimaryKey not found)");
//                throw new NotFoundException(
//                        "Object could not be deleted! (PrimaryKey not found)");
//            }
//        } finally {
//            if (stmt != null) {
//                stmt.close();
//            }
//        }
        
        //Deleting User entry
        String usql = "DELETE FROM user WHERE (userid = ? ) ";
        try {
            stmt = this.connection.prepareStatement(usql);
            stmt.setInt(1, valueObject.getUserId());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException(
                        "Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }      
        
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#deleteAll(java.sql
	 * .Connection)
     */
    @Override
    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM user";
        PreparedStatement stmt = null;

        try {
            stmt = this.connection.prepareStatement(sql);
            int rowcount = databaseUpdate(stmt);
            System.out.println("Deleted rows :" + rowcount);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#countAll(java.sql
	 * .Connection)
     */
    @Override
    public int countAll() throws SQLException {

        String sql = "SELECT count(*) FROM user";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;

        try {
            stmt = this.connection.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                allRows = result.getInt(1);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return allRows;
    }

    @Override
    public User searchMatching(String name, String password) throws SQLException {
        User user = null;
        try {
            user = getObject(name, password);
            return user;
        } catch (NotFoundException ex) {
            logger.log(Level.WARNING, "Fail to find user: {0}", name);
        }
        return (user);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#searchMatching(java
	 * .sql.Connection, sg.edu.nus.iss.phoenix.authenticate.entity.User)
     */
    @Override
    public List<User> searchMatching(User valueObject) throws SQLException {

        List<User> searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE 1=1 ");

        if (valueObject.getUserId()<=0) {
            if (first) {
                first = false;
            }
            sql.append("AND id = ").append(valueObject.getUserId()).append(" ");
        }

        if (valueObject.getPassword() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND password LIKE '").append(valueObject.getPassword())
                    .append("%' ");
        }

        if (valueObject.getName() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND name LIKE '").append(valueObject.getName())
                    .append("%' ");
        }

        if (valueObject.getRoles().get(0).getRole() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND role LIKE '")
                    .append(valueObject.getRoles().get(0).getRole())
                    .append("%' ");
        }

        sql.append("ORDER BY id ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first) {
            searchResults = new ArrayList<User>();
        } else {
            searchResults = listQuery(this.connection.prepareStatement(sql
                    .toString()));
        }

        return searchResults;
    }

    /**
     * databaseUpdate-method. This method is a helper method for internal use.
     * It will execute all database handling that will change the information in
     * tables. SELECT queries will not be executed here however. The return
     * value indicates how many rows were affected. This method will also make
     * sure that if cache is used, it will reset when data changes.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return only one row. The
     * resultset will be converted to valueObject. If no rows were found,
     * NotFoundException will be thrown.
     *
     * @param stmt This parameter contains the SQL statement to be executed.
     * @param valueObject Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQuery(PreparedStatement stmt, User valueObject)
            throws NotFoundException, SQLException {

        try (ResultSet result = stmt.executeQuery()) {

            if (result.next()) {

                valueObject.setUserId(result.getInt("userid"));
                valueObject.setPassword(result.getString("password"));
                valueObject.setName(result.getString("name"));
                DAOFactoryImpl factory = new DAOFactoryImpl();
                RoleDAO roleDAO = factory.getRoleDAO();
                List roles =roleDAO.loadUserRole(valueObject);
                valueObject.setRoles(roles);

            } else {
                // System.out.println("User Object Not Found!");
                throw new NotFoundException("User Object Not Found!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of valueObjects. If no rows were
     * found, an empty List will be returned.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected List<User> listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList<User> searchResults = new ArrayList<>();
        try (ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                User temp = createValueObject();
                temp.setUserId(result.getInt(1));
                System.out.println("printing temp value:"+temp.getUserId());
                temp.setPassword(result.getString("password"));
                temp.setName(result.getString("name"));
                temp.setEmailID(result.getString("emailId"));
                temp.setJoiningDate(result.getDate("joiningDate"));
               // temp.setRoles(createRoles(result.getString("role")));
                //Role e = new Role(result.getString("role"));
                //ArrayList<Role> roles = new ArrayList<Role>();
                //roles.add(e);
                //temp.setRoles(roles);

                searchResults.add(temp);
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List<User>) searchResults;
    }
    protected List<User> listQueryName(PreparedStatement stmt) throws SQLException {

        ArrayList<User> searchResults = new ArrayList<>();
        try (ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                User temp = createValueObject();
                temp.setUserId(result.getInt(1));
                System.out.println("printing temp value:"+temp.getUserId());
                //temp.setPassword(result.getString("password"));
                temp.setName(result.getString("name"));
               // temp.setRoles(createRoles(result.getString("role")));
                //Role e = new Role(result.getString("role"));
                //ArrayList<Role> roles = new ArrayList<Role>();
                //roles.add(e);
                //temp.setRoles(roles);

                searchResults.add(temp);
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List<User>) searchResults;
    }

    private ArrayList<Role> createRoles(final String roles) {
        ArrayList<Role> roleList = new ArrayList<>();
        String[] _r = roles.trim().split(DELIMITER);
        for (String r : _r) {
            roleList.add(new Role(r.trim()));
        }
        return (roleList);
    }

    private Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
    
        try {
            conn = DriverManager.getConnection(DBConstants.dbUrl,
					DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
        }
        return conn;
    }
      private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
