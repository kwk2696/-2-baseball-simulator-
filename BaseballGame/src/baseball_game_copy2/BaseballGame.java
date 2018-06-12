package baseball_game_copy2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BaseballGame extends JFrame {

	private KBO kbo;
	private Manager user;
	private Manager com;
	private PlayBall playball;

	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/��.png")));

	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/������.png"));
	private ImageIcon exittButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/������Entered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/���ӽ��۹�ưEnterd.png"));
	private ImageIcon startButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/���ӽ��۹�ư.png"));
	private ImageIcon tutorialButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Ʃ�丮���ưEnterd.png"));
	private ImageIcon tutorialButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/Ʃ�丮���ư.png"));
	private ImageIcon gameStartButtonBasicImage = new ImageIcon(Main.class.getResource("../images/�����۹�ư.png"));
	private ImageIcon gameStartButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/�����۹�ưEnterd.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/���������̵�.png"));
	private ImageIcon leftButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/���������̵�Entered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/�����������̵�.png"));
	private ImageIcon rightButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/�����������̵�Entered.png"));
	private ImageIcon selectButtonBasicImage = new ImageIcon(Main.class.getResource("../images/����button.png"));
	private ImageIcon selectButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/����buttonEnterd.png"));
	private ImageIcon viewRankButtonBasicImage = new ImageIcon(Main.class.getResource("../images/���������ư.png"));
	private ImageIcon viewRankButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/���������ưEnterd.png"));
	private ImageIcon substitutionButtonBasicImage = new ImageIcon(Main.class.getResource("../images/������ü��ư.png"));
	private ImageIcon substitutionButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/������ü��ưEnterd.png"));
	private ImageIcon substitutionButtonInhibitedImage = new ImageIcon(Main.class.getResource("../images/������ü��ưInhibit.png"));
	private ImageIcon hitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/HIT��ư.png"));
	private ImageIcon hitButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/HIT��ưEnterd.png"));
	private ImageIcon pitchButtonBasicImage = new ImageIcon(Main.class.getResource("../images/������ư.png"));
	private ImageIcon pitchButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/������ưEnterd.png"));
	private ImageIcon ibbButtonBasicImage = new ImageIcon(Main.class.getResource("../images/IBB��ư.png"));
	private ImageIcon ibbButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/IBB��ưEnterd.png"));
	private ImageIcon ibbButtonInhibitedImage = new ImageIcon(Main.class.getResource("../images/IBB��ưinhibit.png"));
	private ImageIcon buntButtonBasicImage = new ImageIcon(Main.class.getResource("../images/��Ʈ��ư.png"));
	private ImageIcon buntButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/��Ʈ��ưEnterd.png"));
	private ImageIcon buntButtonInhibitedImage = new ImageIcon(Main.class.getResource("../images/��Ʈ��ưinhibit.png"));
	private ImageIcon goBackButtonBasicImage = new ImageIcon(Main.class.getResource("../images/���ư����ư.png"));
	private ImageIcon goBackButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/���ư����ưEnterd.png"));
	private ImageIcon gameEndButtonBasicImage = new ImageIcon(Main.class.getResource("../images/���ӳ������ư.png"));
	private ImageIcon gameEndButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/���ӳ������ưEnterd.png"));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton tutorialButton = new JButton(tutorialButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton selectButton = new JButton(selectButtonBasicImage);
	private JButton viewRankButton = new JButton(viewRankButtonBasicImage);
	private JButton goBackButton = new JButton(goBackButtonBasicImage);
	private JButton gameStartButton = new JButton(gameStartButtonBasicImage);
	private JButton substitutionButton = new JButton(substitutionButtonBasicImage);
	private JButton hitButton = new JButton(hitButtonBasicImage);
	private JButton pitchButton = new JButton(pitchButtonBasicImage);
	private JButton buntButton = new JButton(buntButtonInhibitedImage);
	private JButton ibbButton = new JButton(ibbButtonInhibitedImage);
	private JButton gameEndButton = new JButton(gameEndButtonBasicImage);

	private JLabel myTeamLabel = new JLabel();
	private JLabel myTeamNameLabel = new JLabel();
	private JLabel myTeamPitcherLabel = new JLabel();
	private JLabel myTeamBatterLabel = new JLabel();
	private JLabel myTeamPitcherRLabel = new JLabel();
	private JLabel myTeamBatterRLabel = new JLabel();
	private JLabel oppTeamLabel = new JLabel();
	private JLabel oppTeamNameLabel = new JLabel();
	private JLabel oppTeamPitcherLabel = new JLabel();
	private JLabel oppTeamBatterLabel = new JLabel();
	private JLabel oppTeamPitcherRLabel = new JLabel();
	private JLabel oppTeamBatterRLabel = new JLabel();
	private JLabel playerListLabel = new JLabel();
	private JLabel myTeamInning = new JLabel();
	private JLabel oppTeamInning = new JLabel();
	private JLabel commentLabel = new JLabel();
	private JLabel nowInningLabel = new JLabel();
	private JLabel nowPositionLabel = new JLabel();
	private JLabel userTotalLabel = new JLabel();
	private JLabel comTotalLabel = new JLabel();

	private int mouseX, mouseY;

	private boolean isStartScreen = true;
	private boolean isMainScreen = false;
	private boolean isMyTeam = true;
	private boolean isGameScreen = false;
	private boolean isBuntPossible = false;
	private boolean isIBBPossible = false;
	private boolean isSubPossible = true;

	ArrayList<Team> teamList = new ArrayList<Team>();

	private Image selectedImage;
	private Image nameImage;
	private Image myTeamLogo;

	private int nowSelected = 0;
	private int myTeamIndex = 0;
	private int oppTeamIndex = 0;

	private Image runnerIconImage;
	private Image outIconImage;
	private Image topBottomIconImage;
	private Image youWinIconImage;
	private Image youLoseIconImage;
	private Image DrawIconImage;

	private Image commentPopUp;

	public BaseballGame() {
		setUndecorated(true);
		setTitle("KBO Simulator");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		Music introMusic = new Music("��������.mp3",true);
		introMusic.start();

		teamList.add(new Team("SK���̹���name.png", "SK���̹���logo.png", "SK���̹���.png"));
		teamList.add(new Team("�Ե����̾���name.png", "�Ե����̾���logo.png", "�Ե����̾���.jpg"));
		teamList.add(new Team("LGƮ����name.png", "lgƮ����logo.png", "LGƮ����.png"));
		teamList.add(new Team("NC���̳뽺name.png", "nc���̳뽺logo.png", "NC���̳뽺.jpg"));
		teamList.add(new Team("��ȭ�̱۽�name.png", "��ȭ�̱۽�logo.png", "��ȭ�̱۽�.jpg"));
		teamList.add(new Team("�ؼ��������name.png", "�ؼ��������logo.png", "�ؼ��������.jpg"));
		teamList.add(new Team("KT����name.png", "kt����logo.png", "KT����.jpg"));
		teamList.add(new Team("�λ꺣�name.png", "�λ꺣�logo.png", "�λ꺣�.jpg"));
		teamList.add(new Team("�Ｚ���̿���name.png", "�Ｚ���̿���logo.png", "�Ｚ���̿���.jpg"));
		teamList.add(new Team("���Ÿ�̰���name.png", "���Ÿ�̰���logo.png", "���Ÿ�̰���.png"));

		background = new ImageIcon(Main.class.getResource("../images/���ӽ��۹��.png")).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/SK���̹���.png")).getImage();
		nameImage = new ImageIcon(Main.class.getResource("../images/SK���̹���name.png")).getImage();

		exitButton.setBounds(1010, 0, 25, 25);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exittButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		menuBar.setBounds(0, 0, 1040, 25);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		leftButton.setBounds(230, 260, 204, 208);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.setVisible(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
				// �������� �̵�
			}
		});
		add(leftButton);

		rightButton.setBounds(590, 260, 204, 208);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.setVisible(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ���������� �̵�
				selectRight();
			}
		});
		add(rightButton);

		selectButton.setBounds(412, 400, 204, 208);
		selectButton.setBorderPainted(false);
		selectButton.setContentAreaFilled(false);
		selectButton.setFocusPainted(false);
		selectButton.setVisible(false);
		selectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectButton.setIcon(selectButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				selectButton.setIcon(selectButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("���������ġ.mp3",false);
				ButtonMusic.start();
				// �� ����
				if (isMyTeam) {
					/* ������ �� ���� */
					user = new Manager();
					user.select_team(kbo, nowSelected);

					background = new ImageIcon(Main.class.getResource("../images/�����������.png")).getImage();
					isMyTeam = false;
					myTeamIndex = nowSelected;
					nowSelected = 0;
					selectTeam(nowSelected);
				} else {
					com = new Manager();
					com.select_team(kbo, nowSelected);
					oppTeamIndex = nowSelected;

					leftButton.setVisible(false);
					rightButton.setVisible(false);
					selectButton.setVisible(false);
					viewRankButton.setVisible(false);

					background = new ImageIcon(Main.class.getResource("../images/����¹��.png")).getImage();
					gameStartButton.setVisible(true);

					isMainScreen = false;
					isMyTeam = true;

					myTeamLabel.setText(user.view_lineup());
					myTeamLabel.setOpaque(false);
					myTeamLabel.setForeground(Color.white);
					Font font = new Font("���� ���", Font.BOLD, 20);
					myTeamLabel.setFont(font);
					myTeamLabel.setBounds(160, 130, 200, 500);
					myTeamLabel.setVisible(true);

					oppTeamLabel.setText(com.view_lineup());
					oppTeamLabel.setOpaque(false);
					oppTeamLabel.setForeground(Color.white);
					oppTeamLabel.setFont(font);
					oppTeamLabel.setBounds(700, 130, 200, 500);
					oppTeamLabel.setVisible(true);
					
				}
			}
		});
		add(selectButton);
		add(myTeamLabel);
		add(oppTeamLabel);

		substitutionButton.setBounds(37, 600, 198, 48);
		substitutionButton.setBorderPainted(false);
		substitutionButton.setContentAreaFilled(false);
		substitutionButton.setFocusPainted(false);
		substitutionButton.setVisible(false);
		substitutionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				substitutionButton.setIcon(substitutionButtonInhibitedImage);
				if (isSubPossible)
					substitutionButton.setIcon(substitutionButtonEnterdImage);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				substitutionButton.setIcon(substitutionButtonInhibitedImage);
				if (isSubPossible)
					substitutionButton.setIcon(substitutionButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("���������ġ.mp3",false);
				ButtonMusic.start();
				// ������ü
				if (isSubPossible) {
					if (playball.substitution() == 1) {

						commentLabel.setText("���� ��ü!");
						commentLabel.setForeground(Color.white);
						Font font = new Font("���� ���", Font.PLAIN, 16);
						commentLabel.setFont(font);

						playerListLabel.setText(user.view_lineup());
						myTeamPitcherLabel.setText(user.pitcher_name());
						myTeamPitcherRLabel.setText(user.pitcher_ERA());

					} else {
						commentLabel.setText("<html>���� ��ü ����!<br>�� �̻� ��ü�� ������ �����ϴ�");
						commentLabel.setForeground(Color.white);
						Font font = new Font("���� ���", Font.PLAIN, 16);
						commentLabel.setFont(font);
					}
				}
				else
					substitutionButton.setIcon(substitutionButtonInhibitedImage);
			}
		});
		add(substitutionButton);

		hitButton.setBounds(273, 520, 240, 47);
		hitButton.setBorderPainted(false);
		hitButton.setContentAreaFilled(false);
		hitButton.setFocusPainted(false);
		hitButton.setVisible(false);
		hitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hitButton.setIcon(hitButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hitButton.setIcon(hitButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("���������ġ.mp3",false);
				ButtonMusic.start();
				// HIT
				int h = playball.hit();
				playball.set_umpire(h);

				commentLabel.setText(playball.comment_toString());

				if (playball.ai_subsitution() == 1) {
					oppTeamPitcherLabel.setText(com.pitcher_name());
					oppTeamPitcherRLabel.setText(com.pitcher_ERA());
					oppTeamBatterLabel.setText(com.batter_name(playball.com_batter));
					oppTeamBatterRLabel.setText(com.batter_AVG(playball.com_batter));
				}

				playball.check_outcount();

				changeTopBottom();
				myTeamInning.setText(playball.user_score_toString());
				oppTeamInning.setText(playball.com_score_toString());
				myTeamInning.setVisible(true);
				userTotalLabel.setText(Integer.toString(playball.user_score));
				comTotalLabel.setText(Integer.toString(playball.com_score));
				nowInningLabel.setText(Integer.toString(playball.inning));

				/*
				 * commentLabel.setText(playball.comment_toString());
				 * 
				 * playball.check_outcount();
				 */

				if ((playball.out_count != 2 && playball.base == 1 && playball.first_base == true)
						|| (playball.out_count != 2 && playball.base == 1 && playball.second_base == true)
						|| (playball.out_count != 2 && playball.base == 2 && playball.third_base == false)) {
					buntButton.setIcon(buntButtonBasicImage);
					isBuntPossible = true;
				} else {
					buntButton.setIcon(buntButtonInhibitedImage);
					isBuntPossible = false;
				}

				if (playball.end == 1) {
					// System.exit(playball.end);
				}
				
				if (isSubPossible)
					substitutionButton.setIcon(substitutionButtonBasicImage);
			}
		});
		add(hitButton);

		buntButton.setBounds(273, 590, 240, 47);
		buntButton.setBorderPainted(false);
		buntButton.setContentAreaFilled(false);
		buntButton.setFocusPainted(false);
		buntButton.setVisible(false);
		buntButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buntButton.setIcon(buntButtonInhibitedImage);
				if (isBuntPossible)
					buntButton.setIcon(buntButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buntButton.setIcon(buntButtonInhibitedImage);
				if (isBuntPossible)
					buntButton.setIcon(buntButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				// BUNT
				if (isBuntPossible) {
					Music ButtonMusic = new Music("���������ġ.mp3",false);
					ButtonMusic.start();
					int h = playball.sbunt();
					playball.set_umpire(h);

					commentLabel.setText(playball.comment_toString());

					playball.check_outcount();

					changeTopBottom();
					myTeamInning.setText(playball.user_score_toString());
					oppTeamInning.setText(playball.com_score_toString());
					myTeamInning.setVisible(true);
					userTotalLabel.setText(Integer.toString(playball.user_score));
					comTotalLabel.setText(Integer.toString(playball.com_score));
					nowInningLabel.setText(Integer.toString(playball.inning));

					/*
					 * commentLabel.setText(playball.comment_toString());
					 * 
					 * playball.check_outcount();
					 */

					if ((playball.out_count != 2 && playball.base == 1 && playball.first_base == true)
							|| (playball.out_count != 2 && playball.base == 1 && playball.second_base == true)
							|| (playball.out_count != 2 && playball.base == 2 && playball.third_base == false)) {
						buntButton.setIcon(buntButtonBasicImage);
						isBuntPossible = true;
					} else {
						buntButton.setIcon(buntButtonInhibitedImage);
						isBuntPossible = false;
					}
					if (playball.end == 1) {

						// System.exit(playball.end);
					}
					
					if (isSubPossible)
						substitutionButton.setIcon(substitutionButtonBasicImage);
				}
			}
		});
		add(buntButton);

		pitchButton.setBounds(273, 520, 240, 47);
		pitchButton.setBorderPainted(false);
		pitchButton.setContentAreaFilled(false);
		pitchButton.setFocusPainted(false);
		pitchButton.setVisible(false);
		pitchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pitchButton.setIcon(pitchButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pitchButton.setIcon(pitchButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("���������ġ.mp3",false);
				ButtonMusic.start();
				// PITCH
				int h = playball.pitch();
				playball.set_umpire(h);

				commentLabel.setText(playball.comment_toString());

				playball.check_outcount();

				changeTopBottom();
				myTeamInning.setText(playball.user_score_toString());
				oppTeamInning.setText(playball.com_score_toString());
				userTotalLabel.setText(Integer.toString(playball.user_score));
				comTotalLabel.setText(Integer.toString(playball.com_score));
				nowInningLabel.setText(Integer.toString(playball.inning));
				myTeamInning.setVisible(true);

				/*
				 * commentLabel.setText(playball.comment_toString());
				 * 
				 * playball.check_outcount();
				 */

				if ((playball.base == 1 && playball.second_base == true)
						|| (playball.base == 1 && playball.third_base == true)
						|| (playball.base == 2 && playball.first_base == false)) {
					ibbButton.setIcon(ibbButtonBasicImage);
					isIBBPossible = true;
				} else {
					ibbButton.setIcon(ibbButtonInhibitedImage);
					isIBBPossible = false;
				}
				if (playball.end == 1) {
					// System.exit(playball.end);
				}
				if (isSubPossible)
					substitutionButton.setIcon(substitutionButtonBasicImage);
			}
		});
		add(pitchButton);

		ibbButton.setBounds(273, 590, 240, 47);
		ibbButton.setBorderPainted(false);
		ibbButton.setContentAreaFilled(false);
		ibbButton.setFocusPainted(false);
		ibbButton.setVisible(false);
		ibbButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ibbButton.setIcon(ibbButtonInhibitedImage);
				if (isIBBPossible)
					ibbButton.setIcon(ibbButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ibbButton.setIcon(ibbButtonInhibitedImage);
				if (isIBBPossible)
					ibbButton.setIcon(ibbButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				// IBB
				if (isIBBPossible) {
					Music ButtonMusic = new Music("���������ġ.mp3",false);
					ButtonMusic.start();
					int h = playball.IBB();
					playball.set_umpire(h);

					commentLabel.setText(playball.comment_toString());

					playball.check_outcount();

					changeTopBottom();
					myTeamInning.setText(playball.user_score_toString());
					oppTeamInning.setText(playball.com_score_toString());
					myTeamInning.setVisible(true);
					userTotalLabel.setText(Integer.toString(playball.user_score));
					comTotalLabel.setText(Integer.toString(playball.com_score));
					nowInningLabel.setText(Integer.toString(playball.inning));

					/*
					 * commentLabel.setText(playball.comment_toString());
					 * 
					 * playball.check_outcount();
					 */

					if ((playball.base == 1 && playball.second_base == true)
							|| (playball.base == 1 && playball.third_base == true)
							|| (playball.base == 2 && playball.first_base == false)) {
						ibbButton.setIcon(ibbButtonBasicImage);
						isIBBPossible = true;
					} else {
						ibbButton.setIcon(ibbButtonInhibitedImage);
						isIBBPossible = false;
					}
					if (playball.end == 1) {
						// System.exit(playball.end);
					}
					if (isSubPossible)
						substitutionButton.setIcon(substitutionButtonEnterdImage);
				}
			}
		});
		add(ibbButton);

		viewRankButton.setBounds(395, 590, 236, 65);
		viewRankButton.setBorderPainted(false);
		viewRankButton.setContentAreaFilled(false);
		viewRankButton.setFocusPainted(false);
		viewRankButton.setVisible(false);
		viewRankButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				viewRankButton.setIcon(viewRankButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				viewRankButton.setIcon(viewRankButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("switch-1.mp3",false);
				ButtonMusic.start();
				// ��ŷ â ����
				isMainScreen = false;

				leftButton.setVisible(false);
				rightButton.setVisible(false);
				selectButton.setVisible(false);
				viewRankButton.setVisible(false);
				goBackButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../images/��������ȭ��.png")).getImage();
			}
		});
		add(viewRankButton);

		gameStartButton.setBounds(330, 565, 358, 74);
		gameStartButton.setBorderPainted(false);
		gameStartButton.setContentAreaFilled(false);
		gameStartButton.setFocusPainted(false);
		gameStartButton.setVisible(false);
		gameStartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gameStartButton.setIcon(gameStartButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				gameStartButton.setIcon(gameStartButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("���������ġ.mp3",false);
				ButtonMusic.start();
				// ������
				gameStartButton.setVisible(false);
				myTeamLabel.setVisible(false);
				oppTeamLabel.setVisible(false);

				runnerIconImage = new ImageIcon(Main.class.getResource("../images/���ھ�����.png")).getImage();
				outIconImage = new ImageIcon(Main.class.getResource("../images/�ƿ�������.png")).getImage();
				topBottomIconImage = new ImageIcon(Main.class.getResource("../images/������ݾ�����.png")).getImage();
				youWinIconImage = new ImageIcon(Main.class.getResource("../images/�¸�.png")).getImage();
				youLoseIconImage = new ImageIcon(Main.class.getResource("../images/�й�.png")).getImage();
				DrawIconImage = new ImageIcon(Main.class.getResource("../images/���º�.png")).getImage();
				background = new ImageIcon(Main.class.getResource("../images/���ӽ���ȭ��.png")).getImage();
				commentPopUp = new ImageIcon(Main.class.getResource("../images/�ؼ����.png")).getImage();

				isGameScreen = true;

				playball = new PlayBall(user, com);

				substitutionButton.setVisible(true);

				myTeamNameLabel.setText(kbo.getTeamName(myTeamIndex));
				myTeamNameLabel.setOpaque(false);
				myTeamNameLabel.setForeground(Color.white);
				Font font = new Font("���� ���", Font.BOLD, 20);
				myTeamNameLabel.setFont(font);
				myTeamNameLabel.setBounds(275, 135, 100, 50);
				myTeamNameLabel.setVisible(true);

				oppTeamNameLabel.setText(kbo.getTeamName(oppTeamIndex));
				oppTeamNameLabel.setOpaque(false);
				oppTeamNameLabel.setForeground(Color.white);
				oppTeamNameLabel.setFont(font);
				oppTeamNameLabel.setBounds(275, 93, 100, 50);
				oppTeamNameLabel.setVisible(true);

				playerListLabel.setText(myTeamLabel.getText());
				playerListLabel.setOpaque(false);
				playerListLabel.setForeground(Color.white);
				Font sFont = new Font("���� ���", Font.BOLD, 16);
				playerListLabel.setFont(sFont);
				playerListLabel.setBounds(68, 200, 200, 500);
				playerListLabel.setVisible(true);

				myTeamPitcherLabel.setText(user.pitcher_name());
				myTeamPitcherLabel.setOpaque(false);
				myTeamPitcherLabel.setForeground(Color.white);
				Font pfont = new Font("���� ���", Font.BOLD, 20);
				myTeamPitcherLabel.setFont(pfont);
				myTeamPitcherLabel.setBounds(275, 206, 100, 50);
				myTeamPitcherLabel.setVisible(true);

				myTeamPitcherRLabel.setText(user.pitcher_ERA());
				myTeamPitcherRLabel.setOpaque(false);
				myTeamPitcherRLabel.setForeground(Color.white);
				myTeamPitcherRLabel.setFont(pfont);
				myTeamPitcherRLabel.setBounds(490, 205, 100, 50);
				myTeamPitcherRLabel.setVisible(false);

				myTeamBatterLabel.setText(user.batter_name(playball.com_batter));
				myTeamBatterLabel.setOpaque(false);
				myTeamBatterLabel.setForeground(Color.white);
				myTeamBatterLabel.setFont(pfont);
				myTeamBatterLabel.setBounds(690, 206, 100, 50);
				myTeamBatterLabel.setVisible(false);

				myTeamBatterRLabel.setText(user.batter_AVG(playball.com_batter));
				myTeamBatterRLabel.setOpaque(false);
				myTeamBatterRLabel.setForeground(Color.white);
				myTeamBatterRLabel.setFont(pfont);
				myTeamBatterRLabel.setBounds(920, 205, 100, 50);
				myTeamBatterRLabel.setVisible(false);

				oppTeamPitcherLabel.setText(com.pitcher_name());
				oppTeamPitcherLabel.setOpaque(false);
				oppTeamPitcherLabel.setForeground(Color.white);
				oppTeamPitcherLabel.setFont(pfont);
				oppTeamPitcherLabel.setBounds(275, 206, 100, 50);
				oppTeamPitcherLabel.setVisible(false);

				oppTeamPitcherRLabel.setText(com.pitcher_ERA());
				oppTeamPitcherRLabel.setOpaque(false);
				oppTeamPitcherRLabel.setForeground(Color.white);
				oppTeamPitcherRLabel.setFont(pfont);
				oppTeamPitcherRLabel.setBounds(490, 205, 100, 50);
				oppTeamPitcherRLabel.setVisible(false);

				oppTeamBatterLabel.setText(com.batter_name(playball.com_batter));
				oppTeamBatterLabel.setOpaque(false);
				oppTeamBatterLabel.setForeground(Color.white);
				oppTeamBatterLabel.setFont(pfont);
				oppTeamBatterLabel.setBounds(690, 206, 100, 50);
				oppTeamBatterLabel.setVisible(true);

				oppTeamBatterRLabel.setText(com.batter_AVG(playball.com_batter));
				oppTeamBatterRLabel.setOpaque(false);
				oppTeamBatterRLabel.setForeground(Color.white);
				oppTeamBatterRLabel.setFont(pfont);
				oppTeamBatterRLabel.setBounds(920, 205, 100, 50);
				oppTeamBatterRLabel.setVisible(true);

				myTeamInning.setText(playball.user_score_toString());
				myTeamInning.setOpaque(false);
				myTeamInning.setForeground(Color.white);
				myTeamInning.setFont(pfont);
				myTeamInning.setBounds(377, 140, 1043, 50);
				myTeamInning.setVisible(false);

				oppTeamInning.setText("0");
				oppTeamInning.setOpaque(false);
				oppTeamInning.setForeground(Color.white);
				oppTeamInning.setFont(pfont);
				oppTeamInning.setBounds(377, 95, 1043, 50);
				oppTeamInning.setVisible(true);

				userTotalLabel.setText("0");
				userTotalLabel.setOpaque(false);
				userTotalLabel.setForeground(Color.white);
				userTotalLabel.setFont(pfont);
				userTotalLabel.setBounds(890, 140, 50, 50);
				userTotalLabel.setVisible(true);

				comTotalLabel.setText("0");
				comTotalLabel.setOpaque(false);
				comTotalLabel.setForeground(Color.white);
				comTotalLabel.setFont(pfont);
				comTotalLabel.setBounds(890, 95, 50, 50);
				comTotalLabel.setVisible(true);

				commentLabel.setText("����� ���ʴ� �����Դϴ�. ����忡 ���� �غ� �Ϸ�!");
				commentLabel.setOpaque(true);
				commentLabel.setForeground(Color.white);
				commentLabel.setBackground(Color.DARK_GRAY);
				Font cfont = new Font("���� ���", Font.PLAIN, 16);
				commentLabel.setFont(cfont);
				commentLabel.setBounds(546, 587, 470, 80);
				commentLabel.setVisible(true);
				commentLabel.setVerticalAlignment(SwingConstants.CENTER);
				commentLabel.setHorizontalAlignment(SwingConstants.CENTER);

				nowInningLabel.setText("1");
				nowInningLabel.setOpaque(false);
				nowInningLabel.setForeground(Color.white);
				Font ffont = new Font("Calibri", Font.BOLD, 24);
				nowInningLabel.setFont(ffont);
				nowInningLabel.setBounds(273, 310, 240, 47);
				nowInningLabel.setVisible(true);
				nowInningLabel.setVerticalAlignment(SwingConstants.CENTER);
				nowInningLabel.setHorizontalAlignment(SwingConstants.CENTER);

				nowPositionLabel.setText("fielding");
				nowPositionLabel.setOpaque(false);
				nowPositionLabel.setForeground(Color.white);
				nowPositionLabel.setFont(ffont);
				nowPositionLabel.setBounds(273, 410, 240, 47);
				nowPositionLabel.setVisible(true);
				nowPositionLabel.setVerticalAlignment(SwingConstants.CENTER);
				nowPositionLabel.setHorizontalAlignment(SwingConstants.CENTER);

				myTeamLogo = new ImageIcon(
						Main.class.getResource("../images/" + teamList.get(myTeamIndex).getLogoImage())).getImage();

				pitchButton.setVisible(true);
				ibbButton.setVisible(true);

				myTeamPitcherLabel.setVisible(true);
				myTeamPitcherRLabel.setVisible(true);
				oppTeamBatterLabel.setText(com.batter_name(playball.com_batter));
				oppTeamBatterLabel.setVisible(true);
				oppTeamBatterRLabel.setText(com.batter_AVG(playball.com_batter));
				oppTeamBatterRLabel.setVisible(true);

			}
		});
		add(gameStartButton);
		add(playerListLabel);
		add(myTeamNameLabel);
		add(oppTeamNameLabel);
		add(myTeamPitcherLabel);
		add(oppTeamPitcherLabel);
		add(myTeamPitcherRLabel);
		add(oppTeamPitcherRLabel);
		add(myTeamBatterLabel);
		add(oppTeamBatterLabel);
		add(myTeamBatterRLabel);
		add(oppTeamBatterRLabel);
		add(myTeamInning);
		add(oppTeamInning);
		add(commentLabel);
		add(nowInningLabel);
		add(nowPositionLabel);
		add(userTotalLabel);
		add(comTotalLabel);

		startButton.setBounds(400, 400, 236, 65);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				Music ButtonMusic = new Music("switch-1.mp3",false);
				ButtonMusic.start();
				introMusic.close();
				
				// ���� ���� �̺�Ʈ
				startButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				selectButton.setVisible(true);
				viewRankButton.setVisible(true);
				tutorialButton.setVisible(false);

				background = new ImageIcon(Main.class.getResource("../images/��������.png")).getImage();
				isMainScreen = true;
				isStartScreen = false;

				kbo = new KBO();
				
			}
		});
		add(startButton);

		goBackButton.setBounds(825, 600, 236, 65);
		goBackButton.setBorderPainted(false);
		goBackButton.setContentAreaFilled(false);
		goBackButton.setFocusPainted(false);
		goBackButton.setVisible(false);
		goBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goBackButton.setIcon(goBackButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				goBackButton.setIcon(goBackButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("switch-1.mp3",false);
				ButtonMusic.start();

				if (isStartScreen) {
					// ���ӽ���ȭ������ ���ư���
					goBackButton.setVisible(false);
					tutorialButton.setVisible(true);
					startButton.setVisible(true);
					background = new ImageIcon(Main.class.getResource("../images/���ӽ��۹��.png")).getImage();
				} else {
					// ������ȭ������ ���ư���
					goBackButton.setVisible(false);
					leftButton.setVisible(true);
					rightButton.setVisible(true);
					selectButton.setVisible(true);
					viewRankButton.setVisible(true);

					if (isMyTeam)
						background = new ImageIcon(Main.class.getResource("../images/��������.png")).getImage();
					else
						background = new ImageIcon(Main.class.getResource("../images/�����������.png")).getImage();

					isMainScreen = true;
				}

			}
		});
		add(goBackButton);

		tutorialButton.setBounds(400, 470, 236, 65);
		tutorialButton.setBorderPainted(false);
		tutorialButton.setContentAreaFilled(false);
		tutorialButton.setFocusPainted(false);
		tutorialButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tutorialButton.setIcon(tutorialButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tutorialButton.setIcon(tutorialButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonMusic = new Music("switch-1.mp3",false);
				ButtonMusic.start();
				// Ʃ�丮�� â ����
				startButton.setVisible(false);
				tutorialButton.setVisible(false);
				goBackButton.setVisible(true);

				background = new ImageIcon(Main.class.getResource("../images/Ʃ�丮��ȭ��.png")).getImage();
			}
		});
		add(tutorialButton);

		gameEndButton.setBounds(499, 368, 236, 65);
		gameEndButton.setBorderPainted(false);
		gameEndButton.setContentAreaFilled(false);
		gameEndButton.setFocusPainted(false);
		gameEndButton.setVisible(false);
		gameEndButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gameEndButton.setIcon(gameEndButtonEnterdImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				gameEndButton.setIcon(gameEndButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(playball.end);
			}
		});
		add(gameEndButton);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 415, 260, null);
			g.drawImage(nameImage, 405, 195, null);
		}
		if (isGameScreen) {
			g.drawImage(myTeamLogo, 60, 80, null);
			if (playball.first_base) {
				g.drawImage(runnerIconImage, 926, 447, null);
			}
			if (playball.second_base) {
				g.drawImage(runnerIconImage, 758, 345, null);
			}
			if (playball.third_base) {
				g.drawImage(runnerIconImage, 592, 447, null);
			}
			if (playball.out_count == 1) {
				g.drawImage(outIconImage, 65, 210, null);
			}
			if (playball.out_count == 2) {
				g.drawImage(outIconImage, 65, 210, null);
				g.drawImage(outIconImage, 100, 210, null);
			}
			if (playball.out_count == 3) {
				g.drawImage(outIconImage, 135, 210, null);
			}
			if (playball.top_bottom == 1) {

				g.drawImage(topBottomIconImage, 228, 136, null);
			}
			if (playball.top_bottom == 0) {
				g.drawImage(topBottomIconImage, 228, 98, null);
			}
			if (playball.end != 1) {
				g.drawImage(commentPopUp, 533, 585, null);
			}
			if (playball.end == 1) {

				if (Win() == 1)
					g.drawImage(youWinIconImage, 0, 0, null);
				else if (Win() == 2)
					g.drawImage(DrawIconImage, 0, 0, null);
				else
					g.drawImage(youLoseIconImage, 0, 0, null);

				hitButton.setVisible(false);
				pitchButton.setVisible(false);
				ibbButton.setVisible(false);
				buntButton.setVisible(false);
				substitutionButton.setVisible(false);

				nowPositionLabel.setVisible(false);
				nowInningLabel.setVisible(false);
				commentLabel.setVisible(false);
				playerListLabel.setVisible(false);
				myTeamPitcherLabel.setVisible(false);
				oppTeamPitcherLabel.setVisible(false);
				myTeamPitcherRLabel.setVisible(false);
				oppTeamPitcherRLabel.setVisible(false);
				myTeamBatterLabel.setVisible(false);
				oppTeamBatterLabel.setVisible(false);
				myTeamBatterRLabel.setVisible(false);
				oppTeamBatterRLabel.setVisible(false);

				gameEndButton.setVisible(true);

			}
		}
		paintComponents(g);
		this.repaint();
	}

	public void selectTeam(int nowSelected) {
		nameImage = new ImageIcon(Main.class.getResource("../images/" + teamList.get(nowSelected).getNameImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + teamList.get(nowSelected).getStartImage()))
				.getImage();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = teamList.size() - 1;
		else
			nowSelected--;
		selectTeam(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == teamList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTeam(nowSelected);
	}

	public void changeTopBottom() {
		if (playball.top_bottom == 0) {
			pitchButton.setVisible(true);
			ibbButton.setVisible(true);
			buntButton.setVisible(false);
			hitButton.setVisible(false);

			myTeamPitcherLabel.setVisible(true);
			oppTeamPitcherLabel.setVisible(false);

			myTeamPitcherRLabel.setVisible(true);
			oppTeamPitcherRLabel.setVisible(false);

			oppTeamBatterLabel.setText(com.batter_name(playball.com_batter));
			myTeamBatterLabel.setVisible(false);
			oppTeamBatterLabel.setVisible(true);

			oppTeamBatterRLabel.setText(com.batter_AVG(playball.com_batter));
			myTeamBatterRLabel.setVisible(false);
			oppTeamBatterRLabel.setVisible(true);

			nowPositionLabel.setText("fielding");

			isSubPossible = true;
			
		} else {
			hitButton.setVisible(true);
			ibbButton.setVisible(false);
			buntButton.setVisible(true);
			pitchButton.setVisible(false);

			myTeamPitcherLabel.setVisible(false);
			oppTeamPitcherLabel.setVisible(true);

			myTeamPitcherRLabel.setVisible(false);
			oppTeamPitcherRLabel.setVisible(true);

			myTeamBatterLabel.setText(user.batter_name(playball.user_batter));
			myTeamBatterLabel.setVisible(true);
			oppTeamBatterLabel.setVisible(false);

			myTeamBatterRLabel.setText(user.batter_AVG(playball.user_batter));
			myTeamBatterRLabel.setVisible(true);
			oppTeamBatterRLabel.setVisible(false);

			nowPositionLabel.setText("batting");

			isSubPossible = false;
		}
	}

	public int Win() {

		int Winner = 0;

		if (playball.user_score > playball.com_score)
			Winner = 1;
		else if (playball.user_score == playball.com_score)
			Winner = 2;

		return Winner;
	}
}
