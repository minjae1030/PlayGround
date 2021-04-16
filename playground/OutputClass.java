package playpark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class OutputClass {
	public RunClass run = null;
	private Scanner sc = null;
	public OutputClass() throws IOException {
		run = new RunClass();
		sc = new Scanner(System.in);	
	}
	public void printErrorMessage() { // 콘솔 입력 에러 시 에러문구 출력 메소드
		System.out.println("잘못된 입력입니다! 다시 입력해주세요.");
	}
	
	public void printPrice(int priceResult) {
		System.out.printf("가격은 %d원 입니다.\n" , priceResult );
		System.out.println("감사합니다.\n");
	}
	
	public int checkContinue() {
		int choice = 0;
		System.out.println("계속 발권 하시겠습니까?");
		System.out.println("1. 티켓 발권\n2. 종료");
		while (true) {
			choice = sc.nextInt();
			if (choice == ConstValueClass.CHOICE_CONTINUE || choice == ConstValueClass.CHOICE_EXIT) {
				break;
			} else {
				printErrorMessage();
			}
		}
		return choice;
	}
	
	public void orderPrint(int totalPrice, ArrayList<CustomInfo> cusInfoArr) {
		System.out.print("티켓 발권을 종료합니다. 감사합니다.\n");
		System.out.println("");
		System.out.println("===================민재랜드==================");
		
		for (int i = 0; i < cusInfoArr.size(); i++) {
			CustomInfo info = cusInfoArr.get(i);
			switch (info.getTicketkind()) {
			case 1:
				System.out.print(ConstValueClass.DAY + " ");
				break;
			case 2:
				System.out.print(ConstValueClass.NIGHT + " ");
				break;
			default:
				break;
			}
			if (run.calAgeGroup(info.getAge()) == ConstValueClass.BABY) {
				
			} else if (run.calAgeGroup(info.getAge()) == ConstValueClass.CHILD) {
				System.out.print(ConstValueClass.CHILDREN + "  ");
			} else if (run.calAgeGroup(info.getAge()) == ConstValueClass.TEEN) {
				System.out.print(ConstValueClass.JUVENILE + "  ");
			} else if (run.calAgeGroup(info.getAge()) == ConstValueClass.ADULT) {
				System.out.print(ConstValueClass.ADULT_PEOPLE + "  ");
			} else if (run.calAgeGroup(info.getAge()) == ConstValueClass.OLD) {
				System.out.print(ConstValueClass.OLD_PEOPLE + "  ");
			} else {
			}
			System.out.printf("X %3d", info.getTicketnum());
			System.out.printf("%8d원    ", info.getTicketprice());
			
			switch (info.getDc()) {
			case 1:
				System.out.print("*우대적용 없음\n");
				break;
			case 2:
				System.out.print("*장애인 우대적용\n");
				break;
			case 3:
				System.out.print("*국가유공자 우대적용\n");
				break;
			case 4:
				System.out.print("*다자녀 우대적용\n");
				break;
			case 5:
				System.out.print("*임산부 우대적용\n");
				break;
			default:
				break;
			}
		}
		
		System.out.printf("\n입장료 총액은 %d원 입니다.\n" , totalPrice);
		System.out.println("===========================================\n");
	}
	
	public void saveFILE(ArrayList<CustomInfo> cusInfoArr) throws IOException {
		// 날짜, 권종, 연령구분 , 수량, 가격 , 우대사항 ex) 20210415,주간권,어른,1,56000,없음
		FileWriter fw = new FileWriter(ConstValueClass.PATH, true);
		FileWriter fw1 = new FileWriter(ConstValueClass.PATH1,true);
		String text = "", text1 = "";
		for (int i = 0; i < cusInfoArr.size(); i++) {
			CustomInfo info = cusInfoArr.get(i);
			text += info.getDate() + "," + info.getTicketname() + "," + info.getAgegroup()
					+ "," + info.getTicketnum() + "," + info.getTicketprice() + "," + info.getDcname() + "\n";
			fw.write(text);
			text = "";
		}
		fw.close();
		
		for (int i = 0; i < cusInfoArr.size(); i++) {
			CustomInfo info = cusInfoArr.get(i);
			text1 += info.getDate() + "," + info.getTicketkind() + "," + info.getAgekind() + "," + info.getTicketnum()
				+ "," + info.getTicketprice() + "," + info.getDc() + "\n";
			fw1.write(text1);
			text1 = "";
		}
		fw1.close();
	
	}
	
}
