package com.perfios.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.perfios.pojo.Users;

public class UsersDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/EazyBank?useSSL=true";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (uname,balance,uemail,upan,upwd,umobile,uaddress) VALUES "
			+ " (?, ?, ?,?,?,?,?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set upwd = ? where id = ?;";
	private static final String CHECK_USER_BALANCE = "select balance from users where id =?";
	
	public UsersDao() {
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public int insertUser(Users user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		int r=0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getUname());
			preparedStatement.setDouble(2, user.getBalance());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPan());
			preparedStatement.setString(5, user.getPwd());
			preparedStatement.setString(6,user.getMobile());
			preparedStatement.setString(7, user.getAddress());
			System.out.println(preparedStatement);
			r=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return r;
	}

	

	public List<Users> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Users> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");				
				String name = rs.getString("uname");
				double balance=rs.getDouble("balance");
				String email = rs.getString("uemail");
				String pan = rs.getString("upan");
				String pwd = rs.getString("upwd");
				String mobile = rs.getString("umobile");
				String address = rs.getString("uaddress");
				users.add(new Users(id, name, balance,email,pan,pwd,mobile, address));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean changePassword(int id, String password) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, password);			
			statement.setInt(2, id);

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	public double getBalance(int id) throws SQLException {
		double balance=0;
		
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_BALANCE);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object.
			while (rs.next()) {				
				balance=rs.getDouble("balance");
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return balance;
		
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
