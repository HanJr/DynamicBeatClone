package dynamic_beat_13;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e) {
		//void 대신에 나는 static으로 사용해서 문제있었음.
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

/* 나의 실수, 여기다가 만드는 것이 아니다. 
 * 여기다가 만들경우, code가 깔끔하지 않고, press 되었을 때 highlight 이미지를 DynamicBeat에 보내기 복잡해진다. 가능한지 모르겠다. 
 * 연결고리를 이해해야한다. 그래픽을 DynamicBeat에 적용시키기 위해서는 Graphics를 사용해야하는 것을 기억. 현재 Graphics가 DynamicBeat에 이어진 class들은 Game, DynamicBeatclass들 밖에 없으므로
 * 저 두곳에서 사용해야한다는 결론 도출	
	public void pressedS() {
		
	}
	
	public void releasedS() {
		
	}
*/
}
