package modelli;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

import modelli.liste.ListaAttuatori;
import modelli.liste.ListaSensori;

public class RegolaSingoloSensore extends TimerTask{

	private String nomeRegola;
	private AntecedenteSingoloSensore antecedente;
	private Conseguente conseguente;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
	private boolean attivaDisattiva;
	private String tipoRegola;
	
	public RegolaSingoloSensore (String nomeRegola, AntecedenteSingoloSensore antecedente, Conseguente conseguente, String tipoRegola) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
		this.attivaDisattiva = true;
		this.tipoRegola = tipoRegola;
	}

	/**
	 * @return  ritorna il nome della regola
	 */
	public String getNomeRegola() {
		return nomeRegola;
	}

	/**
	 * @return  il conseguente associato alla regola
	 */
	public AntecedenteSingoloSensore getAntecedente() {
		return antecedente;
	}

	/**
	 * @return  ritorna l'operatore booleano relativo alla regola che indica il suo stato 
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
	public void run() {
		
		if(attivaDisattiva) {
			boolean operazioneSoddisfata = false;
			boolean sensoreTrovato = false;
			boolean operazioneTimeSoddisfata = false;
			int valoreRilevato = 0;

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
				//Ciclo per trovare la posizione del sensore NUMERICO se non viene trovato allora il sensore e' NON NUMERICO 
				for(int i = 0; i < ListaSensori.getInstance().getListSize(); i++) {
					if(ListaSensori.getInstance().getSensorFromList(i).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore())) {
						
						if(tipoRegola.equalsIgnoreCase("Numerico")) {
							//Viene trasformato il valore confrontato in un int 
							valoreRilevato = Integer.parseInt(antecedente.getValoreConfrontato());
							 
							//Ciclo if else per verificare quale e' l'operazione booleana da soddisfare
							if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">")) {
								//Ciclo che verifica se l'operazione booleana > e' verificata
								if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() > valoreRilevato) {
									operazioneSoddisfata = true;
								} else {
									System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
								}
							} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">=")) {
								
								//Ciclo che verifica se l'operazione booleana >= e' verificata
								if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() >= valoreRilevato) {
									operazioneSoddisfata = true;
								} else {
									System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
								}
							} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<")) {
								
								//Ciclo che verifica se l'operazione booleana < e' verificata
								if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() < valoreRilevato) {
									operazioneSoddisfata = true;
								} else {
									System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
								}
							} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<=")) {
								
								//Ciclo che verifica se l'operazione booleana <= e' verificata
								if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() <= valoreRilevato) {
									operazioneSoddisfata = true;
								} else {
									System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
								}
							} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("=")) {
								
								//Ciclo che verifica se l'operazione booleana = e' verificata
								if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() == valoreRilevato) {
									operazioneSoddisfata = true;
								}  else {
									System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
								}
							}
						} else {
							//Ciclo per trovare la posizione del sensore NON NUMERICO
							for(int j = 0; j < ListaSensori.getInstance().getListSize(); j++) {
								if(ListaSensori.getInstance().getSensorFromList(j).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore())) {
									
									//Ciclo che verifica se l'operazione booleana = e' verificata
									if(ListaSensori.getInstance().getSensorFromList(j).getValoreRilevatoNonNumerico() == antecedente.getValoreConfrontato()) {
										operazioneSoddisfata = true;
									}  else {
										System.out.println("L'operazione della regola " + nomeRegola + " non è soddisfata");
									}
								}
							}
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
						for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
							
							//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
							if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
								
								System.out.println("L'operazione delle regola " + nomeRegola + " e' soddisfatta, l'attuatore " + azione.getNomeAttuatore() + " e' stato impostato a " + azione.getValore());
								//Se if si avvera' allora viene impostato il valore contenuto nell'azione
								//Non serve prenderlo dalla categoria visto che e' gia' stato preso nel metodo della creazione dell'azione
								ListaAttuatori.getInstance().getActuatorFromList(k).setModalita(azione.getValore());
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
