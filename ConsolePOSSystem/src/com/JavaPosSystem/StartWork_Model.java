package com.JavaPosSystem;

import java.util.LinkedList;

public class StartWork_Model {

	private static StartWork_Model singleton = new StartWork_Model();

	private StartWork_Model() {
		setQuantitySum(0);
	}

	public static synchronized StartWork_Model getInstance() {
		return singleton;
	}

	public int getQuantitySum() {
		return quantitySum;
	}

	public void setQuantitySum(int quantitySum) {
		this.quantitySum = quantitySum;
	}

	// 장바구니 리스트
	LinkedList<String> shoppingBasketList = new LinkedList<String>();
	// 장바구니 물건의 개수 리스트
	LinkedList<Integer> quantityItem = new LinkedList<Integer>();
	// 장바구니 가격 리스트
	LinkedList<Integer> paymentPrice = new LinkedList<Integer>();
	// 미성년자 금지 물품 리스트
	LinkedList<String> adultCheckingItem = new LinkedList<String>();
	// 환불 물품 리스트
	LinkedList<String> refundItemName = new LinkedList<String>();
	// 환불 물품 가격 리스트
	LinkedList<Integer> refundItemNumber = new LinkedList<Integer>();

	// 장바구니 가격 총합
	private int quantitySum;

	// 생년월일 검증
	static String birthdayPattern = "\\d{8}";

	// 카드번호 검증
	static String cardPattern = "\\d{4}-\\d{4}-\\d{8}";

}
