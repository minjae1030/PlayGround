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

	// **** Ƽ�� ���� �Է¹޾� �����ϴ� �޼ҵ� ****
	public int inputTicketSelect() { // ������ �Է¹޾� ���ù�ȣ�� Return
		int ticketselect;
		System.out.print("������ �����ϼ���.\n1. �ְ���\n2. �߰���\n");
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

	// **** �ֹι�ȣ �Է� �޼ҵ� *****
	public String inputIDnumber() { // �ֹι�ȣ�� �Է¹޾� String���� Return
		String customerIDNumber = "";
		System.out.print("�ֹε�Ϲ�ȣ�� �Է��ϼ���.(xxxxxx-xxxxxxx)\n");
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

	// **** Ƽ�� �ֹ� ���� �Է� �޼ҵ� ****
	public int inputOrderCount() { // Ƽ�� ������ �Է¹޾� int�� Return
		int orderCount = 0;
		System.out.print("��� �ֹ��Ͻðڽ��ϱ�?(�ִ� 10��)\n");
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

	// **** ������ �Է� �޼ҵ� ****
	public int inputDcSelect() { // �������� �����Ͽ� ���ù�ȣ�� int�� Return
		int dcSelect = 0;
		System.out.print("�������� �����ϼ���.\n");
		System.out.println("1. ����\n2. �����\n3. ����������\n4. ���ڳ� \n5. �ӻ��");
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
	
	// **** ��� �߱��Ͻðڽ��ϱ� �޼ҵ� ****
	public int checkContinue() {
		int choice = 0;
		System.out.println("��� �߱� �Ͻðڽ��ϱ�?");
		System.out.println("1. Ƽ�� �߱�\n2. ����");
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
	// **** ������ �߱� ���� �޼ҵ� ****
	public int checkFINAL() {
		int choice = 0;
		System.out.print("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����) : ");
		choice = sc.nextInt();
		return choice;
	}

}
