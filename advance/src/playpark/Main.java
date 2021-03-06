package playpark;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputClass inputClass = new InputClass();
		RunClass runClass = new RunClass();
		OutputClass outClass = new OutputClass();
		DBsave dbClass = new DBsave();
		int ticketSelect = 0, orderCount = 0, dcSelect = 0, priceResult = 0, choice = 0, total_price = 0;
		int choice_final = 0, age = 0;
		String customerIDNumber = "";
		
		do {
			while (true) {
				ticketSelect = inputClass.inputTicketSelect();
				customerIDNumber = inputClass.inputIDnumber();
				orderCount = inputClass.inputOrderCount();
				dcSelect = inputClass.inputDcSelect();
				age = runClass.calAge(customerIDNumber);
				priceResult = runClass.calProcess(customerIDNumber, ticketSelect, dcSelect, orderCount, priceResult,
						age);
				total_price += priceResult;
				runClass.saveOrder(ticketSelect, age, orderCount, priceResult, dcSelect, ConstValueClass.cusInfoArr);
				dbClass.saveDB(ConstValueClass.cusInfoArr);
				outClass.printPrice(priceResult);
				choice = inputClass.checkContinue();
				if (choice == ConstValueClass.CHOICE_EXIT) {
					break;
				}
			}
			outClass.orderPrint(total_price, ConstValueClass.cusInfoArr);
			outClass.saveFILE(ConstValueClass.cusInfoArr);
			choice_final = inputClass.checkFINAL();
		} while (choice_final == 1);
	
	}

	
}
