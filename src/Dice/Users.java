package Dice;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
public class Users implements IFunction {

	
 
	Scanner sc = new Scanner(System.in);
	@Override
	public Dto login(String Name, String Password, Dao dao) {
		
		Dto dto = new Dto();
		
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
			FileWriter obj = new FileWriter("D:\\NTThai\\KITS-03\\JAVA\\Dice\\fileBill\\" + name+ "-" + code + ".txt");
			obj.write("YourName " + name + ": " + "You have withdrawn money: " + getMoney + "VND" + " time: " + new Date());
			obj.close();
			System.out.print("You have withdrawn money: "+ getMoney + " please take bill in fileBill... \n");
			
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
	}
 
	
	
}
