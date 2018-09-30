/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.service.test;

import com.sun.media.jfxmedia.logging.Logger;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.dao.impl.test.ProgramScheduleDaoImplTest;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;

/**
 *
 * @author romila
 */
public class ProgramScheduleServiceTest {
    DAOFactoryImpl factory;
    ProgramScheduleDao programScheduleDao;
    
    public ProgramScheduleServiceTest(){
        super();
    }
    
    @Before
    public void setUp() {
         factory = new DAOFactoryImpl();
	programScheduleDao = factory.getProgramScheduleDao();
        
    }
 
    
   @Test
    public void processCreateServiceTest(){
                 
        try {
            assertNotNull(programScheduleDao);
           
           //Create Annual Schedule Object.
           AnnualSchedule annualObj = new AnnualSchedule();
           annualObj.setAssignedBy("pointyhead");
           annualObj.setYear(2018);
           programScheduleDao.createAnnualSchedule(annualObj);
           
	} catch (SQLException sqlExcept) {
           
           java.util.logging.Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, sqlExcept);
           assertFalse("Tryig to invoke dao passing duplicate primary key",sqlExcept!=null);
	}
    }
    
    
       @Test
      public void processCreateWeek() {
          assertNotNull(programScheduleDao);
           
           //Create Annual Schedule Object.
           WeeklySchedule weekObj = new WeeklySchedule();
           weekObj.setAssignedBy("pointyhead");
           weekObj.setYear(2018);
           weekObj.setStartDate(new Date());
            try {
                    programScheduleDao.createWeeklySchedule(weekObj);
            } catch (SQLException sqlExcept) {
                sqlExcept.printStackTrace();
                assertFalse("Tryig to invoke dao and create week passing duplicate primary key",sqlExcept!=null);
            }
	}
    
}
