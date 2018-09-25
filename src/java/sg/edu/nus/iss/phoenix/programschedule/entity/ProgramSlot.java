package sg.edu.nus.iss.phoenix.programschedule.entity;

import java.sql.Date;
import java.sql.Time;

public class ProgramSlot{
  
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private String programSlotName;
    private Date dateofProgram;
    private Time duration;
    private Time startTime;


    public ProgramSlot(){        
    }
    
     public ProgramSlot (String nameIn) {
          this.programSlotName = nameIn;
    }
     
    public ProgramSlot (String nameIn,
          Date dateofprogramIn,
          Time durationIn,
          Time startTimeIn) {
          this.programSlotName = nameIn;
          this.dateofProgram = dateofprogramIn;
          this.duration = durationIn;
          this.startTime = startTimeIn;
    }
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */

    public String getprogramSlotName() {
          return this.programSlotName;
    }
    public void setprogramSlotName(String programnameIn) {
          this.programSlotName = programnameIn;
    }

    public Date getdateofProgram() {
          return this.dateofProgram;
    }
    public void setdateofProgram(Date dataofProgramIn) {
          this.dateofProgram = dataofProgramIn;
    }

    public Time getduration() {
          return this.duration;
    }
    public void setduration(Time durationIn) {
          this.duration = durationIn;
    }
    
    public Time getstartTime() {
        return this.startTime;
  }
  public void setstartTime(Time startTimeIn) {
        this.startTime = startTimeIn;
  }
}





