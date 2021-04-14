package playpark;

import java.io.IOException;

public class Main {
	public static int position = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputClass inputClass = new InputClass();
		RunClass runClass = new RunClass();
		OutputClass outClass = new OutputClass();
		int orderList [][] = new int[100][5];
		int ticketSelect = 0, orderCount = 0, dcSelect = 0, priceResult = 0, choice = 0, total_price = 0;
		String customerIDNumber ="";
		
		int age = 0, ageGroup = 0, calPrice = 0, calResult = 0; priceResult = 0;
		while (true) {
			ticketSelect = inputClass.inputTicketSelect();
			customerIDNumber = inputClass.inputIDnumber();
			orderCount = inputClass.inputOrderCount();
			dcSelect = inputClass.inputDcSelect();
			age = runClass.calAge(customerIDNumber);
			priceResult = runClass.calProcess(customerIDNumber, ticketSelect, dcSelect, orderCount, priceResult, age);
			total_price += priceResult;
			runClass.saveOrder(ticketSelect, age, orderCount, priceResult, dcSelect, position, orderList);
			outClass.printPrice(priceResult);
			choice = outClass.checkContinue();
			if (choice == ConstValueClass.CHOICE_EXIT) {
				break;
			}
		}
		outClass.orderPrint(total_price, position, orderList);
		outClass.saveFILE(total_price, position, orderList);
	}

}
