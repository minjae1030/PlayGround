package playpark;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
	public BufferedReader reader = null;

	public ReadCSV() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(ConstValueClass.PATH1));
	}

	public int [][] parseTICKETDATA() throws IOException {
		String line;
		String line_data[] = new String[6];
		int result[][] = new int[2][7];
		int count = 0;
		int ticketday = 0, baby = 0, child = 0, teen = 0, adult = 0, old = 0;
		int ticketnight = 0, baby_night = 0, child_night = 0, teen_night = 0, adult_night = 0, old_night = 0;
		int total_price = 0;
		int total_price_night = 0;
		while ((line = reader.readLine()) != null) {
			// [0]날짜 / [1] 권종 /[2] 연령구분/[3] 수량/[4] 가격/[5] 우대사항
			if (count > 0) {
				line_data = line.split(",");
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
}
