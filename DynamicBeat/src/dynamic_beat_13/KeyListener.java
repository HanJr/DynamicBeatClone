package dynamic_beat_13;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e) {
		//void ��ſ� ���� static���� ����ؼ� �����־���.
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.pressedS();
			new Music("high hat1.mp3", false).start();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressedD();
			new Music("snare1.mp3", false).start();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.pressedSpace();
			new Music("kick1.mp3", false).start();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.pressedK();
			new Music("snare2.mp3", false).start();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.pressedL();
			new Music("high hat2.mp3", false).start();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.releasedS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releasedD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.releasedSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releasedK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releasedL();
		}
	}

/* ���� �Ǽ�, ����ٰ� ����� ���� �ƴϴ�. 
 * ����ٰ� ������, code�� ������� �ʰ�, press �Ǿ��� �� highlight �̹����� DynamicBeat�� ������ ����������. �������� �𸣰ڴ�. 
 * ������� �����ؾ��Ѵ�. �׷����� DynamicBeat�� �����Ű�� ���ؼ��� Graphics�� ����ؾ��ϴ� ���� ���. ���� Graphics�� DynamicBeat�� �̾��� class���� Game, DynamicBeatclass�� �ۿ� �����Ƿ�
 * �� �ΰ����� ����ؾ��Ѵٴ� ��� ����	
	public void pressedS() {
		
	}
	
	public void releasedS() {
		
	}
*/
}
