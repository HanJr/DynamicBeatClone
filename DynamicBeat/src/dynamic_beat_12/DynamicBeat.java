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
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();//�� ���⼭�� getImage() ����ϳ�? class�� �����ΰ�?

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); //�� getImage�� ������� ���ұ�?
	
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
	

	
	//trackList.add(new Track("okgo_highlight", "okgo", "okgoStartImage", "okgoGameImage")); ����� declare�� �ϴ� ��, �׷��Ƿ� �Ұ��ϴ�.
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	int mouseX, mouseY;
	
	public DynamicBeat() {
		//���� �������� Ʈ������Ʈ�� �ø���: ���α׷��� ���� �� ��, ���� �ڵ���� �ε��� �̷�����µ�, ���� Ʈ������Ʈ �ε��� �̷������ ���� ��Ȳ����, ��ư�� ������ ���� ����
		trackList.add(new Track("okgo_highlight.mp3", "okgo.mp3", "okgoStartImage.jpg", "okgoGameImage.jpg", "OK GO - BEENZINO & E SENS"));
		trackList.add(new Track("beautifulLife_highlight.mp3", "beautifulLife.mp3", "beautifulLifeStartImage.jpg", "beautifulLifeGameImage.jpg", "Beautiful - Crush"));
		trackList.add(new Track("allofmylife_highlight.mp3", "allofmylife.mp3", "allofmylifeStartImage.jpg", "allofmylifeGameImage.jpg", "All of My Life - Park Won"));	
		
		setUndecorated(true); // �⺻ �Ŵ��� ����
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0)); // �⺻ ��׶��� ���� ������� ��ȯ
		setLayout(null);//���� �⺻ ����Ʈ ���̾ƿ��� �������μ�, �Ŵ��ٸ� ���ϴ� ��ġ�� �״�� ���� �� �ִ�

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
		
		//MouseAdapter�ڿ� ()��Ծ�����
		//MouseEvent e ��ü ��Ծ�����
		//exitButton�� ���õ� �ڵ� ��ü�� �޴��ٺ��� ���� �;���, �޴��� ���� �ö� ������ ���δ�. �ֱ׷���? ������ Ư���ϴ�.
		exitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				exitButton.setIcon(exitButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); �̷����ϸ� �� �ȵɱ�? �ٽ� add�� �ؾ��ϴ°ǰ�?
				//�� ������, variable�� ���� �ڽ��� ���̰�, ������ ��ư�� �����, �ƿ� ���ο� ���� ����� �Ͱ� ����. �Ź� ���� ������� �� ����, setBound�� �� ���� ���� �ʳ�?
				//mouseClickedSound�� ��������� start�Ǽ� ���Ǿ��� ���� �����ϸ� �� �� ����
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); �̷����ϸ� �� �ȵɱ�?				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedSound = new Music("mouseClickedSound.mp3", false);
				mouseClickedSound.start();
				
				try {
					Thread.sleep(300);//try catch�� �䱸�ȴ�. required in syntax. Thread class�� ��������ϰ���?
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
				//exitButton = new JButton(exitButtonEnteredImage); �̷����ϸ� �� �ȵɱ�? �ٽ� add�� �ؾ��ϴ°ǰ�?
				//�� ������, variable�� ���� �ڽ��� ���̰�, ������ ��ư�� �����, �ƿ� ���ο� ���� ����� �Ͱ� ����. �Ź� ���� ������� �� ����, setBound�� �� ���� ���� �ʳ�?
				//mouseClickedSound�� ��������� start�Ǽ� ���Ǿ��� ���� �����ϸ� �� �� ����
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); �̷����ϸ� �� �ȵɱ�?				
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
				//exitButton = new JButton(exitButtonEnteredImage); �̷����ϸ� �� �ȵɱ�? �ٽ� add�� �ؾ��ϴ°ǰ�?
				//�� ������, variable�� ���� �ڽ��� ���̰�, ������ ��ư�� �����, �ƿ� ���ο� ���� ����� �Ͱ� ����. �Ź� ���� ������� �� ����, setBound�� �� ���� ���� �ʳ�?
				//mouseClickedSound�� ��������� start�Ǽ� ���Ǿ��� ���� �����ϸ� �� �� ����
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); �̷����ϸ� �� �ȵɱ�?				
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
		easyButton.setFocusPainted(false);//���� ��Ȯ�� ��
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
		hardButton.setFocusPainted(false);//���� ��Ȯ�� ��
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
		
		//backButton�� Game���� �������� DynamicBeat���� code�ȴ�. �� �׷���?***************
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
		
		//MouseAdapter�ڿ� ()��Ծ�����
		//MouseEvent e ��ü ��Ծ�����
		//exitButton�� ���õ� �ڵ� ��ü�� �޴��ٺ��� ���� �;���, �޴��� ���� �ö� ������ ���δ�. �ֱ׷���? ������ Ư���ϴ�.
		startButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				startButton.setIcon(startButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); �̷����ϸ� �� �ȵɱ�? �ٽ� add�� �ؾ��ϴ°ǰ�?
				//�� ������, variable�� ���� �ڽ��� ���̰�, ������ ��ư�� �����, �ƿ� ���ο� ���� ����� �Ͱ� ����. �Ź� ���� ������� �� ����, setBound�� �� ���� ���� �ʳ�?
				//mouseClickedSound�� ��������� start�Ǽ� ���Ǿ��� ���� �����ϸ� �� �� ����
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); �̷����ϸ� �� �ȵɱ�?				
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
		
		//MouseAdapter�ڿ� ()��Ծ�����
		//MouseEvent e ��ü ��Ծ�����
		//exitButton�� ���õ� �ڵ� ��ü�� �޴��ٺ��� ���� �;���, �޴��� ���� �ö� ������ ���δ�. �ֱ׷���? ������ Ư���ϴ�.
		mainExitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				mainExitButton.setIcon(mainExitButtonEnteredImage);
				//exitButton = new JButton(exitButtonEnteredImage); �̷����ϸ� �� �ȵɱ�? �ٽ� add�� �ؾ��ϴ°ǰ�?
				//�� ������, variable�� ���� �ڽ��� ���̰�, ������ ��ư�� �����, �ƿ� ���ο� ���� ����� �Ͱ� ����. �Ź� ���� ������� �� ����, setBound�� �� ���� ���� �ʳ�?
				//mouseClickedSound�� ��������� start�Ǽ� ���Ǿ��� ���� �����ϸ� �� �� ����
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainExitButton.setIcon(mainExitButtonBasicImage);
				//exitButton = new JButton(exitButtonBasicImage); �̷����ϸ� �� �ȵɱ�?				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music mouseClickedSound = new Music("mouseClickedSound.mp3", false);
				mouseClickedSound.start();
				
				try {
					Thread.sleep(300);//try catch�� �䱸�ȴ�. required in syntax. Thread class�� ��������ϰ���?
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(mainExitButton);		
		
		menuBar.setBounds(0, 0, 1280, 30); // menuBar ��ġ�� ���� ���� ����
		add(menuBar);
		
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();//fixed
				mouseY = e.getY();//fixed RELATIVE mouse position value TO THE SOURCE - MENUBAR�� ������ ���ζ�� �����ϸ� ��
			}
		});
		
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen(); //����� e�� �ʿ��ϳ�, pressed������ e�� �ʿ䰡 ���� ��?(���� Ʋ�� �ʿ���.)
										  //���� e�� ��� �۵����ϳ�, �̻��ϰ� �۵��Ѵ�. �ֱ׷���?
				int y = e.getYOnScreen(); //x,y ���� ��ȭ�Ѵ�  absolute mouse position value on screen

				setLocation(x - mouseX, y - mouseY);// �̰��� ��� ��ü ȭ���� �����̴°�? mouseX���� 0�� �� �̰��� ��� ���ϴ����� �����غ��� ����.
													// �����غ���
				// setLocation �޼ҵ�� ���� Container�� ��ġ�� setup�ϴ� �޼ҵ��̴�. 
				// munuBar�� � �� ���� Ŭ���ߴٰ� ��������. �ʴ� menuBar ���� �� ��, (0,0),�� �������� ���� mouseX, Y�� ���� Windows desktop ȭ�� ���� ��, (0,0), �� ��������  ���� x, y���� ���� ���� ���̴�.
				// �� x, y���� �� �״�� ���α׷����� ������� ��ǻ�� ȭ�� �� ����ũž ȭ�� pixel(���� �� ��ǻ�� resolution�� : 1920, 1080)�� �������� ���� ���콺 ��ġ�� ���밪�̴�.
				// �츮�� �� ���밪 x, y�� �������� ���α׷� Container: JFrame�� ��ġ�� setting ���־�� �Ѵ�.
				// mouseX, Y���� ���α׷� menuBar(0,0) ���� ���콺�� Ŭ�� �� ��ġ������ �Ÿ��� ���� �ȴ�. �� �Ÿ��� ���밪 x, y���� ���ָ�, ���α׷� location �� ��ġ�� ���Ǵ� ���̴�. 
													
				

				//x �� y ���� �� ��ǻ�� ��ũ�� ���ַ���� �������� ���� ȭ�� ���� ���� ��ġ���̰�
				//mouse x,y���� menubar�� �������� �޴��� �������� ���� Ŭ���� ���콺 ��ġ�� (�� menubar�� ������ ���� ���� ��� �� ����)	
						
				
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
		startButton.setVisible(false);// 5�� ����, ��� ��ŸƮ ��ư�� �����ϳ���� ����� �� 3�ٷ� �ذ��
		mainExitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		leftButton.setVisible(true);
		rightButton.setVisible(true); //left and right button�� paintComponent �׸��� Image(ex: selectedImage) �� drawImage()�� �������� ���캸�� (�������� ���� drawImage ���)
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
		setFocusable(true);// ���� �Ʒ��� �����Ƿμ�, Ű���� �̺�Ʈ�� ���� ���� �۵��ϵ��� �Ѵٰ� �Ѵ�. 
	}
	
	//paint �޼ҵ带 Ʈ���� �ϴ� ����� �ΰ����� �ִ�: �ý���/��
	//paint�� �ڵ����� run �ȴ�. directly calling paint�� �����ʴ�.
	//�ý����� �츮�� �� �� ���� ����, �׷��� app-triggered painting�� ������ ����� �ʿ��ѵ�, �װ��� �ٷ� repaint�̴�.
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //an empty off-screen image is created
		screenGraphic = screenImage.getGraphics(); //off-screen image's graphic is saved to the variable and the variable is passed to the back buffer
		screenDraw((Graphics2D)screenGraphic); //CASTING�� FONT�� EDGE�� �� �ε巴�� ��������� ��� �Ǿ���.
		g.drawImage(screenImage, 0, 0, null);//The image which is drawn in the off-screen is passed to this screen
				
	}
	
	//The method draws the introBackground to the off-screen image(back buffer)
	//Graphics2D�� �ٲپ��� ������, ��Ʈ�� �� �ε巴�� ���̱��ϱ� ���ؼ����� ����: https://docs.oracle.com/javase/tutorial/2d/advanced/quality.html
	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); //what is null arg?
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 135, null);
		}
		if(isGameScreen) {
			game.drawScreen(g, selectedNum);
		}
		paintComponents(g);//menuBar�� �������� ���������� �ʰ�, �����Ǿ� ��𼭵� ���Ǿ����� ���̱� ������, component�μ� �׷�����.
		this.repaint();//repaint�� �� �ٸ� paint�� �θ��� ���̰�, �� repaint�� call �ߴ� paint�� ������� ���ư�, ��ũ���� �ϼ��� �̹����� ����Ѵ�.
		
		//paintComponents�� ����, ���������� ������Ʈ���� draw�Ǿ����� �ʴ´�. repaint�� ����, ��ũ���� ��ü������ �Ϻ��ϰ� ��ο� ���� �ʴ´�.
	}
}
