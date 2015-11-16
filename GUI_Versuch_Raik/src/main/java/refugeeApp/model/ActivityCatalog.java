package refugeeApp.model;

import org.salespointframework.catalog.Catalog;

import refugeeApp.model.Activity.ActivityType;


public interface ActivityCatalog extends Catalog<Activity> {
	/**
	 * Returns all {@link Aid}s by type.
	 * 
	 * @param type must not be {@literal null}.
	 * @return
	 */
	Iterable<Activity> findByType(ActivityType type);

}
