package com.perfios.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.perfios.pojo.Loan;
import com.perfios.pojo.Users;

public class LoanDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/EazyBank?useSSL=true";
	private String jdbcloanname = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_LOAN_SQL = "INSERT INTO Loan" + "  (name,cibilScore,age,salary,amount,designation,company,tenure,status) VALUES "
			+ " (?, ?, ?,?,?,?,?,?,?);";

	
	private static final String SELECT_ALL_LOAN = "select * from Loan";
	private static final String DELETE_LOAN_SQL = "delete from Loan where lid = ?;";
	private static final String UPDATE_LOAN_SQL = "update Loan set status = ? where lid = ?;";
	public LoanDao() {}
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcloanname, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public int insertLoan(Loan loan) throws SQLException {
		System.out.println(INSERT_LOAN_SQL);
		int n=0;
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOAN_SQL)) {
			preparedStatement.setString(1, loan.getName());
			preparedStatement.setInt(2, loan.getCibilScore());
			preparedStatement.setInt(3, loan.getAge());
			preparedStatement.setDouble(4, loan.getSalary());
			preparedStatement.setDouble(5, loan.getAmount());
			preparedStatement.setString(6, loan.getDesignation());
			preparedStatement.setString(7, loan.getCompany());
			preparedStatement.setInt(8, loan.getTenure());
			preparedStatement.setString(9, loan.getStatus());
			System.out.println(preparedStatement);
			n=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public List<Loan> selectAllLoans() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Loan> loans = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOAN);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("lid");				
				String name = rs.getString("name");
				int cibilScore=rs.getInt("cibilScore");				
				int age=rs.getInt("age");
				double salary=rs.getDouble("salary");
				double amount=rs.getDouble("amount");
				String designation=rs.getString("designation");
				String company=rs.getString("company");
				int tenure=rs.getInt("tenure");
				String status=rs.getString("status");
				loans.add(new Loan(id,cibilScore,age,salary,amount,designation,company,name,tenure,status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loans;
	}

	public boolean deleteLoan(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LOAN_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean changeStatus(int id) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LOAN_SQL);) {
			statement.setString(1, "approved");
			statement.setInt(2, id);
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}


}
