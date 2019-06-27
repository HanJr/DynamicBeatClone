package dynamic_beat_12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{
	
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image noteRouteBasicImageS = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteBasicImageD = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteBasicImageK = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteBasicImageL = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	private Image noteRouteWideImage = new ImageIcon(Main.class.getResource("../images/noteRouteWide.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgeImage = new ImageIcon(Main.class.getResource("../images/basicJudge.png")).getImage();
	private ImageIcon basicJudgeImage = new ImageIcon(Main.class.getResource("../images/basicJudge.png"));
	private ImageIcon earlyJudgeImage = new ImageIcon(Main.class.getResource("../images/earlyJudge.png"));
	private ImageIcon greatJudgeImage = new ImageIcon(Main.class.getResource("../images/greatJudge.png"));
	private ImageIcon perfectJudgeImage = new ImageIcon(Main.class.getResource("../images/perfectJudge.png"));
	private ImageIcon lateJudgeImage = new ImageIcon(Main.class.getResource("../images/lateJudge.png"));
	private ImageIcon missJudgeImage = new ImageIcon(Main.class.getResource("../images/missImage.png"));
	
	
	private String titleAndMusic;
	public static Music gameMusic;
	private String difficulty;
	
	private ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleAndMusic, String gameMusicTitle, String difficulty) {
		this.titleAndMusic = titleAndMusic;
		gameMusic = new Music(gameMusicTitle, false);
		this.difficulty = difficulty;
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
		
		g.drawImage(judgeImage, 590, 410, null);
		
		for(int i = 0; i < noteList.size(); i++) {
			if(noteList.get(i).getY() >= 620) {
				judgeImage = missJudgeImage.getImage();
			}
			if(!noteList.get(i).getIsJudged()) {
				noteList.get(i).drawScreen(g);//g 안보내도 되나?				
			}
			else{
				noteList.remove(i);
			}
				

		}// Judgement line보다 밑에 있어야지 노트가 위에 출력이 된다. 
	}
	
	public void pressedS() {
		judge("S");
		System.out.println("S: " + gameMusic.getTime());
		noteRouteBasicImageS = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedS() {
		noteRouteBasicImageS = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();		
	}
	
	public void pressedD() {
		judge("D");
		System.out.println("D: " + gameMusic.getTime());
		noteRouteBasicImageD = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedD() {
		noteRouteBasicImageD = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();		
	}
	
	public void pressedK() {
		judge("K");
		System.out.println("K: " + gameMusic.getTime());
		noteRouteBasicImageK = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedK() {
		noteRouteBasicImageK = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();
	}
	
	public void pressedL() {
		judge("L");
		System.out.println("L: " + gameMusic.getTime());
		noteRouteBasicImageL = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releasedL() {
		noteRouteBasicImageL = new ImageIcon(Main.class.getResource("../images/noteRouteBasic.png")).getImage();		
	}
	
	public void pressedSpace() {
		judge("SPACE");
		System.out.println("SP: " + gameMusic.getTime());
		noteRouteWideImage = new ImageIcon(Main.class.getResource("../images/noteRouteWidePressed.png")).getImage();
	}
	
	public void releasedSpace() {
		noteRouteWideImage = new ImageIcon(Main.class.getResource("../images/noteRouteWide.png")).getImage();		
	}	
	
	public void dropNotes() {
		
		int i = 0;
		Beat[] beats = null;
		int startTime;
		if(titleAndMusic.equals("OK GO - BEENZINO & E SENS") && difficulty.equals("Easy")) {
			startTime = 15000 - Main.REACH_TIME * 1000; //내 예상대로 이 공식에는 문제가 있음. 스타트 타임이 리치타임 * 1000 보다 커야된다는 것임. 
													   //그러나 조건이 충족되었을 떄 이 공식은 리치타임에 상관없이 언제나 동일한 음악의 첫 박자에 노트를 판정라인에 딜리버리 할 수 있도록 해준다.
			int gap = 1300;
			int halfGap = 600;
			beats = new Beat[] {
				new Beat(startTime - 3000, "S"),
				new Beat(startTime - 3000, "L"),
				
				new Beat(startTime, "S"),
				new Beat(startTime, "L"),
				
				new Beat(startTime + gap, "D"),
				new Beat(startTime + gap, "K"),
				
				new Beat(startTime + gap * 2, "S"),
				new Beat(startTime + gap * 2, "L"),
				
				new Beat(startTime + gap * 3, "S"),
				new Beat(startTime + gap * 3, "L"),
				
				
				new Beat(startTime + (gap * 3 + halfGap), "SPACE"),
				new Beat(startTime + (gap * 3 + halfGap + 325), "SPACE"),
				
				new Beat(startTime + (gap * 4), "SPACE"),
				new Beat(startTime + (gap * 4 + 280), "SPACE"),
				
				new Beat(startTime + (gap * 5 + 280), "SPACE"),
				
				new Beat(startTime + (gap * 6 + 280), "SPACE"),
				
				new Beat(startTime + (gap * 7 + 280), "SPACE"),
				new Beat(startTime + (gap * 7 + (280 * 2 )), "D"),		
				new Beat(startTime + (gap * 7 + (280 * 2 )), "K"),	
				new Beat(startTime + (gap * 7 + (280 * 3 )), "S"),		
				new Beat(startTime + (gap * 7 + (280 * 3 )), "L"),
				new Beat(startTime + (gap * 7 + (280 * 4 )), "D"),		
				new Beat(startTime + (gap * 7 + (280 * 4 )), "K"),

				new Beat(startTime + (gap * 8 + 280), "SPACE"),
				new Beat(startTime + (gap * 8 + (280 * 2 )), "D"),		
				new Beat(startTime + (gap * 8 + (280 * 2 )), "K"),	
				new Beat(startTime + (gap * 8 + (280 * 3 )), "S"),		
				new Beat(startTime + (gap * 8 + (280 * 3 )), "L"),
				new Beat(startTime + (gap * 8 + (280 * 4 )), "D"),		
				new Beat(startTime + (gap * 8 + (280 * 4 )), "K"),
				
				new Beat(startTime + (gap * 9 + 280), "SPACE"),
				new Beat(startTime + (gap * 9 + (280 * 2 )), "D"),		
				new Beat(startTime + (gap * 9 + (280 * 2 )), "K"),	
				new Beat(startTime + (gap * 9 + (280 * 3 )), "S"),		
				new Beat(startTime + (gap * 9 + (280 * 3 )), "L"),
				new Beat(startTime + (gap * 9 + (280 * 4 )), "D"),		
				new Beat(startTime + (gap * 9 + (280 * 4 )), "K"),
	
				new Beat(startTime + (gap * 10 + 280), "SPACE"),
				new Beat(startTime + (gap * 10 + (280 * 2 )), "D"),		
				new Beat(startTime + (gap * 10 + (280 * 2 )), "K"),	
				new Beat(startTime + (gap * 10 + (280 * 3 )), "S"),		
				new Beat(startTime + (gap * 10 + (280 * 3 )), "L"),
				new Beat(startTime + (gap * 10 + (280 * 4 )), "D"),		
				new Beat(startTime + (gap * 10 + (280 * 4 )), "K"),
				new Beat(startTime + (gap * 10 + (280 * 5 )), "SPACE"),
			};
		}
		if(titleAndMusic.equals("OK GO - BEENZINO & E SENS") && difficulty.equals("Hard")) {
			startTime = 15000 - Main.REACH_TIME * 1000; //내 예상대로 이 공식에는 문제가 있음. 스타트 타임이 리치타임 * 1000 보다 커야된다는 것임. 
													   //그러나 조건이 충족되었을 떄 이 공식은 리치타임에 상관없이 언제나 동일한 음악의 첫 박자에 노트를 판정라인에 딜리버리 할 수 있도록 해준다.
			int gap = 1300;
			beats = new Beat[] {
				new Beat(startTime + (gap * 10 + (280 * 5 )), "SPACE"),
			};
		}
		else if(titleAndMusic.equals("Beautiful - Crush") && difficulty.equals("Easy")) {
			startTime = 1000 - Main.REACH_TIME * 1000;
			beats = new Beat[] {
				new Beat(startTime, "S"),

			};
		}
		else if(titleAndMusic.equals("Beautiful - Crush") && difficulty.equals("Hard")) {
			startTime = 1000 - Main.REACH_TIME * 1000;
			beats = new Beat[] {
				new Beat(startTime, "S"),

			};
		}
		else if(titleAndMusic.equals("All of My Life - Park Won") && difficulty.equals("Easy")) {
			startTime = 1000 - Main.REACH_TIME * 1000;
			beats = new Beat[] {
				new Beat(startTime, "S"),

			};
		}
		else if(titleAndMusic.equals("All of My Life - Park Won") && difficulty.equals("Hard")) {
			startTime = 1000 - Main.REACH_TIME * 1000;
			beats = new Beat[] {
				new Beat(startTime, "S"),

			};
		}
		gameMusic.start();
		while(i < beats.length) {
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();			
				noteList.add(note);
				i++;
			}
		}
	}
	
	public void printJudgeImage(String judge) {
		if(judge.equals("early")) {
			judgeImage = earlyJudgeImage.getImage();
		}
		else if(judge.equals("great")) {
			judgeImage = greatJudgeImage.getImage();			
		}
		else if(judge.equals("perfect")) {
			judgeImage = perfectJudgeImage.getImage();
		}
		else if(judge.equals("late")) {
			judgeImage = lateJudgeImage.getImage();
		}
	}
	
	public void judge(String judgingKey) {
		for(int i = 0; i < noteList.size(); i++) {
			if(noteList.get(i).getNoteType().equals(judgingKey)) {
				printJudgeImage(noteList.get(i).judge());
			}
		}
	}
	
	@Override
	public void run() {
		dropNotes();// constructor에 있어야지 변화에 맞추어서 다른 노트를 생성할 수 있다.
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
}
