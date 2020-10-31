package modelli;

import static utility.MessaggiErroriMenu.ERRORE_ATTENZIONE_IL_NOME_DI_QUESTA_CATEGORIA_È_GIÀ_PRESENTE;
import static utility.MessaggiErroriMenu.MESS_INSERISCI_IL_NOME_DELLA_CATEGORIA_DEI_SENSORI;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import modelli.categorie.CategoriaAttuatoriModalitaNonParametriche;
import modelli.categorie.CategoriaAttuatoriModalitaParametriche;
import modelli.categorie.CategoriaSensori;
import modelli.dispositivi.Attuatore;
import modelli.dispositivi.Sensore;
import modelli.dispositivi.SensoreNonNumerico;
import modelli.dispositivi.SensoreNumerico;
import modelli.liste.ListaAttuatori;
import modelli.liste.ListaCategoriaAttuatori;
import modelli.liste.ListaCategoriaSensori;
import modelli.liste.ListaRegoleDueSensori;
import modelli.liste.ListaRegoleSempreVere;
import modelli.liste.ListaRegoleSingoloSensore;
import modelli.liste.ListaSensori;
import modelli.liste.ListaUnitaImmobiliare;

public class FileReader {
	
public void letturaFileCategorie() throws IOException {
		
		boolean parametrico = false;
		boolean nonParametrico = false;
		ArrayList<String> dominioValoriRilevati = new ArrayList<>();
		ArrayList<String> listaModalitaNonParametriche = new ArrayList<>();
		CategoriaSensori categoriaCreata;
		CategoriaAttuatoriModalitaNonParametriche categoriaNonParametricaCreata = null;
		CategoriaAttuatoriModalitaParametriche categoriaParametricaCreata = null;
		ModalitaOperativaParametrica modalita;
		Parametro parametro;
		ArrayList<ModalitaOperativaParametrica> listaModalitaOperativeParametriche = new ArrayList<>();
		
		Path pathToFile = Paths.get("Cartel1.csv");
		
		try(BufferedReader buff = Files.newBufferedReader(pathToFile)) {
			
			String line = buff.readLine();
			
			while(line != null) {
				String[] attributes = line.split(";");
				
				//Se il primo attributo e' categoria sensore allora verra' creato una categoria sensore 
				if(attributes[0].equals("Categoria sensore")) {
							
					if(ListaCategoriaSensori.getInstance().alreadyExist(attributes[1])) {
						System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
					} else {
							//Se la categoria e' numerica allora verra' creata una categoria numerica 
							if(attributes[3].equalsIgnoreCase("Numerico")) {
								categoriaCreata = new CategoriaSensori(attributes[1], attributes[2], attributes[4]);
								
							//Se la categoria e' non numerica allora verra' creata una categoria non numerica 
							} else {
								for(int i = 4; i < attributes.length; i++ ) {
									dominioValoriRilevati.add(attributes[i]);
								}
								
								categoriaCreata = new CategoriaSensori(attributes[1], attributes[2], dominioValoriRilevati);
							}
							
							ListaCategoriaSensori.getInstance().addToList(attributes[1], categoriaCreata);
							System.out.println("La categoria " + attributes[1] + " e' stata aggiunta.");
						
					}
				} else if(attributes[0].equals("Categoria attuatore")) {
					
					if(ListaCategoriaAttuatori.getInstance().alreadyExist(attributes[1])) {
						System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
					} else {
						if(attributes[3].equalsIgnoreCase("Non Parametrica")) {
							for(int i = 4; i < attributes.length; i++) {
								listaModalitaNonParametriche.add(attributes[i]);
							}
							categoriaNonParametricaCreata = new CategoriaAttuatoriModalitaNonParametriche(attributes[1], attributes[2], listaModalitaNonParametriche);
							parametrico = true;
						} else {
							int valoreParametro = Integer. parseInt(attributes[6]);
							parametro = new Parametro(attributes[5], valoreParametro);
							modalita = new ModalitaOperativaParametrica(attributes[4], parametro);
							listaModalitaOperativeParametriche.add(modalita);
							categoriaParametricaCreata = new CategoriaAttuatoriModalitaParametriche(attributes[1], attributes[2], listaModalitaOperativeParametriche);
							nonParametrico = true;
						}
						
						if(parametrico) {
							ListaCategoriaAttuatori.getInstance().addToList(attributes[1], categoriaNonParametricaCreata);
							System.out.println("La categoria " + attributes[1] + " e' stata aggiunta.");
						} else if(nonParametrico) {
							ListaCategoriaAttuatori.getInstance().addToList(attributes[1], categoriaParametricaCreata);
							System.out.println("La categoria " + attributes[1] + " e' stata aggiunta.");
						}
					}
				}
				

				line = buff.readLine();
				
			}

		}
	}

public void letturaFileUnita() throws IOException {
	
	Path pathToFile = Paths.get("Cartel2.csv");
	UnitaImmobiliare unita;
	ArrayList<String> stanze = new ArrayList<>();
	ArrayList<String> artefatti = new ArrayList<>();
	boolean lettoArtefatti = false;
	boolean esisteUnita = false;
	Sensore sensoreNumerico;
	Sensore sensoreNonNumerico;
	Attuatore attuatore;
	
	try(BufferedReader buff = Files.newBufferedReader(pathToFile)) {
		String line = buff.readLine();
		
		while(line != null) {
			String[] attributes = line.split(";");
			 
			//Ciclo if che parte se legge unita per creare un unita'
			if(attributes[0].equalsIgnoreCase("Unita")) {
				if(ListaUnitaImmobiliare.getInstance().alreadyExist(attributes[1])) {
					System.out.println("Esiste gia' un'unita con il nome " + attributes[1] + ".");
					esisteUnita = true;
				} else {
					unita = new UnitaImmobiliare();
					unita.setNomeUnita(attributes[1]);
					
					//Ciclo for fino alla fine della linea che inserisce tutte le stanze, se legge artefatti allora iniza a inserire gli artefatti 
					for(int i = 2; i < attributes.length; i++) {
						if(attributes[i].equalsIgnoreCase("Artefatti")) {
							lettoArtefatti = true;
							i++;
						}
						if(!lettoArtefatti) {
							unita.aggiungiStanza(attributes[i]);
						} else {
							unita.aggiungiArtefatto(attributes[i]);
						}
					}
					
					ListaUnitaImmobiliare.getInstance().addUnitaToList(unita);
					System.out.println("Aggiunta l'unita immobiliare " + attributes[1] + ".");
				}
			}
			
			//Ciclo if che parte se legge sensore numerico per creare un sensore numerico 
			else if(attributes[0].equalsIgnoreCase("Sensore Numerico") && !esisteUnita) {
				if(ListaSensori.getInstance().alreadyExist(attributes[1])) {
					System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
				} else {
					sensoreNumerico = new SensoreNumerico(attributes[1], attributes[2], ListaCategoriaSensori.getInstance().getCategoriaSensori(attributes[3]), true, attributes[6], Integer.parseInt(attributes[5]));
					ListaSensori.getInstance().addSensoreToList(sensoreNumerico);
					System.out.println("Aggiunto il sensore numerico " + attributes[1] + ".");
				}
			}
			
			//Ciclo if che parte se legge sensore non numerico per creare un sensore non numerico 
			else if(attributes[0].equalsIgnoreCase("Sensore Non Numerico") && !esisteUnita) {
				if(ListaSensori.getInstance().alreadyExist(attributes[1])) {
					System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
				} else {
					sensoreNonNumerico = new SensoreNonNumerico(attributes[1], attributes[2], ListaCategoriaSensori.getInstance().getCategoriaSensori(attributes[3]), true, attributes[4]);
					ListaSensori.getInstance().addSensoreToList(sensoreNonNumerico);
					System.out.println("Aggiunto il sensore non numerico " + attributes[1] +".");
				}
			}
			
			else if(attributes[0].equalsIgnoreCase("Attuatore") && !esisteUnita) {
				if(ListaAttuatori.getInstance().alreadyExist(attributes[1])) {
					System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
				} else {
					attuatore = new Attuatore(attributes[1], attributes[2], ListaCategoriaAttuatori.getInstance().getCategoriaAttuatori(attributes[3]), true, attributes[4]);
					ListaAttuatori.getInstance().addAttuatoreToList(attuatore);
					System.out.println("Aggiunto l'attuatore " + attributes[1] +".");
				}
			}
			
			
			line = buff.readLine();
		}
	}
}

public void letturaFileRegole() throws IOException {
	

	Path pathToFile = Paths.get("Cartel3.csv");
	AntecedenteSempreVero sempreVero;
	AntecedenteSingoloSensore singoloSensore;
	AntecedenteTraDueSensori dueSensori;
	Conseguente conseguente;
	ArrayList<Azioni> azioni = new ArrayList<>();
	Azioni azione;
	RegolaSempreVera regolaSempreVera;
	RegolaSingoloSensore regolaSingoloSensore;
	RegolaDueSensori regolaDueSensori;
	

	try(BufferedReader buff = Files.newBufferedReader(pathToFile)) {
		String line = buff.readLine();
		System.out.println("Path trovato.");
		
		while(line != null) {
			String[] attributes = line.split(";");
			 
			if(attributes[0].equalsIgnoreCase("Regola Sempre Vera")) {
				if(ListaRegoleSempreVere.getInstance().alreadyExist(attributes[1])) {
					System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
				} else {
					sempreVero = new AntecedenteSempreVero();
					for(int i = 5; i < attributes.length; i = i + 2) {
						for(int j = 6; j < attributes.length; j = j + 2) {
							azione = new Azioni(attributes[i], attributes[j]);
							azioni.add(azione);
						}
					}
					conseguente = new Conseguente(azioni);
					regolaSempreVera = new RegolaSempreVera(attributes[1], sempreVero, conseguente) ;
					ListaRegoleSempreVere.getInstance().addToList(attributes[1], regolaSempreVera);
					System.out.println("La regola " + attributes[1] + " di tipo Sempre Vera e' stata aggiunta'.");
				}
			}
			
			if(attributes[0].equalsIgnoreCase("Regola Singolo Sensore")) {
				if(ListaRegoleSingoloSensore.getInstance().alreadyExist(attributes[1])) {
					System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
				} else {
					singoloSensore = new AntecedenteSingoloSensore(attributes[3], attributes[4], attributes[5], Float.parseFloat(attributes[6]), attributes[7]);
					for(int i = 5; i < attributes.length; i = i + 2) {
						for(int j = 6; j < attributes.length; j = j + 2) {
							azione = new Azioni(attributes[i], attributes[j]);
							azioni.add(azione);
						}
					}
					conseguente = new Conseguente(azioni);
					regolaSingoloSensore = new RegolaSingoloSensore(attributes[1], singoloSensore, conseguente, "Importato");
					ListaRegoleSingoloSensore.getInstance().addToList(attributes[1], regolaSingoloSensore);
					System.out.println("La regola " + attributes[1] + " di tipo Singolo Sensore e' stata aggiunta'.");
				}
			}
			
			if(attributes[0].equalsIgnoreCase("Regola Due Sensori")) {
				if(ListaRegoleDueSensori.getInstance().alreadyExist(attributes[1])) {
					System.out.println("Esiste gia' una categoria con il nome " + attributes[1] + ".");
				} else {
					dueSensori = new AntecedenteTraDueSensori(attributes[3], attributes[4], attributes[6], Float.parseFloat(attributes[6]), attributes[7]);
					for(int i = 5; i < attributes.length; i = i + 2) {
						for(int j = 6; j < attributes.length; j = j + 2) {
							azione = new Azioni(attributes[i], attributes[j]);
							azioni.add(azione);
						}
					}
					conseguente = new Conseguente(azioni);
					regolaDueSensori = new RegolaDueSensori(attributes[1], dueSensori, conseguente);
					ListaRegoleDueSensori.getInstance().addToList(attributes[1], regolaDueSensori);
					System.out.println("La regola " + attributes[1] + " di tipo Due Sensori e' stata aggiunta'.");
				}
			}
			line = buff.readLine();
		}
	}
	
}

}
