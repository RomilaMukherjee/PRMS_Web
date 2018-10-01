/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;

/**
 * Class representing a list of weekly schedules
 * @author Ragu
 */
public class WeeklySchedules {
    /**
     * List of weekly schedules
     */
    private List<WeeklySchedule> weeklySchedules;

    public List<WeeklySchedule> getWeeklySchedules() {
        return weeklySchedules;
    }

    public void setWeeklySchedules(List<WeeklySchedule> weeklySchedules) {
        this.weeklySchedules = weeklySchedules;
    }
}
