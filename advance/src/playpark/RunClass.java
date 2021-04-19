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
	// **** ���� ��� ****
	public int calAge(String customerIDNumber) {
		int age = 0;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat time = new SimpleDateFormat("yyyy");
		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		String [] ID_arr = customerIDNumber.split("-");
		String temp = "1";
		String ID_first_str = temp + ID_arr[0]; 
		int ID_first = Integer.parseInt(ID_first_str); // 1+�ֹι�ȣ ���ڸ� 
		int ID_last = Integer.parseInt(ID_arr[1]); // �ֹι�ȣ ���ڸ�
		int type = 0, BIRTH_mon_day = 0, customerYear = 0, customerMonth = 0, customerDay = 0;
		int koreanAge = 0, currentYear = 0, currentMonth = 0, currentDay;
		String currentYear_str, currentMonth_str, currentDay_str;
		
		type = ID_last / ConstValueClass.SEVEN_DIGIT; // �ֹε��ڸ� ù��ȣ
		BIRTH_mon_day = ID_first % ConstValueClass.FIVE_DIGIT; // �������
		customerDay = BIRTH_mon_day % ConstValueClass.TWO_DIGIT; // ��
		customerMonth = BIRTH_mon_day / ConstValueClass.TWO_DIGIT; // ��
		customerYear = (ID_first / ConstValueClass.FIVE_DIGIT) % ConstValueClass.TWO_DIGIT; // ����
		
		if ((type == ConstValueClass.MALE_NEW) || (type == ConstValueClass.FEMALE_NEW)) { // 2000��� ������ ���
			customerYear += ConstValueClass.NEW_GENERATION; // ���⿡ 2000�� ���Ͽ� 20xx �� ����
		} else {
			customerYear += ConstValueClass.OLD_GENERATION; // ���⿡ 1900�� ���Ͽ� 19xx �� ����
		}
		
		currentYear_str = time.format(cal.getTime());
		currentYear = Integer.parseInt(currentYear_str);
		koreanAge = (currentYear - customerYear) + 1; // (����⵵ - ������) + 1
		
		//**** �� ���� ��� ****
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
	// **** ���̿� ���� ���ɱ׷� �з� ****
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
	
	// **** ���̿� �ְ�,�߰��ǿ� ���� �������� ****
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
	
	// **** �����׿� ���� �������� ****
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
	
	// **** Ƽ�ϰ����� ���� �������� ****
	public int calPriceResult(int calPrice, int orderCount) {
		return calPrice * orderCount;
	}
	
	// **** �Է°��� ���� �� ������� ****
	public int calProcess(String customerIDNumber, int ticketSelect, int dcSelect, int orderCount,
							int priceResult, int age) {
		int calPrice = 0;
		age = calAge(customerIDNumber); // ���� �޾ƿ���
		calPrice = calPriceProcess(age, ticketSelect); // ���̿� ���� Ƽ�� ����
		calPrice = calDc(calPrice, dcSelect); // ���̿� ���� Ƽ�� ���ݰ� �����׿� ���� �� ���� ����
		priceResult = calPriceResult(calPrice, orderCount); // �� ���Ұ��� x Ƽ�� �������� ���� ���� ���� 
		
		return priceResult;
	}
	// **** ����� ���� ���� ****
	public void saveOrder(int ticketSelect, int age, int orderCount, int priceResult, int dcSelect,
							ArrayList<CustomInfo> cusInfoArr) {
		//** �ڵ�**
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
