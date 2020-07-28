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
	public void delGame (int id) {
		try {
			String queryString = "delete from game where id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
			System.out.print("Delete game success !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptmt != null) {
					ptmt.close();
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
	public void delHistoryGame (int id) {
		try {
			String queryString = "delete from historygame where IDGAME = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptmt != null) {
					ptmt.close();
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
	public void addGame(String time, String rs) {
		try {
			String queryString = "Insert into "+ "game(time,result)" + "Values(?,?)";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			ptmt.setString(1, time);
			ptmt.setString(2, rs);
			
			ptmt.executeUpdate();
			 
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
	public void InserthHistoryGame(int idGame, int idUser,String rs) {
		try {
			String queryString = "Insert into "+ "historygame(IDGAME,USERID,RESULT)" + "Values(?,?,?)";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			ptmt.setInt(1, idGame);
			ptmt.setInt(2, idUser);
			ptmt.setString(3, rs);
			ptmt.executeUpdate();
			 
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
	
	public int selectIdNewGame () {
		try {
			
			String queryString = "SELECT id FROM game ORDER BY id DESC LIMIT 1";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);

			resultSet = ptmt.executeQuery();
			
//			List<Dto> dtoList = new ArrayList<Dto>();
			Dto test = new Dto();
			
			while(resultSet.next()) {
				test.setIdGame(resultSet.getInt("id"));		 
			}
			
			return test.getIdGame();
			 
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
		return -1; 
		 
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
	
	public Dto inputMoney (int id,int Money) {
		try {
			String queryString = "update users set MONEY = ? WHERE id  = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, Money);
			ptmt.setInt(2, id);
			
			resultSet = ptmt.executeQuery();
			Dto dto = new Dto();
			
			dto.setId(id);
			
			dto.setMONEY(String.valueOf(Money));
			
			System.out.println("input money success !!!");
			System.out.println("total money user: " + dto.getMONEY() + "VND");
			return dto;
			
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
	
	public boolean updatePassword (int id,String pw) {
		try {
			String queryString = "update users set PASSWORD = ? WHERE id  = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, pw);
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
	public void showProfileUser (int id) {
		try {
			String queryString = "SELECT * FROM USERS where id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();
			
			while(resultSet.next()) {
				 System.out.println("**********************************************");
				 System.out.println("NAME: " + resultSet.getString("NAME") + " ===== MONEY: " + resultSet.getString("MONEY") + " ===== INFO: " + resultSet.getString("info"));
				 System.out.println("**********************************************");
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
	
	public void showHistory () {
		try {
			//update users set info = ? WHERE id  = ?
			String queryString = "select * from  game";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			resultSet = ptmt.executeQuery();
			while(resultSet.next()) {
				System.out.println("id: " + resultSet.getString(1) + " || " + "time: " + resultSet.getString(2) + " || " +  "result: " + resultSet.getString(3));
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
	public void showHistoryUser (int id) {
		try {
			//update users set info = ? WHERE id  = ?
			String queryString = "SELECT u.NAME, h.RESULT,g.time FROM users u JOIN historygame h ON u.id = h.USERID JOIN game g ON h.IDGAME = g.id WHERE h.USERID = ?";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();
			while(resultSet.next()) {
				System.out.println("Name: " + resultSet.getString(1) + " || " + "Time: " + resultSet.getString(3) + " || " +  "Result: " + resultSet.getString(2));
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
	public boolean checkIdGame (int idGame) {
		try {
			String queryString = "SELECT * FROM game where id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			ptmt.setInt(1, idGame);
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
	public void updateMoneyUser (int id,int money) {
		try {
			String queryString = "update users set MONEY = ? WHERE id  = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			ptmt.setInt(1, money);
			ptmt.setInt(2, id);
			
			resultSet = ptmt.executeQuery();
			
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
	
	
}
