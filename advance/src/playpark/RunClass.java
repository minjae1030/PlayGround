package playpark;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RunClass {
	private Calendar cal = null;
	private SimpleDateFormat sdt = null;
	public RunClass() {
		cal = Calendar.getInstance();
		sdt = new SimpleDateFormat("yyyyMMdd");
	}
	// **** 나이 계산 ****
	public int calAge(String customerIDNumber) {
		int age = 0;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat time = new SimpleDateFormat("yyyy");
		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		String [] ID_arr = customerIDNumber.split("-");
		String temp = "1";
		String ID_first_str = temp + ID_arr[0]; 
		int ID_first = Integer.parseInt(ID_first_str); // 1+주민번호 앞자리 
		int ID_last = Integer.parseInt(ID_arr[1]); // 주민번호 뒷자리
		int type = 0, BIRTH_mon_day = 0, customerYear = 0, customerMonth = 0, customerDay = 0;
		int koreanAge = 0, currentYear = 0, currentMonth = 0, currentDay;
		String currentYear_str, currentMonth_str, currentDay_str;
		
		type = ID_last / ConstValueClass.SEVEN_DIGIT; // 주민뒷자리 첫번호
		BIRTH_mon_day = ID_first % ConstValueClass.FIVE_DIGIT; // 생년월일
		customerDay = BIRTH_mon_day % ConstValueClass.TWO_DIGIT; // 일
		customerMonth = BIRTH_mon_day / ConstValueClass.TWO_DIGIT; // 월
		customerYear = (ID_first / ConstValueClass.FIVE_DIGIT) % ConstValueClass.TWO_DIGIT; // 생년
		
		if ((type == ConstValueClass.MALE_NEW) || (type == ConstValueClass.FEMALE_NEW)) { // 2000년생 이후일 경우
			customerYear += ConstValueClass.NEW_GENERATION; // 생년에 2000을 더하여 20xx 로 변경
		} else {
			customerYear += ConstValueClass.OLD_GENERATION; // 생년에 1900을 더하여 19xx 로 변경
		}
		
		currentYear_str = time.format(cal.getTime());
		currentYear = Integer.parseInt(currentYear_str);
		koreanAge = (currentYear - customerYear) + 1; // (현재년도 - 고객생년) + 1
		
		//**** 만 나이 계산 ****
		currentMonth_str = month.format(cal.getTime());
		currentMonth = Integer.parseInt(currentMonth_str);
		
		currentDay_str = day.format(cal.getTime());
		currentDay = Integer.parseInt(currentDay_str);
		if ((customerMonth < currentMonth) || 
				((customerMonth == currentMonth) && (customerDay <= currentDay))) {
			age = koreanAge - ConstValueClass.AFTER_BIRTH;
		} else {
			age = koreanAge - ConstValueClass.BEFORE_BIRTH;
		}
		
		return age;	
	}
	// **** 나이에 따른 연령그룹 분류 ****
	public int calAgeGroup(int age) {
		if (age < ConstValueClass.MIN_CHILD) {
			return 1;
		} else if ((age >= ConstValueClass.MIN_CHILD) && (age <= ConstValueClass.MAX_CHILD)) {
			return 2;
		} else if ((age >= ConstValueClass.MIN_TEEN) && (age <= ConstValueClass.MAX_TEEN)) {
			return 3;
		} else if ((age >= ConstValueClass.MIN_ADULT) && age <= ConstValueClass.MAX_ADULT) {
			return 4;
		} else if (age > ConstValueClass.MAX_ADULT) {
			return 5; 
		}
		return 0;
	}
	
	// **** 나이와 주간,야간권에 대한 할인적용 ****
	public int calPriceProcess(int age, int ticketSelect) {
		int calPrice = 0;
		if (calAgeGroup(age) == ConstValueClass.BABY) {
			calPrice = ConstValueClass.BABY_PRICE;
		} else if (calAgeGroup(age) == ConstValueClass.CHILD) {
			if (ticketSelect == 1) {
				calPrice = ConstValueClass.CHILD_DAY_PRICE;
			} else if (ticketSelect == 2) {
				calPrice = ConstValueClass.CHILD_NIGHT_PRICE;
			}
		} else if (calAgeGroup(age) == ConstValueClass.TEEN) {
			if (ticketSelect == 1) {
				calPrice = ConstValueClass.TEEN_DAY_PRICE;
			} else if (ticketSelect == 2) {
				calPrice = ConstValueClass.TEEN_NIGHT_PRICE;
			}
		} else if (calAgeGroup(age) == ConstValueClass.ADULT) {
			if (ticketSelect == 1) {
				calPrice = ConstValueClass.ADULT_DAY_PRICE;
			} else if (ticketSelect == 2) {
				calPrice = ConstValueClass.ADULT_NIGHT_PRICE;
			}
		} else {
			calPrice = ConstValueClass.OLD_DAY_PRICE;
		}
		return calPrice;
	}
	
	// **** 우대사항에 따른 할인적용 ****
	public int calDc(int calPrice, int dcSelect) {
		switch (dcSelect) {
		case 2:
			calPrice *= ConstValueClass.DISABLE_DC_RATE;
			break;
		case 3:
			calPrice *= ConstValueClass.MERIT_DC_RATE;
			break;
		case 4:
			calPrice *= ConstValueClass.MULTICHILD_DC_RATE;
			break;
		case 5:
			calPrice *= ConstValueClass.PREGNANT_DC_RATE;
			break;
		default:
			break;
		}
		return calPrice;
		
	}
	
	// **** 티켓개수에 따른 가격총합 ****
	public int calPriceResult(int calPrice, int orderCount) {
		return calPrice * orderCount;
	}
	
	// **** 입력값에 대한 총 가격출력 ****
	public int calProcess(String customerIDNumber, int ticketSelect, int dcSelect, int orderCount,
							int priceResult, int age) {
		int calPrice = 0;
		age = calAge(customerIDNumber); // 나이 받아오기
		calPrice = calPriceProcess(age, ticketSelect); // 나이에 따른 티켓 가격
		calPrice = calDc(calPrice, dcSelect); // 나이에 따른 티켓 가격과 우대사항에 따른 총 지불 가격
		priceResult = calPriceResult(calPrice, orderCount); // 총 지불가격 x 티켓 가격으로 최종 지불 가격 
		
		return priceResult;
	}
	// **** 사용자 정보 저장 ****
	public void saveOrder(int ticketSelect, int age, int orderCount, int priceResult, int dcSelect,
							ArrayList<CustomInfo> cusInfoArr) {
		//** 코드**
		int agegroup = 0;
		agegroup = calAgeGroup(age);
		CustomInfo cusInfo = new CustomInfo();
		cusInfo.setDate(sdt.format(cal.getTime()));
		cusInfo.setTicketkind(ticketSelect);
		cusInfo.setAge(age);
		cusInfo.setTicketnum(orderCount);
		cusInfo.setTicketprice(priceResult);
		cusInfo.setDc(dcSelect);
		cusInfo.setAgekind(agegroup);
		
		switch (agegroup) {
		case 1 :
			cusInfo.setAgegroup(ConstValueClass.INFANT);
			break;
		case 2 :
			cusInfo.setAgegroup(ConstValueClass.CHILDREN);
			break;
		case 3 :
			cusInfo.setAgegroup(ConstValueClass.JUVENILE);
			break;
		case 4 :
			cusInfo.setAgegroup(ConstValueClass.ADULT_PEOPLE);
			break;
		case 5 :
			cusInfo.setAgegroup(ConstValueClass.OLD_PEOPLE);
			break;
		default:
			break;
		}
		
		switch (ticketSelect) {
		case 1 :
			cusInfo.setTicketname(ConstValueClass.TICKET_DAY);
			break;
		case 2 :
			cusInfo.setTicketname(ConstValueClass.TICKET_NIGHT);
			break;
		default:
			break;
		}
		
		switch (dcSelect) {
		case 1 :
			cusInfo.setDcname(ConstValueClass.NONE);
			break;
		case 2 :
			cusInfo.setDcname(ConstValueClass.DISABLE);
			break;
		case 3 :
			cusInfo.setDcname(ConstValueClass.YOUGONGJA);
			break;
		case 4 :
			cusInfo.setDcname(ConstValueClass.MULTICHILD);
			break;
		case 5 :
			cusInfo.setDcname(ConstValueClass.PREGNANT);
			break;
		default :
			break;
		}
		
		
		cusInfoArr.add(cusInfo);
		
	}
	
}
