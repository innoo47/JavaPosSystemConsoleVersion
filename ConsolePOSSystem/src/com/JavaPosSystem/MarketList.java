package com.JavaPosSystem;

import java.util.LinkedList;

public class MarketList {

	private static MarketList singleton = new MarketList();

	private MarketList() {

		// 0
		this.itemName.add("우유");
		this.price.add(1200);
		this.expirationDate.add(1);
		this.adultCheck.add(false);
		this.number.add(10);

		// 1
		this.itemName.add("소주");
		this.price.add(1900);
		this.expirationDate.add(0);
		this.adultCheck.add(true);
		this.number.add(5);

		// 2
		this.itemName.add("담배");
		this.price.add(4500);
		this.expirationDate.add(365);
		this.adultCheck.add(true);
		this.number.add(12);

		// 3
		this.itemName.add("두부");
		this.price.add(1500);
		this.expirationDate.add(1);
		this.adultCheck.add(false);
		this.number.add(5);

		// 4
		this.itemName.add("오뎅");
		this.price.add(1000);
		this.expirationDate.add(1);
		this.adultCheck.add(false);
		this.number.add(15);

		// 5
		this.itemName.add("샴푸");
		this.price.add(15000);
		this.expirationDate.add(365);
		this.adultCheck.add(false);
		this.number.add(3);

		// 6
		this.itemName.add("주스");
		this.price.add(4000);
		this.expirationDate.add(120);
		this.adultCheck.add(false);
		this.number.add(6);

		// 7
		this.itemName.add("라면");
		this.price.add(5000);
		this.expirationDate.add(60);
		this.adultCheck.add(false);
		this.number.add(8);

		// 8
		this.itemName.add("맥주");
		this.price.add(2500);
		this.expirationDate.add(365);
		this.adultCheck.add(true);
		this.number.add(5);

		// 9
		this.itemName.add("냄비");
		this.price.add(30000);
		this.expirationDate.add(0);
		this.adultCheck.add(false);
		this.number.add(3);
	}

	public static synchronized MarketList getInstance() {
		return singleton;
	}

	/* 데이터의 추가 및 삭제가 빈번하게 일어나는 경우 LinkedList가 좋다고 하여 LinkedList로 진행 */
	// 물품 이름 리스트
	LinkedList<String> itemName = new LinkedList<String>();
	// 물품 가격 리스트
	LinkedList<Integer> price = new LinkedList<Integer>();
	// 물품 유통기한 리스트
	LinkedList<Integer> expirationDate = new LinkedList<Integer>();
	// 물품 미성년자 금지 물품 체크 리스트
	LinkedList<Boolean> adultCheck = new LinkedList<Boolean>();
	// 물품의 개수 리스트
	LinkedList<Integer> number = new LinkedList<Integer>();

	// 물품 추가 메서드
	public void addItem(String itemName, int price, int expirationDate, boolean adultCheck, int number) {
		this.itemName.add(itemName);
		this.price.add(price);
		this.expirationDate.add(expirationDate);
		this.adultCheck.add(adultCheck);
		this.number.add(number);
	}

	// 중복 물품 추가 메서드
	public void setItem(int index, int result) {
		this.number.set(index, result);
	}

	// 물품 제거 메서드
	public void removeItem(int index) {
		if (index >= 0 && index < itemName.size()) {
			itemName.remove(index);
			price.remove(index);
			expirationDate.remove(index);
			adultCheck.remove(index);
			number.remove(index);
		}
	}

}
