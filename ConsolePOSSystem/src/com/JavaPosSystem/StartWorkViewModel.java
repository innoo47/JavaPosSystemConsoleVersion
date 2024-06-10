package com.JavaPosSystem;

import java.util.Scanner;
import java.util.regex.Pattern;

public class StartWorkViewModel {

	StartWork_Model startWorkModel = StartWork_Model.getInstance();
	MarketList marketList = MarketList.getInstance();
	MainMenu_Model mainMenuModel = MainMenu_Model.getInstance();
	Scanner scanner = new Scanner(System.in);

	// [1] 물품 판매 뷰 모델
	public void itemSellingViewModel() {
		System.out.println("+---------------------------------------+");
		MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
		mainMenuViewModel.checkStockViewModel();
		System.out.println("+---------------------------------------+");
		System.out.println("|          [ 결제 ]    [ 뒤로가기 ]        |");
		System.out.println("+---------------------------------------+");
		System.out.print("| 제품명 : ");
		String itemName = scanner.nextLine();

		switch (itemName) {
		case "결제":
			this.paymentProgressing();
			break;
		case "뒤로가기":
			StartWorkView startWorkView = new StartWorkView();
			startWorkModel.shoppingBasketList.clear();
			startWorkModel.quantityItem.clear();
			startWorkModel.paymentPrice.clear();
			startWorkModel.adultCheckingItem.clear();
			startWorkModel.setQuantitySum(0);
			System.out.println("+---------------------------------------+");
			System.out.println();
			startWorkView.workView();
			break;
		default:
			if (marketList.itemName.contains(itemName) == true) {
				System.out.print("| 개 수 : ");
				String sQuantity = scanner.nextLine();
				int quantity = 0;
				try {
					quantity = Integer.parseInt(sQuantity);
				} catch (NumberFormatException e) {
					System.out.println("+---------------------------------------+");
					System.out.println("| 개수가 0보다 큰 정수가 아닙니다. 다시 입력하세요 |");
					System.out.println("+---------------------------------------+");
					this.itemSellingViewModel();
				}
				System.out.println("+---------------------------------------+");

				if (quantity <= 0) {
					System.out.println("+---------------------------------------+");
					System.out.println("| 개수가 0보다 큰 정수가 아닙니다. 다시 입력하세요 |");
					System.out.println("+---------------------------------------+");
					this.itemSellingViewModel();
					/* MARK: 수정 : 결제 시 개수가 초과하더라도 결제가 진행되는 이슈 발생 */
				} else if (quantity > marketList.number.get(marketList.itemName.indexOf(itemName))) {
					System.out.println("+---------------------------------------+");
					System.out.println("| 개수만큼 재고가 존재하지 않습니다 다시 입력하세요 |");
					System.out.println("+---------------------------------------+");
					this.itemSellingViewModel();
				} else if (startWorkModel.shoppingBasketList.indexOf(itemName) == -1) {
					// 동일 물품이 없을 시
					startWorkModel.quantityItem.add(quantity);
					startWorkModel.shoppingBasketList.add(itemName);
					startWorkModel.paymentPrice
							.add(marketList.price.get(marketList.itemName.indexOf(itemName)) * quantity);
				} else {
					/* MARK: 수정 : 중복 물품 입력 시 수량 증가로 수정 예정 - 완료 */
					// 동일 물품이 존재할 시
					int index = startWorkModel.shoppingBasketList.indexOf(itemName);
					startWorkModel.quantityItem.set(index, startWorkModel.quantityItem.get(index) + quantity);
					startWorkModel.paymentPrice.set(index,
							marketList.price.get(marketList.itemName.indexOf(itemName)) * quantity
									+ startWorkModel.paymentPrice.get(index));
				}

				System.out.println();
				System.out.println();
				System.out.println("+---------------------------------------+");
				System.out.println("|              현재 장바구니               |");
				System.out.println("+---------------------------------------+");
				int sum = 0;
				for (int i = 0; i < startWorkModel.shoppingBasketList.size(); ++i) {
					System.out.printf("| %s	| %s개	| %s원\r\n", startWorkModel.shoppingBasketList.get(i),
							startWorkModel.quantityItem.get(i), startWorkModel.paymentPrice.get(i));
					sum += startWorkModel.paymentPrice.get(i);
					startWorkModel.setQuantitySum(sum);
				}
				System.out.println("+---------------------------------------+");
				System.out.printf("| 총 가격 : %s원\r\n", startWorkModel.getQuantitySum());
				this.itemSellingViewModel();

			} else {
				System.out.println("+---------------------------------------+");
				System.out.println("|        해당 물품은 존재하지 않습니다.        |");
				System.out.println("+---------------------------------------+");
				this.itemSellingViewModel();
			}
		}

	}

	// [2] 물품 환불 뷰 모델
	public void itemRefundViewModel() {
		System.out.println("+---------------------------------------+");
		System.out.println("|       환불 할 물품의 이름을 입력해주세요.     |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ");
		String itemName = scanner.nextLine();
		startWorkModel.refundItemName.add(itemName);

		System.out.println("+---------------------------------------+");
		System.out.println("|       환불 할 물품의 가격을 입력해주세요.     |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ");
		String sPrice = scanner.nextLine();
		int price = Integer.parseInt(sPrice);
		startWorkModel.refundItemNumber.add(price);

		System.out.println("+---------------------------------------+");
		System.out.println("|            환불이 완료 되었습니다.          |");
		System.out.println("+---------------------------------------+");

		for (int i = 0; i < startWorkModel.refundItemName.size(); ++i) {
			System.out.println("| " + startWorkModel.refundItemName.get(i) + "\t| "
					+ startWorkModel.refundItemNumber.get(i) + "원");
		}
		System.out.println("+---------------------------------------+");
		System.out.printf("| 현재 잔고 : %s원 \r\n", mainMenuModel.getMoney());
		System.out.println("+---------------------------------------+");
		this.back();
	}

	// [3] 미성년자 금지 물품 뷰 모델
	public void minorsBannedListViewModel() {

		System.out.println("+---------------------------------------+");

		for (int i = 0; i < marketList.itemName.size(); ++i) {
			if (marketList.adultCheck.get(i) == true) {
				System.out.println("| " + marketList.itemName.get(i));
			}
		}
		this.back();

	}

	// [4] 물품 입고 뷰 모델
	public void warehousingViewModel() {
		StartWorkView startWorkView = new StartWorkView();

		System.out.println("+---------------------------------------+");
		System.out.println("|       입고 할 물품의 이름을 입력해주세요.     |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ");
		String itemName = scanner.nextLine();

		if (marketList.itemName.contains(itemName)) {
			System.out.println("+---------------------------------------+");
			System.out.println("|         이미 물품이 목록에 존재합니다.       |");
			System.out.println("+---------------------------------------+");
			System.out.println("|       입고 할 물품의 수량을 입력해주세요.     |");
			System.out.println("+---------------------------------------+");
			System.out.print("| ");
			String sQuantityItem = scanner.nextLine();
			int quantityItem = Integer.parseInt(sQuantityItem);
			int result = marketList.number.get(itemName.indexOf(itemName)) + quantityItem;

			marketList.setItem(itemName.indexOf(itemName), result);

		} else {
			System.out.println("+---------------------------------------+");
			System.out.println("|       입고 할 물품의 가격을 입력해주세요.     |");
			System.out.println("+---------------------------------------+");
			System.out.print("| ");
			String sPrice = scanner.nextLine();
			int price = Integer.parseInt(sPrice);

			System.out.println("+---------------------------------------+");
			System.out.println("|     입고 할 물품이 미성년자 금지 물품인가요?   |");
			System.out.println("+---------------------------------------+");
			System.out.println("|            [ 네 ]   [ 아니오 ]          |");
			System.out.println("+---------------------------------------+");
			System.out.print("| ");
			String adultItem = scanner.nextLine();
			boolean adultCheck = false;
			if (adultItem.equals("네")) {
				adultCheck = true;

			} else if (adultItem.equals("아니오")) {
				adultCheck = false;
			} else {
				System.out.println("+---------------------------------------+");
				System.out.println("|          네, 아니오로만 입력해주세요        |");
				System.out.println("+---------------------------------------+");
				this.warehousingViewModel();
			}

			System.out.println("+---------------------------------------+");
			System.out.println("|       입고 할 물품의 수량을 입력해주세요.     |");
			System.out.println("+---------------------------------------+");
			System.out.print("| ");
			String sQuantityItem = scanner.nextLine();
			int quantityItem = Integer.parseInt(sQuantityItem);

			if (mainMenuModel.expirationHourItem.contains(itemName)) {
				System.out.println("+---------------------------------------+");
				System.out.println("|   입고 할 물품이 몇 시간동안 유통이 가능한가요?  |");
				System.out.println("+---------------------------------------+");
				System.out.print("| ");
				String sExpirationHour = scanner.nextLine();
				int expirationHour = Integer.parseInt(sExpirationHour);
				System.out.println("+---------------------------------------+");
				System.out.println("|               물품 추가 완료             |");
				System.out.println("+---------------------------------------+");
				System.out.println();
				System.out.println();

				marketList.addItem(itemName, price, expirationHour, adultCheck, quantityItem);
				startWorkView.workView();
			} else {
				System.out.println("+---------------------------------------+");
				System.out.println("|    입고 할 물품이 며칠동안 유통이 가능한가요?   |");
				System.out.println("+---------------------------------------+");
				System.out.print("| ");
				String sExpirationDay = scanner.nextLine();
				int expirationDay = Integer.parseInt(sExpirationDay);
				System.out.println("+---------------------------------------+");
				System.out.println("|               물품 추가 완료             |");
				System.out.println("+---------------------------------------+");
				System.out.println();
				System.out.println();

				marketList.addItem(itemName, price, expirationDay, adultCheck, quantityItem);
				startWorkView.workView();
			}

		}

	}

	// [5] 재고 검색 뷰 모델
	public void searchingInventoryViewModel() {
		System.out.print("| 물품명 : ");
		String searchItem = scanner.nextLine();

		for (int i = 0; i < marketList.itemName.size(); ++i) {
			if (marketList.itemName.contains(searchItem)) {
				System.out.println("+---------------------------------------+");
				System.out.println("|              재고가 존재합니다.           |");
				System.out.println("+---------------------------------------+");
				this.back();
			} else {
				System.out.println("+---------------------------------------+");
				System.out.println("|           재고가 존재하지 않습니다.         |");
				System.out.println("+---------------------------------------+");
				this.back();
			}
		}
	}

	// 결제 메서드
	public void paymentProgressing() {
		for (int i = 0; i < marketList.itemName.size(); ++i) {
			if (marketList.adultCheck.get(i) == true) {
				startWorkModel.adultCheckingItem.add(marketList.itemName.get(i));
			}
		}
		for (int j = 0; j < startWorkModel.shoppingBasketList.size(); ++j) {
			if (startWorkModel.adultCheckingItem.contains(startWorkModel.shoppingBasketList.get(j)) == true) {
				this.adultCheckingView();
				break;
			}
		}
		this.insertCardNumber();

		/* MARK : 추가예정 : 물품의 개수를 낮추고 0이라면 리스트 삭제 - 완료 */
		for (int k = 0; k < startWorkModel.shoppingBasketList.size(); ++k) {
			try {
				marketList.number.set(marketList.itemName.indexOf(startWorkModel.shoppingBasketList.get(k)),
						marketList.number.get(marketList.itemName.indexOf(startWorkModel.shoppingBasketList.get(k)))
								- startWorkModel.quantityItem.get(k));

				if (marketList.number.get(marketList.itemName.indexOf(startWorkModel.shoppingBasketList.get(k))) <= 0) {

					// itemName을 먼저 삭제하면 뒤의 리스트들의 index가 사라져 error발생으로 변수에 미리 저장
					int index = marketList.itemName.indexOf(startWorkModel.shoppingBasketList.get(k));
					marketList.itemName.remove(index);
					marketList.price.remove(index);
					marketList.adultCheck.remove(index);
					marketList.number.remove(index);
					marketList.expirationDate.remove(index);
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("배열 인덱스가 초과" + e.getMessage());
			}

		}

		int money = mainMenuModel.getMoney();
		mainMenuModel.setMoney(money + startWorkModel.getQuantitySum());
		int sales = mainMenuModel.getSales();
		mainMenuModel.setSales(sales + startWorkModel.getQuantitySum());

		System.out.println("+---------------------------------------+");
		System.out.println("|            결제가 완료되었습니다.           |");
		System.out.println("+---------------------------------------+");
		System.out.printf("| 현재 잔고 : %s원\r\n", mainMenuModel.getMoney());
		System.out.printf("| 매 출 액 : %s원\r\n", mainMenuModel.getSales());
		System.out.println("+---------------------------------------+");
		System.out.println();
		/* MARK : 추가예정 : StartWork_Model의 리스트들 clear하기 - 완료 */
		startWorkModel.shoppingBasketList.clear();
		startWorkModel.quantityItem.clear();
		startWorkModel.paymentPrice.clear();
		startWorkModel.adultCheckingItem.clear();
		startWorkModel.setQuantitySum(0);

		StartWorkView startWorkView = new StartWorkView();
		startWorkView.workView();

	}

	// 성인인증 뷰 및 메서드
	public void adultCheckingView() {
		System.out.println("+---------------------------------------+");
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------+");
		System.out.println("|                성인 인증                |");
		System.out.println("+---------------------------------------+");
		System.out.println("+---------------------------------------+");
		System.out.println("|    생년월일을 기입해주세요.  ex)19990407    |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ");
		String birthday = scanner.nextLine();

		boolean validationBirthday = Pattern.matches(StartWork_Model.birthdayPattern, birthday);
		/* MARK : 수정예정 : 로직자체 변경 - 완료 */
		if (validationBirthday == true) {
			Date_Extension dateExtension = Date_Extension.getInstance();
			if (dateExtension.adultCheckingMethod(birthday) == true) {
				System.out.println("+---------------------------------------+");
				System.out.println("|           성인 인증에 성공했습니다.         |");
				System.out.println("+---------------------------------------+");
			} else {
				System.out.println("+---------------------------------------+");
				System.out.println("|           성인 인증에 실패했습니다.         |");
				System.out.println("+---------------------------------------+");
				startWorkModel.shoppingBasketList.clear();
				startWorkModel.quantityItem.clear();
				startWorkModel.paymentPrice.clear();
				startWorkModel.adultCheckingItem.clear();
				startWorkModel.setQuantitySum(0);
				StartWorkView startWorkView = new StartWorkView();
				startWorkView.workView();

			}
		} else {
			System.out.println("+---------------------------------------+");
			System.out.println("|           기입 방법을 참고하십시오.         |");
			System.out.println("+---------------------------------------+");
			this.adultCheckingView();
		}

	}

	// 카드 번호 입력 메서드
	public void insertCardNumber() {
		System.out.println("+---------------------------------------+");
		System.out.println("|       결제 할 카드의 번호를 입력해주세요      |");
		System.out.println("|        ex) 1234-1234-12345678         |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ");
		String cardNumber = scanner.nextLine();
		boolean validation = Pattern.matches(StartWork_Model.cardPattern, cardNumber);
		if (validation == false) {
			System.out.println("+---------------------------------------+");
			System.out.println("|        카드번호가 형식에 맞지 않습니다.       |");
			System.out.println("|              다시 시도하세요.             |");
			System.out.println("+---------------------------------------+");
			this.insertCardNumber();
		}

	}

	// 뒤로가기 메서드
	private void back() {
		StartWorkView startWorkView = new StartWorkView();
		System.out.println("+---------------------------------------+");
		System.out.println("|        뒤로 가려면 back을 입력하세요        |");
		System.out.println("+---------------------------------------+");
		System.out.print("| ");
		String str = scanner.nextLine();

		if (str.equals("back")) {
			System.out.println("+---------------------------------------+");
			System.out.println();
			System.out.println();
			startWorkView.workView();
		} else {
			System.out.println("+---------------------------------------+");
			System.out.println("|    잘못 입력하셨습니다. 다시 입력해 주세요.    |");
			System.out.println("+---------------------------------------+");
			this.back();
		}
	}
}
