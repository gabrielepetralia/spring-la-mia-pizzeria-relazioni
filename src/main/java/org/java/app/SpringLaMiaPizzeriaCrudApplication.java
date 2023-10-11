package org.java.app;

import java.time.LocalDate;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Pizza pizza1 = new Pizza("Margherita", "pizza con pomodoro e mozzarella", "https://www.unmondodisapori.it/wp-content/uploads/2017/10/margherita.jpg", 5.50);
		Pizza pizza2 = new Pizza("Tedesca", "pizza con wurstel e patatine", "https://www.scattidigusto.it/wp-content/uploads/2015/11/pizza-wurstel-patatine-pomodoro.jpg", 6.50);
		Pizza pizza3 = new Pizza("Norma", "pizza con melanzane e ricotta", "https://cdn.cook.stbm.it/thumbnails/ricette/144/144902/hd750x421.jpg", 7.50);
		Pizza pizza4 = new Pizza("Tonnara", "pizza con tonno e cipolla","https://www.gustissimo.it/articoli/ricette/pizze/pizza-tonno-e-cipolla.jpg", 7.00);
		Pizza pizza5 = new Pizza("Diavola", "pizza con salame piccante","https://www.saporidellamurgia.com/prodotti/0302628/pizza_calabrese.jpg", 7.00);
		
		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		
		SpecialOffer specialOffer1 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 1", 
				pizza1);
		SpecialOffer specialOffer2 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 2", 
				pizza2);
		SpecialOffer specialOffer3 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 3", 
				pizza3);
		SpecialOffer specialOffer4 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 4", 
				pizza4);
		SpecialOffer specialOffer5 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 5",
				pizza4);
		
		specialOfferService.save(specialOffer1);
		specialOfferService.save(specialOffer2);
		specialOfferService.save(specialOffer3);
		specialOfferService.save(specialOffer4);
		specialOfferService.save(specialOffer5);
		
		System.out.println("Insert OK!");
	}

}
