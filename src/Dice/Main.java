package Dice;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean cfAcount = false;
		String isCheckAcount;
		String Name;
		String Pw = "";
		String Money;
		String info;
		String bet;
		boolean isPlayGame = true;
		int betMoney = 0;
		int inputMoney = 0;
		int orderNumber = -1;
		int idUserUpdate;
		int getMoney = 0;
		int idGame = -1;
		String rsBet;
		boolean checkNumber = false,checkMoney = false,checkBet = false,continu = false,checkNumberMoney = false;
		String ContentInfo;
		
		
		
		Dto dto = new Dto();
		
		Dto admin = new Dto();
		
		Users user = new Users();
		
		Dao dao = new Dao();
		
		
		admin = dao.findUser("admin", "admin");
		do {
			System.out.print("Do you have account? y / n: ");
			isCheckAcount = sc.next();
			if(isCheckAcount.equalsIgnoreCase("y")) {
				
				System.out.println("LOGIN");
				
				System.out.print("input name: ");
				dto.setNAME(sc.next());
				
				System.out.print("input password: ");
				dto.setPASSWORD(sc.next());
				
				Name = dto.getNAME();
				
				Pw = dto.getPASSWORD();
				
				dto = user.login(Name, Pw, dao);
				
				
				if(dto.getNAME() != null) {
					cfAcount = true;
				}else {
					System.out.println("username or password not valid!!!");
					cfAcount = false;
				}
				
				
			 }else if(isCheckAcount.equalsIgnoreCase("n")) {
				 
				 System.out.println("Register");
				 
				 System.out.print("input name: ");
				 dto.setNAME(sc.next());
				 
				 System.out.print("input password: ");
				 dto.setPASSWORD(sc.next());
				 
				 System.out.print("input money: ");
				 dto.setMONEY(sc.next());
				  
				 sc.nextLine();
				 
				 System.out.print("input info: ");
				 dto.setInfo(sc.nextLine());
				  
				  Name = dto.getNAME();
				  Pw = dto.getPASSWORD();
				  Money = dto.getMONEY();
				  info = dto.getInfo();
				  
				 if(user.register(Name, Pw,Money,info, dao)) {
					 System.out.println("Register Success !!");
					 cfAcount = false;
				 }
			 }else {
				System.out.println("Do you undersand ?");
				cfAcount = false;
			}
		} while (cfAcount == false);
		
		
		
		if(dto.getTypeOfUser().equalsIgnoreCase("0")) {
			do {
				System.out.println();
				user.showCvAdmin();
				while(checkNumber == false ) {
					System.out.print("order number: ");
					if(sc.hasNextInt()) {
						orderNumber = sc.nextInt();
						if(orderNumber != 0 && orderNumber != 1 && orderNumber != 2 && orderNumber != 3 && orderNumber != 4 && orderNumber != 5) {
							System.out.println("You just select 0 1 2 3 4 5");
							checkNumber = false;
						}else {
							checkNumber = true;
						}
					}else {
						System.out.println("Do you understand ?");
						sc.next();
						checkNumber = false;
					}
				}
				
				if(orderNumber == 0) {
					user.showUser(dao);
					System.out.println();
					System.out.print("select id user want update info: ");
					idUserUpdate = sc.nextInt();
					sc.nextLine();
					
					System.out.printf("Content update info: ");
					ContentInfo = sc.nextLine();
					
					dto.setInfo(ContentInfo);
					
					ContentInfo = dto.getInfo();
					
					if(user.updateUser(idUserUpdate, ContentInfo, dao)) {
						System.out.println("Update success!!! \n");
						cfAcount = true;
						checkNumber = false;
						 
					};
					
				}else if(orderNumber == 2) {
					do {
						System.out.print("Do you want get money number(divided by 100)? : ");
						if(sc.hasNextInt()) {
							getMoney = sc.nextInt();
							if(getMoney > Integer.valueOf(dto.getMONEY())) {
								System.out.println("getMoney < totalMoneyUser");
								checkMoney = false;
							}else {
								int rsMoney = Integer.valueOf(dto.getMONEY()) - user.getMoney(dto.getId(), Integer.valueOf(dto.getMONEY()), getMoney, dao);
								
								dto.setMONEY(String.valueOf(user.getMoney(dto.getId(), Integer.valueOf(dto.getMONEY()), getMoney, dao)));
								if(getMoney == rsMoney) {
									user.bill(dto.getId(), dto.getNAME(), getMoney);
									checkMoney = true;
									checkNumber = false;
								}
							}
						}else {
							System.out.println("Do you understand?");
							sc.next();
							checkMoney = false;
						}
						 
					} while (checkMoney == false || getMoney % 100 != 0);
					
					 
				}else if(orderNumber == 1) {
					 System.out.println("-------------------List history game-------------------");
					 user.showHistory(dao);
					 System.out.println("-------------------End history game-------------------");
					 checkNumber = false;
					 while(checkNumber == false ) {
						 System.out.print("Which game id would you like to delete: ");
							if(sc.hasNextInt()) {
								idGame = sc.nextInt();
								checkNumber = true;
							}else {
								System.out.println("Do you understand number id ?");
								sc.next();
								checkNumber = false;
							}
						}
					 user.delHistoryGame(idGame, dao);
					 user.delGame(idGame, dao);
					 cfAcount = true;
					 checkNumber = false;
					  
				}else if(orderNumber == 3) {
					 System.out.print("input String new password: ");
					 Pw = sc.next();
					 user.updatePassword(dto.getId(), Pw, dao);
					 System.out.print("Change password success !!! \n");
					 cfAcount = true;
					 checkNumber = false;
				}else if(orderNumber == 4) {
					do {
						System.out.printf("input money: ");
						if(sc.hasNextInt()) {
							inputMoney = sc.nextInt();
							checkNumberMoney = true;
						}else{
							System.out.println("input money please!!");
							sc.next();
							checkNumberMoney = false;
						}
					} while (checkNumberMoney == false);
					 inputMoney = Integer.parseInt(dto.getMONEY()) + inputMoney;
					 user.inputMoney(dto.getId(), inputMoney, dao);
					
					 cfAcount = true;
					 checkNumber = false;
				}else if(orderNumber == 5) {
					System.out.print("Sea you again !!!");
					 return;
				}
			} while (cfAcount == true);
			
		}else {
			
			do {
				System.out.println();
				user.showCvUser();
				checkNumber = false;
				while(checkNumber == false ) {
					System.out.print("order number: ");
					if(sc.hasNextInt()) {
						orderNumber = sc.nextInt();
						if(orderNumber != 0 && orderNumber != 1 && orderNumber != 2 && orderNumber != 3 && orderNumber != 4 && orderNumber != 5 && orderNumber != 6) {
							System.out.println("You just select 0 1 2 3 4 5 6");
							checkNumber = false;
						}else {
							checkNumber = true;
						}
					}else {
						System.out.println("Do you understand ?");
						sc.next();
						checkNumber = false;
					}
				}
				
				if(orderNumber == 0) {
					System.out.printf("---------------DICE GAME START--------------- \n");
					System.out.println("Do you want to bet left(1,2,3,4,5) or right(6,7,8,9,10)?");
					do {
						do {
							System.out.println();
							System.out.printf("you select left or right: ");
							bet = sc.next();
							if(bet.equalsIgnoreCase("left")) {
								checkBet = true;
							}else if(bet.equalsIgnoreCase("right")){
								checkBet = true;
							}else {
								System.out.println("you just choose left or right...");
								checkBet = false;
							}
						} while (checkBet == false);
						
						do {
							System.out.printf("You bet money: ");
							if(sc.hasNextInt()) {
								betMoney = sc.nextInt();
								checkNumberMoney = true;
								isPlayGame = true;
								if(betMoney > Integer.parseInt(dto.getMONEY())) {
									System.out.println("bet money > your money please choose 4/input money");
									checkNumberMoney = true;
									isPlayGame = false;
								}else {
									checkNumberMoney = true;
									
								}
							}else{
								System.out.println("input money please!!");
								sc.next();
								checkNumberMoney = false;
							}
						} while (checkNumberMoney == false);
						 
						
						
					} while (checkBet == false);
					
					if(isPlayGame == true) {
						playGame playGame = new playGame();
						
						Thread thread = new Thread(playGame, "A");
						thread.start();
						try {
							thread.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(user.checkBet(playGame.dto.getResultGame()) == true) {
							rsBet = "Left";
						}else{
							rsBet = "Right";
						}
						 
						if(bet.equalsIgnoreCase(rsBet)) {
							DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
							Date date = new Date();
							admin.setMONEY(String.valueOf(Integer.parseInt(admin.getMONEY()) - betMoney));
							
							user.updateMoneyUser(admin.getId(), Integer.parseInt(admin.getMONEY()), dao);
							
							
							dto.setMONEY(String.valueOf(Integer.parseInt(dto.getMONEY()) + betMoney));
							
							user.updateMoneyUser(dto.getId(), Integer.parseInt(dto.getMONEY()), dao);

							user.InsertGame(String.valueOf(dateFormat.format(date)), rsBet, dao);
							
							idGame = user.selectIdNewGame(dao);
							 
							
							user.InserthHistoryGame(idGame, dto.getId(),"Win", dao);
							
							System.out.println("you win: money you: " +   dto.getMONEY());
							
						}else {
							DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
							Date date = new Date();
							admin.setMONEY(String.valueOf(Integer.parseInt(admin.getMONEY()) + betMoney));
							
							user.updateMoneyUser(admin.getId(), Integer.parseInt(admin.getMONEY()), dao);
							
							
							dto.setMONEY(String.valueOf(Integer.parseInt(dto.getMONEY()) - betMoney));
							
							user.updateMoneyUser(dto.getId(), Integer.parseInt(dto.getMONEY()), dao);
	  
							 
						
							user.InsertGame(String.valueOf(dateFormat.format(date)), rsBet, dao);
							
							idGame = user.selectIdNewGame(dao);

							user.InserthHistoryGame(idGame, dto.getId(),"Lost", dao);
							
							System.out.println("you lost: money you: " +   dto.getMONEY());
							 
						}
					}
					
					continu = false;
					
				}else if(orderNumber == 1) {
					user.ShowInfoUser(dto.getId(), dao);
					continu = false;
				}else if(orderNumber == 2) {
					 System.out.print("input String new password: ");
					 Pw = sc.next();
					 user.updatePassword(dto.getId(), Pw, dao);
					 System.out.print("Change password success !!! \n");
					 cfAcount = true;
					 checkNumber = false;
					continu = false;
				}else if(orderNumber == 3) {
					do {
						System.out.print("Do you want get money number(divided by 100)? : ");
						if(sc.hasNextInt()) {
							getMoney = sc.nextInt();
							if(getMoney > Integer.valueOf(dto.getMONEY())) {
								System.out.println("getMoney < totalMoneyUser please input money");
								checkMoney = false;
							}else {
								int rsMoney = Integer.valueOf(dto.getMONEY()) - user.getMoney(dto.getId(), Integer.valueOf(dto.getMONEY()), getMoney, dao);
								
								dto.setMONEY(String.valueOf(user.getMoney(dto.getId(), Integer.valueOf(dto.getMONEY()), getMoney, dao)));
								if(getMoney == rsMoney) {
									user.bill(dto.getId(), dto.getNAME(), getMoney);
									checkMoney = true;
									checkNumber = false;
									continu = false;
								}
							}
						}else {
							System.out.println("Do you understand?");
							sc.next();
							checkMoney = false;
						}
						 
					} while (checkMoney == false || getMoney % 100 != 0);
				}else if(orderNumber == 4) {
					do {
						System.out.printf("input money: ");
						if(sc.hasNextInt()) {
							inputMoney = sc.nextInt();
							checkNumberMoney = true;
						}else{
							System.out.println("input money please!!");
							sc.next();
							checkNumberMoney = false;
						}
					} while (checkNumberMoney == false);
					inputMoney = Integer.parseInt(dto.getMONEY()) + inputMoney;
					dto = user.inputMoney(dto.getId(), inputMoney, dao);
					continu = false;
				}
				else if(orderNumber == 5) {
					 System.out.println("---------------List history game---------------");
					 user.showHistoryUser(dto.getId(), dao);
					 System.out.println("-------------------End history game-------------------");
					 continu = false;
				}else if(orderNumber == 6) {
					System.out.println("See you again!!!");
					return;
				}
			} while (continu == false);
		}
		
		 
	}
}
