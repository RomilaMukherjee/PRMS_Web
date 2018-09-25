/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.dao;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.ProgramSlot;

/**
 *
 * @author Ragu
 */
public interface ProgramScheduleDao {
    
    public abstract AnnualSchedule createAnnualValueObject();
    
    public abstract WeeklySchedule createWeeklyValueObject();
    
    public abstract List<AnnualSchedule> loadAllAnnualSchedule() throws SQLException;
    
    public abstract List<WeeklySchedule> loadAllWeeklySchedule()throws SQLException;
    
    public abstract void createAnnualSchedule(AnnualSchedule valueObject) throws SQLException;
    
    public abstract void createWeeklySchedule(WeeklySchedule valueObject) throws SQLException;
    
    public abstract ProgramSlot createValueObject();
        
    public abstract List<ProgramSlot> loadAllProgramSlot() throws SQLException;
    
    public abstract void createProgramSlot(ProgramSlot valueObject) throws SQLException;
    
    public abstract void saveProgramSlot(ProgramSlot valueObject)
			throws NotFoundException, SQLException;
    
    public abstract void deleteProgramSlot(ProgramSlot valueObject)
			throws NotFoundException, SQLException;
}
