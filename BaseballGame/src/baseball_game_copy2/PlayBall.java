package baseball_game_copy2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/*게임 시작*/
public class PlayBall extends Umpire {
	Manager user;
	Manager com;

	Batter batter; // 현재 타자
	Pitcher pitcher; // 현재 투수

	private double Lg_AVG = 0.286; // 리그 평균 타율
	private double sbunt_AVG = 0.624; // 리그 희생번트 실패 확률

	public PlayBall(Manager user, Manager com) { // 게임 시작 초기화
		this.user = user;
		this.com = com;
		// get_LgAVG();
	}

	/*
	 * 볼넷 = -1, 아웃 = 0, 1루타 = 1, 2루타 = 2, 3루타 = 3, 홈런 = 4, 번트성공 = 5, 번트 실패 = 6,
	 * 고의사구= 7
	 */
	public int hit() {// 타자 강공 액션
		double hit_avg, first_avg, second_avg, third_avg, HR_avg, BB_avg;
		int i;

		batter_pitcher();
		//System.out.println(pitcher.Name+" "+batter.Name);
		hit_avg = Log5(batter.AVG, pitcher.AVG);
		first_avg = (batter.H - batter.twoB - batter.threeB - batter.HR) / batter.H;
		first_avg = Math.round(first_avg * 1000) / 1000.0;
		second_avg = batter.twoB / batter.H;
		second_avg = Math.round(second_avg * 1000) / 1000.0;
		third_avg = batter.threeB / batter.H;
		third_avg = Math.round(third_avg * 1000) / 1000.0;
		HR_avg = batter.twoB / batter.H;
		HR_avg = Math.round(HR_avg * 1000) / 1000.0;
		BB_avg = batter.BB / (batter.PA - batter.H);
		BB_avg = Math.round(BB_avg * 1000) / 1000.0;

		double r1 = Math.random();
		if (r1 < hit_avg) {
			double r2 = Math.random();
			if (r2 < first_avg)
				i = 1;
			else if (r2 < first_avg + second_avg)
				i = 2;
			else if (r2 < first_avg + second_avg + HR_avg)
				i = 4;
			else
				i = 3;
		} else {
			double r3 = Math.random();
			if (r3 < BB_avg)
				i = -1;
			else
				i = 0;
		}

		return i;
	}

	/*
	 * 2사가 아닐 때, 1루주자만 있을때, 2루주자만 있을때, 1,2루주자 있을때 희생번트 가능
	 */
	public int sbunt() {// 타자 희생번트 액션
		batter_pitcher();

		double r = Math.random();
		if (r < sbunt_AVG)
			return 6;
		else
			return 5;
	}

	public int pitch() {// 투수 던지기 액션
		int h = hit();
		return h;
	}

	/*
	 * 2루주자만 있을때, 3루주자만 있을때, 2,3루 주자 있을때 고의사구 가능
	 */
	public int IBB() {// 투수 고의사구 액션
		batter_pitcher();
		return 7;
	}

	private void batter_pitcher() {// 현재 타자 투수 갱신
		if (top_bottom == 0) {
			pitcher = user.P;
			//System.out.println((com_batter + 1) + "번 째 타자입니다.");
			batter = com.starting.get(com_batter);
			com_batter++;
			if (com_batter == 9)
				com_batter = 0;
		} else {
			pitcher = com.P;
			//System.out.println((user_batter + 1) + "번 째 타자입니다.");
			batter = user.starting.get(user_batter);
			user_batter++;
			if (user_batter == 9)
				user_batter = 0;
		}
	}

	private double Log5(double batter_avg, double pitcher_avg) { // bill james Log5이론으로 안타 확률 구하기
		double cal_avg[][] = new double[5][5];

		cal_avg[0][0] = batter_avg;
		cal_avg[0][1] = Lg_AVG;
		cal_avg[0][2] = cal_Log5(cal_avg[0][0]);
		cal_avg[0][3] = cal_Log5(cal_avg[0][1]);
		cal_avg[0][4] = cal_avg[0][2] / (cal_avg[0][2] + cal_avg[0][3]);
		cal_avg[0][4] = (Math.round(cal_avg[0][4] * 1000) / 1000.0);

		cal_avg[1][0] = 1 - pitcher_avg;
		cal_avg[1][1] = 1 - Lg_AVG;
		cal_avg[1][2] = cal_Log5(cal_avg[1][0]);
		cal_avg[1][3] = cal_Log5(cal_avg[1][1]);
		cal_avg[1][4] = cal_avg[1][2] / (cal_avg[1][2] + cal_avg[1][3]);
		cal_avg[1][4] = (Math.round(cal_avg[1][4] * 1000) / 1000.0);

		cal_avg[2][0] = cal_avg[0][4];
		cal_avg[2][1] = cal_avg[1][4];
		cal_avg[2][2] = cal_Log5(cal_avg[2][0]);
		cal_avg[2][3] = cal_Log5(cal_avg[2][1]);
		cal_avg[2][4] = cal_avg[2][2] / (cal_avg[2][2] + cal_avg[2][3]);
		cal_avg[2][4] = (Math.round(cal_avg[2][4] * 1000) / 1000.0);

		cal_avg[3][0] = 0.5;
		cal_avg[3][1] = Lg_AVG;
		cal_avg[3][2] = cal_Log5(cal_avg[3][0]);
		cal_avg[3][3] = cal_Log5(cal_avg[3][1]);
		cal_avg[3][4] = cal_avg[3][2] / (cal_avg[3][2] + cal_avg[3][3]);
		cal_avg[3][4] = (Math.round(cal_avg[3][4] * 1000) / 1000.0);

		cal_avg[4][0] = cal_avg[2][4];
		cal_avg[4][1] = cal_avg[3][4];
		cal_avg[4][2] = cal_Log5(cal_avg[4][0]);
		cal_avg[4][3] = cal_Log5(cal_avg[4][1]);
		cal_avg[4][4] = cal_avg[4][2] / (cal_avg[4][2] + cal_avg[4][3]);
		cal_avg[4][4] = (Math.round(cal_avg[4][4] * 1000) / 1000.0);
		
		//System.out.println("log5확률: "+cal_avg[4][4]);
		return cal_avg[4][4];
	}

	private double cal_Log5(double a) { // 소수점 넷째짜리에서 반올림
		double flag = a / (2 * (1 - a));
		return (Math.round(flag * 1000) / 1000.0);
	}

	private void get_LgAVG() { // csv파일에서 리그 평균타율 불러오기
		String file_name = "League.csv";
		Reader isr;
		BufferedReader br = null;
		InputStream fis;

		try {
			fis = new FileInputStream(file_name);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			String line = br.readLine();
			String[] flag = line.split(",");
			Lg_AVG = Float.parseFloat(flag[0]);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int substitution() {
		int r;
		int num_RP = user.bench_RP.size();
		if (num_RP == 0) {
			if (user.bench_CP == null) {
				return 0;
			} else {
				user.P = user.bench_CP;
				user.bench_CP = null;
				user_pitch = 0;
				return 1;
			}
		}
		if (inning == 9 && (user_score - com_score >= 0) && user.bench_CP != null) {
			user.P = user.bench_CP;
			user.bench_CP = null;
			user_pitch = 0;
			return 1;
		} else {
			r = (int) (Math.random() * num_RP);
			user.P = user.bench_RP.get(r);
			user.bench_RP.remove(r);
			user_pitch = 0;
			return 1;
		}
	}
	public int ai_subsitution() {
		int r;
		int com_RP = com.bench_RP.size();
		if(com_Spitch > 95.0) {
			r = (int) (Math.random() * com_RP);
			com.P = com.bench_RP.get(r);
			com.bench_RP.remove(r);
			com_Spitch = 0;
			com_isRP = true;
			//System.out.println("상대 교채완료"+com.P.Name);
			return 1;
		}
		if(com_isRP == true && com_Rpitch > 25) {
			if (com_RP == 0) {
				if (com.bench_CP == null) {
					return 0;
				} else {
					com.P = com.bench_CP;
					com.bench_CP = null;
					com_Rpitch = 0;
					return 1;
				}
			}
			if (inning == 9 && (com_score - user_score >= 0) && com.bench_CP != null) {
				com.P = com.bench_CP;
				com.bench_CP = null;
				com_Rpitch = 0;
				return 1;
			} else {
				r = (int) (Math.random() * com_RP);
				com.P = com.bench_RP.get(r);
				com.bench_RP.remove(r);
				com_Rpitch = 0;
				return 1;
			}
		}
		return 0;
	}
}