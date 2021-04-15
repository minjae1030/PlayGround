package readCSV;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ReadCSV rc = new ReadCSV();
		OutputClass outClass = new OutputClass();
		int result[][];
		result = rc.parseTICKETDATA();
		outClass.printTICKETDATA2(result);
		rc.parseDAYDATA();
	}

}
