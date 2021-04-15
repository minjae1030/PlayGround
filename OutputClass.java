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
		System.out.println("=================민재랜드================");
		
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
		System.out.println("=======================================\n");
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
	public void printTICKETDATA1(String temp) {
		System.out.printf("\n=============== report.csv ===============\n");
		System.out.printf("날짜\t\t권종\t연령구분\t수량\t가격\t우대사항\n");
		String line_data[] = temp.split("\n");
		String line_comma_data[];
		for (int i = 0; i < line_data.length; i++) {
			line_comma_data = line_data[i].split(",");
			for (int j = 0; j < line_comma_data.length; j++) {
				System.out.printf("%s\t", line_comma_data[j]);
			}
			System.out.println("");
		}
		System.out.printf("---------------------------------------------\n");
	}
	
	public void printTICKETDATA2(int result[][]) { // 권종별 판매 현황
		System.out.printf("\n=============== 권종 별 판매현황 ===============\n");
		System.out.printf("주간권 총 %s매\n", result[0][0]);
		System.out.printf("유아 %s매, 어린이 %s매, 청소년 %s매, 어른 %s매, 노인 %s매\n",
				result[0][1], result[0][2], result[0][3], result[0][4], result[0][5]);
		System.out.printf("주간권 매출 : %s원\n\n", result[0][6]);
		System.out.printf("야간권 총 %s매\n", result[1][0]);
		System.out.printf("유아 %s매, 어린이 %s매, 청소년 %s매, 어른 %s매, 노인 %s매\n",
				result[1][1], result[1][2], result[1][3], result[1][4], result[1][5]);
		System.out.printf("야간권 매출 : %s원\n", result[1][6]);
		System.out.printf("---------------------------------------------\n");
	
	}
	
	public void printDAYDATA1(HashSet<String> sellday, int date_price_arr[]) {
		Iterator<String> iter = sellday.iterator();
		int count = 0;
		System.out.printf("\n=============== 일자별 매출 현황 ===============\n");
		while (iter.hasNext()) {
			System.out.printf("%s : 총 매출\t%s원",iter.next(), date_price_arr[count]);
			count++;
		}
	}
	
	public void printDAYDATA2(int date_dc_count_arr []) {
		System.out.printf("\n=============== 우대권 판매 현황 ===============\n");
		System.out.printf("총 판매 티켓수 : \t%s매\n", date_dc_count_arr[0]);
		System.out.printf("우대 없음     : \t%s매\n", date_dc_count_arr[1]);
		System.out.printf("장애인       : \t%s매\n", date_dc_count_arr[2]);
		System.out.printf("국가유공자    : \t%s매\n", date_dc_count_arr[3]);
		System.out.printf("다자녀       : \t%s매\n", date_dc_count_arr[4]);
		System.out.printf("임산부       : \t%s매\n", date_dc_count_arr[5]);
		System.out.printf("\n===========================================\n");
		
	}
	
}
