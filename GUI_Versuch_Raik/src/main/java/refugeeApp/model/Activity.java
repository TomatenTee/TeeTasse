package refugeeApp.model;

import javax.persistence.Entity;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

@Entity
public class Activity extends Product{

	private static final long serialVersionUID = 3602564501178687501L;

	public static enum ActivityType { //auch für Kategorien verwenden ?
		Activity;
	}
	private String category, image, description;
	private ActivityType type;

	@SuppressWarnings({ "unused", "deprecation" })
	private Activity() {}

	public Activity(String name, String image, String description, Money price, String category, ActivityType type) {

		super(name, price);

		this.image = image;
		this.category = category;
		this.type = type;
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public String getImage() {
		return image;
	}

	public ActivityType getType() {
		return type;
	}
	
	public String getDescription() {
		return description;
	}
}
