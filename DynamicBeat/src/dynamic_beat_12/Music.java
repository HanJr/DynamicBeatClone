package dynamic_beat_12;

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
			bis = new  BufferedInputStream(fis);//�� ���۰� ���Ǿ���ϳ�?
			player = new Player(bis);//fis�� �ٲپ �� ���ư���.
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// �ð��� �˷��ִ� �޼ҵ�
	public int getTime() {
		if(player == null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); // ������ ����ڴ� �̰��� �ʿ��ϴ�.
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