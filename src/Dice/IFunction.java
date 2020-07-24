package Dice;

public interface IFunction {
	
	String[] functionAdmin = {"updateInfoUser", "historyGame","getMoney","exit"};
	
	String[] functionUser = {"playGame","exit","showProfile"};
	
	Dto login(String Name, String Password, Dao dao);
	
	boolean register(String Name, String Password,String MONEY, String info, Dao dao);
	boolean showUser(Dao dao);
	boolean updateUser(int id, String info,Dao dao);
	int getMoney(int idUser,int total, int getMoney,Dao dao);
	void bill(int id,String name,int getMoney);
	
	
}


/// nhap us pw
