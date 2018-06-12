package baseball_game_copy2;

import java.util.ArrayList;

public class KBO {
	private ArrayList<Team> teams; 
	private static int num_Teams;
	private static String [] team_names 
	= {"SK","Lotte","LG","NC","��ȭ","�ؼ�","KT","�λ�","�Ｚ","KIA"}; //10�� ���� �̸� ����
	
	public KBO() {//teams �ʱ�ȭ
		teams = new ArrayList<>();		
		num_Teams =  team_names.length;
		for(String team_name:team_names) {
			Team team = new Team(team_name);
			teams.add(team);
		}
	}
	
	public Team get(int i) { //�� ��ȯ
		return teams.get(i);
	}
	
	public int getnum_teams() {//�� ���� ��ȯ
		return num_Teams;
	}
	
	public String getTeamName(int index) { //�� �̸� ��ȯ
		return team_names[index];
	}	
}
