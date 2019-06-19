package dynamic_beat_12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dynamic_beat_6.Main;

public class DynamicBeat extends JFrame{

	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();//왜 여기서는 getImage() 사용하나? class가 무엇인가?

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); //왜 getImage를 사용하지 못할까?
	
	private ImageIcon exitButtonBasicImage= new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon mainExitButtonBasicImage= new ImageIcon(Main.class.getResource("../images/mainExitButtonBasic.png"));
	private ImageIcon mainExitButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/mainExitButtonEntered.png"));
	private ImageIcon startButtonBasicImage= new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon leftButtonBasicImage= new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasicImage= new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));	
	private ImageIcon easyButtonBasicImage= new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon hardButtonBasicImage= new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));	
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	
	public static Game game;
	
	private JButton exitButton = new JButton(exitButtonBasicImage);	
	private JButton startButton = new JButton(startButtonBasicImage);		
	private JButton mainExitButton = new JButton(mainExitButtonBasicImage);	
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);	
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	public ArrayList<Track> trackList= new ArrayList<Track>();
	private int selectedNum = 0;
	
	private Music selectedMusic;
	private Music introMusic = new Music("adventuresHimitsu.mp3", true);
	
	private Image selectedImage;
	
	private Music gameMusic;	
	

	
	//trackList.add(new Track("okgo_highlight", "okgo", "okgoStartImage", "okgoGameImage")); 여기는 declare만 하는 곳, 그러므로 불가하다.
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	int mouseX, mouseY;
	
	public DynamicBeat() {
		//가장 위쪽으로 트랙리스트를 올린다: 프로그램이 실행 된 후, 위쪽 코드부터 로딩이 이루어지는데, 아직 트랙리스트 로딩이 이루어지지 않은 상황에서, 버튼이 눌리는 것을 방지
		trackList.add(new Track("okgo_highlight.mp3", "okgo.mp3", "okgoStartImage.jpg", "okgoGameImage.jpg", "OK GO - BEENZINO & E SENS"));
		trackList.add(new Track("beautifulLife_highlight.mp3", "beautifulLife.mp3", "beautifulLifeStartImage.jpg", "beautifulLifeGameImage.jpg", "Beautiful - Crush"));
		trackList.add(new Track("allofmylife_highlight.mp3", "allofmylife.mp3", "allofmylifeStartImage.jpg", "allofmylifeGameImage.jpg", "All of My Life - Park Won"));	
		
		setUndecorated(true); // 기본 매뉴바 삭제
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0)); // 기본 백그라운드 색을 흰색으로 변환
		setLayout(null);//이제 기본 디펄트 레이아웃을 지움으로서, 매뉴바를 원하는 위치에 그대로 넣을 수 있다

		addKeyListener(new KeyListener());
		

		introMusic.start(); // start method of the Thread class calls the run()		
		
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);	
		backButton.setVisible(false);
		
		exitButton.setContentAreaFilled(false); //remove button's default structure
		exitButton.setBorderPainted(false); //remove button's default structure
		exitButton.setFocusPainted(false); //remove button's default structure
		exitButton.setBounds(1250, 0, 30, 30);
		
		//MouseAdapter뒤에 ()까먹었었음
		//MouseEvent e 전체 까먹었었음
		//exitButton과 관련된 코드 전체가 메뉴바보다 전에 와야지, 메뉴바 위에 올라간 것으로 보인다. 왜그러지? 오더가 특이하다.
		exitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				exitButton.setIcon(exitButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); 이렇게하면 왜 안될까? 다시 add를 해야하는건가?
				//내 생각에, variable은 그저 박스일 뿐이고, 저렇게 버튼을 만들면, 아예 새로운 것을 만든는 것과 같다. 매번 새로 만들어질 때 마다, setBound를 할 수는 없지 않나?
				//mouseClickedSound가 만들어지고 start되서 사용되어진 것을 참조하면 될 것 같다
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); 이렇게하면 왜 안될까?				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedSound = new Music("mouseClickedSound.mp3", false);
				mouseClickedSound.start();
				
				try {
					Thread.sleep(300);//try catch가 요구된다. required in syntax. Thread class를 살펴봐야하겠지?
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(exitButton);

		leftButton.setContentAreaFilled(false);
		leftButton.setBorderPainted(false);
		leftButton.setFocusPainted(false);
		leftButton.setBounds(270, 330, 60, 60);
		
		leftButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				leftButton.setIcon(leftButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); 이렇게하면 왜 안될까? 다시 add를 해야하는건가?
				//내 생각에, variable은 그저 박스일 뿐이고, 저렇게 버튼을 만들면, 아예 새로운 것을 만든는 것과 같다. 매번 새로 만들어질 때 마다, setBound를 할 수는 없지 않나?
				//mouseClickedSound가 만들어지고 start되서 사용되어진 것을 참조하면 될 것 같다
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); 이렇게하면 왜 안될까?				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedSound = new Music("mouseClickedSound.mp3", false);
				mouseClickedSound.start();
				selectLeft();
				
			}
		});
		
		add(leftButton);		
		
		rightButton.setContentAreaFilled(false);
		rightButton.setBorderPainted(false);
		rightButton.setFocusPainted(false);
		rightButton.setBounds(950, 330, 60, 60);
		
		rightButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				rightButton.setIcon(rightButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); 이렇게하면 왜 안될까? 다시 add를 해야하는건가?
				//내 생각에, variable은 그저 박스일 뿐이고, 저렇게 버튼을 만들면, 아예 새로운 것을 만든는 것과 같다. 매번 새로 만들어질 때 마다, setBound를 할 수는 없지 않나?
				//mouseClickedSound가 만들어지고 start되서 사용되어진 것을 참조하면 될 것 같다
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); 이렇게하면 왜 안될까?				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedSound = new Music("mouseClickedSound.mp3", false);
				mouseClickedSound.start();
				selectRight();
				
			}
		});
		
		add(rightButton);		
		
		easyButton.setContentAreaFilled(false);
		easyButton.setBorderPainted(false);
		easyButton.setFocusPainted(false);//뭔지 정확히 모름
		easyButton.setBounds(720, 590, 110, 110);
		
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedMusic = new Music("mouseClickedSound.mp3", false);
				mouseClickedMusic.start();
				gameStart("Easy");
			}			
		});
		
		add(easyButton);
		
		hardButton.setContentAreaFilled(false);
		hardButton.setBorderPainted(false);
		hardButton.setFocusPainted(false);//뭔지 정확히 모름
		hardButton.setBounds(830, 590, 110, 110);
		
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedMusic = new Music("mouseClickedSound.mp3", false);
				mouseClickedMusic.start();
				gameStart("Hard");

			}			
		});		
		
		add(hardButton);
		
		//backButton은 Game에서 쓰이지만 DynamicBeat에서 code된다. 왜 그럴까?***************
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setBounds(20, 50, 120, 120);
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				backToMain();
			}			
		});
		
		add(backButton);
		
		startButton.setContentAreaFilled(false); //remove button's default structure
		startButton.setBorderPainted(false); //remove button's default structure
		startButton.setFocusPainted(false); //remove button's default structure
		startButton.setBounds(850, 400, 420, 130);
		
		//MouseAdapter뒤에 ()까먹었었음
		//MouseEvent e 전체 까먹었었음
		//exitButton과 관련된 코드 전체가 메뉴바보다 전에 와야지, 메뉴바 위에 올라간 것으로 보인다. 왜그러지? 오더가 특이하다.
		startButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				startButton.setIcon(startButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); 이렇게하면 왜 안될까? 다시 add를 해야하는건가?
				//내 생각에, variable은 그저 박스일 뿐이고, 저렇게 버튼을 만들면, 아예 새로운 것을 만든는 것과 같다. 매번 새로 만들어질 때 마다, setBound를 할 수는 없지 않나?
				//mouseClickedSound가 만들어지고 start되서 사용되어진 것을 참조하면 될 것 같다
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); 이렇게하면 왜 안될까?				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedSound = new Music("mouseClickedSound.mp3", false);
				mouseClickedSound.start();
				introToMain();
			}
		});
		
		add(startButton);		
		
		mainExitButton.setContentAreaFilled(false); //remove button's default structure
		mainExitButton.setBorderPainted(false); //remove button's default structure
		mainExitButton.setFocusPainted(false); //remove button's default structure
		mainExitButton.setBounds(850, 530, 420, 130);
		
		//MouseAdapter뒤에 ()까먹었었음
		//MouseEvent e 전체 까먹었었음
		//exitButton과 관련된 코드 전체가 메뉴바보다 전에 와야지, 메뉴바 위에 올라간 것으로 보인다. 왜그러지? 오더가 특이하다.
		mainExitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				mainExitButton.setIcon(mainExitButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); 이렇게하면 왜 안될까? 다시 add를 해야하는건가?
				//내 생각에, variable은 그저 박스일 뿐이고, 저렇게 버튼을 만들면, 아예 새로운 것을 만든는 것과 같다. 매번 새로 만들어질 때 마다, setBound를 할 수는 없지 않나?
				//mouseClickedSound가 만들어지고 start되서 사용되어진 것을 참조하면 될 것 같다
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainExitButton.setIcon(mainExitButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); 이렇게하면 왜 안될까?				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedSound = new Music("mouseClickedSound.mp3", false);
				mouseClickedSound.start();
				
				try {
					Thread.sleep(300);//try catch가 요구된다. required in syntax. Thread class를 살펴봐야하겠지?
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(mainExitButton);		
		
		menuBar.setBounds(0, 0, 1280, 30); // menuBar 위치와 높이 넓이 설정
		add(menuBar);
		
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();//fixed
				mouseY = e.getY();//fixed RELATIVE mouse position value TO THE SOURCE - MENUBAR가 세상의 전부라고 생각하면 됌
			}
		});
		
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen(); //여기는 e가 필요하나, pressed에서는 e가 필요가 없다 왜?(내가 틀림 필요함.)
										  //위의 e가 없어도 작동은하나, 이상하게 작동한다. 왜그럴까?
				int y = e.getYOnScreen(); //x,y 값은 변화한다  absolute mouse position value on screen

				setLocation(x - mouseX, y - mouseY);// 이것이 어떻게 전체 화면을 움직이는가? mouseX값이 0일 때 이것이 어떻게 변하는지를 생각해보면 쉽다.
													// 설명해보라
				// setLocation 메소드는 현재 Container의 위치를 setup하는 메소드이다. 
				// munuBar에 어떤 한 점을 클릭했다고 가정하자. 너는 menuBar 왼쪽 끝 위, (0,0),를 기준으로 계산된 mouseX, Y의 값과 Windows desktop 화면 왼쪽 끝, (0,0), 을 기준으로  계산된 x, y값을 갖고 있을 것이다.
				// 즉 x, y값은 말 그대로 프로그램과는 상관없이 컴퓨터 화면 즉 데스크탑 화면 pixel(현재 내 컴퓨터 resolution은 : 1920, 1080)을 기준으로 계산된 마우스 위치의 절대값이다.
				// 우리는 이 절대값 x, y를 기준으로 프로그램 Container: JFrame의 위치를 setting 해주어야 한다.
				// mouseX, Y값은 프로그램 menuBar(0,0) 부터 마우스가 클릭 된 위치까지의 거리로 보면 된다. 이 거리를 절대값 x, y에서 빼주면, 프로그램 location 의 위치가 계산되는 것이다. 
													
				

				//x 와 y 값은 내 컴퓨터 스크린 레솔루션을 기준으로 게임 화면 왼쪽 끝의 위치값이고
				//mouse x,y값은 menubar를 기준으로 메뉴바 위에서의 내가 클릭한 마우스 위치값 (즉 menubar의 높낮이 범위 밖을 벗어날 수 없다)	
						
				
			}
		});

		
	}
	
	private void selectMusic(int selectedNum) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		
		selectedMusic = new Music(trackList.get(selectedNum).getHighlightMusic(), true);
		selectedImage = new ImageIcon(Main.class.getResource("../images/"  + trackList.get(selectedNum).getSelectionImage()) ).getImage();
		
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(selectedNum == 0)
			selectedNum = trackList.size() - 1;
		else
			selectedNum--;
		
		selectMusic(selectedNum);	
	}
	
	public void selectRight() {
		if(selectedNum >= trackList.size() - 1)
			selectedNum = 0;
		else
			selectedNum++;	
		
		selectMusic(selectedNum);
	}
	
	public void backToMain() {
		if(gameMusic != null)
			gameMusic.close();
		
		isMainScreen = true;
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		selectedMusic = new Music(trackList.get(selectedNum).getHighlightMusic(), true);
		selectedMusic.start();
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		rightButton.setVisible(true);
		backButton.setVisible(false);
		isGameScreen = false;
		game.close();
		
	}
	
	public void introToMain() {
		introMusic.close();		
		selectMusic(0);
		startButton.setVisible(false);// 5번 강좌, 어떻게 스타트 버튼을 구현하나라는 고민이 단 3줄로 해결됌
		mainExitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		leftButton.setVisible(true);
		rightButton.setVisible(true); //left and right button과 paintComponent 그리고 Image(ex: selectedImage) 와 drawImage()의 차이점을 살펴보라 (역동적인 것은 drawImage 사용)
	}
	
	public void gameStart(String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);
		isMainScreen = false;		
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selectedNum).getGameImage())).getImage();
		game = new Game(trackList.get(selectedNum).getTitleAndMusician(), trackList.get(selectedNum).getGameMusic());
		isGameScreen = true;
		setFocusable(true);// 가장 아래로 내리므로서, 키보드 이벤트가 에러 없이 작동하도록 한다고 한다. 
	}
	
	//paint 메소드를 트리거 하는 방법은 두가지가 있다: 시스템/앱
	//paint를 자동으로 run 된다. directly calling paint는 옳지않다.
	//시스템은 우리가 손 댈 것이 없다, 그러나 app-triggered painting은 별도의 방법이 필요한데, 그것이 바로 repaint이다.
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //an empty off-screen image is created
		screenGraphic = screenImage.getGraphics(); //off-screen image's graphic is saved to the variable and the variable is passed to the back buffer
		screenDraw((Graphics2D)screenGraphic); //CASTING은 FONT의 EDGE를 더 부드럽게 만들기위해 사용 되었다.
		g.drawImage(screenImage, 0, 0, null);//The image which is drawn in the off-screen is passed to this screen
				
	}
	
	//The method draws the introBackground to the off-screen image(back buffer)
	//Graphics2D로 바꾸어진 이유는, 폰트를 더 부드럽게 보이기하기 위해서였다 참조: https://docs.oracle.com/javase/tutorial/2d/advanced/quality.html
	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); //what is null arg?
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 135, null);
		}
		if(isGameScreen) {
			game.drawScreen(g, selectedNum);
		}
		paintComponents(g);//menuBar과 같은것은 역동적이지 않고, 고정되어 어디서든 사용되어지는 것이기 때문에, component로서 그려진다.
		this.repaint();//repaint는 또 다른 paint를 부르는 것이고, 이 repaint를 call 했던 paint는 원래대로 돌아가, 스크린에 완성된 이미지를 출력한다.
		
		//paintComponents를 빼면, 직관적으로 컴포넌트들이 draw되어있지 않는다. repaint를 빼면, 스크린이 전체적으로 완벽하게 드로우 되지 않는다.
	}
}
