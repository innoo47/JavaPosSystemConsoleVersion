package com.JavaPosSystem;

public class LoginViewModel implements Loginable {
	Date_Extension date = Date_Extension.getInstance();

	@Override
	public boolean login(String id, String pw) {
		// TODO Auto-generated method stub
		System.out.println("+---------------------------------------+");

		if (id.equals("admin") && pw.equals("admin")) {
			System.out.println("| 로그인 성공                              |");
			System.out.print("| 접속 시간 : ");
			date.loginCheck();
			System.out.println("+---------------------------------------+");
			System.out.println();
			return true;

		} else {
			System.out.println("| 로그인 실패                              |");
			System.out.println("| 다시 로그인을 시도해 주세요                  |");
			System.out.println("+---------------------------------------+");
			return false;

		}
	}

}
