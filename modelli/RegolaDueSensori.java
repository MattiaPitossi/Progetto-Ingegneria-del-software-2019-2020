package modelli;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

import modelli.liste.ListaAttuatoriController;
import modelli.liste.ListaAttuatoriModel;
import modelli.liste.ListaSensoriController;
import modelli.liste.ListaSensoriModel;

public class RegolaDueSensori extends TimerTask{
	
	private String nomeRegola;
	private AntecedenteTraDueSensori antecedente;
	private Conseguente conseguente;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
	private boolean attivaDisattiva;
	private ListaSensoriController controllerSensori = new ListaSensoriController();
	private ListaAttuatoriController controllerAttuatori = new ListaAttuatoriController();
		
	public RegolaDueSensori (String nomeRegola, AntecedenteTraDueSensori antecedente, Conseguente conseguente) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
		this.attivaDisattiva = true;
	}

	/**
	 * @return  il nome della regola
	 */
	public String getNomeRegola() {
		return nomeRegola;
	}

	/**
	 * @return  ritorna l'antecedente tra due sensori
	 */
	public AntecedenteTraDueSensori getAntecedente() {
		return antecedente;
	}

	/**
	 * @return  ritorna il conseguente
	 */
	public Conseguente getConseguente() {
		return conseguente;
	}

	/**
	 * @return  ritorna l'operatore booleano relativo alla regola che indica il suo stato 
	 */
	public boolean getAttivaDisattiva() {
		return attivaDisattiva;
	}

	/**
	 * @param attivaDisattiva consente di impostare lo stato attivo o disattivo della regola
	 */
	public void setAttivaDisattiva(boolean attivaDisattiva) {
		this.attivaDisattiva = attivaDisattiva;
	}

	@Override
	//Metodo da attivare ogni tot tempo 
	public void run() {
		if(attivaDisattiva) {
			boolean operazioneSoddisfata = false;
			boolean operazioneTimeSoddisfata = false;
			System.out.println("");
			System.out.println("Verifica della regola " + nomeRegola + "... ");
			Timestamp timestampDaVerificare = new Timestamp(System.currentTimeMillis());
			conseguente.setStart(Float.valueOf(sdf.format(timestampDaVerificare)));
			
			if(antecedente.getTime() != 000.00) {
				if(antecedente.getOperatoreBooleanTime().equalsIgnoreCase(">")) {
					if(Float.valueOf(sdf.format(timestampDaVerificare)) > antecedente.getTime()) {
						operazioneTimeSoddisfata = true;
					}
				} else if(antecedente.getOperatoreBooleanTime().equalsIgnoreCase(">=")) {
					if(Float.valueOf(sdf.format(timestampDaVerificare)) >= antecedente.getTime()) {
						operazioneTimeSoddisfata = true;
					}
				} else if(antecedente.getOperatoreBooleanTime().equalsIgnoreCase("<")) {
					if(Float.valueOf(sdf.format(timestampDaVerificare)) < antecedente.getTime()) {
						operazioneTimeSoddisfata = true;
					}
				} else if(antecedente.getOperatoreBooleanTime().equalsIgnoreCase("<=")) {
					if(Float.valueOf(sdf.format(timestampDaVerificare)) <= antecedente.getTime()) {
						operazioneTimeSoddisfata = true;
					}
				} else if(antecedente.getOperatoreBooleanTime().equalsIgnoreCase("=")) {
					if(Float.valueOf(sdf.format(timestampDaVerificare)) == antecedente.getTime()) {
						operazioneTimeSoddisfata = true;
					}
				} 
			} else {
				operazioneTimeSoddisfata = true;
			}
			
			if(operazioneTimeSoddisfata) {
				//Ciclo per trovare la posizione del sensore scelto nella regola 
				for(int i = 0; i < controllerSensori.getListSize(); i++) {
					//Se viene trovato inizia il ciclo per trovare la posizone del secondo 
					if(controllerSensori.verificaNomePrimoSensore(i, antecedente)) {
						//Ciclo per troavere la poszione del secondo sensore 
						for(int j = 0; j < controllerSensori.getListSize(); j++) {
							
							//Se viene trovato il sensore si iniza a verificare la regola 
							if(controllerSensori.verificaNomeSecondoSensore(j, antecedente)) {
								//Ciclo if else per verificare quale e' l'operazione booleana da soddisfare
								if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">")) {
									
									//Ciclo che verifica se l'operazione booleana > e' verificata
									if(controllerSensori.getValoreRilevato(i) > controllerSensori.getValoreRilevato(j)) {
										operazioneSoddisfata = true;
									} else {
										System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
									}
								} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">=")) {
									
									//Ciclo che verifica se l'operazione booleana >= e' verificata
									if(controllerSensori.getValoreRilevato(i) >= controllerSensori.getValoreRilevato(j)) {
										operazioneSoddisfata = true;
									} else {
										System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
									}
								} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<")) {
									
									//Ciclo che verifica se l'operazione booleana < e' verificata
									if(controllerSensori.getValoreRilevato(i) < controllerSensori.getValoreRilevato(j)) {
										operazioneSoddisfata = true;
									} else {
										System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
									}
								} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<=")) {
									
									//Ciclo che verifica se l'operazione booleana <= e' verificata
									if(controllerSensori.getValoreRilevato(i) <= controllerSensori.getValoreRilevato(j)) {
										operazioneSoddisfata = true;
									} else {
										System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
									}
								} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("=")) {
									
									//Ciclo che verifica se l'operazione booleana = e' verificata
									if(controllerSensori.getValoreRilevato(i) == controllerSensori.getValoreRilevato(j)) {
										operazioneSoddisfata = true;
									}  else {
										System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
									}
								}
							}
							
							//If per verificare se il conseguente deve partire 
							if(operazioneSoddisfata) {
								
								//Viene trasformata l'ora in un valore float e salvato 
								Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								conseguente.setStart(Float.valueOf(sdf.format(timestamp)));
								
								//Il conseguente puo' contenere piu' azioni 
								for(Azioni azione : conseguente.getArrayAzioni()) {
									
									//Ciclo for per trovare la posizione dell'attuatore 
									for(int k = 0; k < controllerAttuatori.getSize(); k++) {
										
										//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
										if(controllerAttuatori.verificaNomeAttuatoreAzione(k, azione)) {
											
											System.out.println("L'operazione delle regola " + nomeRegola + " e' soddisfatta, l'attuatore " + azione.getNomeAttuatore() + " e' stato impostato a " + azione.getValore());
											//Se if si avvera' allora viene impostato il valore contenuto nell'azione
											//Non serve prenderlo dalla categoria visto che e' gia' stato preso nel metodo della creazione dell'azione
											controllerAttuatori.setModalita(k, azione);
										}
									}
								}
							}
						}
					}
					
				}
			} else {
				System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
			}
		}
		
	}

}


