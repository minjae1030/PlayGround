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
	
	public void orderPrint(int totalPrice, ArrayList<CustomInfo> cusInfoArr) {
		System.out.print("Ƽ�� �߱��� �����մϴ�. �����մϴ�.\n");
		System.out.println("");
		System.out.println("===================���緣��==================");
		
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
			System.out.printf("%8d��    ", info.getTicketprice());
			
			switch (info.getDc()) {
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
		System.out.println("===========================================\n");
	}
	
	public void saveFILE(ArrayList<CustomInfo> cusInfoArr) throws IOException {
		// ��¥, ����, ���ɱ��� , ����, ���� , ������ ex) 20210415,�ְ���,�,1,56000,����
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
