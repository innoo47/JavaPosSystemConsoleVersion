package com.JavaPosSystem;

import java.util.Scanner;

public class LoginView {
	Scanner scanner = new Scanner(System.in);
	LoginViewModel login = new LoginViewModel();
	MainMenuView mainMenuView = new MainMenuView();
	Login_Model privacyData = Login_Model.getInstance();

	public void view() {

		System.out.println("+---------------------------------------+");
		System.out.println("|    JAVA POS SYSTEM CONSOLE VERSION    |");
		System.out.println("+---------------------------------------+");
		System.out.println("|                 LOGIN                 |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ID 입력 : ");
		String scanId = scanner.nextLine();
		privacyData.setId(scanId);
		System.out.print("| PW 입력 : ");
		String scanPw = scanner.nextLine();
		privacyData.setPw(scanPw);

		if (login.login(privacyData.getId(), privacyData.getPw()) == true) {
			// 로그인 성공시 알바비 카운트 시작
			MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
			mainMenuViewModel.startTracking();
			mainMenuView.mainMenuView();
		} else {
			System.out.println();
			this.view();
		}

	}

}
