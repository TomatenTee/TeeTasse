package refugeeApp.model.validation;

import org.hibernate.validator.constraints.NotEmpty;

public class ActivityForm {

	private String name;
	@NotEmpty(message = "{ActivityForm.name.NotEmpty}")
	
	private String image;
	private String description;
	@NotEmpty(message = "{ActivityForm.description.NotEmpty}")
	
	private String category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
