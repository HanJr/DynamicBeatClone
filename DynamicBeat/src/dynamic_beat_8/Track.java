package dynamic_beat_8;

public class Track {
	private String highlightMusic;
	private String gameMusic;
	private String SelectionImage;
	private String GameImage;
	
	
	
	public Track(String highlightMusic, String gameMusic, String selectionImage, String gameImage) {
		super();
		this.highlightMusic = highlightMusic;
		this.gameMusic = gameMusic;
		SelectionImage = selectionImage;
		GameImage = gameImage;
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
