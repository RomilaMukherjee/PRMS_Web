/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.programschedule.dao.ProgramScheduleDao;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author Ragu
 */
public class ProgramScheduleDaoImpl implements ProgramScheduleDao{
    Connection connection;
    
    @Override
    public List<AnnualSchedule> loadAllAnnualSchedule() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `annual-schedule` ORDER BY `year` ASC; ";
		List<AnnualSchedule> searchResults = listQuery(connection
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
    
    protected List<AnnualSchedule> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<AnnualSchedule> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				AnnualSchedule temp = createValueObject();

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
        
    @Override
	public AnnualSchedule createValueObject() {
		return new AnnualSchedule();
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
        
        protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

}
