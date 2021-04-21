package playpark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DBsave {
	private Calendar cal = null;
	private SimpleDateFormat sdt = null;
	public DBsave() {
		cal = Calendar.getInstance();
		sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	public void saveDB (ArrayList<CustomInfo> cusInfoArr) {
		String time = sdt.format(cal.getTime());
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/testdb", "root", "kopo04");
			Statement stmt = connect.createStatement();
			
			for (int i = 0; i < cusInfoArr.size(); i++) {
				CustomInfo cus = cusInfoArr.get(i);
				String temp ="";
				temp = String.format("INSERT INTO `report`(`date`, `type`, `age`, `ticketnum`, `price`, `dc`) "
						+ "values ('%s','%s','%s','%d','%d','%s')", time, cus.getTicketname(), cus.getAgegroup()
						,cus.getTicketnum(), cus.getTicketprice(), cus.getDcname());
				
				stmt.execute(temp);
			}
			stmt.close();
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
}


