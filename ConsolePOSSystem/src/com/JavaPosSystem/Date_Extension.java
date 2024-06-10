package com.JavaPosSystem;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date_Extension {

	private static Date_Extension singleton = new Date_Extension();

	private Date_Extension() {
		this.login = null;
		this.logout = null;
		this.loginDate = this.now;
	}

	public static synchronized Date_Extension getInstance() {
		return singleton;
	}

	String login; // 로그인 시간을 String Type으로 저장
	String logout; // 로그아웃 시간을 String Type으로 저장
	LocalDateTime loginDate; // 로그인 시간을 LocalDateTime Type으로 저장
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime logoutTime;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd a HH:mm:ss");

	// 로그인 시간 체크 메서드
	public void loginCheck() {
		this.login = now.format(dtf);
		System.out.println(login + "	|");
	}

	// 로그아웃 시간 체크 메서드
	public void logoutCheck() {
		this.logoutTime = LocalDateTime.now();
		this.logout = logoutTime.format(dtf);

	}

	// 일한 시간 체크 메서드
	public void workingHours() {
		LocalDateTime startDateTime = LocalDateTime.parse(login, dtf);
		LocalDateTime endDateTime = LocalDateTime.parse(logout, dtf);

		Duration duration = Duration.between(startDateTime, endDateTime);
		long hoursDifference = duration.toHours();
		System.out.println("| 일한 시간 : " + hoursDifference + "시간");
	}

	// 유통기한 체크 메서드 (Day)
	public String expirationDateDayCheck(int plusDay) {
		if (plusDay == 0) {
			return "유통기한이 존재하지 않습니다.";
		} else {
			LocalDateTime expirationDate = loginDate.plusDays(plusDay);
			String sExpirationDate = expirationDate.format(dtf);
			return sExpirationDate;
		}
	}

	// 유통기한 체크 메서드 (Hour)
	public String expirationDateHourCheck(int plusHour) {
		if (plusHour == 0) {
			return "유통기한이 존재하지 않습니다.";
		} else {
			LocalDateTime expirationDate = loginDate.plusHours(plusHour);
			String sExpirationDate = expirationDate.format(dtf);
			return sExpirationDate;
		}
	}

	/* MARK : 수정예정 : 로직자체 변경 - 완료 */
	// 성인인증 메서드
	public boolean adultCheckingMethod(String sBirthday) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate birthDay = LocalDate.parse(sBirthday, formatter);

			LocalDate today = LocalDate.now();

			LocalDate adultDate = today.minusYears(19);

			// 비교하여 성인인지 확인
			return !birthDay.isAfter(adultDate);
		} catch (DateTimeParseException e) {
			// 날짜 형식이 잘못된 경우 false 반환
			return false;
		}
	}

}
