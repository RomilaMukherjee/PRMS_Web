/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;

/**
 *
 * @author Ragu
 */
public class AnnualSchedules {
    private List<AnnualSchedule> ASList;

    public List<AnnualSchedule> getASList() {
        return ASList;
    }

    public void setASList(List<AnnualSchedule> ASList) {
        this.ASList = ASList;
    }
}
