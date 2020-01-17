package lt.studija.song;

public class NewSong {
	private String title;
	private String albumName;
	private int lengthInSeconds;
	private String mp3File;

	public NewSong(String title, String albumName, int lengthInSeconds, String mp3File) {
		this.title = title;
		this.albumName = albumName;
		this.lengthInSeconds = lengthInSeconds;
		this.mp3File = mp3File;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public int getLengthInSeconds() {
		return lengthInSeconds;
	}

	public void setLengthInSeconds(int lengthInSeconds) {
		this.lengthInSeconds = lengthInSeconds;
	}

	public String getMp3File() {
		return mp3File;
	}

	public void setMp3File(String mp3File) {
		this.mp3File = mp3File;
	}

}
