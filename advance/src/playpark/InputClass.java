package playpark;

import java.io.IOException;
import java.util.Scanner;

public class InputClass {
	private Scanner sc = null;
	public OutputClass outclass = null;

	public InputClass() throws IOException {
		sc = new Scanner(System.in);
		outclass = new OutputClass();
	}

	// **** 티켓 권종 입력받아 리턴하는 메소드 ****
	public int inputTicketSelect() { // 권종을 입력받아 선택번호로 Return
		int ticketselect;
		System.out.print("권종을 선택하세요.\n1. 주간권\n2. 야간권\n");
		while (true) {
			ticketselect = sc.nextInt();
			if (ticketselect == 1 || ticketselect == 2) {
				break;
			} else {
				outclass.printErrorMessage();
			}
		}
		return ticketselect;
	}

	// **** 주민번호 입력 메소드 *****
	public String inputIDnumber() { // 주민번호를 입력받아 String으로 Return
		String customerIDNumber = "";
		System.out.print("주민등록번호를 입력하세요.(xxxxxx-xxxxxxx)\n");
		while (true) {
			customerIDNumber = sc.next();
			if ((customerIDNumber.length() >= ConstValueClass.FULL_DIGIT_MIN.length() + 1)
					&& (customerIDNumber.length() < ConstValueClass.FULL_DIGIT.length() + 1)
					&& (customerIDNumber.contains("-") == true)) {
				break;
			} else {
				outclass.printErrorMessage();
			}
		}
		return customerIDNumber;
	}

	// **** 티켓 주문 개수 입력 메소드 ****
	public int inputOrderCount() { // 티켓 개수를 입력받아 int로 Return
		int orderCount = 0;
		System.out.print("몇개를 주문하시겠습니까?(최대 10개)\n");
		while (true) {
			orderCount = sc.nextInt();
			if ((orderCount <= ConstValueClass.MAX_COUNT) && (orderCount >= ConstValueClass.MIN_COUNT)) {
				break;
			} else {
				outclass.printErrorMessage();
			}
		}
		return orderCount;
	}

	// **** 우대사항 입력 메소드 ****
	public int inputDcSelect() { // 우대사항을 선택하여 선택번호를 int로 Return
		int dcSelect = 0;
		System.out.print("우대사항을 선택하세요.\n");
		System.out.println("1. 없음\n2. 장애인\n3. 국가유공자\n4. 다자녀 \n5. 임산부");
		while (true) {
			dcSelect = sc.nextInt();
			if ((dcSelect >= 1) && (dcSelect <= 5)) {
				break;
			} else {
				outclass.printErrorMessage();
			}
		}
		return dcSelect;
	}
	
	// **** 계속 발권하시겠습니까 메소드 ****
	public int checkContinue() {
		int choice = 0;
		System.out.println("계속 발권 하시겠습니까?");
		System.out.println("1. 티켓 발권\n2. 종료");
		while (true) {
			choice = sc.nextInt();
			if (choice == ConstValueClass.CHOICE_CONTINUE || choice == ConstValueClass.CHOICE_EXIT) {
				break;
			} else {
				outclass.printErrorMessage();
			}
		}
		return choice;
	}
	// **** 마지막 발권 여부 메소드 ****
	public int checkFINAL() {
		int choice = 0;
		System.out.print("계속 진행(1: 새로운 주문, 2: 프로그램 종료) : ");
		choice = sc.nextInt();
		return choice;
	}

}
