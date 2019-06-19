package dynamic_beat_13;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	private Image basicNoteImage = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
	private Image wideNoteImage = new ImageIcon(Main.class.getResource("../images/wideNote.png")).getImage();

	/*  580 = ����������   x ��ġ, �츮�� ��Ʈ�� ������� 1�ʿ� ��Ȯ�ϰ� �������� ���Ѵ�. 
	 *  1000/Main.SLEEP_TIME�� 1�ʰ� �󸶸�ŭ�� ������ �߻��ϴ°��� ����Ѵ�. �� 100��
	 *  Main.NOTE_SPEED �� �� ������ �߻��ϴ� Ƚ���� ���ϸ�, ��Ʈ�� 1�ʿ� �󸶸�ŭ �����̴��� ����� �����ϴ� = 700
	 *  1�ʿ� 700�� �����̴� ��Ʈ�� ����������, 1�� �ڿ� ��Ȯ�� ��Ʈ�� 580�� �ִ� �������ο� ����߸��� ���ؼ�, 580 - 700�� �����μ� ��Ʈ�� ȭ�� �ٱ��������� ����߸��� ���� �ʿ��ϴ�.
	 */
	private int x, y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED; 

	private String noteType;
	
	public Note(int x, String noteType) {
		this.x = x;
		this.noteType = noteType;
	}
	
	public void drawScreen(Graphics2D g) {
		if(noteType.equals("basic")) {
			g.drawImage(basicNoteImage, x, y, null);
		}
		else if(noteType.equals("wide")) {
			g.drawImage(wideNoteImage, x, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				Thread.sleep(Main.SLEEP_TIME);//�̰��� ������ ��Ʈ�� �������� ���� �������� ���������� ���� ��������.
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());//err�� ����?
		}
	}
}
