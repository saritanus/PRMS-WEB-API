package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Time;
import java.util.Date;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.User;
/**
 * This entity object represents a time slot in a weekly schedule as defined by
 * the radio station manager in which a radio program is broadcast.
 * @author Sarita
 * @created 20-Sep-2017 1:02:59 AM
 */
public final class ProgramSlot {

    public ProgramSlot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

         public void finalize() throws Throwable {

	}
	public void ProgramSlot(){

	}
        private int id;
        private Time duration;
   	private Date endTime;	
	private Date startTime;
        private int weekId;
        private User presenter = new User();
        private User producer = new User();
	public RadioProgram radioProgram = new RadioProgram();

    public void setId(int id) {
        this.id = id;
    }
        public int getId() {
        return id;
    }
    public void setDuration(Time duration) {
        this.duration = duration;
    }
    public Time getDuration() {
        return duration;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
       public Date getEndTime() {
        return endTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }
    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
    public int getWeekId() {
        return weekId;
    }
    public void setPresenter(User presenter) {
        this.presenter = presenter;
    }
      public User getPresenter() {
        return presenter;
    }
    public void setProducer(User producer) {
        this.producer = producer;
    }
    public User getProducer() {
        return producer;
    }
    public void setRadioProgram(RadioProgram radioProgram) {
        this.radioProgram = radioProgram;
    }

    public RadioProgram getRadioProgram() {
        return radioProgram;
    }
    
      
     public ProgramSlot (int id,Time duration,Date startTime,Date endTime,int weekId,
                    User presenter,User producer,RadioProgram radioProgram) {
          this.id = id;
          this.duration = duration;
          this.startTime= startTime;
          this.endTime = endTime;
          this.weekId = weekId;
          this.presenter = presenter;
          this.producer = producer;
          this.radioProgram = radioProgram;
          }
        
}//end ProgramSlot