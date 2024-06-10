package com.JavaPosSystem;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenuViewModel {

	Scanner scanner = new Scanner(System.in);
	MarketList marketList = MarketList.getInstance();
	Date_Extension date = Date_Extension.getInstance();
	MainMenu_Model mainMenuModel = MainMenu_Model.getInstance();

	// [1] 재고확인 뷰 모델
	public void checkStockViewModel() {
		for (int i = 0; i < marketList.itemName.size(); ++i) {
			System.out.print("| ");
			System.out.print(marketList.itemName.get(i));
			System.out.print("\t|");
			System.out.printf(" %d개\t|", marketList.number.get(i));
			int n = marketList.number.get(i);
			for (int j = 0; j < n; ++j) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	// [2] 현재 잔고 확인 뷰 모델
	public void currentBalanceCheckViewModel() {
		System.out.printf("| 현재잔고 : %s원 입니다.\r\n", mainMenuModel.getMoney());
	}

	// [3] 매출액 뷰 모델
	public void salesViewModel() {
		System.out.printf("| 매 출 액 : %s원 입니다.\r\n", mainMenuModel.getSales());

	}

	// [4] 유통기한 확인 뷰 모델
	public void expirationDateViewModel() {
		for (int i = 0; i < marketList.itemName.size(); ++i) {
			System.out.print("| ");
			System.out.print(marketList.itemName.get(i));
			System.out.print("	");
			if (mainMenuModel.expirationHourItem.contains(marketList.itemName.get(i))) {
				String expirationDateHour = date.expirationDateHourCheck(marketList.expirationDate.get(i));
				System.out.println(expirationDateHour);
			} else {
				String expirationDate = date.expirationDateDayCheck(marketList.expirationDate.get(i));
				System.out.println(expirationDate);
			}
		}

	}

	// [6] 로그아웃 메서드
	public void logoutViewModel() {
		this.currentBalanceCheckViewModel();
		this.salesViewModel();
		System.out.println("| 현재 알바비 : " + mainMenuModel.getTotalWage() + "원");
		System.out.println("| 로그인 시간 : " + date.login);
		date.logoutCheck();
		System.out.println("| 로그아웃 시간 : " + date.logout);
		date.workingHours();
		System.out.println("+---------------------------------------+");
	}

	// 분당 알바비 증가 메서드
	public void startTracking() {
		Timer timer = new Timer();
		MainMenuView mainMenuView = new MainMenuView();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				mainMenuModel.addWage();
			}
		}, 0, 60000);

	}

	// 되돌아가는 메서드
	public void back() {
		MainMenuView mainMenuView = new MainMenuView();

		System.out.println("+---------------------------------------+");
		System.out.println("|        뒤로 가려면 back을 입력하세요        |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ");
		String str = scanner.nextLine();

		if (str.equals("back")) {
			System.out.println("+---------------------------------------+");
			System.out.println();
			System.out.println();
			mainMenuView.mainMenuView();
		} else {
			System.out.println("+---------------------------------------+");
			System.out.println("|    잘못 입력하셨습니다. 다시 입력해 주세요.    |");
			System.out.println("+---------------------------------------+");
			this.back();
		}
	}

}
