package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.Date;
/**
 * This entity object represents the radio program schedule for a week as defined
 * by radio station manager in terms of a series of time slots in which specific
 * programs are broadcast.
 * @author Sarita
 * @created 20-Sep-2017 1:03:16 AM
 */
public final class WeeklySchedule {
       
        public void finalize() throws Throwable {

	}
	public void WeeklySchedule(){

	}
        
        private int weekId;
        private int sequence;
	private Date startDate;
	public int programId;

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getSequence() {
        return sequence;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getProgramId() {
        return programId;
    }
	
}//end WeeklySchedule