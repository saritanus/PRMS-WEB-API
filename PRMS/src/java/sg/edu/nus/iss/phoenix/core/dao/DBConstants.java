package sg.edu.nus.iss.phoenix.core.dao;

public class DBConstants {
	
    // Data Connection Variables
    public static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String dbUrl = "jdbc:mysql://localhost:3306/phoenixft04";
    public static final String dbUserName = "root";
    public static final String dbPassword = "root";

    // Table names
    public static final String userTableName = "user";
    public static final String roleTableName = "role";
    public static final String userRoleTableName = "user-role";
    public static final String radioProgramTableName = "radio-program";
    public static final String programSlotTableName = "program-slot";
    public static final String weeklyScheduleTableName = "wekly-schedule";
    public static final String annualScheduleTableName = "annual-schedule";
   
    //User table field names
    public static final String u_id = "userid";
    public static final String u_name = "name";
    public static final String u_password = "password";
    public static final String u_role ="role";
    public static final String u_emailId = "emailId";
    public static final String u_joiningDate = "joiningDate";

    //Role table field names
    public static final String r_roleId = "role";
    public static final String r_role = "role";
    public static final String r_access ="accessPrivilege";
    
    //User-Role table field names
    public static final String ur_roleId = "roleId";
    public static final String ur_userId= "userId";
        
    //Schedule table field names
    public static final String s_scheduleId= "scheduleId";
    public static final String s_duration = "duration";
    public static final String s_startTime ="startTime";
    public static final String s_endTime ="endTime";
    public static final String s_programId = "programId";
    public static final String s_presenterId ="presenterId";
    public static final String s_producerId ="producerId";
    public static final String s_weekId ="weekId";
       
    //Weekly Schedule table field names
    public static final String ws_weekId= "scheduleId";
    public static final String ws_sequence= "sequence";
    public static final String ws_startTime= "startTime";
    public static final String ws_endTime= "endTime";
    public static final String ws_annualId= "annualId";
    
    //Annual Schedule table field names
    public static final String as_annualId ="annualId";
    public static final String as_yearNo ="yearNo";
  }
