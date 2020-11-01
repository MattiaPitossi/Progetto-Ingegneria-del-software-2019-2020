package modelli;

import java.util.TimerTask;

import modelli.liste.ListaAttuatori;
import modelli.liste.ListaSensori;

public class RegolaSingoloSensore extends TimerTask{

	private String nomeRegola;
	private AntecedenteSingoloSensore antecedente;
	private Conseguente conseguente;
	
	public RegolaSingoloSensore (String nomeRegola, AntecedenteSingoloSensore antecedente, Conseguente conseguente) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
	}




	public AntecedenteSingoloSensore getAntecedente() {
		return antecedente;
	}



	@Override
	public void run() {
		boolean operazioneSoddisfata = false;
		boolean sensoreTrovato = false;
		int valoreRilevato = 0;
		
		//Ciclo per trovare la posizione del sensore NUMERICO se non viene trovato allora il sensore e' NON NUMERICO 
		for(int i = 0; i < ListaSensori.getInstance().getListSize(); i++) {
			if(ListaSensori.getInstance().getSensorFromList(i).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore())) {
				
				//Valore boolean che viene impostato true per non fare anche il ciclo del sensore NON NUMERICO 
				sensoreTrovato = true;
				
				//Viene trasformato il valore confrontato in un int 
				valoreRilevato = Integer.parseInt(antecedente.getValoreConfrontato());
				 
				//Ciclo if else per verificare quale e' l'operazione booleana da soddisfare
				if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">")) {
					//Ciclo che verifica se l'operazione booleana > e' verificata
					if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() > valoreRilevato) {
						operazioneSoddisfata = true;
					} else {
						System.out.println("L'operazione non è soddisfata");
					}
				} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">=")) {
					
					//Ciclo che verifica se l'operazione booleana >= e' verificata
					if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() >= valoreRilevato) {
						operazioneSoddisfata = true;
					} else {
						System.out.println("L'operazione non è soddisfata");
					}
				} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<")) {
					
					//Ciclo che verifica se l'operazione booleana < e' verificata
					if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() < valoreRilevato) {
						operazioneSoddisfata = true;
					} else {
						System.out.println("L'operazione non è soddisfata");
					}
				} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<=")) {
					
					//Ciclo che verifica se l'operazione booleana <= e' verificata
					if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() <= valoreRilevato) {
						operazioneSoddisfata = true;
					} else {
						System.out.println("L'operazione non è soddisfata");
					}
				} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("=")) {
					
					//Ciclo che verifica se l'operazione booleana = e' verificata
					if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() == valoreRilevato) {
						operazioneSoddisfata = true;
					}  else {
						System.out.println("L'operazione non è soddisfata");
					}
				}
			}
		}
		
		if(!sensoreTrovato) {
			//Ciclo per trovare la posizione del sensore NON NUMERICO
			for(int j = 0; j < ListaSensori.getInstance().getListSize(); j++) {
				if(ListaSensori.getInstance().getSensorFromList(j).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore())) {
					
					//Ciclo che verifica se l'operazione booleana = e' verificata
					if(ListaSensori.getInstance().getSensorFromList(j).getValoreRilevatoNonNumerico() == antecedente.getValoreConfrontato()) {
						operazioneSoddisfata = true;
					}  else {
						System.out.println("L'operazione non è soddisfata");
					}
				}
			}
		}
		
		//If per verificare se il conseguente deve partire 
		if(operazioneSoddisfata) {
			
			//Il conseguente puo' contenere piu' azioni 
			for(Azioni azione : conseguente.getArrayAzioni()) {
				
				//Ciclo for per trovare la posizione dell'attuatore 
				for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
					
					//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
					if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
						
						//Se if si avvera' allora viene impostato il valore contenuto nell'azione
						//Non serve prenderlo dalla categoria visto che e' gia' stato preso nel metodo della creazione dell'azione
						ListaAttuatori.getInstance().getActuatorFromList(k).setModalita(azione.getValore());
					}
				}
			}
		}
	}
}
