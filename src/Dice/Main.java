package Dice;
 
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean cfAcount = false;
		String isCheckAcount;
		String Name;
		String Pw;
		String Money;
		String info;
		int orderNumber = -1;
		int idUserUpdate;
		int getMoney = 0;
		boolean checkNumber = false;
		String ContentInfo;
		
		
		
		Dto dto = new Dto();
		
		Users user = new Users();
		
		Dao dao = new Dao();
		
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
						if(orderNumber != 0 && orderNumber != 1 && orderNumber != 2 && orderNumber != 3) {
							System.out.println("You just select 0 1 2 3");
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
					};
					
				}else if(orderNumber == 2) {
					do {
						System.out.print("Do you want get money number(divided by 100)? : ");
						if(sc.hasNextInt()) {
							getMoney = sc.nextInt();
							if(getMoney > Integer.valueOf(dto.getMONEY())) {
								System.out.println("getMoney < totalMoneyUser");
								checkNumber = false;
							}else {
								int rsMoney = Integer.valueOf(dto.getMONEY()) - user.getMoney(dto.getId(), Integer.valueOf(dto.getMONEY()), getMoney, dao);
								
								dto.setMONEY(String.valueOf(user.getMoney(dto.getId(), Integer.valueOf(dto.getMONEY()), getMoney, dao)));
								if(getMoney == rsMoney) {
									user.bill(dto.getId(), dto.getNAME(), getMoney);
									checkNumber = true;
								}
							}
						}else {
							System.out.println("Do you understand?");
							sc.next();
							checkNumber = false;
						}
						 
					} while (checkNumber == false || getMoney % 100 != 0);
					
					 
				}else if(orderNumber == 3) {
					System.out.print("See you again !!!");
					return;
				}
			} while (cfAcount == true);
			
		}else {
			user.showCvUser();
			while(checkNumber == false ) {
				System.out.print("order number: ");
				if(sc.hasNextInt()) {
					orderNumber = sc.nextInt();
					if(orderNumber != 0 && orderNumber != 1 && orderNumber != 2) {
						System.out.println("You just select 0 1 2");
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
			
		}
		
		 
	}
}
