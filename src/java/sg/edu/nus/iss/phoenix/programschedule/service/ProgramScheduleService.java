/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.ProgramSlot;

/**
 *
 * @author Ragu
 */
public class ProgramScheduleService {
    DAOFactoryImpl factory;
    ProgramScheduleDao programScheduleDao;

	public ProgramScheduleService() {
		super();
		// Sorry. This implementation is wrong. To be fixed.
		factory = new DAOFactoryImpl();
		programScheduleDao = factory.getProgramScheduleDao();
	}
        
        public void processCreate(AnnualSchedule as) {
		try {
			programScheduleDao.createAnnualSchedule(as);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        public void processCreateWeek(WeeklySchedule ws) {
		try {
			programScheduleDao.createWeeklySchedule(ws);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        public ArrayList<AnnualSchedule> findAllAS() {
		ArrayList<AnnualSchedule> currentList = new ArrayList<AnnualSchedule>();
		try {
			currentList = (ArrayList<AnnualSchedule>) programScheduleDao.loadAllAnnualSchedule();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;

	}
        
        public ArrayList<WeeklySchedule> findAllWS() {
		ArrayList<WeeklySchedule> currentList = new ArrayList<WeeklySchedule>();
		try {
			currentList = (ArrayList<WeeklySchedule>) programScheduleDao.loadAllWeeklySchedule();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;

	}
        
        public ArrayList<ProgramSlot> findAllProgramSlot() {
		ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
		try {
			currentList = (ArrayList<ProgramSlot>) programScheduleDao.loadAllProgramSlot();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;

	}
        
	public void processCreateProgramSlot(ProgramSlot ps) {
		try {
			programScheduleDao.createProgramSlot(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processModifyProgramSlot(ProgramSlot ps) {
		
			try {
				programScheduleDao.saveProgramSlot(ps);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void processDeleteProgramSlot(String name) {

            try {
                ProgramSlot ps = new ProgramSlot(name);
                programScheduleDao.deleteProgramSlot(ps);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}
        
}
