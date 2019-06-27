package dynamic_beat_12;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	private Image basicNoteImage = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
	private Image wideNoteImage = new ImageIcon(Main.class.getResource("../images/wideNote.png")).getImage();
	private boolean isJudged = false;
	
	/*  580 = ����������   x ��ġ, �츮�� ��Ʈ�� ������� 1�ʿ� ��Ȯ�ϰ� �������� ���Ѵ�. 
	 *  1000/Main.SLEEP_TIME�� 1�ʰ� �󸶸�ŭ�� ������ �߻��ϴ°��� ����Ѵ�. �� 100��
	 *  Main.NOTE_SPEED �� �� ������ �߻��ϴ� Ƚ���� ���ϸ�, ��Ʈ�� 1�ʿ� �󸶸�ŭ �����̴��� ����� �����ϴ� = 700
	 *  1�ʿ� 700�� �����̴� ��Ʈ�� ����������, 1�� �ڿ� ��Ȯ�� ��Ʈ�� 580�� �ִ� �������ο� ����߸��� ���ؼ�, 580 - 700�� �����μ� ��Ʈ�� ȭ�� �ٱ��������� ����߸��� ���� �ʿ��ϴ�.
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
					Thread.sleep(Main.SLEEP_TIME);//�̰��� ������ ��Ʈ�� �������� ���� �������� ���������� ���� ��������.
				}else {
					isJudged = true;
					close();
					System.out.println("miss");
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());//err�� ����?
		}
	}
	
	public void close() {
		this.interrupt();
	}
}
