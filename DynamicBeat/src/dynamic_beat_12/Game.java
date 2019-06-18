package dynamic_beat_12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dynamic_beat_6.Main;

public class Game extends Thread{
	
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image noteRouteBasicImageS = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteBasicImageD = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteBasicImageK = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteBasicImageL = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteWideImage = new ImageIcon(Main.class.getResource("../images/noteRouteWide.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	
	private String titleAndMusic;
	private Music gameMusic;
	
	private ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleAndMusic, String gameMusicTitle) {
		this.titleAndMusic = titleAndMusic;
		gameMusic = new Music(gameMusicTitle, false);
		gameMusic.start();
		dropNotes(titleAndMusic);// constructor에 있어야지 변화에 맞추어서 다른 노트를 생성할 수 있다.
	}
	
	public void drawScreen(Graphics2D g, int selectedNum) {
		//이 drawScreen함수를 어떻게 DynamicBeat Class에서 적용시키는지 몰랐다.
		g.drawImage(gameInfoImage, 0, 660, null); //g 까먹으면 안돼. 너 까먹었어
		
		g.drawImage(noteRouteBasicImageS, 302, 30, null);
		g.drawImage(noteRouteLineImage, 402, 30, null);
		g.drawImage(noteRouteBasicImageD, 406, 30, null);
		g.drawImage(noteRouteLineImage, 506, 30, null);
		g.drawImage(noteRouteWideImage, 510, 30, null);
		g.drawImage(noteRouteLineImage, 770, 30, null);
		g.drawImage(noteRouteBasicImageK, 774, 30, null);
		g.drawImage(noteRouteLineImage, 874, 30, null);
		g.drawImage(noteRouteBasicImageL, 878, 30, null);
		
		g.drawImage(judgementLineImage, 0, 580, null);
		

		for(int i = 0; i < noteList.size(); i++) {
			noteList.get(i).drawScreen(g);//g 안보내도 되나?
		}// Judgement line보다 밑에 있어야지 노트가 위에 출력이 된다. 
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Ariel", Font.BOLD, 30));
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString(titleAndMusic, 30, 705);
		g.setColor(Color.BLACK);			
		g.drawString("S", 342, 610);
		g.drawString("D", 446, 610);
		g.drawString("SPACE BAR", 552, 610);
		g.drawString("K", 814, 610);
		g.drawString("L", 918, 610);
	}
	
	public void pressedS() {
		noteRouteBasicImageS = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedS() {
		noteRouteBasicImageS = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();		
	}
	
	public void pressedD() {
		noteRouteBasicImageD = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedD() {
		noteRouteBasicImageD = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();		
	}
	
	public void pressedK() {
		noteRouteBasicImageK = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedK() {
		noteRouteBasicImageK = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	}
	
	public void pressedL() {
		noteRouteBasicImageL = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedL() {
		noteRouteBasicImageL = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();		
	}
	
	public void pressedSpace() {
		noteRouteWideImage = new ImageIcon(Main.class.getResource("../images/noteRouteWidePressed.png")).getImage();
	}
	
	public void releasedSpace() {
		noteRouteWideImage = new ImageIcon(Main.class.getResource("../images/noteRouteWide.png")).getImage();		
	}	
	
	public void dropNotes(String titleAndMusic) {
		Note note = new Note(302, "basic");
		note.drop();
		note.start();
		
		noteList.add(note);
	}
	
	@Override
	public void run() {
		
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
}
