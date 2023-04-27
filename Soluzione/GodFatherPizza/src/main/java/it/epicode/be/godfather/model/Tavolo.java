package it.epicode.be.godfather.model;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tavolo {

	private int numeroTavolo;
	private int numeroCopertiMax;
	private Stato stato;
	
	
	
}
