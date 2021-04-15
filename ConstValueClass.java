package playpark;

import java.util.ArrayList;

public class ConstValueClass {
	public static final int BABY_PRICE = 0, ADULT_DAY_PRICE = 56000, ADULT_NIGHT_PRICE = 46000, TEEN_DAY_PRICE = 47000,
			TEEN_NIGHT_PRICE = 40000, CHILD_DAY_PRICE = 44000, CHILD_NIGHT_PRICE = 37000, OLD_DAY_PRICE = 44000,
			OLD_NIGHT_PRICE = 44000;
	
	public static String FULL_DIGIT = "10000000000000", FULL_DIGIT_MIN = "10000000000";
	public static int SEVEN_DIGIT = 1000000, FIVE_DIGIT = 10000;
	public static int CHOICE_CONTINUE = 1, CHOICE_EXIT = 2;
	
	public static int TWO_DIGIT = 100, ONE_DIGIT = 10, OLD_GENERATION = 1900, NEW_GENERATION = 2000, MALE_OLD = 1,
			FEMALE_OLD = 2, MALE_NEW = 3, FEMALE_NEW = 4, BEFORE_BIRTH = 2, AFTER_BIRTH = 1;
	
	public static int MIN_BABY = 1, MIN_CHILD = 3, MIN_TEEN = 13, MIN_ADULT = 19, MAX_CHILD = 12, MAX_TEEN = 18,
			MAX_ADULT = 64;
	
	public static int BABY = 1, CHILD = 2, TEEN = 3, ADULT = 4, OLD = 5;
	
	public static double DISABLE_DC_RATE = 0.6, MERIT_DC_RATE = 0.5, MULTICHILD_DC_RATE = 0.8, PREGNANT_DC_RATE = 0.85;
	
	public static int MAX_COUNT = 10, MIN_COUNT = 1;
	
	public static String DAY = "주간권", NIGHT = "야간권", INFANT = "유아", CHILDREN = "어린이", JUVENILE = "청소년",
							ADULT_PEOPLE = "어른", OLD_PEOPLE = "노인", TICKET_DAY = "주간권", TICKET_NIGHT = "야간권",
							NONE = "없음", DISABLE = "장애인", YOUGONGJA = "국가유공자", MULTICHILD = "다자녀", PREGNANT = "임산부" ;
	
	public static String PATH = "C:\\Users\\kmj\\Desktop\\PlayGround.csv";
	public static ArrayList<CustomInfo> cusInfoArr = new ArrayList<CustomInfo>();
}
