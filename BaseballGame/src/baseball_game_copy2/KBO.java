package baseball_game_copy2;

import java.util.ArrayList;

public class KBO {
	private ArrayList<Team> teams; 
	private static int num_Teams;
	private static String [] team_names 
	= {"SK","Lotte","LG","NC","한화","넥센","KT","두산","삼성","KIA"}; //10개 구단 이름 저장
	
	public KBO() {//teams 초기화
		teams = new ArrayList<>();		
		num_Teams =  team_names.length;
		for(String team_name:team_names) {
			Team team = new Team(team_name);
			teams.add(team);
		}
	}
	
	public Team get(int i) { //팀 반환
		return teams.get(i);
	}
	
	public int getnum_teams() {//팀 개수 반환
		return num_Teams;
	}
	
	public String getTeamName(int index) { //팀 이름 반환
		return team_names[index];
	}	
}
