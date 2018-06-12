package baseball_game_copy2;

/*심판 정보 저장*/
public class Umpire {
	protected int user_score;  //우리팀 점수
	protected int com_score;  //상대팀 점수
	protected int score[][];  //매 이닝 득점상황 저장
	protected int out_count;  //현재 아웃카운트

	protected int base;            //차있는 base 수          
	protected boolean first_base;  //1루 차있는지 여부
	protected boolean second_base; //2루 차있는지 여부
	protected boolean third_base;  //3루 ㅏ있는지 여부

	protected int user_batter; //우리팀 타순
	protected int com_batter;  //상대팀 타순
	protected int inning;     //현재 이닝
	protected int top_bottom; //초,말
	
	protected double user_pitch; //우리팀 투구수
	protected double com_Spitch; //상대팀 선발 투구수
	protected double com_Rpitch; //싱대팀 불펜 투구수
	protected boolean com_isRP;
	protected int end; //게임종료 여부 저장

	private double DP = 0.1;    //더블플레이 확률
	private double O12 = 0.116; //아웃시, 1->2 진루 확률
	private double O23 = 0.281; //아웃시, 2->3 진루 확률
	private double O34 = 0.409; //아웃시, 3->홈 진루 확률
	private double H13 = 0.276; //안타시, 1->3 진루 확률
	private double H24 = 0.618; //안타시, 2->홈 진루 확률
	private double D14 = 0.4;   //2루타시, 1->홈 진루 확률
	private double P_per_PA = 3.86; // 리그 평균 타석당 투구수
	
	private String sb_comment;  //gui에 출려갈 코멘트 저장

	public Umpire() {//심판 정보 초기화
		user_score = 0;
		com_score = 0;
		score = new int[2][12];
		out_count = 0;

		base = 0;
		first_base = false;
		second_base = false;
		third_base = false;

		user_batter = 0;
		com_batter = 0;
		inning = 1;
		top_bottom = 0;
		
		user_pitch = 0;
		com_Spitch = 0;
		com_Rpitch = 0;
		com_isRP = false;
		end = 0;

		sb_comment = "";

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 12; j++) {
				score[i][j] = -1;
			}
		}
	}

	private void add_score(int i) {//총 점수 갱신
		if (top_bottom == 0)
			com_score += i;
		else
			user_score += i;

		score[top_bottom][inning - 1] += i;
	}

	public String com_score_toString() {// (gui)에 출력할 상대팀 점수 반환
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		for (int i = 0; i < 12; i++) {
			if (score[0][i] != -1) {
				sb.append(score[0][i]);
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			}
		}
		return sb.toString();
	}

	public String user_score_toString() {// (gui)에 출력할 우리팀 점수 반환
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		for (int i = 0; i < 12; i++) {
			if (score[1][i] != -1) {
				sb.append(score[1][i]);
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			}
		}
		return sb.toString();
	}

	public String comment_toString() {// (gui)에 출력할 코멘트 반환
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(sb_comment);
		return sb.toString();
	}

	public void set_umpire(int hit) {// 현재 심판 정보 갱신
		double r;

		if (score[top_bottom][inning - 1] == -1) {
			score[top_bottom][inning - 1] = 0;
		}

		if (base == 0) {
			switch (hit) {
			case -1:
				sb_comment = "볼넷<br>주자 1루에 걸어들어갑니다.";
			case 1:
				first_base = true;
				base = 1;
				sb_comment = "깔끔한 안타!";
				break;
			case 0:
				if (out_count == 2) {
					sb_comment = "아웃!<br>공수교대!";
				} else {
					sb_comment = "타자 아웃!";
				}
				out_count++;
				break;
			case 2:
				second_base = true;
				base = 1;
				sb_comment = "2루타!";
				break;
			case 3:
				third_base = true;
				base = 1;
				sb_comment = "타자 3루까지 도전합니다!<br>...세잎!";
				break;
			case 4:
				add_score(1);
				sb_comment = "타구 쭉쭉 뻗어 나갑니다!<br>솔로홈런!! ";
				break;
			}
		} else if (base == 1) {
			if (first_base == true) {
				switch (hit) {
				case -1:
					second_base = true;
					base = 2;
					sb_comment = "볼넷!<br>1,2루 모두 채워집니다.";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "아웃!<br>공수교대!";
					} else {
						if (r < DP) {
							first_base = false;
							base = 0;
							out_count += 2;
							if (out_count == 1) {
								sb_comment = "병살타!<br>공수교대!";
							} else {
								sb_comment = "병살타!<br>순식간에 아웃카운트 두개가 올라갑니다.";
							}
						} else if (r < DP + O12) {
							first_base = false;
							second_base = true;
							out_count++;
							sb_comment = "진루타!<br>주자 2루에 들어갑니다.";
						} else {
							out_count++;
							sb_comment = "아웃!<br>주자 꼼짝도 못합니다!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H13) {
						third_base = true;
						base = 2;
						sb_comment = "안타!<br>1루주자 3루까지 들어갑니다.";
					} else {
						second_base = true;
						base = 2;
						sb_comment = "안타!";
					}
					break;
				case 2:
					r = Math.random();
					if (r < D14) {
						add_score(1);
						first_base = false;
						second_base = true;
						sb_comment = "2루타!<br>1루주자 홈까지...세잎!";
					} else {
						first_base = false;
						second_base = true;
						third_base = true;
						base = 2;
						sb_comment = "깔끔한 2루타!";
					}
					break;
				case 3:
					first_base = false;
					third_base = true;
					add_score(1);
					sb_comment = "3루타!<br>1루 주자 득점!";
					break;
				case 4:
					first_base = false;
					base = 0;
					add_score(2);
					sb_comment = "쭉쭉 뻗어나가는 타구!<br>담장을 넘어갑니다!";
					break;
				case 5:
					first_base = false;
					second_base = true;
					out_count++;
					sb_comment = "희생번트 성공!<br>아웃카운트 하나와 2루를 바꿉니다.";
					break;
				case 6:
					out_count++;
					sb_comment = "희생번트 실패!<br>주자 2루에서 아웃!";
					break;
				}
			} else if (second_base == true) {
				switch (hit) {
				case -1:
					first_base = true;
					base = 2;
					sb_comment = "볼넷!<br>비어있던 1루 채워집니다.";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "아웃!<br>공수교대!";
					} else {
						if (r < O23) {
							second_base = false;
							third_base = true;
							out_count++;
							sb_comment = "진루타!<br>주자 3루에 들어갑니다.";
						} else {
							out_count++;
							sb_comment = "아웃!<br>주자 2루에서 움직이지 못합니다!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H24) {
						add_score(1);
						first_base = true;
						second_base = false;
						sb_comment = "안타!<br>2루주자 홈 쇄도...세잎!";
					} else {
						first_base = true;
						second_base = false;
						third_base = true;
						base = 2;
						sb_comment = "깔끔한 안타!";
					}
					break;
				case 2:
					add_score(1);
					sb_comment = "2루타!<br>2루주자 득점!";
					break;
				case 3:
					second_base = false;
					third_base = true;
					add_score(1);
					sb_comment = "3루타!<br>2루주자 걸어서 들어옵니다.";
					break;
				case 4:
					second_base = false;
					base = 0;
					add_score(2);
					sb_comment = "쭉쭉 뻗어나가는 타구!<br>담장을 넘어갑니다!";
					break;
				case 5:
					second_base = false;
					third_base = true;
					out_count++;
					sb_comment = "희생번트 성공!<br>주자 3루에 들어갑니다.";
					break;
				case 6:
					first_base = true;
					second_base = false;
					out_count++;
					sb_comment = "희생번트 실패!<br>주자 3루에서 아웃됩니다.";
					break;
				case 7:
					first_base = true;
					base = 2;
					sb_comment = "고의사구!<br>비어있던 1루를 채웁니다.";
					break;
				}
			} else if (third_base == true) {
				switch (hit) {
				case -1:
					first_base = true;
					base = 2;
					sb_comment = "볼넷!<br>타자 1루로 걸어갑니다.";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "쓰리아웃!<br>공수교대";
					} else {
						if (r < O34) {
							third_base = false;
							if (out_count != 2)
								add_score(1);
							out_count++;
							base = 0;
							sb_comment = "3루주자 태그...세잎!";
						} else {
							out_count++;
							sb_comment = "아웃!<br>3루주자 움직이지 못합니다.";
						}
					}
					break;
				case 1:
					first_base = true;
					third_base = false;
					add_score(1);
					sb_comment = "안타!<br>3루주자 홈인!";
					break;
				case 2:
					second_base = true;
					third_base = false;
					add_score(1);
					sb_comment = "2루타!<br>3루주자 여유있게 들어옵니다.";
					break;
				case 3:
					add_score(1);
					sb_comment = "3루타!<br>3루주자 걸어서 들어옵니다.";
					break;
				case 4:
					third_base = false;
					base = 0;
					sb_comment = "간다,간다,간다!<br>홈런!!!!!!";
					add_score(2);
				case 7:
					first_base = true;
					base = 2;
					sb_comment = "고의사구!<br>비어있던 1루를 채웁니다.";
					break;
				}
			}
		} else if (base == 2) {
			if (third_base == false) {
				switch (hit) {
				case -1:
					third_base = true;
					base = 3;
					sb_comment = "볼넷!<br>주자 만루!!";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "쓰리아웃!<br>공수교대!";
					} else {
						if (r < DP) {
							first_base = false;
							second_base = false;
							third_base = true;
							base = 1;
							out_count += 2;
							sb_comment = "중요한 상황에서 나온 병살타!<br>흐름을 끊습니다.";
						} else if (r < DP + O12) {
							first_base = false;
							third_base = true;
							out_count++;
							sb_comment = "진루타!<br>주자 한 베이스씩 이동합니다.";
						} else if (r < DP + O12 + O23) {
							second_base = false;
							third_base = true;
							out_count++;
							sb_comment = "타자 아웃!<br>2루주자는 3루에 들어갑니다.";
						} else {
							out_count++;
							sb_comment = "타자 아웃!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H13) {
						second_base = false;
						third_base = true;
						add_score(1);
						sb_comment = "안타!<br>2루주자 득점!<br>1루주자...3루 세잎!";
					} else if (r < H13 + H24) {
						add_score(1);
						sb_comment = "안타!<br>2루주자 홈 쇄도...세잎!";
					} else {
						third_base = true;
						base = 3;
						sb_comment = "안타!<br>베이스 꽉 찹니다!";
					}
					break;
				case 2:
					r = Math.random();
					if (r < D14) {
						first_base = false;
						add_score(2);
						base = 1;
						sb_comment = "2루타!<br>1,2루주자 모두 홈인!";
					} else {
						first_base = false;
						third_base = true;
						add_score(1);
						sb_comment = "2루타!<br>2루주자 홈인!<br>1루주자는 3루까지 갑니다.";
					}
					break;
				case 3:
					first_base = false;
					second_base = false;
					third_base = true;
					base = 1;
					add_score(2);
					sb_comment = "3루타!<br>1루주자까지 여유롭게 들어옵니다.";
					break;
				case 4:
					first_base = false;
					second_base = false;
					base = 0;
					add_score(3);
					sb_comment = "간다,간다,간다!<br>담장을 훌쩍넘기는 홈런!!!";
					break;
				case 5:
					// 주자 1,2루 상황 번트 실패시 확인
					first_base = false;
					third_base = true;
					out_count++;
					sb_comment = "희성번트 성공!<br>주자 2,3루가 됩니다.";
					break;
				case 6:
					out_count++;
					sb_comment = "희생번트 실패!<br>타자 3루에서 아웃!";
					break;
				}
			} else if (second_base == false) {
				switch (hit) {
				case -1:
					second_base = true;
					base = 3;
					sb_comment = "볼넷!<br>주자 만루!";
					break;
				case 0:
					if (out_count == 2) {
						out_count++;
						sb_comment = "쓰리아웃!<br>공수교대!";
					} else {
						r = Math.random();
						if (r < DP) {
							first_base = false;
							base = 1;
							out_count += 2;
							sb_comment = "중요한 상황에서 나온 병살타!<br>타자 고개를 들지 못합니다.";
						} else if (r < DP + O34) {
							double r2 = Math.random();
							if (r2 < O12) {// 1,3루주자 진루
								first_base = false;
								second_base = true;
								third_base = false;
								add_score(1);
								out_count++;
								base = 1;
								sb_comment = "진루타!<br>3루주자 홈인!<br>1루주자 2루로!";
							} else {// 3루주자만 진루
								third_base = false;
								add_score(1);
								out_count++;
								base = 1;
								sb_comment = "3루주자 태그업<br>...세잎!";
							}
						} else if (r < DP + O34 + O12) {// 1루주자만 진루
							first_base = false;
							second_base = true;
							out_count++;
							sb_comment = "타자 아웃!<br>다행이 병살은 아니네요.";
						} else {
							out_count++;
							sb_comment = "타자 아웃!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H13) {
						add_score(1);
						sb_comment = "안타!<br>1득점!<br>3루에서...세잎!";
					} else {
						second_base = true;
						third_base = false;
						add_score(1);
						sb_comment = "깔끔한 안타!<br>3루주자 홈인!";
					}
					break;
				case 2:
					r = Math.random();
					if (r < D14) {
						first_base = false;
						second_base = true;
						third_base = false;
						add_score(2);
						base = 1;
						sb_comment = "2루타!<br>1루주자까지 홈에 들어옵니다.";
					} else {
						first_base = false;
						second_base = true;
						add_score(1);
						sb_comment = "깔끔한 2루타!<br>3루주자 홈인!";
					}
					break;
				case 3:
					first_base = false;
					base = 1;
					add_score(2);
					sb_comment = "외야수 키를 넘기는 타구!<br>3루타!!";
					break;
				case 4:
					first_base = false;
					third_base = false;
					base = 0;
					add_score(3);
					sb_comment = "외야수 그저 바라만 봅니다!<br>쓰리런!";
					break;
				}
			} else if (first_base == false) {
				switch (hit) {
				case -1:
					first_base = true;
					base = 3;
					sb_comment = "볼넷!<br>타자 걸어들어갑니다.";
					break;
				case 0:
					if (out_count == 2) {
						out_count++;
						sb_comment = "타자 기회를 살리지 못합니다.<br>공수교대!";
					} else {
						r = Math.random();
						if (r < O34) {
							third_base = false;
							out_count++;
							base = 1;
							add_score(1);
							sb_comment = "3루주자 태크업<br>...세잎!";
						} else if (r < O34 + O23) {
							second_base = false;
							out_count++;
							base = 1;
							add_score(1);
							sb_comment = "진루타!<br>주자 한 베이스씩 이동합니다.";
						} else {
							out_count++;
							sb_comment = "타자 아웃!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H24) {
						first_base = true;
						second_base = false;
						third_base = false;
						base = 1;
						add_score(2);
						sb_comment = "안타!<br>2루주자까지 홈인!";
					} else {
						first_base = true;
						second_base = false;
						add_score(1);
						sb_comment = "안타!<br>3루주자 홈인!";
					}
					break;
				case 2:
					third_base = false;
					base = 1;
					add_score(2);
					sb_comment = "2루타!<br>2,3루 주자 모두 홈인!";
					break;
				case 3:
					second_base = false;
					base = 1;
					add_score(2);
					sb_comment = "외야수 키를 훌쩍 넘기는 타구!<br>3루타!!";
					break;
				case 4:
					second_base = false;
					third_base = false;
					base = 0;
					add_score(3);
					sb_comment = "맞자 마자 홈런을 직감한 타구!<br>쓰리런!";
					break;
				case 7:
					first_base = true;
					base = 3;
					break;
				}
			}
		} else {
			switch (hit) {
			case -1:
				add_score(1);
				sb_comment = "투수 제구가 흔들립니다.<br>밀어내기 볼넷!";
				break;
			case 0:
				if (out_count == 2) {
					out_count++;
					sb_comment = "타자 아웃!<br>만루 위기를 벗어납니다.";
				} else {
					r = Math.random();
					if (r < O34) {
						third_base = false;
						base = 2;
						out_count++;
						add_score(1);
						sb_comment = "3루주자 태그업...세잎!";
					} else {
						out_count++;
						sb_comment = "타자 아웃!";
					}
				}
				break;
			case 1:
				r = Math.random();
				if (r < H24) {
					third_base = false;
					base = 2;
					add_score(2);
					sb_comment = "안타!<br>2루주자까지 홈인!";
				} else {
					add_score(1);
					sb_comment = "안타!<br>여전히 주자만루!";
				}
				break;
			case 2:
				r = Math.random();
				if (r < D14) {
					first_base = false;
					third_base = false;
					base = 1;
					add_score(3);
					sb_comment = "싹쓸이 2루타!!";
				} else {
					first_base = false;
					base = 2;
					add_score(2);
					sb_comment = "2루타!<br>주자 2,3루!";
				}
				break;
			case 3:
				first_base = false;
				second_base = false;
				base = 1;
				add_score(3);
				sb_comment = "주자 모두 쓸어담습니다!<br>3루타!";
				break;
			case 4:
				first_base = false;
				second_base = false;
				third_base = false;
				base = 0;
				add_score(4);
				sb_comment = "GRAND SLAM!!!!!";
				break;
			}
		}
		add_pitch();
		//print_info();
		
		end = check_end();
	}

	private void add_pitch() {// 현재 투수 투구수 갱신
		if(top_bottom == 0) {
			user_pitch += P_per_PA;
		}
		else {
			if(com_isRP == false) {
				com_Spitch += P_per_PA;
			}
			else {
				com_Rpitch += P_per_PA;
			}
		}
	}
	
	public void check_outcount() {// 공수교대해야 하는지 확인
		if (out_count == 3) {
			out_count = 0;
			base = 0;

			first_base = false;
			second_base = false;
			third_base = false;

			if (top_bottom == 0)
				top_bottom = 1;
			else {
				top_bottom = 0;
				inning++;
			}
		}
	}

	private int check_end() {// 게임이 끝났는지 확인
		if (inning == 9 && top_bottom == 0 && out_count == 3) {
			if (user_score > com_score)
				return 1;
			else
				return 0;
		}
		if (inning == 9 && top_bottom == 1 && out_count == 3) {
			if (user_score != com_score)
				return 1;
			else
				return 0;
		} else if (inning == 10 && top_bottom == 1 && user_score > com_score)
			return 1;
		else if (inning == 10 && top_bottom == 1 && out_count == 3 && user_score != com_score)
			return 1;
		else if (inning == 11 && top_bottom == 1 && user_score > com_score)
			return 1;
		else if (inning == 11 && top_bottom == 1 && out_count == 3 && user_score != com_score)
			return 1;
		else if (inning == 12 && top_bottom == 1 && user_score > com_score)
			return 1;
		else if (inning == 12 && top_bottom == 1 && out_count == 3)
			return 1;
		return 0;
	}

	public void print_info() {// (console)현재 상황  출력
		System.out.println("이닝: " + inning + " 상하: " + top_bottom);
		System.out.println(user_score + " : " + com_score);
		System.out.println("아웃 카운트: " + out_count);
		System.out.println("1루: " + first_base + " 2루: " + second_base + " 3루: " + third_base);
		if(top_bottom == 0) {
			System.out.println("우리팀 투구수: "+user_pitch);
		}
		else {
			if(com_isRP == false) {
				System.out.println("상대팀 선발 투구수: "+com_Spitch);
			}
			else {
				System.out.println("상대팀 불펜 투구수: "+com_Rpitch);
			}			
		}
		System.out.println("\n");
	}
}
