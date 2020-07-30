package Dice;

import java.util.List;

public interface IFunction {
	
	
	Dto login(String Name, String Password, Dao dao);
	
	boolean register(String Name, String Password,String MONEY, String info, Dao dao);
	
	boolean showUser(Dao dao);
	
	boolean updateUser(int id, String info,Dao dao);
	int getMoney(int idUser,int total, int getMoney,Dao dao);
	void bill(int id,String name,int getMoney);
	void showHistory(Dao dao);
	boolean checkIdGame(int idGame,Dao dao);
	void updateMoneyUser(int id,int money,Dao dao);
	void showHistoryUser(int id,Dao dao);
	boolean updatePassword(int id, String pw,Dao dao);
	
	void delGame(int id,Dao dao);
	void delHistoryGame(int id,Dao dao);
	
	Dto inputMoney(int id, int Money,Dao dao);
	
	void InsertGame(String time,String rs,Dao dao);
	
	int selectIdNewGame(Dao dao);
	
	List<Integer> allIDgame(Dao dao);
	
	void InserthHistoryGame(int idGame,int idUser,String rs,Dao dao);
}


/// nhap us pw
