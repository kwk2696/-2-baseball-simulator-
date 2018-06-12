package baseball_game_copy2;

/*���� ���� ����*/
public class Umpire {
	protected int user_score;  //�츮�� ����
	protected int com_score;  //����� ����
	protected int score[][];  //�� �̴� ������Ȳ ����
	protected int out_count;  //���� �ƿ�ī��Ʈ

	protected int base;            //���ִ� base ��          
	protected boolean first_base;  //1�� ���ִ��� ����
	protected boolean second_base; //2�� ���ִ��� ����
	protected boolean third_base;  //3�� ���ִ��� ����

	protected int user_batter; //�츮�� Ÿ��
	protected int com_batter;  //����� Ÿ��
	protected int inning;     //���� �̴�
	protected int top_bottom; //��,��
	
	protected double user_pitch; //�츮�� ������
	protected double com_Spitch; //����� ���� ������
	protected double com_Rpitch; //�̴��� ���� ������
	protected boolean com_isRP;
	protected int end; //�������� ���� ����

	private double DP = 0.1;    //�����÷��� Ȯ��
	private double O12 = 0.116; //�ƿ���, 1->2 ���� Ȯ��
	private double O23 = 0.281; //�ƿ���, 2->3 ���� Ȯ��
	private double O34 = 0.409; //�ƿ���, 3->Ȩ ���� Ȯ��
	private double H13 = 0.276; //��Ÿ��, 1->3 ���� Ȯ��
	private double H24 = 0.618; //��Ÿ��, 2->Ȩ ���� Ȯ��
	private double D14 = 0.4;   //2��Ÿ��, 1->Ȩ ���� Ȯ��
	private double P_per_PA = 3.86; // ���� ��� Ÿ���� ������
	
	private String sb_comment;  //gui�� ����� �ڸ�Ʈ ����

	public Umpire() {//���� ���� �ʱ�ȭ
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

	private void add_score(int i) {//�� ���� ����
		if (top_bottom == 0)
			com_score += i;
		else
			user_score += i;

		score[top_bottom][inning - 1] += i;
	}

	public String com_score_toString() {// (gui)�� ����� ����� ���� ��ȯ
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

	public String user_score_toString() {// (gui)�� ����� �츮�� ���� ��ȯ
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

	public String comment_toString() {// (gui)�� ����� �ڸ�Ʈ ��ȯ
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(sb_comment);
		return sb.toString();
	}

	public void set_umpire(int hit) {// ���� ���� ���� ����
		double r;

		if (score[top_bottom][inning - 1] == -1) {
			score[top_bottom][inning - 1] = 0;
		}

		if (base == 0) {
			switch (hit) {
			case -1:
				sb_comment = "����<br>���� 1�翡 �ɾ���ϴ�.";
			case 1:
				first_base = true;
				base = 1;
				sb_comment = "����� ��Ÿ!";
				break;
			case 0:
				if (out_count == 2) {
					sb_comment = "�ƿ�!<br>��������!";
				} else {
					sb_comment = "Ÿ�� �ƿ�!";
				}
				out_count++;
				break;
			case 2:
				second_base = true;
				base = 1;
				sb_comment = "2��Ÿ!";
				break;
			case 3:
				third_base = true;
				base = 1;
				sb_comment = "Ÿ�� 3����� �����մϴ�!<br>...����!";
				break;
			case 4:
				add_score(1);
				sb_comment = "Ÿ�� ���� ���� �����ϴ�!<br>�ַ�Ȩ��!! ";
				break;
			}
		} else if (base == 1) {
			if (first_base == true) {
				switch (hit) {
				case -1:
					second_base = true;
					base = 2;
					sb_comment = "����!<br>1,2�� ��� ä�����ϴ�.";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "�ƿ�!<br>��������!";
					} else {
						if (r < DP) {
							first_base = false;
							base = 0;
							out_count += 2;
							if (out_count == 1) {
								sb_comment = "����Ÿ!<br>��������!";
							} else {
								sb_comment = "����Ÿ!<br>���İ��� �ƿ�ī��Ʈ �ΰ��� �ö󰩴ϴ�.";
							}
						} else if (r < DP + O12) {
							first_base = false;
							second_base = true;
							out_count++;
							sb_comment = "����Ÿ!<br>���� 2�翡 ���ϴ�.";
						} else {
							out_count++;
							sb_comment = "�ƿ�!<br>���� ��¦�� ���մϴ�!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H13) {
						third_base = true;
						base = 2;
						sb_comment = "��Ÿ!<br>1������ 3����� ���ϴ�.";
					} else {
						second_base = true;
						base = 2;
						sb_comment = "��Ÿ!";
					}
					break;
				case 2:
					r = Math.random();
					if (r < D14) {
						add_score(1);
						first_base = false;
						second_base = true;
						sb_comment = "2��Ÿ!<br>1������ Ȩ����...����!";
					} else {
						first_base = false;
						second_base = true;
						third_base = true;
						base = 2;
						sb_comment = "����� 2��Ÿ!";
					}
					break;
				case 3:
					first_base = false;
					third_base = true;
					add_score(1);
					sb_comment = "3��Ÿ!<br>1�� ���� ����!";
					break;
				case 4:
					first_base = false;
					base = 0;
					add_score(2);
					sb_comment = "���� ������� Ÿ��!<br>������ �Ѿ�ϴ�!";
					break;
				case 5:
					first_base = false;
					second_base = true;
					out_count++;
					sb_comment = "�����Ʈ ����!<br>�ƿ�ī��Ʈ �ϳ��� 2�縦 �ٲߴϴ�.";
					break;
				case 6:
					out_count++;
					sb_comment = "�����Ʈ ����!<br>���� 2�翡�� �ƿ�!";
					break;
				}
			} else if (second_base == true) {
				switch (hit) {
				case -1:
					first_base = true;
					base = 2;
					sb_comment = "����!<br>����ִ� 1�� ä�����ϴ�.";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "�ƿ�!<br>��������!";
					} else {
						if (r < O23) {
							second_base = false;
							third_base = true;
							out_count++;
							sb_comment = "����Ÿ!<br>���� 3�翡 ���ϴ�.";
						} else {
							out_count++;
							sb_comment = "�ƿ�!<br>���� 2�翡�� �������� ���մϴ�!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H24) {
						add_score(1);
						first_base = true;
						second_base = false;
						sb_comment = "��Ÿ!<br>2������ Ȩ �⵵...����!";
					} else {
						first_base = true;
						second_base = false;
						third_base = true;
						base = 2;
						sb_comment = "����� ��Ÿ!";
					}
					break;
				case 2:
					add_score(1);
					sb_comment = "2��Ÿ!<br>2������ ����!";
					break;
				case 3:
					second_base = false;
					third_base = true;
					add_score(1);
					sb_comment = "3��Ÿ!<br>2������ �ɾ ���ɴϴ�.";
					break;
				case 4:
					second_base = false;
					base = 0;
					add_score(2);
					sb_comment = "���� ������� Ÿ��!<br>������ �Ѿ�ϴ�!";
					break;
				case 5:
					second_base = false;
					third_base = true;
					out_count++;
					sb_comment = "�����Ʈ ����!<br>���� 3�翡 ���ϴ�.";
					break;
				case 6:
					first_base = true;
					second_base = false;
					out_count++;
					sb_comment = "�����Ʈ ����!<br>���� 3�翡�� �ƿ��˴ϴ�.";
					break;
				case 7:
					first_base = true;
					base = 2;
					sb_comment = "���ǻ籸!<br>����ִ� 1�縦 ä��ϴ�.";
					break;
				}
			} else if (third_base == true) {
				switch (hit) {
				case -1:
					first_base = true;
					base = 2;
					sb_comment = "����!<br>Ÿ�� 1��� �ɾ�ϴ�.";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "�����ƿ�!<br>��������";
					} else {
						if (r < O34) {
							third_base = false;
							if (out_count != 2)
								add_score(1);
							out_count++;
							base = 0;
							sb_comment = "3������ �±�...����!";
						} else {
							out_count++;
							sb_comment = "�ƿ�!<br>3������ �������� ���մϴ�.";
						}
					}
					break;
				case 1:
					first_base = true;
					third_base = false;
					add_score(1);
					sb_comment = "��Ÿ!<br>3������ Ȩ��!";
					break;
				case 2:
					second_base = true;
					third_base = false;
					add_score(1);
					sb_comment = "2��Ÿ!<br>3������ �����ְ� ���ɴϴ�.";
					break;
				case 3:
					add_score(1);
					sb_comment = "3��Ÿ!<br>3������ �ɾ ���ɴϴ�.";
					break;
				case 4:
					third_base = false;
					base = 0;
					sb_comment = "����,����,����!<br>Ȩ��!!!!!!";
					add_score(2);
				case 7:
					first_base = true;
					base = 2;
					sb_comment = "���ǻ籸!<br>����ִ� 1�縦 ä��ϴ�.";
					break;
				}
			}
		} else if (base == 2) {
			if (third_base == false) {
				switch (hit) {
				case -1:
					third_base = true;
					base = 3;
					sb_comment = "����!<br>���� ����!!";
					break;
				case 0:
					r = Math.random();
					if (out_count == 2) {
						out_count++;
						sb_comment = "�����ƿ�!<br>��������!";
					} else {
						if (r < DP) {
							first_base = false;
							second_base = false;
							third_base = true;
							base = 1;
							out_count += 2;
							sb_comment = "�߿��� ��Ȳ���� ���� ����Ÿ!<br>�帧�� �����ϴ�.";
						} else if (r < DP + O12) {
							first_base = false;
							third_base = true;
							out_count++;
							sb_comment = "����Ÿ!<br>���� �� ���̽��� �̵��մϴ�.";
						} else if (r < DP + O12 + O23) {
							second_base = false;
							third_base = true;
							out_count++;
							sb_comment = "Ÿ�� �ƿ�!<br>2�����ڴ� 3�翡 ���ϴ�.";
						} else {
							out_count++;
							sb_comment = "Ÿ�� �ƿ�!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H13) {
						second_base = false;
						third_base = true;
						add_score(1);
						sb_comment = "��Ÿ!<br>2������ ����!<br>1������...3�� ����!";
					} else if (r < H13 + H24) {
						add_score(1);
						sb_comment = "��Ÿ!<br>2������ Ȩ �⵵...����!";
					} else {
						third_base = true;
						base = 3;
						sb_comment = "��Ÿ!<br>���̽� �� ���ϴ�!";
					}
					break;
				case 2:
					r = Math.random();
					if (r < D14) {
						first_base = false;
						add_score(2);
						base = 1;
						sb_comment = "2��Ÿ!<br>1,2������ ��� Ȩ��!";
					} else {
						first_base = false;
						third_base = true;
						add_score(1);
						sb_comment = "2��Ÿ!<br>2������ Ȩ��!<br>1�����ڴ� 3����� ���ϴ�.";
					}
					break;
				case 3:
					first_base = false;
					second_base = false;
					third_base = true;
					base = 1;
					add_score(2);
					sb_comment = "3��Ÿ!<br>1�����ڱ��� �����Ӱ� ���ɴϴ�.";
					break;
				case 4:
					first_base = false;
					second_base = false;
					base = 0;
					add_score(3);
					sb_comment = "����,����,����!<br>������ ��½�ѱ�� Ȩ��!!!";
					break;
				case 5:
					// ���� 1,2�� ��Ȳ ��Ʈ ���н� Ȯ��
					first_base = false;
					third_base = true;
					out_count++;
					sb_comment = "�񼺹�Ʈ ����!<br>���� 2,3�簡 �˴ϴ�.";
					break;
				case 6:
					out_count++;
					sb_comment = "�����Ʈ ����!<br>Ÿ�� 3�翡�� �ƿ�!";
					break;
				}
			} else if (second_base == false) {
				switch (hit) {
				case -1:
					second_base = true;
					base = 3;
					sb_comment = "����!<br>���� ����!";
					break;
				case 0:
					if (out_count == 2) {
						out_count++;
						sb_comment = "�����ƿ�!<br>��������!";
					} else {
						r = Math.random();
						if (r < DP) {
							first_base = false;
							base = 1;
							out_count += 2;
							sb_comment = "�߿��� ��Ȳ���� ���� ����Ÿ!<br>Ÿ�� ���� ���� ���մϴ�.";
						} else if (r < DP + O34) {
							double r2 = Math.random();
							if (r2 < O12) {// 1,3������ ����
								first_base = false;
								second_base = true;
								third_base = false;
								add_score(1);
								out_count++;
								base = 1;
								sb_comment = "����Ÿ!<br>3������ Ȩ��!<br>1������ 2���!";
							} else {// 3�����ڸ� ����
								third_base = false;
								add_score(1);
								out_count++;
								base = 1;
								sb_comment = "3������ �±׾�<br>...����!";
							}
						} else if (r < DP + O34 + O12) {// 1�����ڸ� ����
							first_base = false;
							second_base = true;
							out_count++;
							sb_comment = "Ÿ�� �ƿ�!<br>������ ������ �ƴϳ׿�.";
						} else {
							out_count++;
							sb_comment = "Ÿ�� �ƿ�!";
						}
					}
					break;
				case 1:
					r = Math.random();
					if (r < H13) {
						add_score(1);
						sb_comment = "��Ÿ!<br>1����!<br>3�翡��...����!";
					} else {
						second_base = true;
						third_base = false;
						add_score(1);
						sb_comment = "����� ��Ÿ!<br>3������ Ȩ��!";
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
						sb_comment = "2��Ÿ!<br>1�����ڱ��� Ȩ�� ���ɴϴ�.";
					} else {
						first_base = false;
						second_base = true;
						add_score(1);
						sb_comment = "����� 2��Ÿ!<br>3������ Ȩ��!";
					}
					break;
				case 3:
					first_base = false;
					base = 1;
					add_score(2);
					sb_comment = "�ܾ߼� Ű�� �ѱ�� Ÿ��!<br>3��Ÿ!!";
					break;
				case 4:
					first_base = false;
					third_base = false;
					base = 0;
					add_score(3);
					sb_comment = "�ܾ߼� ���� �ٶ� ���ϴ�!<br>������!";
					break;
				}
			} else if (first_base == false) {
				switch (hit) {
				case -1:
					first_base = true;
					base = 3;
					sb_comment = "����!<br>Ÿ�� �ɾ���ϴ�.";
					break;
				case 0:
					if (out_count == 2) {
						out_count++;
						sb_comment = "Ÿ�� ��ȸ�� �츮�� ���մϴ�.<br>��������!";
					} else {
						r = Math.random();
						if (r < O34) {
							third_base = false;
							out_count++;
							base = 1;
							add_score(1);
							sb_comment = "3������ ��ũ��<br>...����!";
						} else if (r < O34 + O23) {
							second_base = false;
							out_count++;
							base = 1;
							add_score(1);
							sb_comment = "����Ÿ!<br>���� �� ���̽��� �̵��մϴ�.";
						} else {
							out_count++;
							sb_comment = "Ÿ�� �ƿ�!";
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
						sb_comment = "��Ÿ!<br>2�����ڱ��� Ȩ��!";
					} else {
						first_base = true;
						second_base = false;
						add_score(1);
						sb_comment = "��Ÿ!<br>3������ Ȩ��!";
					}
					break;
				case 2:
					third_base = false;
					base = 1;
					add_score(2);
					sb_comment = "2��Ÿ!<br>2,3�� ���� ��� Ȩ��!";
					break;
				case 3:
					second_base = false;
					base = 1;
					add_score(2);
					sb_comment = "�ܾ߼� Ű�� ��½ �ѱ�� Ÿ��!<br>3��Ÿ!!";
					break;
				case 4:
					second_base = false;
					third_base = false;
					base = 0;
					add_score(3);
					sb_comment = "���� ���� Ȩ���� ������ Ÿ��!<br>������!";
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
				sb_comment = "���� ������ ��鸳�ϴ�.<br>�о�� ����!";
				break;
			case 0:
				if (out_count == 2) {
					out_count++;
					sb_comment = "Ÿ�� �ƿ�!<br>���� ���⸦ ����ϴ�.";
				} else {
					r = Math.random();
					if (r < O34) {
						third_base = false;
						base = 2;
						out_count++;
						add_score(1);
						sb_comment = "3������ �±׾�...����!";
					} else {
						out_count++;
						sb_comment = "Ÿ�� �ƿ�!";
					}
				}
				break;
			case 1:
				r = Math.random();
				if (r < H24) {
					third_base = false;
					base = 2;
					add_score(2);
					sb_comment = "��Ÿ!<br>2�����ڱ��� Ȩ��!";
				} else {
					add_score(1);
					sb_comment = "��Ÿ!<br>������ ���ڸ���!";
				}
				break;
			case 2:
				r = Math.random();
				if (r < D14) {
					first_base = false;
					third_base = false;
					base = 1;
					add_score(3);
					sb_comment = "�Ͼ��� 2��Ÿ!!";
				} else {
					first_base = false;
					base = 2;
					add_score(2);
					sb_comment = "2��Ÿ!<br>���� 2,3��!";
				}
				break;
			case 3:
				first_base = false;
				second_base = false;
				base = 1;
				add_score(3);
				sb_comment = "���� ��� �������ϴ�!<br>3��Ÿ!";
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

	private void add_pitch() {// ���� ���� ������ ����
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
	
	public void check_outcount() {// ���������ؾ� �ϴ��� Ȯ��
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

	private int check_end() {// ������ �������� Ȯ��
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

	public void print_info() {// (console)���� ��Ȳ  ���
		System.out.println("�̴�: " + inning + " ����: " + top_bottom);
		System.out.println(user_score + " : " + com_score);
		System.out.println("�ƿ� ī��Ʈ: " + out_count);
		System.out.println("1��: " + first_base + " 2��: " + second_base + " 3��: " + third_base);
		if(top_bottom == 0) {
			System.out.println("�츮�� ������: "+user_pitch);
		}
		else {
			if(com_isRP == false) {
				System.out.println("����� ���� ������: "+com_Spitch);
			}
			else {
				System.out.println("����� ���� ������: "+com_Rpitch);
			}			
		}
		System.out.println("\n");
	}
}
