package baseball_game_copy2;

public class Batter extends Player {
	private double G; //게임수
	protected double PA; //타석
	private double AB; //타수
	private double R; //득점
	protected double H; //안타
	protected double twoB; //2루타
	protected double threeB; //3루타
	protected double HR; //홈런
	private double RBI; //타점
	protected double BB; //볼넷
	
	public Batter(String ...flags) {
		if(flags.length != 14)
			throw new IllegalArgumentException("not exact number of properties");
		this.Name =flags[1];
		this.POS = flags[2];
		this.AVG = Double.parseDouble(flags[3]);
		this.G=Double.parseDouble(flags[4]);
		this.PA =Double.parseDouble(flags[5]);
		this.AB=Double.parseDouble(flags[6]);
		this.R=Double.parseDouble(flags[7]);
		this.H=Double.parseDouble(flags[8]);
		this.twoB=Double.parseDouble(flags[9]);
		this.threeB=Double.parseDouble(flags[10]);
		this.HR=Double.parseDouble(flags[11]);
		this.RBI=Double.parseDouble(flags[12]);
		this.BB=Double.parseDouble(flags[13]);
	}
	
	
	public String toString() { //(gui) 타자 정보 출력
		StringBuilder sb = new StringBuilder();		
		sb.append(POS+" "+Name+" "+AVG);
		return sb.toString();
	}
	
}

