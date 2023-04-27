package it.epicode.be.godfather.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ListaOrdini {

	private static List<Ordine> listaOrdini = new ArrayList<>();
	
	public void aggiungiOrdine(Ordine ordine) {
		listaOrdini.add(ordine);
	}
	
	public List<Ordine> getListaOrdine(){
		return this.listaOrdini;
	}
	
	public int creaNumeroOrdine(List<Ordine> list) {
		return list.size() + 1;
	}
}
