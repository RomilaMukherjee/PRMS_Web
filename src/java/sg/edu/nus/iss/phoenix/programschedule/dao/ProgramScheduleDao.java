/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.dao;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author Ragu
 */
public interface ProgramScheduleDao {
    
    public abstract AnnualSchedule createValueObject();
    
    public abstract List<AnnualSchedule> loadAllAnnualSchedule() throws SQLException;
    
    public abstract void createAnnualSchedule(AnnualSchedule valueObject) throws SQLException;
}
