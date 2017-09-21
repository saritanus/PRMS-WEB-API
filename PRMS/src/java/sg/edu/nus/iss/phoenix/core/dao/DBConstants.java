package sg.edu.nus.iss.phoenix.core.dao;

public class DBConstants {
	
	// Data Connection Variables
	public static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/phoenixft04";
	public static final String dbUserName = "root";
	public static final String dbPassword = "root";
	 
	//Not yet refactored!!!!!!!!!!!!!!!!!!!
	
	// Table names
    public static final String usersTableName = "user";
    public static final String rolesTableName = "role";
   
    //Users table field names
    public static final String u_id = "userid";
    public static final String u_name = "name";
    public static final String u_password = "password";
    public static final String u_role ="role";

    //roles table field names
    public static final String r_role = "role";
    public static final String r_access ="accessPrivilege";
    



}
