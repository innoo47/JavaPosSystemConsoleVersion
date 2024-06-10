package com.JavaPosSystem;

import java.util.Scanner;

public class MainMenuView {
	Scanner scanner = new Scanner(System.in);
	MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
	MainMenu_Model mainMenuModel = MainMenu_Model.getInstance();

	// 메인 메뉴 뷰
	public void mainMenuView() {

		System.out.println("+---------------------------------------+");
		System.out.println("|    JAVA POS SYSTEM CONSOLE VERSION    |");
		System.out.println("+---------------------------------------+");
		System.out.println("|               MAIN MENU               |");
		System.out.println("+---------------------------------------+");
		System.out.println("| 현재 알바비 : " + mainMenuModel.getTotalWage() + "원" + "\t\t\t|");
		System.out.println("+---------------------------------------+");
		System.out.println("|     [1] 재고확인		[2] 현재 잔고 확인 |");
		System.out.println("|     [3] 매출액		[4] 유통기한      |");
		System.out.println("|     [5] 업무시작		[6] 로그아웃	|");
		System.out.println("+---------------------------------------+");
		System.out.print("| 메뉴 선택 : ");
		String selectMenu = scanner.nextLine();

		switch (selectMenu) {
		case "1":
			// 재고 확인 뷰로 이동
			this.checkStockView();
			break;
		case "2":
			// 현재 잔고 확인 뷰로 이동
			this.currentBalanceCheckView();
			break;
		case "3":
			// 매출액 뷰로 이동
			this.salesView();
			break;
		case "4":
			// 유통기한 확인 뷰로 이동
			this.expirationDateView();
			break;
		case "5":
			// 업무시작 뷰로 이동
			this.startWorkView();
			break;
		case "6":
			// 로그아웃
			this.logoutView();
			break;
		default:
			System.out.println("+---------------------------------------+");
			System.out.println("| 메뉴를 잘못 선택하셨습니다.  다시 선택해 주세요. |");
			System.out.println("+---------------------------------------+");
			this.mainMenuView();
		}

	}

	// [1] 재고확인 뷰
	private void checkStockView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|                재고 확인                |");
		System.out.println("+---------------------------------------+");

		mainMenuViewModel.checkStockViewModel();
		mainMenuViewModel.back();

	}

	// [2] 현재 잔고 확인 뷰
	private void currentBalanceCheckView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|              현재 잔고 확인              |");
		System.out.println("+---------------------------------------+");

		mainMenuViewModel.currentBalanceCheckViewModel();
		mainMenuViewModel.back();

	}

	// [3] 매출액 뷰
	private void salesView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|                  매출액                 |");
		System.out.println("+---------------------------------------+");

		mainMenuViewModel.salesViewModel();
		mainMenuViewModel.back();
	}

	// [4] 유통기한 확인 뷰
	private void expirationDateView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|               유통기한 확인              |");
		System.out.println("+---------------------------------------+");

		mainMenuViewModel.expirationDateViewModel();
		mainMenuViewModel.back();
	}

	// [5] 업무시작 뷰
	private void startWorkView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();

		StartWorkView workView = new StartWorkView();
		workView.workView();
	}

	// [6] 로그아웃 뷰
	private void logoutView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|            로그아웃 되었습니다.            |");
		System.out.println("+---------------------------------------+");

		mainMenuViewModel.logoutViewModel();
	}

}
