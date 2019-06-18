package dynamic_beat_11;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;

import javazoom.jl.player.Player;

//thread is a small application
public class Music extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../musics/"+name).toURI());			
			fis = new FileInputStream(file);		
			bis = new  BufferedInputStream(fis);//왜 버퍼가 사용되어야하나?
			player = new Player(bis);//fis로 바꾸어도 잘 돌아간다.
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 시간을 알려주는 메소드
	public int getTime() {
		if(player == null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); // 스레드 상속자는 이것이 필요하다.
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);			
			}while(isLoop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
