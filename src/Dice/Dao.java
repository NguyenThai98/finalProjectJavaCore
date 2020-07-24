package Dice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Dao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	
	public Dao() {
		 
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	public boolean addUser(String NAME, String PASSWORD,String MONEY, String info) {
		try {
			String queryString = "Insert into "+ "users(NAME,PASSWORD,MONEY,typeOfUser,info)" + "Values(?,?,?,?,?)";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			ptmt.setString(1, NAME);
			ptmt.setString(2, PASSWORD);
			ptmt.setString(3, MONEY);
			ptmt.setString(4, "1");
			ptmt.setString(5, info);
			
			ptmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Dto findUser (String NAME, String PW) {
		try {
			
			String queryString = "SELECT * FROM USERS WHERE NAME = ? AND  PASSWORD = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			ptmt.setString(1, NAME );
			ptmt.setString(2, PW );
			
			resultSet = ptmt.executeQuery();
			
//			List<Dto> dtoList = new ArrayList<Dto>();
			Dto test = new Dto();
			
			while(resultSet.next()) {
				test.setId(resultSet.getInt(1));
				test.setNAME(resultSet.getString(2));
				test.setMONEY(resultSet.getString(4));
				test.setTypeOfUser(resultSet.getString(5));
				 
			}
			
			return test;
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null; 
		 
	}
	
	public boolean updateUser (int id,String info) {
		try {
			String queryString = "update users set info = ? WHERE id  = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, info);
			ptmt.setInt(2, id);
			
			resultSet = ptmt.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
		 
	}
	
	public void findAllUser () {
		try {
			String queryString = "SELECT * FROM USERS";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
	
			resultSet = ptmt.executeQuery();
			
			while(resultSet.next()) {
				 System.out.println("ID: " + resultSet.getString("id") + " Info: " + resultSet.getString("info"));
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		 
	}
	
	public int getMoney (int idUser ,int total, int getMoney) {
		try {
			getMoney = total - getMoney;
			//update users set info = ? WHERE id  = ?
			String queryString = "update users set money = ? where id = ?";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			ptmt.setInt(1, getMoney);
			ptmt.setInt(2, idUser);
			
			resultSet = ptmt.executeQuery();
			
			return getMoney;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	return getMoney;	 
	}
	
	
	
}
