package baseball_game_copy2;

/*감독: user, com*/
public class Manager extends Lineup {
	protected Team team;

	public Manager() {
	}

	public void select_team(KBO kbo, int i) { //팀 고르기
		this.team = kbo.get(i);
		default_lineup(this.team.getTeam_name());
	}
}
