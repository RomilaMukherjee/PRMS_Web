/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.entity;

import java.util.Date;

/**
 *
 * @author Ragu
 */
public class WeeklySchedule {
    private Date startDate;
    private String assignedBy;
    
    public WeeklySchedule() {
        
    }
    
    public WeeklySchedule(Date startDate, String assignedBy) {
        this.startDate = startDate;
        this.assignedBy = assignedBy;
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
