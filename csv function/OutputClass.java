package readCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;


public class OutputClass {
	
	public void printTICKETDATA1(String temp) {
		System.out.printf("\n=============== report.csv ===============\n");
		System.out.printf("��¥           ����   ���ɱ��� ����     ����  ������\n");
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
	
	public void printTICKETDATA2(int result[][]) { // ������ �Ǹ� ��Ȳ
		System.out.printf("\n=============== ���� �� �Ǹ���Ȳ ===============\n");
		System.out.printf("�ְ��� �� %s��\n", result[0][0]);
		System.out.printf("���� %s��, ��� %s��, û�ҳ� %s��, � %s��, ���� %s��\n",
				result[0][1], result[0][2], result[0][3], result[0][4], result[0][5]);
		System.out.printf("�ְ��� ���� : %s��\n\n", result[0][6]);
		System.out.printf("�߰��� �� %s��\n", result[1][0]);
		System.out.printf("���� %s��, ��� %s��, û�ҳ� %s��, � %s��, ���� %s��\n",
				result[1][1], result[1][2], result[1][3], result[1][4], result[1][5]);
		System.out.printf("�߰��� ���� : %s��\n", result[1][6]);
		System.out.printf("---------------------------------------------\n");
	
	}
	
	public void saveTICKETDATA(int result[][]) throws IOException {
		//����,�ְ���,�߰���
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
		System.out.printf("\n=============== ���ں� ���� ��Ȳ ===============\n");
		while (iter.hasNext()) {
			System.out.printf("%s : �� ����\t%s��\n",iter.next(), date_price_arr[count]);
			count++;
		}
	}
	public void saveDAYDATA1(HashSet<String> sellday, int date_price_arr[]) throws IOException { // ���ں�  : �� ���� -> CSV����
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
		System.out.printf("\n============= ���� �Ǹ� ��Ȳ =============\n");
		System.out.printf("�� �Ǹ� Ƽ�ϼ� :     %s��\n", date_dc_count_arr[0]);
		System.out.printf("��� ����      :     %s��\n", date_dc_count_arr[1]);
		System.out.printf("�����         :     %s��\n", date_dc_count_arr[2]);
		System.out.printf("����������     :     %s��\n", date_dc_count_arr[3]);
		System.out.printf("���ڳ�         :     %s��\n", date_dc_count_arr[4]);
		System.out.printf("�ӻ��         :     %s��\n", date_dc_count_arr[5]);
		System.out.printf("\n===========================================\n");
		
	}
}
