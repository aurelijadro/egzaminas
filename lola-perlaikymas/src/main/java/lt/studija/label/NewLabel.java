package lt.studija.label;

import javax.validation.constraints.NotNull;

public class NewLabel {

	@NotNull(message = "Dovana must have a title")
	private String title;
	private String logo;
	private String category;
	private String size;

	public NewLabel(@NotNull(message = "Label must have a title") String title, String logo, String category,
			String size) {
		this.title = title;
		this.logo = logo;
		this.category = category;
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
