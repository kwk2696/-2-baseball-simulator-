package baseball_game_copy2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/*���� ����*/
public class PlayBall extends Umpire {
	Manager user;
	Manager com;

	Batter batter; // ���� Ÿ��
	Pitcher pitcher; // ���� ����

	private double Lg_AVG = 0.286; // ���� ��� Ÿ��
	private double sbunt_AVG = 0.624; // ���� �����Ʈ ���� Ȯ��

	public PlayBall(Manager user, Manager com) { // ���� ���� �ʱ�ȭ
		this.user = user;
		this.com = com;
		// get_LgAVG();
	}

	/*
	 * ���� = -1, �ƿ� = 0, 1��Ÿ = 1, 2��Ÿ = 2, 3��Ÿ = 3, Ȩ�� = 4, ��Ʈ���� = 5, ��Ʈ ���� = 6,
	 * ���ǻ籸= 7
	 */
	public int hit() {// Ÿ�� ���� �׼�
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
	 * 2�簡 �ƴ� ��, 1�����ڸ� ������, 2�����ڸ� ������, 1,2������ ������ �����Ʈ ����
	 */
	public int sbunt() {// Ÿ�� �����Ʈ �׼�
		batter_pitcher();

		double r = Math.random();
		if (r < sbunt_AVG)
			return 6;
		else
			return 5;
	}

	public int pitch() {// ���� ������ �׼�
		int h = hit();
		return h;
	}

	/*
	 * 2�����ڸ� ������, 3�����ڸ� ������, 2,3�� ���� ������ ���ǻ籸 ����
	 */
	public int IBB() {// ���� ���ǻ籸 �׼�
		batter_pitcher();
		return 7;
	}

	private void batter_pitcher() {// ���� Ÿ�� ���� ����
		if (top_bottom == 0) {
			pitcher = user.P;
			//System.out.println((com_batter + 1) + "�� ° Ÿ���Դϴ�.");
			batter = com.starting.get(com_batter);
			com_batter++;
			if (com_batter == 9)
				com_batter = 0;
		} else {
			pitcher = com.P;
			//System.out.println((user_batter + 1) + "�� ° Ÿ���Դϴ�.");
			batter = user.starting.get(user_batter);
			user_batter++;
			if (user_batter == 9)
				user_batter = 0;
		}
	}

	private double Log5(double batter_avg, double pitcher_avg) { // bill james Log5�̷����� ��Ÿ Ȯ�� ���ϱ�
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
		
		//System.out.println("log5Ȯ��: "+cal_avg[4][4]);
		return cal_avg[4][4];
	}

	private double cal_Log5(double a) { // �Ҽ��� ��°¥������ �ݿø�
		double flag = a / (2 * (1 - a));
		return (Math.round(flag * 1000) / 1000.0);
	}

	private void get_LgAVG() { // csv���Ͽ��� ���� ���Ÿ�� �ҷ�����
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
			//System.out.println("��� ��ä�Ϸ�"+com.P.Name);
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