package readCSV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		ReadCSV rc = new ReadCSV();
		OutputClass outClass = new OutputClass();
		SaveDB dbClass = new SaveDB();
		int result[][];
		
		result = rc.parseTICKETDATA();
		outClass.printTICKETDATA2(result);
		rc.parseDAYDATA();
		System.out.println("***************************DB로부터 읽어들인 데이타 판매 현황 **********************************");
		dbClass.readDB(ConstValueClass.cusInfoArr);
	}

}
