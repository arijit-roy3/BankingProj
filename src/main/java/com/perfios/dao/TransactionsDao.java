package com.perfios.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.perfios.pojo.Transactions;

public class TransactionsDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/EazyBank?useSSL=true";
	private String jdbctransactionname = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_TRANSACTIONS_SQL = "INSERT INTO transactions" + "  (account_from, account_to,amount,tdate,ttype) VALUES "
			+ " (?, ?, ?,?,?);";

	
	private static final String SELECT_ALL_TRANSACTIONS = "select * from transactions;";
	private static final String DELETE_TRANSACTIONS_SQL = "delete from transactions where tid = ?;";
	

	
	public TransactionsDao() {
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbctransactionname, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertTransaction(Transactions transaction) throws SQLException {
		System.out.println(INSERT_TRANSACTIONS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTIONS_SQL)) {
			preparedStatement.setInt(1, transaction.getFrom());
			preparedStatement.setInt(2, transaction.getTo());
			preparedStatement.setDouble(3, transaction.getAmount());
			preparedStatement.setDate(4, (Date) transaction.getDate());
			preparedStatement.setString(5, transaction.getType());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Transactions> selectAllTransactions() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Transactions> Transactions = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRANSACTIONS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("tid");
				int from = rs.getInt("account_from");
				int to = rs.getInt("account_to");				
				double amount=rs.getDouble("amount");
				Date date=rs.getDate("tdate");
				String type = rs.getString("ttype");
				
				Transactions.add(new Transactions(id, from,to,amount,date,type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Transactions;
	}
	public boolean deletetransaction(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TRANSACTIONS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

}
