package Dice;

import java.util.Random;

// Class name startwith Upper case ????????????????
// 소문자로 하면 다 지워버릴거에요오오오오오오오오오오오오오
public class playGame implements Runnable {
	// 아주 잘못했어요................... dont write operation when field define
	Random rn = new Random();
	Dto dto = new Dto();
	private int max;
	private int min;
	private int diff;
	private int kq;
	private boolean checkIn;
	
	@Override
	public void run() {
		checkIn = true;
		max = 10;
		min= 1;
		diff= max - min;
		kq = rn.nextInt(diff + 1) + min;
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
