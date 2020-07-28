package Dice;

import java.util.Random;

public class playGame implements Runnable {
	int max = 10;
	int min = 1;
	int diff = max - min;
	Random rn = new Random();
	public int kq = rn.nextInt(diff + 1) + min;
	boolean checkIn = true;
	Dto dto = new Dto();
 
	@Override
	public void run() {
		
			for (int i = 4; i >= 0 ; i--) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {}
				if(checkIn == true) {
					System.out.printf("Game shake the dice stop in 5s... %d ",i);
					checkIn = false;
				}else {
					System.out.print(i + " ");
				}
			}
			dto.setResultGame(kq);
			System.out.println();
			System.out.println("Result game: " + dto.getResultGame());
			
	}
}
