package dynamic_beat_12;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	private Image basicNoteImage = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
	private Image wideNoteImage = new ImageIcon(Main.class.getResource("../images/wideNote.png")).getImage();
	private boolean isJudged = false;
	
	/*  580 = 판정라인의   x 위치, 우리는 노트가 여기까지 1초에 정확하게 떨어지길 원한다. 
	 *  1000/Main.SLEEP_TIME은 1초간 얼마만큼의 멈춤이 발생하는가를 계산한다. 총 100번
	 *  Main.NOTE_SPEED 에 총 멈춤이 발생하는 횟수를 곱하면, 노트가 1초에 얼마만큼 움직이는지 계산이 가능하다 = 700
	 *  1초에 700을 움직이는 노트를 갖고있으니, 1초 뒤에 정확히 노트를 580에 있는 판정라인에 떨어뜨리기 위해선, 580 - 700을 함으로서 노트를 화면 바깥에서부터 떨어뜨리는 것이 필요하다.
	 */
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; 

	private String noteType;
	
	public Note(String noteType) {
		this.noteType = noteType;
		if(noteType.equals("S")) {
			x = 302;
		}
		else if(noteType.equals("D")) {
			x = 406;
		}
		else if(noteType.equals("SPACE")) {
			x = 510;
		}
		else if(noteType.equals("K")) {
			x = 774;
		}
		else if(noteType.equals("L")) {
			x = 878;
		}
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getIsJudged() {
		return isJudged;
	}
	
	public void setIsJudged(boolean isJudged) {
		this.isJudged = isJudged;
	}
	
	public String getNoteType() {
		return noteType;
	}
	
	public void drawScreen(Graphics2D g) {
		if(!noteType.equals("SPACE")) {
			g.drawImage(basicNoteImage, x, y, null);
		}
		else{
			g.drawImage(wideNoteImage, x, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
	}
	
	public String judge() {
		String judge = "";
		isJudged = true;
		
		if(y > 550 && y < 570) {
			judge = "early";
		}
		else if(y >= 570 && y < 580) {
			judge = "great";
		}
		else if(y >= 580 && y < 590) {
			judge = "perfect";
		}
		else if(y >= 590 && y < 600) {
			judge = "great";
		}		
		else if(y >= 600 && y <= 620) {
			judge = "late";
		}		
		else {
			isJudged = false;
		}
		
		System.out.println(judge);
		
		return judge;
	}
	
	@Override
	public void run() {
		try {
			while(!isJudged) {
				drop();
				if(y < 620) {
					Thread.sleep(Main.SLEEP_TIME);//이것이 없으면 노트가 떨어지는 것이 보이지도 않을정도록 빨리 떨어진다.
				}else {
					isJudged = true;
					close();
					System.out.println("miss");
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());//err이 뭐지?
		}
	}
	
	public void close() {
		this.interrupt();
	}
}
