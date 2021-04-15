package playpark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class ReadCSV {
	public BufferedReader reader = null;
	public BufferedReader reader1 = null;
	public OutputClass out = null;
	
	public ReadCSV() throws IOException {
		reader = new BufferedReader(new FileReader(ConstValueClass.PATH1));
		reader1 = new BufferedReader(new FileReader(ConstValueClass.PATH1));
		out = new OutputClass();
		
	}

	public int [][] parseTICKETDATA() throws IOException {
		String line;
		String line_data[] = new String[6];
		String temp = "";
		int result[][] = new int[2][7];
		int count = 0;
		int ticketday = 0, baby = 0, child = 0, teen = 0, adult = 0, old = 0;
		int ticketnight = 0, baby_night = 0, child_night = 0, teen_night = 0, adult_night = 0, old_night = 0;
		int total_price = 0;
		int total_price_night = 0;
		while ((line = reader.readLine()) != null) {
			// [0]날짜  [1] 권종  [2] 연령구분 [3] 수량 [4] 가격 [5] 우대사항
			if (count > 0) {
				line_data = line.split(",");
				// **** printTICKETDATA1에 넘겨주기위하여 temp에 csv 라인별 정보 저장 ****
				for (int i = 0; i < line_data.length; i++) {
					if (i == (line_data.length -1)) {
						temp += line_data[i] + "\n";
					} else {
						temp += line_data[i] +",";
					}
				}
				// **** 권종 별 판매현황 통계를 위한 parsing ****
				if (Integer.parseInt(line_data[1]) == 1) {
					
					ticketday += Integer.parseInt(line_data[3]); // 주간권 총 티켓수

					if (Integer.parseInt(line_data[2]) == 1) {
						baby += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 2) {
						child += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 3) {
						teen += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 4) {
						adult += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 5) {
						old += Integer.parseInt(line_data[3]);
					}

					total_price += Integer.parseInt(line_data[4]);

				} else if (Integer.parseInt(line_data[1]) == 2) {
					ticketnight += Integer.parseInt(line_data[3]); // 야간권 총 티켓수

					if (Integer.parseInt(line_data[2]) == 1) {
						baby_night += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 2) {
						child_night += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 3) {
						teen_night += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 4) {
						adult_night += Integer.parseInt(line_data[3]);
					} else if (Integer.parseInt(line_data[2]) == 5) {
						old_night += Integer.parseInt(line_data[3]);
					}

					total_price_night += Integer.parseInt(line_data[4]);

				}
			}
			count ++;
		}
		out.printTICKETDATA1(temp); // 권종별 콘솔 출력
		reader.close();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				if (i == 0) {
					switch (j) {
					case 0:
						result[i][j] = ticketday;
						break;
					case 1:
						result[i][j] = baby;
						break;
					case 2:
						result[i][j] = child;
						break;
					case 3:
						result[i][j] = teen;
						break;
					case 4:
						result[i][j] = adult;
						break;
					case 5:
						result[i][j] = old;
						break;
					case 6:
						result[i][j] = total_price;
					default:
						break;	
					}
				} else {
					switch (j) {
					case 0:
						result[i][j] = ticketnight;
						break;
					case 1:
						result[i][j] = baby_night;
						break;
					case 2:
						result[i][j] = child_night;
						break;
					case 3:
						result[i][j] = teen_night;
						break;
					case 4:
						result[i][j] = adult_night;
						break;
					case 5:
						result[i][j] = old_night;
						break;
					case 6:
						result[i][j] = total_price_night;
					default:
						break;	
					}
				}
				
			}
		}
		return result;
	}
	
	public void parseDAYDATA() throws IOException {
		String line;
		String line_data[];
		String temp = "";
		int count = 0;
		int date_price_arr[] = new int [100];
		int date_dc_count_arr[] = new int [6];
		HashSet<String> sellday = new HashSet<String>();
		
		while ((line = reader1.readLine()) != null) {
			if (count > 0) {
				line_data = line.split(",");
				sellday.add(line_data[0]);
				date_dc_count_arr[0] += Integer.parseInt(line_data[3]);
				if (Integer.parseInt(line_data[5]) == 1) {
					date_dc_count_arr[1] += Integer.parseInt(line_data[3]);
				} else if (Integer.parseInt(line_data[5]) == 2) {
					date_dc_count_arr[2] += Integer.parseInt(line_data[3]);
				} else if (Integer.parseInt(line_data[5]) == 3) {
					date_dc_count_arr[3] += Integer.parseInt(line_data[3]);
				} else if (Integer.parseInt(line_data[5]) == 4) {
					date_dc_count_arr[4] += Integer.parseInt(line_data[3]);
				} else if (Integer.parseInt(line_data[5]) == 5) {
					date_dc_count_arr[5] += Integer.parseInt(line_data[3]);
				}
				for (int i = 0; i < line_data.length; i++) {
					if ( i == 0 ) { // 날짜
						temp += line_data[i] + ",";
					} else if (i == 4) { // 가격
						temp += line_data[i] + "\n";
					}
				}
			}
			count++;
		}
		Iterator<String> iter = sellday.iterator();
		String temp_arr [] = temp.split("\n");
		String temp_comma_arr[];
		
		//[0] 날짜 , [1] 가격
		int cnt = 0;
		String key = "";
		while (iter.hasNext()) {
			key = iter.next();
			int sum = 0;
			for (int i = 0; i < temp_arr.length; i++) { 
				temp_comma_arr = temp_arr[i].split(",");
				if (key.equals(temp_comma_arr[0])) {
					 sum += Integer.parseInt(temp_comma_arr[1]);
				}
			}
			date_price_arr[cnt] = sum;
			cnt++;
		}
		
		out.printDAYDATA1(sellday, date_price_arr);
		out.printDAYDATA2(date_dc_count_arr); // 우대 판매 현황
		reader1.close();
		

	}
	
}
