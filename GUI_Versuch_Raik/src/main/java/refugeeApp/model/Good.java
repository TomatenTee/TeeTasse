package refugeeApp.model;

import javax.persistence.Entity;
import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

@Entity
public class Good extends Product{

	private static final long serialVersionUID = 3602178166541657501L;

	public static enum GoodType {
		Good;
	}
	private String category, image;
	private GoodType type;
	
	@SuppressWarnings({ "unused", "deprecation" })
	private Good() {}
	
	public Good(String name, String image, Money price, String category, GoodType type) {

		super(name, price);

		this.image = image;
		this.category = category;
		this.type = type;
	}

	public String getCategory() {
		return category;
	}
	
	public String getImage() {
		return image;
	}

	public GoodType getType() {
		return type;
	}
}
