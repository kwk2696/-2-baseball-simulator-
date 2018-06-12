package baseball_game_copy2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/*라인업*/
public class Lineup {
	protected ArrayList<Batter> starting; // 스타팅 라인업
	protected Pitcher P; // 선발투수
	protected ArrayList<Pitcher> bench_SP; // 벤치 투수
	protected ArrayList<Pitcher> bench_RP;
	protected Pitcher bench_CP;
	protected ArrayList<Batter> bench_batter; // 벤치 타자 

	public Lineup() {
	}

	public void default_lineup(String team_name) { // csv파일에서 라인업 불러오기
		starting = new ArrayList<>();
		bench_SP = new ArrayList<>();
		bench_RP = new ArrayList<>();
		bench_batter = new ArrayList<>();

		String file_name = team_name + ".csv";
		Reader isr;
		BufferedReader br = null;
		InputStream fis;

		try {
			fis = new FileInputStream(file_name);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line = "";
			line = br.readLine();
			String[] flag;
			for (int i = 0; i < 9; i++) {
				line = br.readLine();
				flag = line.split(",");
				Batter batter = new Batter(flag);
				starting.add(batter);
			}
			line = br.readLine();
			flag = line.split(",");
			P = new Pitcher(flag);

			line = br.readLine();

			for (int i = 0; i < 6; i++) {
				line = br.readLine();
				flag = line.split(",");
				Batter batter = new Batter(flag);
				bench_batter.add(batter);
			}
			for (int i = 0; i < 4; i++) {
				line = br.readLine();
				flag = line.split(",");
				Pitcher pitcher = new Pitcher(flag);
				bench_SP.add(pitcher);
			}
			for (int i = 0; i < 6; i++) {
				line = br.readLine();
				flag = line.split(",");
				Pitcher pitcher = new Pitcher(flag);
				bench_RP.add(pitcher);
			}
			line = br.readLine();
			flag = line.split(",");
			bench_CP = new Pitcher(flag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String view_lineup() { // (gui)스타팅 라인업 목록 반환
		StringBuilder sb = new StringBuilder();

		sb.append("<html>");

		int i = 1;
		for (Player batter : starting) {
			sb.append(i + ". ");
			sb.append(batter.toString());
			sb.append("<br>");
			i++;
		}

		sb.append(i + ". ");
		sb.append(P.toString());
		sb.append("<br>");

		sb.append("</html>");

		return sb.toString();
	}

	public String pitcher_name() {// (gui)투수 이름 반환
		return P.Name;
	}
	public String pitcher_ERA() {// (gui)투수 방어율 반환
		return Double.toString(P.ERA);
	}
	public String batter_name(int i) {// (gui)타자 이름 반환
		return starting.get(i).Name;
	}
	public String batter_AVG(int i) {// (gui)타자 타율 반환
		return Double.toString(starting.get(i).AVG);
	}

}
