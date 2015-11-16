package refugeeApp.model;

import org.salespointframework.catalog.Catalog;

import refugeeApp.model.Good.GoodType;


public interface GoodCatalog extends Catalog<Good> {
	/**
	 * Returns all {@link Good}s by type.
	 * 
	 * @param type must not be {@literal null}.
	 * @return
	 */
	Iterable<Good> findByType(GoodType type);

}
