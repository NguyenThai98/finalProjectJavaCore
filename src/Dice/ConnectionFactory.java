package Dice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	// DB connection info must be private field
	private String driverClassName = "org.mariadb.jdbc.Driver";
	private String connectionUrl = "jdbc:mariadb://localhost:3306/dice";
	private String dbUser = "root";
	private String dbPwd = "nguyenthai";
	
	private static ConnectionFactory connectionFactory = null;
	private Connection conn = null;
	
	private ConnectionFactory() {
		try {
			Class.forName(driverClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException{
		if(conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(connectionUrl,dbUser,dbPwd);
		}
		return conn;
	}
	public static ConnectionFactory getInstance(){
		if(connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
}
