package it.epicode.be.godfather.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@PropertySource("classpath:application.properties")
public class Ordine {

	private Tavolo tavoloAssociato;
	private List<MenuItem> prodottiScelti = new ArrayList<>();
	private int numeroOrdine;
	private int numeroTavolo;
	private StatoOrdine statoOrdine;
	private int numeroCoperti;
	private LocalDate orarioAcquisizione;
	@Value("${order.costocoperto}") private double costoCoperto;
	private double importoTotale;
	
	public Ordine(Tavolo tavolo, int numeroCoperti) {
		ListaOrdini list = new ListaOrdini();
		list.aggiungiOrdine(this);
		this.tavoloAssociato = tavolo;
		this.numeroOrdine = list.creaNumeroOrdine(list.getListaOrdine());
		this.numeroTavolo = this.tavoloAssociato.getNumeroTavolo();
		this.statoOrdine = StatoOrdine.IN_CORSO;
		this.numeroCoperti = numeroCoperti;
		this.orarioAcquisizione = LocalDate.now();
//		System.out.println("STEPH " + costoCoperto);
		this.importoTotale = numeroCoperti * costoCoperto;

	}
	
	public void aggiungiProdotto(MenuItem prod) {
		this.prodottiScelti.add(prod);
//		this.importoTotale += prod.getPrice();
		List<Double> prezzi = this.prodottiScelti.stream().map(e -> e.getPrice()).toList();
		double nuovoPrezzo = 0;
		for(int i = 0; i < prezzi.size(); i++) {
			nuovoPrezzo += prezzi.get(i);
		}
		
		this.importoTotale = nuovoPrezzo;
	}
	
	public void rimuoviProdotto(MenuItem prod) {
		this.prodottiScelti = this.prodottiScelti.stream().filter(e -> e != prod).toList();
		double nuovoPrezzo = 0;
		for(int i = 0; i < this.prodottiScelti.size(); i++) {
			nuovoPrezzo += this.prodottiScelti.get(i).getPrice();
		}
		
		this.importoTotale = nuovoPrezzo;
	}
}
