package it.epicode.be.godfather;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import it.epicode.be.godfather.config.MenuConfig;
import it.epicode.be.godfather.model.DrinkLemonade;
import it.epicode.be.godfather.model.Ordine;
import it.epicode.be.godfather.model.PizzaMargherita;
import it.epicode.be.godfather.model.PizzaSalami;
import it.epicode.be.godfather.model.Stato;
import it.epicode.be.godfather.model.Tavolo;

@Component
public class OrdineRunner implements CommandLineRunner {

	AnnotationConfigApplicationContext appContext;
//	Menu menu;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		creaOrdine();
	}

	public void creaOrdine() {
//		menu = new Menu();
		GestioneMenu stampa = new GestioneMenu();
		stampa.stampaMenu();
		
		appContext = new AnnotationConfigApplicationContext(MenuConfig.class);
		PizzaMargherita pizza1 = (PizzaMargherita) appContext.getBean("pizzaMargherita");
		PizzaSalami pizza2 = (PizzaSalami) appContext.getBean("pizzaSalami");
		DrinkLemonade drink1 = (DrinkLemonade) appContext.getBean("drinkLemonade");
		
		Tavolo tavolo1 = new Tavolo(1,4, Stato.LIBERO);
		Ordine ordine = new Ordine(tavolo1, 2);
		ordine.aggiungiProdotto(pizza1);
		ordine.aggiungiProdotto(pizza2);
		ordine.aggiungiProdotto(drink1);
		ordine.rimuoviProdotto(pizza2);
		
		System.out.println("\nNuovo Ordine: ");
		System.out.println("Tavolo numero " + ordine.getTavoloAssociato().getNumeroTavolo());
		System.out.println("Numero coperti: " + ordine.getNumeroCoperti());
		ordine.getProdottiScelti().forEach(e -> System.out.println(e.getName()));
		System.out.println("Totale da pagare: " + ordine.getImportoTotale());
		
		appContext.close();
	}

}
