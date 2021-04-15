package playpark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class OutputClass {
	public RunClass run = null;
	public Scanner sc = null;
	Calendar cal = null;
	SimpleDateFormat sdt = null;
	public OutputClass() throws IOException {
		run = new RunClass();
		sc = new Scanner(System.in);
		cal = Calendar.getInstance();
		sdt = new SimpleDateFormat("yyyyMMdd");
		
	}
	public void printErrorMessage() { // �ܼ� �Է� ���� �� �������� ��� �޼ҵ�
		System.out.println("�߸��� �Է��Դϴ�! �ٽ� �Է����ּ���.");
	}
	
	public void printPrice(int priceResult) {
		System.out.printf("������ %d�� �Դϴ�.\n" , priceResult );
		System.out.println("�����մϴ�.\n");
	}
	
	public int checkContinue() {
		int choice = 0;
		System.out.println("��� �߱� �Ͻðڽ��ϱ�?");
		System.out.println("1. Ƽ�� �߱�\n2. ����");
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
	
	public void orderPrint(int totalPrice, int position, int orderList[][]) {
		System.out.print("Ƽ�� �߱��� �����մϴ�. �����մϴ�.\n");
		System.out.println("");
		System.out.println("=================���緣��================");
		for (int i = 0; i < position; i++) {
			switch (orderList[i][0]) {
			case 1:
				System.out.print(ConstValueClass.DAY + " ");
				break;
			case 2:
				System.out.print(ConstValueClass.NIGHT + " ");
				break;
			default:
				break;
			}
			if (run.calAgeGroup(orderList[i][1]) == ConstValueClass.BABY) {
				System.out.print(ConstValueClass.INFANT + "  ");
			} else if (run.calAgeGroup(orderList[i][1]) == ConstValueClass.CHILD) {
				System.out.print(ConstValueClass.CHILDREN + "  ");
			} else if (run.calAgeGroup(orderList[i][1]) == ConstValueClass.TEEN) {
				System.out.print(ConstValueClass.JUVENILE + "  ");
			} else if (run.calAgeGroup(orderList[i][1]) == ConstValueClass.ADULT) {
				System.out.print(ConstValueClass.ADULT_PEOPLE + "  ");
			} else if (run.calAgeGroup(orderList[i][1]) == ConstValueClass.OLD) {
				System.out.print(ConstValueClass.OLD_PEOPLE + "  ");
			} else {
			}
			
			System.out.printf("X %3d", orderList[i][2]);
			System.out.printf("%8d��    ", orderList[i][3]);
			
			switch(orderList[i][4]) {
			case 1:
				System.out.print("*������� ����\n");
				break;
			case 2:
				System.out.print("*����� �������\n");
				break;
			case 3:
				System.out.print("*���������� �������\n");
				break;
			case 4:
				System.out.print("*���ڳ� �������\n");
				break;
			case 5:
				System.out.print("*�ӻ�� �������\n");
				break;
			default:
				break;
			}
		} 
		
		System.out.printf("\n����� �Ѿ��� %d�� �Դϴ�.\n" , totalPrice);
		System.out.println("=======================================\n");
	}
	
	public void saveFILE(int totalPrice, int position, int orderList[][], ArrayList<CustomInfo> cusInfoArr) throws IOException {
		// ��¥, ����, ���ɱ��� , ����, ���� , ������ 
		FileWriter fw = new FileWriter(ConstValueClass.PATH, true);
		String text = "";
		for (int i = 0; i < cusInfoArr.size(); i++) {
			CustomInfo info = cusInfoArr.get(i);
			text += info.getDate() +"," +info.getTickectkind() + "," + info.getAge()
					+"," + info.getTicketnum() +"," + info.getTicketprice() +"," +info.getDc() + "\n";
			fw.write(text);
			text = "";
		}
		fw.close();
		
		// **** ���� �ڵ� ****
		/*
		FileWriter fw = new FileWriter(ConstValueClass.PATH, true);
		String today = sdt.format(cal.getTime());
		String temp = "";
		String temp_arr [];
		for (int i = 0; i < position; i++) {
			for (int j = 0; j < orderList[position].length; j++) {
				if (j == orderList[position].length - 1) {
					temp += orderList[i][j] + "\n";
				} else {
				temp += orderList[i][j] + ",";
				}
			}
		}
		temp_arr = temp.split("\n");
		for (int i = 0; i < temp_arr.length; i++) {
			temp_arr[i] = today + "," + temp_arr[i]+"\n";
			fw.write(temp_arr[i]);
		}
		fw.close();
		*/
	}
	
}
