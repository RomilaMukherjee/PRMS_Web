/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.service;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

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
}
