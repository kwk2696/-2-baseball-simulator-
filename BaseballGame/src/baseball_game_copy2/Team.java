package baseball_game_copy2;

//import java.util.ArrayList;
//import java.io.*;

public class Team {
	
	private String nameImage;
	private String logoImage;
	private String startImage;
	
	private String Team_name;

	public String getNameImage() {
		return nameImage;
	}
	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}
	public String getLogoImage() {
		return logoImage;
	}
	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public Team(String nameImage, String logoImage, String startImage) {
		super();
		this.nameImage = nameImage;
		this.logoImage = logoImage;
		this.startImage = startImage;
	}
	
	public Team(String Team_name) { //∆¿ √ ±‚»≠ 
		this.Team_name = Team_name;
	}
	
	public String getTeam_name() {
		return this.Team_name;
	}
}
