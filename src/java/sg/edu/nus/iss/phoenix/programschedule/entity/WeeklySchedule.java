/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.entity;

import java.util.Date;

/**
 * Class representing a weekly schedule and related methods
 * @author Ragu
 */
public class WeeklySchedule {
    private Date startDate;
    private String assignedBy;
    private int year;
    
    /**
     * Constructor
     */
    public WeeklySchedule() {
        
    }
    
    /**
     * Constructor
     * @param startDate Start date 
     * @param assignedBy Name of person who assigns the schedule
     * @param year  Year of schedule
     */
    public WeeklySchedule(Date startDate, String assignedBy, int year) {
        this.startDate = startDate;
        this.assignedBy = assignedBy;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }
}
