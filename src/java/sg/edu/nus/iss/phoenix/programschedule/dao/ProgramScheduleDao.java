/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.ProgramSlot;

/**
 *ProgramScheduleDao Interface for implementing program schedule DAO.
 * @author Ragu
 */
public interface ProgramScheduleDao {
    
    /**
	 * Creates an annual schedule and returns created object. This method is used when the Dao class needs to
	 * create new Annual value object instance. The reason why this method exists is
	 * that sometimes the programmer may want to extend also the valueObject and
	 * then this method can be over-rided to return extended valueObject.
     * @return Annual schedule object
	 */
    public abstract AnnualSchedule createAnnualValueObject();
    
    /**
	 * Creates a weekly schedule and returns created object. This method is used when the Dao class needs to
	 * create new Weekly value object instance. The reason why this method exists is
	 * that sometimes the programmer may want to extend also the valueObject and
	 * then this method can be over-rided to return extended valueObject.
     * @return weekly schedule object
	 */
    public abstract WeeklySchedule createWeeklyValueObject();
    
    /**
	 * Returns a list of all annual schedules. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
     * @return List of annual schedule objects
     * @throws java.sql.SQLException
	 */
    public abstract List<AnnualSchedule> loadAllAnnualSchedule() throws SQLException;
    
    /**
	 * Returns a list of weekly schedules of a requested year. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 *
         *@param  year Year for which you want to load weekly schedule
     * @return List of weekly schedule objects
     * @throws java.sql.SQLException
	 */
    public abstract List<WeeklySchedule> loadWeeklySchedule(int year)throws SQLException;
    
    /**
	 * Creates an annual schedule. This will create new row in database according to supplied
	 * valueObject contents. Make sure that values for all NOT NULL columns are
	 * correctly specified. Also, if this table does not use automatic
	 * surrogate-keys the primary-key must be specified. After INSERT command
	 * this method will read the generated primary-key back to valueObject if
	 * automatic surrogate-keys were used.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be created. If
	 *            automatic surrogate-keys are not used the Primary-key field
	 *            must be set for this to work properly.
     * @throws java.sql.SQLException
	 */
    public abstract void createAnnualSchedule(AnnualSchedule valueObject) throws SQLException;
    
    /**
	 * Creates a weekly schedule. This will create new row in database according to supplied
	 * valueObject contents. Make sure that values for all NOT NULL columns are
	 * correctly specified. Also, if this table does not use automatic
	 * surrogate-keys the primary-key must be specified. After INSERT command
	 * this method will read the generated primary-key back to valueObject if
	 * automatic surrogate-keys were used.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be created. If
	 *            automatic surrogate-keys are not used the Primary-key field
	 *            must be set for this to work properly.
     * @throws java.sql.SQLException
	 */
    public abstract void createWeeklySchedule(WeeklySchedule valueObject) throws SQLException;
    
    /**
	 * Creates a program slot and returns the created object. This method is used when the Dao class needs to
	 * create new value object instance. The reason why this method exists is
	 * that sometimes the programmer may want to extend also the valueObject and
	 * then this method can be over-rided to return extended valueObject.
     * @return A program slot object
	 */
    public abstract ProgramSlot createValueObject();
    
    /**
	 * Returns a list of all program slots. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
     * @return List of all program slot objects
     * @throws java.sql.SQLException
	 */
    public abstract List<ProgramSlot> loadAllProgramSlot() throws SQLException;
    
     /**
         * Returns a list of program slot for a requested week's start date. 
         * This will load valueObject contents from database using
	 * Primary-Key as identifier. Upper layer should use this so that
	 * valueObject instance is created and only primary-key should be specified.
	 * Then call this method to complete other persistent information. This
	 * method will overwrite all other fields except primary-key and possible
	 * runtime variables. If load can not find matching row, NotFoundException
	 * will be thrown.
        * @param weekStartDate This parameter contains the class instance to be loaded.
        *          Primary-key field must be set for this to work properly.
        * @return List of program slots
        *          
        * @throws java.sql.SQLException
        */
    public abstract List<ProgramSlot> loadProgramSlot(Date weekStartDate)throws SQLException;
    
    /**
	 * Creates a program slot. This will create new row in database according to supplied
	 * valueObject contents. Make sure that values for all NOT NULL columns are
	 * correctly specified. Also, if this table does not use automatic
	 * surrogate-keys the primary-key must be specified. After INSERT command
	 * this method will read the generated primary-key back to valueObject if
	 * automatic surrogate-keys were used.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be created. If
	 *            automatic surrogate-keys are not used the Primary-key field
	 *            must be set for this to work properly.
     * @throws java.sql.SQLException
	 */
    public abstract void createProgramSlot(ProgramSlot valueObject) throws SQLException;
    
    /**
	 * Updates a program slot. This method will save the current state of valueObject to
	 * database. Save can not be used to create new instances in database, so
	 * upper layer must make sure that the primary-key is correctly specified.
	 * Primary-key will indicate which instance is going to be updated in
	 * database. If save can not find matching row, NotFoundException will be
	 * thrown.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be saved.
	 *            Primary-key field must be set for this to work properly.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
	 */
    public abstract void saveProgramSlot(ProgramSlot valueObject)
			throws NotFoundException, SQLException;
    
    	/**
	 * Deletes a program slot. This method will remove the information from database as
	 * identified by by primary-key in supplied valueObject. Once valueObject
	 * has been deleted it can not be restored by calling save. Restoring can
	 * only be done using create method but if database is using automatic
	 * surrogate-keys, the resulting object will have different primary-key than
	 * what it was in the deleted object. If delete can not find matching row,
	 * NotFoundException will be thrown.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be deleted.
	 *            Primary-key field must be set for this to work properly.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
	 */
    public abstract void deleteProgramSlot(ProgramSlot valueObject)
			throws NotFoundException, SQLException;
}
