package readCSV;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SaveDB {
	private Connection connect;
	private Statement stmt;
	private ResultSet rset;

	public SaveDB() throws SQLException {
		connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/testdb", "root", "PASSWD");
		stmt = connect.createStatement();

	}

	public int ageGroup(String agegroup) {
		if (agegroup.contains("유아")) {
			return 1;
		} else if (agegroup.contains("어린이")) {
			return 2;
		} else if (agegroup.contains("청소년")) {
			return 3;
		} else if (agegroup.contains("어른")) {
			return 4;
		} else
			return 5;
	}

	public int dcNum(String dc) {
		if (dc.contains("없음")) {
			return 1;
		} else if (dc.contains("장애인")) {
			return 2;
		} else if (dc.contains("국가유공자")) {
			return 3;
		} else if (dc.contains("다자녀")) {
			return 4;
		} else
			return 5;
	}

	public void readDB(ArrayList<CustomInfo> cusInfoArr) {
		
		int agegroup;
		int dcnum;
		String ticketkind;
		String day_ticket_num = ""; // 주간권 총 티켓수
		String night_ticket_num = ""; // 야간권 총 티켓수
		String day_ticket_price = "";
		String night_ticket_price = "";
		String age_arr[] = { "유아", "어린이", "청소년", "어른", "노인" };

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// **** report 판매현황을 위하여 arraylist에 값 저장 ****
			rset = stmt.executeQuery("select * from report");
			while (rset.next()) {
				CustomInfo cusInfo = new CustomInfo();
				cusInfo.setDate(rset.getString(1));

				ticketkind = rset.getString(2);
				if (ticketkind.contains("주간권")) {
					cusInfo.setTicketkind(1);
				} else {
					cusInfo.setTicketkind(2);
				}

				agegroup = ageGroup(rset.getString(3));
				cusInfo.setAge(agegroup);

				cusInfo.setTicketnum(Integer.parseInt(rset.getString(4)));

				cusInfo.setTicketprice(Integer.parseInt(rset.getString(5)));

				dcnum = dcNum(rset.getString(6));
				cusInfo.setDc(dcnum);

				cusInfoArr.add(cusInfo);
			}
			printconsoleREPORT(ConstValueClass.cusInfoArr);
			
			// **** 주간권 총 티켓 ****
			rset = stmt.executeQuery("select sum(ticketnum) from report where type LIKE '주간권'");
			while (rset.next()) {
				day_ticket_num = rset.getString(1);
			}

			// **** 주간권 총 매출 ****
			rset = stmt.executeQuery("select sum(price) from report where type LIKE '주간권'");
			while (rset.next()) {
				day_ticket_price = rset.getString(1);
			}

			// **** 야간권 총 티켓 ****
			rset = stmt.executeQuery("select sum(ticketnum) from report where type LIKE '야간권'");
			while (rset.next()) {
				night_ticket_num = rset.getString(1);
			}

			// **** 야간권 총 매출 ****
			rset = stmt.executeQuery("select sum(price) from report where type LIKE '야간권'");
			while (rset.next()) {
				night_ticket_price = rset.getString(1);
			}

			// **** 주간권 연령그룹 별 티켓 수 ****
			String day_data = "";
			String day_temp = "";
			for (int i = 0; i < age_arr.length; i++) {
				rset = stmt.executeQuery(
						"select sum(ticketnum) from report where age like '" + age_arr[i] + "' and type like '주간권' ");
				while (rset.next()) {
					day_data = rset.getString(1);
					if (day_data == null) {
						day_data = "0";
					}
					if (i == age_arr.length - 1) {
						day_temp += day_data;
					} else {
						day_temp += day_data + " "; // 연령에 따른 주간권 티켓판매수
					}
				}
			}

			// **** 야간권 연령그룹 별 티켓 수 ****
			String night_data = "";
			String night_temp = "";
			for (int i = 0; i < age_arr.length; i++) {
				rset = stmt.executeQuery(
						"select sum(ticketnum) from report where age like '" + age_arr[i] + "' and type like '야간권' ");
				while (rset.next()) {
					night_data = rset.getString(1);
					if (night_data == null) {
						night_data = "0";
					}
					if (i == age_arr.length - 1) {
						night_temp += night_data;
					} else {
						night_temp += night_data + " "; // 연령에 따른 주간권 티켓판매수
					}
				}
			}
			
			// **** 권종 별 티켓판매 현황 출력 ****
			printconsoleTICKET(day_ticket_num, day_temp, day_ticket_price, night_ticket_num, night_temp,
					night_ticket_price, age_arr);
			
			// **** 날짜(오름차순)별 판매 현황 ****
			String date_sell = "";
			String date = "";
			rset = stmt.executeQuery("select date, sum(price) from report group by `date` order by `date` asc");
			while (rset.next()) {
				date_sell += rset.getString(2) + " ";
				date += rset.getString(1) + " ";
			}
			
			printconsoleDC(date_sell, date);
			
			
			// **** 우대사항 별 판매 현황 ****
			System.out.printf("\n============= 우대권 판매 현황 =============\n");
			rset = stmt.executeQuery("select sum(ticketnum) from report");
			while (rset.next()) {
				System.out.printf("총 판매 티켓 수 :  %s매\n", rset.getString(1));
			}
			rset = stmt.executeQuery("select dc, sum(ticketnum) from report group by `dc`");
			while (rset.next()) {
				System.out.printf("%s  :   %s매\n", rset.getString(1), rset.getString(2));
			}
			// rset.close();
			// stmt.close();
			// connect.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void printconsoleREPORT(ArrayList<CustomInfo> cusInfoArr) {
		System.out.println("======================= Report from Database =======================");
		System.out.printf("%s %12s %6s %2s %6s %4s\n", "날짜", "권종", "연령구분", "수량", "가격", "우대사항");
		for (int i = 0; i < cusInfoArr.size(); i++) {
			CustomInfo info = cusInfoArr.get(i);
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", info.getDate(), info.getTicketkind(), info.getAge(),
					info.getTicketnum(), info.getTicketprice(), info.getDc());
		}
	}

	public void printconsoleTICKET(String day_ticket_num, String day_temp, String day_ticket_price,
			String night_ticket_num, String night_temp, String night_ticket_price, String age[]) {
		String day_temp_arr[] = day_temp.split(" ");
		String night_temp_arr[] = night_temp.split(" ");
		System.out.printf("\n=============== 권종 별 판매현황 ===============\n");
		System.out.printf("주간권 총 %s매\n", day_ticket_num);

		for (int i = 0; i < day_temp_arr.length; i++) {
			System.out.printf("%s %s매, ", age[i], day_temp_arr[i]);
		}

		System.out.printf("\n주간권 매출 : %s원\n\n", day_ticket_price);

		for (int i = 0; i < night_temp_arr.length; i++) {
			System.out.printf("%s %s매, ", age[i], night_temp_arr[i]);
		}

		System.out.printf("\n야간권 매출 : %s원\n", night_ticket_price);
		System.out.printf("---------------------------------------------\n");

	}

	public void printconsoleDC(String date_sell, String date) {
		String date_sell_arr[] = date_sell.split(" ");
		String date_arr[] = date.split(" ");
		System.out.printf("\n=============== 일자별 매출현황 ===============\n");
		for (int i = 0; i < date_arr.length; i++) {
			System.out.printf("%s  : %s\n", date_arr[i], date_sell_arr[i]);
		}
	}

}
