/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.dao.impl.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;


/**
 *
 * @author Romila
 */
public class ProgramScheduleDaoImplTest {
    
   Connection connection;
   PreparedStatement stmt;
    public ProgramScheduleDaoImplTest() {
    }
    
    @Before
    public void setUp() {
        
         try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
            try {
                 this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                        DBConstants.dbUserName, DBConstants.dbPassword);
                 assertNotNull(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
		
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        this.connection = null;
        assertNull(connection);
    }

    @Test
    public void testLoadAllAnnualSchedule() {
    
        try {
            
            String sql = "SELECT * FROM `annual-schedule` ORDER BY `year` ASC; ";
            if(connection==null){
                fail();
            }
            stmt = connection.prepareStatement(sql);
            if(stmt==null){
                fail();
            }
            ResultSet result = stmt.executeQuery();
            AnnualSchedule annualObj = new AnnualSchedule();
            List<AnnualSchedule> annualSchd = new ArrayList<AnnualSchedule>();
            assertNotNull(result);
            
            try {
                while (result.next()){
                    annualObj.setYear(result.getInt("year"));
                    annualObj.setAssignedBy(result.getString("assingedBy"));
                    annualSchd.add(annualObj);
                }
                assertNotNull(annualSchd);
                assertFalse(annualSchd.isEmpty());
                
            } catch (SQLException ex) {
                Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
    
    
    @Test
    public void testLoadWeeklySchedule() {
    
        try {
            String year = "2018";
            assertFalse(year.length()<4);
            if(connection==null){
                fail();
            }
            String sql = "SELECT * FROM `weekly-schedule` WHERE (year="+year+") ORDER BY `startDate` ASC";
            stmt = connection.prepareStatement(sql);
            if(stmt==null){
                fail();
            }
            ResultSet result = stmt.executeQuery();
            WeeklySchedule weekObj = new WeeklySchedule();
            List<WeeklySchedule> weekSchd = new ArrayList<WeeklySchedule>();
            assertNotNull(result);
            try {
                while (result.next()){
                    weekObj.setYear(result.getInt("year"));
                    weekObj.setStartDate(result.getDate("startDate"));
                    weekSchd.add(weekObj);
                }
                assertFalse(weekSchd.isEmpty());
                
            } catch (SQLException ex) {
                Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
    
    @Test
    public void testCreateAnnualSchedule(){
        
            int year = 2019;
            String assignedBy ="dilbert";
            int result=0;
            
            if(connection==null){
                fail();
            }
           try {
			String sql = "INSERT INTO `annual-schedule` (`year`, `assingedBy`) VALUES (?,?);";
			stmt = connection.prepareStatement(sql);
                         if(stmt==null){
                            fail();
                        }
                        stmt.setInt(1,year);
			stmt.setString(2, assignedBy);
                        result = stmt.executeUpdate();
			
            }catch (SQLException ex) {
                Logger.getLogger(ProgramScheduleDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
                assertFalse("Tryig to insert duplicate primary key",result!=-1);
             
            }
    }
}
