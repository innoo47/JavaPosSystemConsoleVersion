package com.JavaPosSystem;

import java.util.Scanner;

public class StartWorkView {

	Scanner scanner = new Scanner(System.in);
	StartWorkViewModel startWorkViewModel = new StartWorkViewModel();
	MainMenu_Model mainMenuModel = MainMenu_Model.getInstance();

	public void workView() {
		System.out.println("+---------------------------------------+");
		System.out.println("|    JAVA POS SYSTEM CONSOLE VERSION    |");
		System.out.println("+---------------------------------------+");
		System.out.println("|              START WORK               |");
		System.out.println("+---------------------------------------+");
		System.out.println("| 현재 알바비 : " + mainMenuModel.getTotalWage() + "원" + "\t\t\t|");
		System.out.println("+---------------------------------------+");
		System.out.println("|     [1] 물품판매		[2] 물품 환불	|");
		System.out.println("|     [3] 미성년자 금지 물품	[4] 물품 입고     |");
		System.out.println("|     [5] 재고 검색	[6] 뒤로가기	|");
		System.out.println("+---------------------------------------+");
		System.out.print("| 메뉴 선택 : ");
		String selectMenu = scanner.nextLine();

		switch (selectMenu) {
		case "1":
			// [1] 물품 판매 뷰
			this.itemSellingView();
			break;
		case "2":
			// [2] 물품 환불 뷰
			this.itemRefundView();
			break;
		case "3":
			// [3] 미성년자 금지 물품 뷰
			this.minorsBannedListView();
			break;
		case "4":
			// [4] 물품 입고 뷰
			this.warehousingView();
			break;
		case "5":
			// [5] 재고 검색 뷰
			this.searchingInventoryView();
			break;
		case "6":
			// [6] 뒤로가기
			this.back();
			break;
		default:
			System.out.println("+---------------------------------------+");
			System.out.println("| 메뉴를 잘못 선택하셨습니다.  다시 선택해 주세요. |");
			System.out.println("+---------------------------------------+");
			this.workView();

		}
	}

	// [1] 물품 판매 뷰
	public void itemSellingView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|                물품 판매                |");

		startWorkViewModel.itemSellingViewModel();

	}

	// [2] 물품 환불 뷰
	public void itemRefundView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|                물품 환불                |");
		System.out.println("+---------------------------------------+");

		startWorkViewModel.itemRefundViewModel();

	}

	// [3] 미성년자 금지 물품 뷰
	public void minorsBannedListView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|           미성년자 금지 물품 목록          |");

		startWorkViewModel.minorsBannedListViewModel();
	}

	// [4] 물품 입고 뷰
	public void warehousingView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|                물품 입고                |");
		System.out.println("+---------------------------------------+");

		startWorkViewModel.warehousingViewModel();

	}

	// [5] 재고 검색 뷰
	public void searchingInventoryView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|                재고 검색                |");
		System.out.println("+---------------------------------------+");

		startWorkViewModel.searchingInventoryViewModel();

	}

	// [6] 뒤로가기
	private void back() {
		// MainMenu 객체를 메소드 밖에서 생성시
		// StackOverFlow가 발생
		MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
		mainMenuViewModel.back();

	}
}
