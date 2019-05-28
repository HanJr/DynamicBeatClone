package dynamic_beat_7;

import java.awt.Color;
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
	
	private JButton exitButton = new JButton(exitButtonBasicImage);	
	private JButton startButton = new JButton(startButtonBasicImage);		
	private JButton mainExitButton = new JButton(mainExitButtonBasicImage);	
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	private ArrayList<Track> trackList= new ArrayList<Track>();
	private Music selectedMusic;
	private Image selectedImage;
	
	private int selectedNum = 0;
	
	//trackList.add(new Track("okgo_highlight", "okgo", "okgoStartImage", "okgoGameImage")); ����� declare�� �ϴ� ��, �׷��Ƿ� �Ұ��ϴ�.
	
	private boolean isMainScreen = false;
	
	int mouseX, mouseY;
	
	public DynamicBeat() {
		setUndecorated(true); // �⺻ �Ŵ��� ����
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0)); // �⺻ ��׶��� ���� ������� ��ȯ
		setLayout(null);//���� �⺻ ����Ʈ ���̾ƿ��� �������μ�, �Ŵ��ٸ� ���ϴ� ��ġ�� �״�� ���� �� �ִ�

		trackList.add(new Track("okgo_highlight.mp3", "okgo.mp3", "okgoStartImage.jpg", "okgoGameImage.jpg"));
		trackList.add(new Track("beautifulLife_highlight.mp3", "beautifulLife.mp3", "beautifulLifeStartImage.jpg", "beautifulLifeGameImage.jpg"));
		trackList.add(new Track("allofmylife_highlight.mp3", "allofmylife.mp3", "allofmylifeStartImage.jpg", "allofmylifeGameImage.jpg"));

		Music introMusic = new Music("adventuresHimitsu.mp3", true);
		introMusic.start(); // start method of the Thread class calls the run()		
		
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		
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
				introMusic.close();
				selectMusic(0);
				startButton.setVisible(false);// 5�� ����, ��� ��ŸƮ ��ư�� �����ϳ���� ����� �� 3�ٷ� �ذ��
				mainExitButton.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
				isMainScreen = true;
				leftButton.setVisible(true);
				rightButton.setVisible(true); //left and right button�� paintComponent �׸��� Image(ex: selectedImage) �� drawImage()�� �������� ���캸�� (�������� ���� drawImage ���)
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
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen(); //����� e�� �ʿ��ϳ�, pressed������ e�� �ʿ䰡 ���� ��?(���� Ʋ�� �ʿ���.)
										  //���� e�� ��� �۵����ϳ�, �̻��ϰ� �۵��Ѵ�. �ֱ׷���?
				int y = e.getYOnScreen(); //x,y ���� ��ȭ�Ѵ�

				setLocation(x - mouseX, y - mouseY);// �̰��� ��� ��ü ȭ���� �����̴°�? mouseX���� 0�� �� �̰��� ��� ���ϴ����� �����غ��� ����.
													// �����غ���

				//x �� y ���� �� ��ǻ�� ��ũ�� ���ַ���� �������� ���� ȭ�� ���� ���� ��ġ���̰�
				//mouse x,y���� menubar�� �������� �޴��� �������� ���� Ŭ���� ���콺 ��ġ�� (�� menubar�� ������ ���� ���� ��� �� ����)	
						
				
			}
		});

		
	}
	
	public void selectMusic(int selectedNum) {
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
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //an empty off-screen image is created
		screenGraphic = screenImage.getGraphics(); //off-screen image's graphic is saved to the variable
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);//The image which is drawn in the off-screen is passed to this screen
				
	}
	
	//The method draws the introBackground to the off-screen image to 
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); //what is null arg?
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 135, null);
		}
		paintComponents(g);//menuBar�� �������� ���������� �ʰ�, �����Ǿ� ��𼭵� ���Ǿ����� ���̱� ������, component�μ� �׷�����.
		this.repaint(); //paint() cannot be directly called
	}
}
