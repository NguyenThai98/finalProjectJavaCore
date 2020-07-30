package Dice;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Users implements IFunction {

	Dto dto = new Dto();
 
	Scanner sc = new Scanner(System.in);
	@Override
	public Dto login(String Name, String Password, Dao dao) {
		
		dto = dao.findUser(Name, Password);
		
		if(dto.getNAME() == null) {
			return dto;
		}
		// TODO Auto-generated method stub
		return dto;
		
	}

	
	
	@Override
	public boolean register(String Name, String Password,String MONEY,String info, Dao dao) {
		// TODO Auto-generated method stub
		if(dao.addUser(Name, Password,MONEY,info)) {
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public boolean showUser(Dao dao) {
		// TODO Auto-generated method stub
		System.out.println("----------Info User----------");
		dao.findAllUser();
		System.out.println("-----------------------------");
		return false;
	}

	
	@Override
	public boolean updateUser(int id, String info,Dao dao) {
		// TODO Auto-generated method stub
		if(dao.updateUser(id,info)) {
			return true;
		}
		return false;
	}
	
	public void showCvAdmin() {
		for (int i = 0; i < functionAdmin.length; i++) {
			System.out.println("Do you want? " + i + "/ " +functionAdmin[i]);
		}
	}
	public void showCvUser() {
		for (int i = 0; i < functionUser.length; i++) {
			System.out.println("Do you want? " + i + "/ " + functionUser[i]);
		}
	}
	
	public boolean checkBet(int value) {
		for (int i = 0; i < left.length; i++) {
			if(left[i] == value) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int getMoney(int idUser,int total, int totalGetMoney,Dao dao) {
		
		totalGetMoney = dao.getMoney(idUser,total, totalGetMoney);
		// TODO Auto-generated method stub
		return totalGetMoney;
	}

	@Override
	public void bill(int id, String name, int getMoney) {
		try {

			int code = (int) Math.floor(((Math.random() * 899999) + 100000));
			FileWriter obj = new FileWriter("D:\\NTThai\\KITS-03\\JAVA\\13.NguyenTrongThai\\fileBill\\" + name+ "-" + code + ".txt");
			obj.write("YourName " + name + ": " + "You have withdrawn money: " + getMoney + "VND" + " time: " + new Date());
			obj.close();
			System.out.print("You have withdrawn money: "+ getMoney + " please take bill in fileBill... \n");
			
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
	}

	public void ShowInfoUser(int id,Dao dao) {
		dao.showProfileUser(id);
	}

	@Override
	public void showHistory(Dao dao) {
		// TODO Auto-generated method stub
		dao.showHistory();
		
	}



	@Override
	public boolean checkIdGame(int idGame,Dao dao) {
		// TODO Auto-generated method stub
		boolean check = dao.checkIdGame(idGame);
		return check;
	}



	@Override
	public void updateMoneyUser(int id, int money,Dao dao) {
		dao.updateMoneyUser(id, money);
	}



	@Override
	public boolean updatePassword(int id, String pw, Dao dao) {
		// TODO Auto-generated method stub
		if(dao.updatePassword(id, pw)) {
			return true;
		}
		return false;
	}



	@Override
	public Dto inputMoney(int id, int Money, Dao dao) {
		dto = dao.inputMoney(id, Money);
		return dto;
	}



	@Override
	public void InsertGame(String time, String rs, Dao dao) {
		dao.addGame(time, rs);
	}



	@Override
	public void delGame(int id,Dao dao) {
		dao.delGame(id);
	}



	@Override
	public void delHistoryGame(int id, Dao dao) {
		// TODO Auto-generated method stub
		dao.delHistoryGame(id);
		
	}



	@Override
	public void showHistoryUser(int id, Dao dao) {
		// TODO Auto-generated method stub
		dao.showHistoryUser(id);
		
	}



	@Override
	public void InserthHistoryGame(int idGame, int idUser,String rs, Dao dao) {
		// TODO Auto-generated method stub
		dao.InserthHistoryGame(idGame, idUser,rs);
	}



	@Override
	public int selectIdNewGame(Dao dao) {
		// TODO Auto-generated method stub
		return dao.selectIdNewGame();
	}



	@Override
	public List<Integer> allIDgame(Dao dao) {
		// TODO Auto-generated method stub
		return dao.allIDGame();
	}
 
	
	
}
