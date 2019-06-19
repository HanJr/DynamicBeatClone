package dynamic_beat_12;

//package 이름은 소문자로 만드는 것이 정석

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;	
	public static final int NOTE_SPEED = 7;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 1; //노트 생성 이후, 판정바에 다다르기 까지의 시간.
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DynamicBeat();
	}

}
