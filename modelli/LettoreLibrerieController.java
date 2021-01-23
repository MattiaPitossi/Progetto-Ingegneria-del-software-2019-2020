package modelli;

import java.io.IOException;

import modelli.liste.ListaAttuatoriController;
import modelli.liste.ListaCategoriaAttuatoriController;
import modelli.liste.ListaCategoriaSensoriController;
import modelli.liste.ListaRegoleDueSensoriController;
import modelli.liste.ListaRegoleSempreVereController;
import modelli.liste.ListaRegoleSingoloSensoreController;
import modelli.liste.ListaSensoriController;
import modelli.liste.ListaUnitaImmobiliareController;

public class LettoreLibrerieController {
	
	ListaRegoleSempreVereController controllerRegoleSempreVere;
	ListaRegoleSingoloSensoreController controllerRegoleSingoloSensore;
	ListaRegoleDueSensoriController controllerRegoleDueSensori;
	ListaCategoriaSensoriController controllerCategoriaSensori;
	ListaCategoriaAttuatoriController controllerCategoriaAttuatori;
	ListaUnitaImmobiliareController controllerUnitaImmobiliare;
	ListaSensoriController controllerSensori;
	ListaAttuatoriController controllerAttuatori;
	
	
	public LettoreLibrerieController(ListaRegoleSempreVereController controllerRegoleSempreVere,
			ListaRegoleSingoloSensoreController controllerRegoleSingoloSensore,
			ListaRegoleDueSensoriController controllerRegoleDueSensori,
			ListaCategoriaSensoriController controllerCategoriaSensori,
			ListaCategoriaAttuatoriController controllerCategoriaAttuatori,
			ListaUnitaImmobiliareController controllerUnitaImmobiliare, ListaSensoriController controllerSensori,
			ListaAttuatoriController controllerAttuatori) {
		this.controllerRegoleSempreVere = controllerRegoleSempreVere;
		this.controllerRegoleSingoloSensore = controllerRegoleSingoloSensore;
		this.controllerRegoleDueSensori = controllerRegoleDueSensori;
		this.controllerCategoriaSensori = controllerCategoriaSensori;
		this.controllerCategoriaAttuatori = controllerCategoriaAttuatori;
		this.controllerUnitaImmobiliare = controllerUnitaImmobiliare;
		this.controllerSensori = controllerSensori;
		this.controllerAttuatori = controllerAttuatori;
	}

	private LettoreLibrerie fr = new LettoreLibrerie();

	public void letturaLibreriaCategorie() throws IOException {
		fr.letturaLibreriaCategorie(controllerCategoriaSensori, controllerCategoriaAttuatori);
	}
	
	public void letturaLibreriaRegole() throws IOException {
		fr.letturaLibreriaRegole(controllerRegoleSempreVere, controllerRegoleSingoloSensore, controllerRegoleDueSensori);
	}
	
	public void letturaLibreriaUnita() throws IOException {
		fr.letturaLibreriaUnita(controllerCategoriaSensori, controllerCategoriaAttuatori, controllerUnitaImmobiliare, controllerSensori, controllerAttuatori);
	}
}
