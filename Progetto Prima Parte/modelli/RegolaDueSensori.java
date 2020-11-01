package modelli;

import java.util.TimerTask;

import modelli.liste.ListaAttuatori;
import modelli.liste.ListaSensori;

public class RegolaDueSensori extends TimerTask{
	
	private String nomeRegola;
	private AntecedenteTraDueSensori antecedente;
	private Conseguente conseguente;
		
	public RegolaDueSensori (String nomeRegola, AntecedenteTraDueSensori antecedente, Conseguente conseguente) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
	}


	@Override
	//Metodo da attivare ogni tot tempo 
	public void run() {
		boolean operazioneSoddisfata = false;
		//Ciclo per trovare la posizione del sensore scelto nella regola 
		for(int i = 0; i < ListaSensori.getInstance().getListSize(); i++) {
			//Se viene trovato inizia il ciclo per trovare la posizone del secondo 
			if(ListaSensori.getInstance().getSensorFromList(i).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore())) {
				//Ciclo per troavere la poszione del secondo sensore 
				for(int j = 0; j < ListaSensori.getInstance().getListSize(); j++) {
					
					//Se viene trovato il sensore si iniza a verificare la regola 
					if(ListaSensori.getInstance().getSensorFromList(j).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSecondoSensore())) {
						
						//Ciclo if else per verificare quale e' l'operazione booleana da soddisfare
						if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">")) {
							
							//Ciclo che verifica se l'operazione booleana > e' verificata
							if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() > ListaSensori.getInstance().getSensorFromList(j).getValoreRilevato()) {
								operazioneSoddisfata = true;
							} else {
								System.out.println("L'operazione non è soddisfata");
							}
						} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">=")) {
							
							//Ciclo che verifica se l'operazione booleana >= e' verificata
							if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() >= ListaSensori.getInstance().getSensorFromList(j).getValoreRilevato()) {
								operazioneSoddisfata = true;
							} else {
								System.out.println("L'operazione non è soddisfata");
							}
						} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<")) {
							
							//Ciclo che verifica se l'operazione booleana < e' verificata
							if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() < ListaSensori.getInstance().getSensorFromList(j).getValoreRilevato()) {
								operazioneSoddisfata = true;
							} else {
								System.out.println("L'operazione non è soddisfata");
							}
						} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<=")) {
							
							//Ciclo che verifica se l'operazione booleana <= e' verificata
							if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() <= ListaSensori.getInstance().getSensorFromList(j).getValoreRilevato()) {
								operazioneSoddisfata = true;
							} else {
								System.out.println("L'operazione non è soddisfata");
							}
						} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("=")) {
							
							//Ciclo che verifica se l'operazione booleana = e' verificata
							if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() == ListaSensori.getInstance().getSensorFromList(j).getValoreRilevato()) {
								operazioneSoddisfata = true;
							}  else {
								System.out.println("L'operazione non è soddisfata");
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
			
		}
		
	}

}


