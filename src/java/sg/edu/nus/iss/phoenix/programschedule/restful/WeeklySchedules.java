/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;

/**
 *
 * @author Ragu
 */
public class WeeklySchedules {
    private List<WeeklySchedule> weeklySchedules;

    public List<WeeklySchedule> getWeeklySchedules() {
        return weeklySchedules;
    }

    public void setWeeklySchedules(List<WeeklySchedule> weeklySchedules) {
        this.weeklySchedules = weeklySchedules;
    }
}
