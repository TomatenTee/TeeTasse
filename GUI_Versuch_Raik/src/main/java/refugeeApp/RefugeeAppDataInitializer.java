package refugeeApp;

import refugeeApp.model.User;
import refugeeApp.model.UserRepository;
import refugeeApp.model.Good;
import refugeeApp.model.Activity;
import refugeeApp.model.Activity.ActivityType;
import refugeeApp.model.Good.GoodType;
import refugeeApp.model.GoodCatalog;
import refugeeApp.model.ActivityCatalog;

import org.javamoney.moneta.Money;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import static org.salespointframework.core.Currencies.EURO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RefugeeAppDataInitializer implements DataInitializer {
	
	private final GoodCatalog goodCatalog;
	private final ActivityCatalog activityCatalog;
	private final UserAccountManager userAccountManager;
	private final UserRepository userRepository;
	
	@Autowired
	public RefugeeAppDataInitializer(UserRepository userRepository, UserAccountManager userAccountManager, GoodCatalog goodCatalog, ActivityCatalog activityCatalog){
		Assert.notNull(userRepository, "UserRepsitory must not be null!");
		Assert.notNull(userAccountManager, "UserAccountManager must not be null!");
		Assert.notNull(goodCatalog, "GoodCatalog must not be null!");
		Assert.notNull(activityCatalog, "ActivityCatalog must not be null!");

		this.userRepository = userRepository;
		this.userAccountManager = userAccountManager;
		this.goodCatalog = goodCatalog;
		this.activityCatalog = activityCatalog;
	}
	
	@Override
	public void initialize() {
		initializeUsers (userAccountManager, userRepository);
		initializeGoodCatalog(goodCatalog);
		initializeActivityCatalog(activityCatalog);
	}
	
	private void initializeGoodCatalog(GoodCatalog goodCatalog){
		
		if (goodCatalog.findAll().iterator().hasNext()){
			return;
		}
		
		goodCatalog.save(new Good("Schrank", "schrank", Money.of(0.00, EURO), "Möbel", GoodType.Good));
		goodCatalog.save(new Good("Sneaker", "sneaker", Money.of(0.00, EURO),"Schuhe", GoodType.Good));
	}
	
	private void initializeActivityCatalog(ActivityCatalog activityCatalog){
		
		if (activityCatalog.findAll().iterator().hasNext()){
			return;
		}
		
		activityCatalog.save(new Activity("Fußball für Kinder", "fußballkids","betreutes Fußball spielen im Freien, Bitte selbst mit Essen und Trinken versorgen, da diese Veranstaltung draußen stattfindet, bitte ich außerdem darauf zu achten, dass die Kinder Kleidung tragen die auch schmutzig werden darf", Money.of(14.99, EURO), "Kinder", ActivityType.Activity));
        activityCatalog.save(new Activity("Radfahren durch Dresden", "bike", "Treffpunkt:Postplatz, Dauer: ca 2h", Money.of(14.99, EURO),"Draußen", ActivityType.Activity));
	}
	
	private void initializeUsers(UserAccountManager userAccountManager, UserRepository userRepository){
		if (userAccountManager.findByUsername("Admin").isPresent()){
			return;
		}
		
		// .create(String userName, String password, String ROLE)
		@SuppressWarnings("deprecation")
		UserAccount adminAccount = userAccountManager.create("Admin", "123", new Role("ROLE_ADMIN"));//Salespoint wurde aktualisiert, seit dem alte Funktion. Alternative? 
		userAccountManager.save(adminAccount);
		
		@SuppressWarnings("deprecation")
		final Role refugeeRole = new Role ("ROLE_REFUGEE");
		@SuppressWarnings("deprecation")
		final Role supporterRole = new Role("ROLE_SUPPORTER");
		
		UserAccount re1 = userAccountManager.create("Mohammed", "123", refugeeRole);
		userAccountManager.save(re1);
		
		UserAccount sup1 = userAccountManager.create("Michel", "123", supporterRole);
		userAccountManager.save(sup1);
		
		// User wird erstellt, mit den zuvor festgelegten Eigenschaften plus die Rolle (in der Tabelle angezeigt)
		// Hier könnte man genauso noch die Adresse nehmen, Geschlecht, Herkunft etc.
		User c1 = new User (re1, "Refugee");
		User c2 = new User (sup1, "Supporter");
		
		userRepository.save(c1);
		userRepository.save(c2);
	}
	
	
	
}
