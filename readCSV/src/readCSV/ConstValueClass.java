package readCSV;

import java.util.ArrayList;


public class ConstValueClass {
	public static String PATH = "PlayGround.csv";
	public static String PATH1 = "Report.csv";
	public static String PATH2 = "PerDaySellamount.csv";
	public static String PATH3 = "PerTicketSales.csv";
	public static String INFANT = "유아", CHILDREN = "어린이", JUVENILE = "청소년",
			ADULT_PEOPLE = "어른", OLD_PEOPLE = "노인", TOTAL_NUM = "합계", TOTAL_SELL = "매출";
	public static String TICKET_DAY = "주간권", TICKET_NIGHT = "야간권", TICKET_SELECT = "구분",
			SELL_DAY = "일자", SELL_AMOUNT = "총 매출";
	
	public static ArrayList<CustomInfo> cusInfoArr = new ArrayList<CustomInfo>();
}
