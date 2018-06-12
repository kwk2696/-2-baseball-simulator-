package baseball_game_copy2;

public class Pitcher extends Player{
	protected double ERA; //�����
	private double G; //���Ӽ�
	private double W; //��
	private double L; //��
	private double SV; //���̺�
	private double HLD; //Ȧ��
	private double IP; //�̴�
	private double SO; //����
	private double WHIP; //�̴״� �����
	
	public Pitcher(String ...flags) {
		if(flags.length != 13)
			throw new IllegalArgumentException("not exact number of properties");
		this.Name =flags[1];
		this.POS = flags[2];
		this.AVG = Double.parseDouble(flags[3]);
		this.ERA=Double.parseDouble(flags[4]);
		this.G=Double.parseDouble(flags[5]);
		this.W =Double.parseDouble(flags[6]);
		this.L=Double.parseDouble(flags[7]);
		this.SV=Double.parseDouble(flags[8]);
		this.HLD=Double.parseDouble(flags[9]);
		this.IP=Double.parseDouble(flags[10]);
		this.SO=Double.parseDouble(flags[11]);
		this.WHIP=Double.parseDouble(flags[12]);
	}
	
	
	public String toString() {//(gui) ���� ���� ���
		StringBuilder sb = new StringBuilder();		
		sb.append(POS+" "+Name+" "+ERA);
		return sb.toString();
	}
	
}

