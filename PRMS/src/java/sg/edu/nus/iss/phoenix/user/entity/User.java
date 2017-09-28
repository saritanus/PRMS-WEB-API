package sg.edu.nus.iss.phoenix.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Cloneable, Serializable {

    /**
	 * eclipse identifier
	 */
	//private static final long serialVersionUID = -5500218812568593553L;
	
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    //private int userId;
    private int userId;
    private String name;
    private String password;
    private String emailID;
    private Date joiningDate;
    private List<Role> listRoles = new ArrayList<Role>();
    private List<Integer> roleId = new ArrayList<Integer>();

    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public User () {

    }

    public User (String nameIn) {

          this.name = nameIn;

    }
    
    public User (int userIdIn) {
        
        this.userId = userIdIn;
        
    }

    public User (int userIdIn, String nameIn, String passwordIn, String emailIDIn, Date joiningDateIn) {
          this.userId = userIdIn;
          this.name = nameIn;
          this.password = passwordIn;
          this.emailID = emailIDIn;
          this.joiningDate = joiningDateIn;
    }

    public User(int userId, String name, String password) {
          this.userId = userId;
          this.name = name;
          this.password = password;
    }

    public User(int userId, String name) {
          this.userId=userId;
          this.name=name;
    }
    
    public User(int userId,List<Integer> roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }

    public User(int userIdIn, String nameIn, String emailIDIn, Date joiningDateIn) {
        this.userId = userIdIn;
        this.name = nameIn;
        this.emailID = emailIDIn;
        this.joiningDate = joiningDateIn;
    }

    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */
    public int getUserId() {
          return this.userId;
    }
    public void setUserId(int userId) {
          this.userId = userId;
    }
    
    public String getName() {
          return this.name;
    }
    public void setName(String nameIn) {
          this.name = nameIn;
    }

    public String getPassword() {
          return this.password;
    }
    public void setPassword(String passwordIn) {
          this.password = passwordIn;
    }

    public String getEmailID() {
          return this.emailID;
    }
    public void setEmailID(String emailIDIn) {
          this.emailID = emailIDIn;
    }

    public Date getJoiningDate() {
          return this.joiningDate;
    }
    public void setJoiningDate(Date joiningDateIn) {
          this.joiningDate = joiningDateIn;
    }
    public List<Role> getRoles()
    {
        return this.listRoles; 
    }
    public void setRoles(List<Role> list)
    {
        this.listRoles = list;
    }
    public List<Integer> getRoleId()
    {
        return this.roleId;
    }
    public void setRoleId(List<Integer> list)
    {
        this.roleId = list;
    }

    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param userIdIn
     * @param nameIn
     * @param passwordIn
     * @param emailIDIn
     * @param joiningDateIn
     */

    public void setAll(int userIdIn,String nameIn,
          String passwordIn,
          String emailIDIn,
          Date joiningDateIn) {
          this.userId = userIdIn;
          this.name = nameIn;
          this.password = passwordIn;
          this.emailID = emailIDIn;
          this.joiningDate = joiningDateIn;
    }


    /** 
     * hasEqualMapping-method will compare two RadioProgram instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     * @param valueObject
     * @return 
     */
    public boolean hasEqualMapping(User valueObject) {

          if (this.name == null) {
                    if (valueObject.getName() != null)
                           return(false);
          } else if (!this.name.equals(valueObject.getName())) {
                    return(false);
          }
          if (this.password == null) {
                    if (valueObject.getPassword() != null)
                           return(false);
          } else if (!this.password.equals(valueObject.getPassword())) {
                    return(false);
          }
          if (this.emailID == null) {
                    if (valueObject.getEmailID() != null)
                           return(false);
          } else if (!this.emailID.equals(valueObject.getEmailID())) {
                    return(false);
          }
          if ( this.joiningDate == null) {
                    if (valueObject.getJoiningDate() != null)
                           return(false);
          } else if (!this.joiningDate.equals(valueObject.getJoiningDate())) {
              return(false);
          }
          return true;
    }

    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
        @Override
        public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nUser class, mapping to table user\n");
        out.append("Persistent attributes: \n");
        out.append("userid = ").append(this.userId).append("\n");
        out.append("name = ").append(this.name).append("\n"); 
        out.append("password = ").append(this.password).append("\n"); 
        out.append("emailID = ").append(this.emailID).append("\n");
        out.append("joiningDate = ").append(this.joiningDate).append("\n");
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
        @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Object clone() throws CloneNotSupportedException {
        User cloned = new User();

        if (this.name != null)
             cloned.setName(this.name); 
        if (this.password != null)
             cloned.setPassword(this.password); 
        if (this.emailID != null)
             cloned.setEmailID(this.emailID);
        if (this.joiningDate != null)
             cloned.setJoiningDate((Date)this.joiningDate.clone());
        return cloned;
    }



}