package playpark;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static int count = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputClass inputClass = new InputClass();
		RunClass runClass = new RunClass();
		OutputClass outClass = new OutputClass();
		CustomInfo cusInfo = new CustomInfo();
		int orderList [][] = new int[100][5];
		ArrayList<CustomInfo> cusInfoArr = new ArrayList<CustomInfo>();
		int ticketSelect = 0, orderCount = 0, dcSelect = 0, priceResult = 0, choice = 0, total_price = 0;
		int choice_final = 0;
		String customerIDNumber ="";
		
		int age = 0, ageGroup = 0, calPrice = 0, calResult = 0; priceResult = 0;
		do {
		while (true) {
			ticketSelect = inputClass.inputTicketSelect();
			customerIDNumber = inputClass.inputIDnumber();
			orderCount = inputClass.inputOrderCount();
			dcSelect = inputClass.inputDcSelect();
			age = runClass.calAge(customerIDNumber);
			priceResult = runClass.calProcess(customerIDNumber, ticketSelect, dcSelect, orderCount, priceResult, age);
			total_price += priceResult;
			runClass.saveOrder(ticketSelect, age, orderCount, priceResult, dcSelect, count, orderList, cusInfoArr);
			outClass.printPrice(priceResult);
			choice = outClass.checkContinue();
			if (choice == ConstValueClass.CHOICE_EXIT) {
				break;
			}
		}
		outClass.orderPrint(total_price, count, orderList);
		outClass.saveFILE(total_price, count, orderList, cusInfoArr);
		choice_final = inputClass.checkFINAL();
	} while (choice_final == 1);
	}

}
