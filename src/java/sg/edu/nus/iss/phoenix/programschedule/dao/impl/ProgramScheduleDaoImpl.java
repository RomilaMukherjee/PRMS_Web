/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.ProgramSlot;

/**
 * ProgramSchedule Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve Program Schedule and Progrma Slot object
 * instances.
 */
public class ProgramScheduleDaoImpl implements ProgramScheduleDao{
    Connection connection;
    
    @Override
    public List<AnnualSchedule> loadAllAnnualSchedule() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `annual-schedule` ORDER BY `year` ASC; ";
		List<AnnualSchedule> searchResults = listAnnualScheduleQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
    }
    
    @Override
    public List<WeeklySchedule> loadWeeklySchedule(int year) throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `weekly-schedule` WHERE (year="+year+") ORDER BY `startDate` ASC; ";
		List<WeeklySchedule> searchResults = listWeeklyScheduleQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
    }
        
    @Override
    public synchronized void createAnnualSchedule(AnnualSchedule valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
		try {
			sql = "INSERT INTO `annual-schedule` (`year`, `assingedBy`) VALUES (?,?);";
			stmt = connection.prepareStatement(sql);
                        stmt.setInt(1, valueObject.getYear());
			stmt.setString(2, valueObject.getAssignedBy());
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

	}
    
    @Override
    public synchronized void createWeeklySchedule(WeeklySchedule valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
                Date sqlStartDate = new Date(valueObject.getStartDate().getTime());
                System.out.println("createWeeklySchedule :" + valueObject.getAssignedBy());
                System.out.println("Start date :" + valueObject.getStartDate() + " sqlStart date :" + sqlStartDate);
		openConnection();
		try {
			sql = "INSERT INTO `weekly-schedule` (`startDate`, `assignedBy`, `year`) VALUES (?,?,?);";
			stmt = connection.prepareStatement(sql);
                        stmt.setDate(1, sqlStartDate);
			stmt.setString(2, valueObject.getAssignedBy());
                        stmt.setInt(3, valueObject.getYear());
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

	}
    
    /**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * result set will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException
     */
    protected List<AnnualSchedule> listAnnualScheduleQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<AnnualSchedule> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				AnnualSchedule temp = createAnnualValueObject();

				temp.setYear(result.getInt("year"));
				temp.setAssignedBy(result.getString("assingedBy"));

				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<AnnualSchedule>) searchResults;
	}
    
    /**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * resultset will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException
	 */
    protected List<WeeklySchedule> listWeeklyScheduleQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<WeeklySchedule> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				WeeklySchedule temp = createWeeklyValueObject();

				temp.setStartDate(result.getDate("startDate"));
				temp.setAssignedBy(result.getString("assignedBy"));
                                temp.setYear(result.getInt("year"));
				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<WeeklySchedule>) searchResults;
	}
        
        @Override
	public AnnualSchedule createAnnualValueObject() {
		return new AnnualSchedule();
	}
        
        @Override
	public WeeklySchedule createWeeklyValueObject() {
		return new WeeklySchedule();
	}
        
        @Override
	public ProgramSlot createValueObject() {
		return new ProgramSlot();
	}

	
	@Override
	public List<ProgramSlot> loadAllProgramSlot() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `program-slot` ORDER BY `program-name` ASC; ";
		List<ProgramSlot> searchResults = listProgramSlotQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}

	 @Override
        public List<ProgramSlot> loadProgramSlot(Date weekStartDate) throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `program-slot` WHERE (ws_startDate="+weekStartDate+") ORDER BY `dateOfProgram` ASC; ";
		List<ProgramSlot> searchResults = listProgramSlotQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
        
        }
	@Override
	public synchronized void createProgramSlot(ProgramSlot valueObject) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
		try {
			sql = "INSERT INTO `program-slot` (`duration`, `dateOfProgram`, `startTime`, 'program-name', `ws_startDate`, `producer`, `presenter`) VALUES (?,?,?,?,?,?,?); ";
			stmt = connection.prepareStatement(sql);
			stmt.setTime(1, valueObject.getduration());
			stmt.setDate(2, valueObject.getdateofProgram());
			stmt.setTime(3, valueObject.getstartTime());
                        stmt.setString(4, valueObject.getprogramSlotName());
                        stmt.setDate(5, valueObject.getweekStartDate());
                        stmt.setString(6,valueObject.getpresenter());
                        stmt.setString(7, valueObject.getproducer());
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#save(sg.edu.nus.iss.phoenix.radioprogram.entity.ProgramSlot)
	 */
	@Override
	public void saveProgramSlot(ProgramSlot valueObject) throws NotFoundException,SQLException {

		String sql = "UPDATE `program-slot` SET `duration` = ?, `dateofProgram` = ?, 'startTime'=?, `ws_startDate`=?, `producer`=?, `presenter`=? WHERE (`startTime` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setTime(1, valueObject.getduration());
			stmt.setDate(2, valueObject.getdateofProgram());
			stmt.setTime(3, valueObject.getstartTime());
                        stmt.setString(4, valueObject.getprogramSlotName());
                        stmt.setDate(5, valueObject.getweekStartDate());
                        stmt.setString(6,valueObject.getpresenter());
                        stmt.setString(7, valueObject.getproducer());
                        
			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAO#delete(sg.edu.nus.iss.phoenix.radioprogram.entity.ProgramSlot)
	 */
	@Override
	public void deleteProgramSlot(ProgramSlot valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getprogramSlotName() == null) {
			// System.out.println("Can not delete without Primary-Key!");
			throw new NotFoundException("Can not delete without Primary-Key!");
		}

		String sql = "DELETE FROM `program-slot` WHERE (`startTime` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getprogramSlotName());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}
        
        /**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * resultset will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException
	 */
        protected List<ProgramSlot> listProgramSlotQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<ProgramSlot> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				ProgramSlot temp = createValueObject();
				temp.setprogramSlotName(result.getString("program-name"));
				temp.setdateofProgram(result.getDate("dateOfProgram"));
				temp.setduration(result.getTime("duration"));
                                temp.setstartTime(result.getTime("startTime"));
                                temp.setweekStartDate(result.getDate("ws_startDate"));
                                temp.setpresenter(result.getString("presenter"));
                                temp.setproducer(result.getString("producer"));
				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<ProgramSlot>) searchResults;
	}

        
        private void openConnection() {
		try {
			Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.connection = DriverManager.getConnection(DBConstants.dbUrl,
					DBConstants.dbUserName, DBConstants.dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        /**
	 * databaseUpdate-method. This method is a helper method for internal use.
	 * It will execute all database handling that will change the information in
	 * tables. SELECT queries will not be executed here however. The return
	 * value indicates how many rows were affected. This method will also make
	 * sure that if cache is used, it will reset when data changes.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException
	 */
        protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

}
