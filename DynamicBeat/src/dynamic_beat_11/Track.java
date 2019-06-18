package dynamic_beat_11;

public class Track {
	private String highlightMusic;
	private String gameMusic;
	private String SelectionImage;
	private String GameImage;
	private String titleAndMusician;
	
	
	public Track(String highlightMusic, String gameMusic, String selectionImage, String gameImage, String titleAndMusician) {
		super(); // super()가 없어도 잘 작동한다. 왜 있는지 모르겠다. 
		this.highlightMusic = highlightMusic;
		this.gameMusic = gameMusic;
		SelectionImage = selectionImage;
		GameImage = gameImage;
		this.titleAndMusician = titleAndMusician;
	}
	public String getHighlightMusic() {
		return highlightMusic;
	}
	public void setHighlightMusic(String highlightMusic) {
		this.highlightMusic = highlightMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleAndMusician() {
		return titleAndMusician;
	}
	public void setTitleAndMusician(String titleAndMusician) {
		this.titleAndMusician = titleAndMusician;
	}
	public String getSelectionImage() {
		return SelectionImage;
	}
	public void setSelectionImage(String selectionImage) {
		SelectionImage = selectionImage;
	}
	public String getGameImage() {
		return GameImage;
	}
	public void setGameImage(String gameImage) {
		GameImage = gameImage;
	}
	
	
	
	
}
