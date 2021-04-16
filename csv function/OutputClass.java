package readCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;


public class OutputClass {
	
	public void printTICKETDATA1(String temp) {
		System.out.printf("\n=============== report.csv ===============\n");
		System.out.printf("날짜           권종   연령구분 수량     가격  우대사항\n");
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
	
	public void saveTICKETDATA(int result[][]) throws IOException {
		//구분,주간권,야간권
		FileWriter fw1 = new FileWriter(ConstValueClass.PATH3, true);
		String text = "";
		int baby_day = result[0][1], baby_night = result[1][1];
		int child_day = result[0][2] , child_night = result[1][2];
		int teen_day = result[0][3] , teen_night = result[1][3];
		int adult_day = result[0][4] , adult_night = result[1][4];
		int old_day = result[0][5] , old_night = result[1][5];
		int day_ret = result[0][0], night_ret = result[1][0];
		int day_sell = result[0][6], night_sell = result[1][6];
		text = ConstValueClass.INFANT + "," + baby_day + "," + baby_night + "\n" +
				ConstValueClass.CHILDREN + "," +child_day + "," + child_night + "\n" +
				ConstValueClass.JUVENILE + "," + teen_day + "," + teen_night + "\n" +
				ConstValueClass.ADULT_PEOPLE + "," + adult_day + "," + adult_night + "\n" +
				ConstValueClass.OLD_PEOPLE + "," + old_day + "," + old_night + "\n" +
				ConstValueClass.TOTAL_NUM + "," + day_ret + "," + night_ret + "\n" +
				ConstValueClass.TOTAL_SELL + "," + day_sell + "," + night_sell + "\n";
		fw1.write(text);
		fw1.close();
		
	}
	
	public void printDAYDATA1(HashSet<String> sellday, int date_price_arr[]) { 
		Iterator<String> iter = sellday.iterator();
		int count = 0;
		System.out.printf("\n=============== 일자별 매출 현황 ===============\n");
		while (iter.hasNext()) {
			System.out.printf("%s : 총 매출\t%s원\n",iter.next(), date_price_arr[count]);
			count++;
		}
	}
	public void saveDAYDATA1(HashSet<String> sellday, int date_price_arr[]) throws IOException { // 일자별  : 총 매출 -> CSV저장
		FileWriter fw = new FileWriter(ConstValueClass.PATH2, true);
		Iterator<String> iter = sellday.iterator();
		int count = 0;
		String date = "";
		String line = "";
		while (iter.hasNext()) {
			date = iter.next();
			line = date + "," + date_price_arr[count] + "\n";
			fw.write(line);
			count++;
		}
		fw.close();
	}
	
	
	public void printDAYDATA2(int date_dc_count_arr []) {
		System.out.printf("\n============= 우대권 판매 현황 =============\n");
		System.out.printf("총 판매 티켓수 :     %s매\n", date_dc_count_arr[0]);
		System.out.printf("우대 없음      :     %s매\n", date_dc_count_arr[1]);
		System.out.printf("장애인         :     %s매\n", date_dc_count_arr[2]);
		System.out.printf("국가유공자     :     %s매\n", date_dc_count_arr[3]);
		System.out.printf("다자녀         :     %s매\n", date_dc_count_arr[4]);
		System.out.printf("임산부         :     %s매\n", date_dc_count_arr[5]);
		System.out.printf("\n===========================================\n");
		
	}
}
