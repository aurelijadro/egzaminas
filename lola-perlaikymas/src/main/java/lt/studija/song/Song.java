package lt.studija.song;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lt.studija.artist.Artist;

@Entity
public class Song {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String title;
	@Column
	private String albumName;
	@Column
	private int lengthInSeconds;
	@Column
	private String mp3File;

	@OneToMany(mappedBy = "song")
	private List<Artist> artists = new ArrayList<Artist>();

	public Song() {
	}

	public Song(String title, String albumName, int lengthInSeconds, String mp3File) {
		this.title = title;
		this.albumName = albumName;
		this.lengthInSeconds = lengthInSeconds;
		this.mp3File = mp3File;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	};

}
