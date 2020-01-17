package lt.studija.artist;

public class NewArtist {

	private String title;
	private String name;
	private String surname;
	private String genre;
	private String country;
	private String birthday;
	private String photo;

	public NewArtist(String title, String name, String surname, String genre, String country, String birthday,
			String photo) {
		this.title = title;
		this.name = name;
		this.surname = surname;
		this.genre = genre;
		this.country = country;
		this.birthday = birthday;
		this.photo = photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
