package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.Date;


/**
 * This entity object represents the annual radio station program schedule as a
 * list of annual schedules.
 * @author Sarita
 * @created 20-Sep-2017 1:03:28 AM
 */
public final class AnnualSchedule {
    	public void finalize() throws Throwable {

	}
	public void AnnualSchedule(){

	}
	private int year;
        private int annualId;
	public WeeklySchedule m_WeeklySchedule;
        
        public void setYer(int year)
        {
            this.year = year;
        }
        public int getYear()
        {
            return this.year;
        }
        public void setAnnualId(int annualId)
        {
            this.annualId = annualId;
        }
        public int getAnnualId()
        {
            return annualId;
        }

        
}//end AnnualSchedule