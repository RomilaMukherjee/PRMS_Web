/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.entity;

/**
 * Class representing an annual schedule
 * @author Ragu
 */
public class AnnualSchedule {
    /**
     * Year of annual schedule
     */
    private int year;
    
    /**
     * name of person who assigned the schedule 
     */
    private String assignedBy;
    
    public AnnualSchedule() {
        
    }
    /**
     * Constructor to create an annual schedule object
     * @param year integer representing year 
     * @param assignedBy String representing name of person who assigned the schedule
     */
    public AnnualSchedule(int year, String assignedBy) {
        this.year = year;
        this.assignedBy = assignedBy;
    }
    
    /**
     * Returns year
     * @return Year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of the annual schedule
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     * Returns the name of who assigned the schedule
     * @return name
     */
    public String getAssignedBy() {
        return assignedBy;
    }
    
    /**
     * Sets the person who assigns the schedule to the annual schedule
     * @param assignedBy 
     */
    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }
    
}
