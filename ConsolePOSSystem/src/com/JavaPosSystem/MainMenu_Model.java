package com.JavaPosSystem;

import java.util.LinkedList;

public class MainMenu_Model {

	private static MainMenu_Model singleton = new MainMenu_Model();

	private MainMenu_Model() {
		this.setMoney(390500);
		this.setSales(0);
		this.expirationHourItem.add("오뎅");
	}

	public static synchronized MainMenu_Model getInstance() {
		return singleton;
	}

	// 잔고
	private int money;

	// 매출액
	private int sales;

	private static final int WAGE_PER_MINUTE = 9800;
	private int totalWage = 0;

	public void addWage() {
		this.totalWage += WAGE_PER_MINUTE;
	}

	public int getTotalWage() {
		return totalWage;
	}

	// 시간단위 유통기한 보유 물품 리스트
	LinkedList<String> expirationHourItem = new LinkedList<String>();

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}
}
