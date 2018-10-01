package sg.edu.nus.iss.phoenix.programschedule.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * Represents a program slot and contains related methods
 * 
 */
public class ProgramSlot{
  
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private String programSlotName;
    private Date dateofProgram;
    private Time duration;
    private Time startTime;
    private Date weekStartDate;
    private String presenter;
    private String producer;
    
   /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */
    public ProgramSlot(){        
    }
    
    /**
     * Constructor 
     * @param nameIn name of program slot 
     */
     public ProgramSlot (String nameIn) {
          this.programSlotName = nameIn;
    }
    
     /**
      * Constructor 
      * @param nameIn name of program slot
      * @param dateofprogramIn date of program slot
      * @param durationIn duration of the slot
      * @param startTimeIn Start time of the slot
      * @param weekStartDate Start date of the week of the program slot
      * @param presenter Presenter of the program
      * @param producer Producer of the program
      */
    public ProgramSlot (String nameIn,
          Date dateofprogramIn,
          Time durationIn,
          Time startTimeIn,Date weekStartDate,
          String presenter,
          String producer) {
          this.programSlotName = nameIn;
          this.dateofProgram = dateofprogramIn;
          this.duration = durationIn;
          this.startTime = startTimeIn;
          this.weekStartDate = weekStartDate; 
          this.presenter = presenter;
          this.producer = producer;
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
  
  public Date getweekStartDate() {
          return this.weekStartDate;
    }
    public void setweekStartDate(Date wstartDateIn) {
          this.weekStartDate = wstartDateIn;
    }
     public String getpresenter() {
          return this.presenter;
    }
    public void setpresenter(String presenterIn) {
          this.presenter = presenterIn;
    }
     public String getproducer() {
          return this.producer;
    }
    public void setproducer(String producerIn) {
          this.producer = producerIn;
    }
    
}





